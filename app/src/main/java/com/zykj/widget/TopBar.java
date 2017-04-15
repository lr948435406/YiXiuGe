package com.zykj.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zykj.yixiuge.R;

/**
 * Created by zykj on 2017/4/11.
 */

public class TopBar extends RelativeLayout{
    //定义属性
    //标题
    private  String titleText;
    private  float  titleTextSize;
    private  int    titleTextColor;
    //左侧按钮
    private Drawable leftBackground;

    //右侧按钮
    private  String rightText;
    private  float  rightTextSize;
    private  int    rightTextColor;
    private Drawable rightBackground;
    //控件
    private TextView title;
    private ImageView left;
    private TextView right;
    private Context context;
    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray td = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        // 获取标题的属性
        titleText=td.getString(R.styleable.TopBar_titleText);
        titleTextSize=td.getDimension(R.styleable.TopBar_titleTextSize,0);
        titleTextColor=td.getColor(R.styleable.TopBar_titleTextColor,0);
        //获取左侧按钮的属性
        leftBackground=td.getDrawable(R.styleable.TopBar_leftBackground);
        //获取右侧按钮的属性
        rightText=td.getString(R.styleable.TopBar_rightText);
        rightTextSize=td.getDimension(R.styleable.TopBar_rightTextSize,0);
        rightTextColor=td.getColor(R.styleable.TopBar_rightTextColor,0);
        rightBackground=td.getDrawable(R.styleable.TopBar_rightBackground);
        //  创建控件
        title=new TextView(context);
        left=new ImageView(context);
        right=new TextView(context);

        // 把所有属性设置到控件上
         // 设置title 属性
        title.setText(titleText);
        title.setTextSize(titleTextSize);
        title.setTextColor(titleTextColor);
        title.setGravity(Gravity.CENTER);
        //  设置 left左侧属性

        left.setBackgroundDrawable(leftBackground);
        //  设置 right右侧属性
        right.setText(rightText);
        right.setTextColor(rightTextColor);
        right.setTextSize(rightTextSize);
        right.setGravity(Gravity.RIGHT);
        right.setBackgroundDrawable(rightBackground);

        //把控件设置到View中

        //控制title 的位置
        LayoutParams  titleParams =new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT); //中间位置
        addView(title,titleParams);

        LayoutParams  leftParams =new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT|RelativeLayout.CENTER_VERTICAL); //左侧位置
        leftParams.width=30;
        leftParams.height=40;
        addView(left,leftParams);

        LayoutParams  rightParams =new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT); //右侧位置
        rightParams.topMargin=35;
        addView(right,rightParams);
        // 调用点击函数
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.leftClick();
            }
        });

    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private TopBarClickListener clickListener; //成员变量用于映射外部传入的对象

    //对外提供的函数
    public void setTopBarOnClickListener(TopBarClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //添加回调接口
    public  interface  TopBarClickListener{
        void leftClick(); //左侧点击
    }
}
