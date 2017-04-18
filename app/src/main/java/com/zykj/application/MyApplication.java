package com.zykj.application;

import android.app.Application;

import com.hss01248.dialog.StyledDialog;
import com.zykj.activity.utils.Y;

import org.xutils.x;


/**
 * Created by zykj on 2017/4/18.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化xutils
        x.Ext.init(this);
        // 初始化上下文
        Y.context=this;
        // 初始化StyledDialog
        StyledDialog.init(this);
    }
}
