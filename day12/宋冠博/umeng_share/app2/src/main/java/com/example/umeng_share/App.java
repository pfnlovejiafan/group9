package com.example.umeng_share;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initUmeng();
    }

    private void initUmeng() {
        //开启友盟打印
        UMConfigure.setLogEnabled(true);
        //友盟初始化配置  不管友盟统计。友盟推送。友盟分享必须调用下面这个初始化方法
        UMConfigure.init(this, "59892f08310c9307b60023d0", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "669c30a9584623e70e8cd01b0381dcb4");

        // 微信设置
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setWXFileProvider("com.example.umeng_share.fileprovider");
        // 企业微信设置
        PlatformConfig.setWXWork("wwac6ffb259ff6f66a", "EU1LRsWC5uWn6KUuYOiWUpkoH45eOA0yH-ngL8579zs", "1000002", "wwauthac6ffb259ff6f66a000002");
        PlatformConfig.setWXWorkFileProvider("com.example.umeng_share.fileprovider");
        // QQ设置
        PlatformConfig.setQQZone("101830139", "5d63ae8858f1caab67715ccd6c18d7a5");
        PlatformConfig.setQQFileProvider("com.example.umeng_share.fileprovider");
        //豆瓣RENREN平台目前只能在服务器端配置
        //其他平台设置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setAlipay("2015111700822536");
    }
}
