package com.cn.keranbing.ice.TimeLine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.keranbing.ice.Gif.activity.MainActivity;
import com.cn.keranbing.ice.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by keranbin on 2017/4/14.
 */

public class RecordActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.tvCenter)
    TextView tvCenter;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_timepicker)
    LinearLayout llTimepicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline_activity_record);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ll_timepicker)
    public void onClick() {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(RecordActivity.this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE),true);
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

    }
}
