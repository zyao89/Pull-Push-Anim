package com.zyao89.framework.zrouter;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;

import com.zyao89.framework.zrouter.annotation.ZRouterParam;
import com.zyao89.framework.zrouter.annotation.ZRouterUri;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 路由器
 * Created by zyao89 on 2017/2/8.
 */
public final class ZRouter
{
    private final static String TAG      = ZRouter.class.getSimpleName();
    private static final String ACTION_VIEW = "zrouter.action.VIEW";
    private static ZRouter mInstance;
    private Context mContext;

    private final Map<Method, ServiceMethod<?, ?>> serviceMethodCache = new ConcurrentHashMap<>();

    /**
     * 获取单例引用
     *
     * @return 单例
     */
    public static ZRouter getInstance()
    {
        if (mInstance == null)
        {
            synchronized (ZRouter.class)
            {
                if (mInstance == null)
                {
                    mInstance = new ZRouter();
                }
            }
        }
        return mInstance;
    }

    /**
     * 构造函数
     */
    private ZRouter()
    {
    }

    /**
     * 初始化
     * @param context application
     */
    public void init(Context context)
    {
        this.mContext = context.getApplicationContext();
    }

    @SuppressWarnings("unchecked") // Single-interface proxy creation guarded by parameter safety.
    public <API> API create(final Class<API> service)
    {
        Utils.validateServiceInterface(service);
//        if (validateEagerly)
//        {
//            eagerlyValidateMethods(service);
//        }
        return (API) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new ProxyInvocationHandler());
    }

    private class ProxyInvocationHandler implements InvocationHandler
    {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
            // If the method is a method from Object then defer to normal invocation.
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }
            StringBuilder stringBuilder = new StringBuilder();
            ZRouterUri reqUrl = method.getAnnotation(ZRouterUri.class);
            Log.e(TAG, "IReqApi---reqUrl->" + reqUrl.routerUri());
            stringBuilder.append(reqUrl.routerUri());
            //Type[] parameterTypes = method.getGenericParameterTypes();//获取注解参数类型
            Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();//拿到参数注解
            //Annotation[] annotation = method.getDeclaredAnnotations();
            int pos = 0;
            for (int i = 0; i < parameterAnnotationsArray.length; i++)
            {
                Annotation[] annotations = parameterAnnotationsArray[i];
                if (annotations != null && annotations.length != 0)
                {
                    if (pos == 0)
                    {
                        stringBuilder.append("?");
                    }
                    else
                    {
                        stringBuilder.append("&");
                    }
                    pos++;
                    ZRouterParam reqParam = (ZRouterParam) annotations[0];
                    stringBuilder.append(reqParam.value());
                    stringBuilder.append("=");
                    stringBuilder.append(args[i]);
                    Log.e(TAG, "reqParam---reqParam->" + reqParam.value() + "=" + args[i]);
                }
            }
            //下面就可以执行相应的跳转操作
            openRouterUri(stringBuilder.toString());
//            loadServiceMethod(method);
            return null;
        }
    }

    private void eagerlyValidateMethods(Class<?> service)
    {
        for (Method method : service.getDeclaredMethods())
        {
            loadServiceMethod(method);
        }
    }

    ServiceMethod<?, ?> loadServiceMethod(Method method)
    {
        ServiceMethod<?, ?> result = serviceMethodCache.get(method);
        if (result != null) { return result; }

        synchronized (serviceMethodCache)
        {
            result = serviceMethodCache.get(method);
            if (result == null)
            {
                result = new ServiceMethod();
//                result = new ServiceMethod.Builder<>(this, method).build();
                serviceMethodCache.put(method, result);
            }
        }
        return result;
    }

    /**
     * 通过uri跳转指定页面
     *
     * @param url
     */
    private void openRouterUri(String url)
    {
        PackageManager packageManager = mContext.getPackageManager();
        Intent intent = new Intent(ACTION_VIEW, Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        if (isValid)
        {
            mContext.startActivity(intent);
        }
    }
}
