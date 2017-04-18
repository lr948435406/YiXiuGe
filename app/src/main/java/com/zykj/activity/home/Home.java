package com.zykj.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zaaach.citypicker.CityPickerActivity;
import com.zykj.activity.bean.MyActivity;
import com.zykj.activity.personal.PersonalCenter;
import com.zykj.yixiuge.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class Home extends MyActivity implements View.OnClickListener {
   private static final int REQUEST_CODE_PICK_CITY = 0;
    private LinearLayout home_address,home_phone,home_computer,home_appliance;
    private TextView home_tv_city;
    private ImageView home_img_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       initView();
        initEvent();
    }

    /**
     * 初始化点击事件
     */
    private void initEvent() {
        home_address.setOnClickListener(this);
        home_phone.setOnClickListener(this);
        home_computer.setOnClickListener(this);
        home_appliance.setOnClickListener(this);
        home_img_user.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        home_address= (LinearLayout) findViewById(R.id.home_address);
        home_phone= (LinearLayout) findViewById(R.id.home_phone);
        home_computer= (LinearLayout) findViewById(R.id.home_computer);
        home_appliance= (LinearLayout) findViewById(R.id.home_appliance);
        home_tv_city= (TextView) findViewById(R.id.home_tv_city);
        home_img_user= (ImageView) findViewById(R.id.home_img_user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_address:
                startActivityForResult(new Intent(this, CityPickerActivity.class),
                      REQUEST_CODE_PICK_CITY);
                break;
            case R.id.home_phone:
                startActivity(new Intent(this,HomePhone.class));
                break;
            case R.id.home_computer:
                startActivity(new Intent(this,HomeComputer.class));
                break;
            case R.id.home_appliance:
                startActivity(new Intent(this,HomeAppliance.class));
                break;
            case R.id.home_img_user:
                startActivity(new Intent(this,PersonalCenter.class));
                break;
        }
    }
    //重写onActivityResult方法
    @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
           if (data != null){
               String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
               home_tv_city.setText(city);
            }
        }
    }
}
