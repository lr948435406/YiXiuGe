package com.zykj.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.hss01248.dialog.StyledDialog;
import com.zykj.activity.bean.ComputerBean;
import com.zykj.activity.bean.MobileBean;
import com.zykj.activity.bean.MyActivity;
import com.zykj.activity.utils.Y;
import com.zykj.activity.utils.YURL;
import com.zykj.widget.TopBar;
import com.zykj.yixiuge.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class HomeComputer extends MyActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_computer);

    }

}
