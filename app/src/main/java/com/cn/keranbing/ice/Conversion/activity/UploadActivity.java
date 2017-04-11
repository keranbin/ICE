package com.cn.keranbing.ice.Conversion.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cn.keranbing.ice.App;
import com.cn.keranbing.ice.Conversion.adapter.UploadAdapter;
import com.cn.keranbing.ice.Conversion.service.TransService;
import com.cn.keranbing.ice.Conversion.service.UploadService;
import com.cn.keranbing.ice.Conversion.utils.DividerGridItemDecoration;
import com.cn.keranbing.ice.Conversion.views.RecDialog;
import com.cn.keranbing.ice.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UploadActivity extends AppCompatActivity {
    private String TAG = "UploadActivity";

    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.tvCenter)
    TextView tvCenter;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvFileSize)
    TextView tvFileSize;
    @BindView(R.id.ryUpload)
    RecyclerView ryUpload;
    @BindView(R.id.btnUpload)
    AppCompatButton btnUpload;
    @BindView(R.id.btnRec)
    AppCompatButton btnRec;
    private ArrayList<String> intentDatas = new ArrayList<String>();
    private UploadAdapter adapter;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_activity_upload);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText("取消");
        tvCenter.setText("传照片");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("上传");

        intentDatas = getIntent().getStringArrayListExtra("uriDatas");

        //实例化Adapter
        adapter = new UploadAdapter(UploadActivity.this, intentDatas);


        // 如果我们想要一个GridView形式的RecyclerView，那么在LayoutManager上我们就要使用GridLayoutManager
        // 实例化一个GridLayoutManager，列数为3
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        //把LayoutManager设置给RecyclerView
        ryUpload.setLayoutManager(layoutManager);
        ryUpload.addItemDecoration(new DividerGridItemDecoration(this));

        ryUpload.setAdapter(adapter);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RecDialog dialog = new RecDialog(UploadActivity.this,R.style.CommonDialog,"上传中");
                dialog.show();
                //把参数封装到map中
                HashMap map = new HashMap<String, String>();
                map.put("tid", "MultiFileCBS_uploadFile");
                System.out.print("dfdf");

                List<MultipartBody.Part> bodys=filesToMultipartBodyParts(intentDatas);

                UploadService uploadService = App.RetrofitInstance().create(UploadService.class);
                uploadService.uploadImage(map,bodys)
                        //设置call()执行的线程为新起一个线程
                        .subscribeOn(Schedulers.newThread())
                        //设置onNext()执行的线程在主线程中
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG, "fdfd");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG, e.getMessage());
                            }

                            @Override
                            public void onNext(Object object) {
                                dialog.dismiss();
                                try {
                                    if(object!=null&&object.toString()!=""){
                                        jsonObject=new JSONObject(object.toString());
                                        btnUpload.setBackgroundResource(R.drawable.grey_click_selector);
                                        btnUpload.setClickable(false);
                                        btnRec.setBackgroundResource(R.drawable.orange_click_selector);
                                        btnRec.setClickable(true);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });

        btnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transImage();
            }
        });


    }

    private void transImage() {
        final RecDialog dialog = new RecDialog(this,R.style.CommonDialog,"识别中");
        dialog.show();

        //把参数封装到map中
        HashMap map = new HashMap<String, String>();
        try {
            map.put("tid", "MultiFileCBS_transFile");
            map.put("filename", jsonObject.get("id").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //使用类Retrofit生成接口IPost的实现
        TransService transService = App.RetrofitInstance().create(TransService.class);

        transService.transImage(map)
                //设置call()执行的线程为新起一个线程
                .subscribeOn(Schedulers.newThread())
                //设置onNext()执行的线程在主线程中
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(Object o) {
                        dialog.dismiss();
                        Intent intent=new Intent(UploadActivity.this,RecResultActivity.class);
//                            JSONObject json=new JSONObject(o.toString());
                        intent.putExtra("content", o.toString());
                        intent.putExtra("file",intentDatas.get(0));
                        startActivity(intent);
                    }

                });
    }

    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<String> strFiles) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < strFiles.size(); i++) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            File file = new File(strFiles.get(i));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

}
