package com.cn.keranbing.ice;

import android.app.Application;
import android.graphics.Color;


import com.cn.keranbing.ice.Conversion.utils.GlideImageLoader;
import com.cn.keranbing.ice.Conversion.utils.NobodyConverterFactory;

import java.util.concurrent.TimeUnit;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by keranbin on 2017/3/31.
 */

public class App extends Application {
    public static App app;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initGallerFinal();
        //初始化OkHttp
        OkHttpInstance();
        //初始化Retrofit
        RetrofitInstance();
    }

    //配置GallerFinal
    private void initGallerFinal() {
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0, 111, 59))
                .setTitleBarTextColor(Color.WHITE)
                .setTitleBarIconColor(Color.WHITE)
                .setFabNornalColor(Color.rgb(0, 111, 59))
                .setFabPressedColor(Color.BLUE)
                .setCheckNornalColor(Color.WHITE)
                .setCheckSelectedColor(Color.BLACK)
                //...其他配置
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        ImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        //配置Theme
        GalleryFinal.init(coreConfig);
    }

    /**
     * 初始化okHttp
     *
     * @return
     */
    public static OkHttpClient OkHttpInstance() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient().newBuilder()
                    //设置连接时间
                    .connectTimeout(500000, TimeUnit.MILLISECONDS)
                    //设置读时间
                    .readTimeout(100000, TimeUnit.MILLISECONDS)
                    //设置写时间
                    .writeTimeout(100000, TimeUnit.MILLISECONDS)
                    //设置缓存
                    .cache(new Cache(app.getExternalCacheDir(), 10 * 1024 * 1024))
                    .build();
            return okHttpClient;
        } else {
            return okHttpClient;
        }
    }

    /**
     * 初始化retrofit
     */
    public static Retrofit RetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.11.5.214:8081/filetrans/")
                    //添加Gson支持
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加Retrofit对Rxjava的支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //配置OkHttp
                    .client(okHttpClient).build();
            return retrofit;
        } else {
            return retrofit;
        }
    }


}
