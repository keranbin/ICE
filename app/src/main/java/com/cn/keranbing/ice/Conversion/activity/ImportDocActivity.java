package com.cn.keranbing.ice.Conversion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.keranbing.ice.Conversion.bean.Home;
import com.cn.keranbing.ice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class ImportDocActivity extends AppCompatActivity {

    @BindView(R.id.tvCenter)
    TextView tvCenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivImportDocIcon)
    ImageView ivImportDocIcon;
    @BindView(R.id.tvImportDocTitle)
    TextView tvImportDocTitle;
    @BindView(R.id.llAlbum)
    LinearLayout llAlbum;
    @BindView(R.id.llTakePhoto)
    LinearLayout llTakePhoto;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.llScan)
    LinearLayout llScan;

    private final static int REQUEST_CODE_GALLERY = 0x11;
    private final static int REQUEST_CODE_CAMERA = 0x12;
    private Home recHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion_activity_import_doc);
        ButterKnife.bind(this);
        recHome = (Home) getIntent().getSerializableExtra("HOME");
        //初始化页面
        initView();
    }


    /**
     * 初始化页面
     */
    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivImportDocIcon.setBackgroundResource(recHome.getIcon());
        tvImportDocTitle.setText(recHome.getTitle() + "单据导入");
        tvCenter.setText("导入单据");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //带配置
                FunctionConfig config = new FunctionConfig.Builder()
                        .setMutiSelectMaxSize(8)
                        .build();
                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, config, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (resultList.size() != 0) {
                            ArrayList<String> uriDatas = new ArrayList<String>();
                            for (int i = 0; i < resultList.size(); i++) {
                                uriDatas.add(i, resultList.get(i).getPhotoPath());
                            }
                            Intent intent = new Intent(ImportDocActivity.this, UploadActivity.class);
                            intent.putStringArrayListExtra("uriDatas", uriDatas);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
            }
        });

        llTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
            }
        });

    }


}
