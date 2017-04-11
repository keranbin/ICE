package com.cn.keranbing.ice.Conversion.service;

import java.util.HashMap;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by keranbin on 2017/4/5.
 */

public interface TransService {
    @POST("ajax.mobileSword")
    Observable<Object> transImage(@QueryMap HashMap<String, String> paramsMap);
}
