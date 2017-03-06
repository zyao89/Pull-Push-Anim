package com.zyao89.framework.router;

import com.zyao89.framework.zrouter.annotation.ZRouterParam;
import com.zyao89.framework.zrouter.annotation.ZRouterUri;

/**
 * Created by zyao89 on 2017/2/8.
 */
public interface IRouterUri
{
    void cccc();

    @ZRouterUri(routerUri = "xl://goods:8888/goodsDetail")//请求Url地址
    String jumpToGoodsDetail(@ZRouterParam("goodsId") String goodsId, @ZRouterParam("des") String des);//参数商品Id 商品描述
}





