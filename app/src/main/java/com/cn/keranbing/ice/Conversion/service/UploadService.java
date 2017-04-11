package com.cn.keranbing.ice.Conversion.service;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by keranbin on 2017/3/31.
 */

public interface UploadService {
    /**
     * 通过 List<MultipartBody.Part> 传入多个part实现多文件上传
     * @param parts 每个part代表一个
     * @return 状态信息
     */
    @Multipart
    @POST("ajax.mobileSword")
    Observable<Object> uploadImage(@QueryMap Map<String, String> maps,@Part() List<MultipartBody.Part> parts);
}
