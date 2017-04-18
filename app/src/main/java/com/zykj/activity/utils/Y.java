package com.zykj.activity.utils;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.StyledDialog;
import com.orhanobut.logger.Logger;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * 工具类
 * Created by zykj on 2017/4/8.
 */

public  class Y {
    public static Context context; //全局上下文

    public  static  boolean isLog=true; //控制日志打印的开关







    /**
     * 吐司功能只需要传入一个 字符串
     * @param str
     */
    public static  void t(String  str){
        Toast.makeText(context,str, Toast.LENGTH_LONG).show();
    }

    /**
     * 输出log日志
     * @param str
     */
    public static  void i(String  str){
        if(isLog)
         Logger.i(str);
    }

    /**
     * 检测请求返回的数据是否正确
     */
    public static boolean getRespCode(String  result){
        if("0".equals(JSON.parseObject(result).getString("resp_code"))){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 如果成功获取数据
     */
    public static String getData(String  result){
        return JSON.parseObject(result).getString("data");
    }





    /**
     * get请求  返回成功回调
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable get(String url, Map<String,String> params, MyCommonCall<String> call){

        if(params==null)
            i(url);

        //请求的对象
        RequestParams rp  =new RequestParams(url);

        //检测外部是否传入了参数
        if(params!=null){
            //把参数取出来这是到rp
            i(rp.toString());
            for (Map.Entry<String,String> entry :params.entrySet()) {
                rp.addBodyParameter(entry.getKey(),entry.getValue());
            }
        }
        // 只要发起Get请求就开启对话框
        StyledDialog.buildLoading().show();
        return   x.http().get(rp, call);
    }
    /**
     * post请求  返回成功回调
     * @param params
     * @param call
     * @return
     */
    public static Callback.Cancelable post(RequestParams params, MyCommonCall<String> call){
        return   x.http().post(params, call);
    }
    /**
       实现不需要外部完成的两个函数
     */
    public abstract  static class  MyCommonCall<String> implements Callback.CommonCallback<String>{
        @Override
        public void onFinished() {}

        @Override
        public void onCancelled(CancelledException cex) {}

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            StyledDialog.dismissLoading(); // 关闭
            t("服务器异常");
            ex.printStackTrace();
        }
    }








}
