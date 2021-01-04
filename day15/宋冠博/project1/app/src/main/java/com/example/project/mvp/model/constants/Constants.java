package com.example.project.mvp.model.constants;

/**
 * 常量类 存放所有接口，常量等
 */
public class Constants {

    public static final int CACHED_THREADPOOL = 0;
    public static final int FIXED_THREADPOOL = 1;
    public static final int SCHEDULED_THREADPOOL = 2;
    public static final int SINGLE_THREADPOOL = 3;
    public static final int CUSTOM_THREADPOOL = 4;

    //正式环境
    public static final String RELEASE_BASE_URL = "https://www.chuanzhiwang.cn/api/";

    //测试环境
    public static final String DEBUG_BASE_URL = "https://www.baidu.com/";

    public static final String BAIDU_LIST="?tn=18029102_3_dg";

    //android参数
    public static final String DEVICE = "ios";
    //获取令牌号
    public static final String DECODE = "index";
    //获取选课分组


    //同袍基础地址
    public static final String TONGPAO_BASE_URL = "http://cdwan.cn:7000/tongpao/";
    //banner
    public static final String TONGPAO_HOME_BANNER = "home/banner.json";
    //热门话题
    public static final String TONGPAO_HOME_TALK = "home/topic_discussed.json";

}
