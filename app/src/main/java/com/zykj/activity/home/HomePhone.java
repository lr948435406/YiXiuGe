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
import com.zykj.activity.bean.MobileBean;
import com.zykj.activity.bean.MyActivity;
import com.zykj.activity.utils.Y;
import com.zykj.activity.utils.YURL;
import com.zykj.widget.TopBar;
import com.zykj.yixiuge.R;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/18.
 */

public class HomePhone extends MyActivity {
    List<MobileBean> lists; //品牌的数据源
    int mobileIndex = -1;  //用于检测是否选择了品牌
    @Bind(R.id.phone_tv_pinpai)
    TextView phoneTvPinpai;
    @Bind(R.id.phone_pinpai)
    LinearLayout phonePinpai;
    @Bind(R.id.phone_tv_xinghao)
    TextView phoneTvXinghao;
    @Bind(R.id.phone_xinghao)
    LinearLayout phoneXinghao;
    @Bind(R.id.phone_tv_guzhang)
    TextView phoneTvGuzhang;
    @Bind(R.id.phone_guzhang)
    LinearLayout phoneGuzhang;
    @Bind(R.id.phone_et_miaoshu)
    EditText phoneEtMiaoshu;
    @Bind(R.id.phone_zhaopian)
    FrameLayout phoneZhaopian;
    @Bind(R.id.phone_bt_queren)
    Button phoneBtQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_phone);
        ButterKnife.bind(this);
        TopBar topBar = new TopBar(this);
        topBar.setTopBarOnClickListener(new TopBar.TopBarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }
        });
    }

    @OnClick({R.id.phone_pinpai, R.id.phone_xinghao, R.id.phone_guzhang, R.id.phone_zhaopian, R.id.phone_bt_queren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone_pinpai:
                //发起请求
                Y.get(YURL.FIND_PHONE_BRAND, null,new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                       StyledDialog.dismissLoading(); // 关闭
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(HomePhone.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    phoneTvPinpai.setText(lists.get(options1).getName());
                                    phoneTvPinpai.setTextColor(Color.parseColor("#00cccc"));
                                    mobileIndex = options1; // 当前选择的索引
                                }
                            }).build();

                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MobileBean mb : lists) {
                                strs.add(mb.getName());
                            }

                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            opv.show();


                        } else {
                            //失败
                            Y.t("数据解析失败");

                        }
                    }
                });


                break;
            case R.id.phone_xinghao:
                //检测是否选择了品牌
                if (mobileIndex == -1) {
                    Y.t("请您先选择品牌");
                } else {
                    //开始获取型号数据
                    //发起请求
                    Map map = new HashMap();
                    map.put("pid", lists.get(mobileIndex).getId() + "");
                    Y.get(YURL.FIND_PHONE_MODEL,map, new Y.MyCommonCall<String>() {
                        @Override
                        public void onSuccess(String result) {
                            StyledDialog.dismissLoading(); // 关闭
                            if (Y.getRespCode(result)) {
                                //成功
                                lists = JSON.parseArray(Y.getData(result), MobileBean.class);

                                //创建选择器
                                OptionsPickerView opv = new OptionsPickerView.Builder(HomePhone.this, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        //选择后的监听器
                                        phoneTvXinghao.setText(lists.get(options1).getName());

                                    }
                                }).build();

                                //把lists 进行转换
                                List<String> strs = new ArrayList<String>();
                                for (MobileBean mb : lists) {
                                    strs.add(mb.getName());
                                }

                                //添加数据
                                opv.setPicker(strs, null, null);
                                //显示选择器
                                opv.show();


                            } else {
                                //失败
                                Y.t("数据解析失败");

                            }
                        }
                    });


                }

                break;
            case R.id.phone_guzhang:
                //发起请求
                Y.get(YURL.FIND_PHONE_FAULT,null, new Y.MyCommonCall<String>() {
                    @Override
                    public void onSuccess(String result) {
                        StyledDialog.dismissLoading(); // 关闭
                        if (Y.getRespCode(result)) {
                            //成功
                            lists = JSON.parseArray(Y.getData(result), MobileBean.class);

                            //创建选择器
                            OptionsPickerView opv = new OptionsPickerView.Builder(HomePhone.this, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    //选择后的监听器
                                    phoneTvGuzhang.setText(lists.get(options1).getName());

                                }
                            }).build();

                            //把lists 进行转换
                            List<String> strs = new ArrayList<String>();
                            for (MobileBean mb : lists) {
                                strs.add(mb.getName());
                            }

                            //添加数据
                            opv.setPicker(strs, null, null);
                            //显示选择器
                            opv.show();
                        } else {
                            //失败
                            Y.t("数据解析失败");

                        }
                    }
                });
                break;
            case R.id.phone_zhaopian:
                break;
            case R.id.phone_bt_queren:
                break;
        }
    }
}
