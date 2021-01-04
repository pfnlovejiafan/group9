package com.example.project.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarUtil {
    //设置状态栏颜色  Android4.4之前没有沉浸式状态栏这个概念，但是可以设置状态栏透明
    public static void setStatusBarColor(Activity activity, int colorRes) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//大于等于5.0的系统

            //添加状态栏的背景色 这么一个标记
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(activity.getResources().getColor(colorRes));
            //状态栏中文字的颜色变为黑色
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                    View.SYSTEM_UI_FLAG_FULLSCREEN);


        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//大于等于4.4 并且 小于5.0的系统

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

    }

    //设置全屏(隐藏状态栏和Toolbar) == theme里面设置的
    public static void setFullScreen(Activity activity) {
        Window window = activity.getWindow();

        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    //设置沉浸式状态栏(状态栏透明，内容填充到状态栏下方)
    public static void setStatusBarTrans(Activity activity) {
        Window window = activity.getWindow();

        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        //设置透明
        window.setStatusBarColor(Color.TRANSPARENT);
    }


    //隐藏状态栏及底部虚拟按键，点击屏幕显示
    //隐藏状态栏及底部虚拟按键，屏幕顶部下划显示
    public static void hideShowStatusBar(Activity activity) {
        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        //阻止内容超出状态栏
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


    }

}
