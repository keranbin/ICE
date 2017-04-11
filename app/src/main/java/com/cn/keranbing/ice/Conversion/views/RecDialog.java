package com.cn.keranbing.ice.Conversion.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cn.keranbing.ice.R;

import butterknife.BindView;

/**
 * Created by keranbin on 2017/4/7.
 */

public class RecDialog extends ProgressDialog {
    private TextView tvPrompt;

    public RecDialog(Context context) {
        super(context);
    }

    public RecDialog(Context context, int theme,String strPrompt) {
        super(context, theme);
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.common_dialog, null);

        tvPrompt= (TextView) view.findViewById(R.id.tv_load_dialog);
        tvPrompt.setText(strPrompt);
        setView(view);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }


    @Override
    public void show() {
        super.show();
    }

}
