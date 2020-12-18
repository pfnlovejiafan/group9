package com.example.skp7day03zuoye.model.bean;

import java.util.List;

public class TuiJianBean {
    /**
     * status : 200
     * message : 成功
     * data : {"data":[{"id":61,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/icon_etpack.png","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/1111.png","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"2019高光时刻","intro":"2019年那些事,有哪些最让你怀念呢？","feedNum":100,"tagId":1,"enterNum":1000,"followNum":100,"hasFollow":false},{"id":62,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/21434249C0E2090713793722702E4487.jpg","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/2222.png","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"2020新年快乐","intro":"2020年展望未来，许下美好的愿望","feedNum":101,"tagId":2,"enterNum":1001,"followNum":101,"hasFollow":false},{"id":63,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/%E5%A4%9A%E5%BD%A9%E5%85%AC%E9%B8%A1.jpg","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/%E5%A4%9A%E5%BD%A9%E5%85%AC%E9%B8%A1.jpg","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"多彩生活","intro":"多彩生活，由于这只多彩公鸡","feedNum":100,"tagId":3,"enterNum":1000,"followNum":100,"hasFollow":false},{"id":64,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/%E8%80%83%E6%8B%89.jpg","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/%E8%80%83%E6%8B%89.jpg","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"Aukoala","intro":"考拉 作为澳大利亚本土品牌，澳考拉雪地靴由麦克森（Bill.Decksen）于上世纪70年代初创立Decksen公司所有，是澳大利亚人最喜欢的雪地靴品牌之一。","feedNum":100,"tagId":4,"enterNum":1000,"followNum":100,"hasFollow":false},{"id":65,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/relaxing-1979674_640.jpg","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/relaxing-1979674_640.jpg","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"放松时刻","intro":"放松时刻,放松下自己","feedNum":100,"tagId":5,"enterNum":1000,"followNum":100,"hasFollow":false},{"id":66,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/music-3507317_640.jpg","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/music-3507317_640.jpg","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"音乐时刻","intro":"好听的音乐，给到你我","feedNum":100,"tagId":6,"enterNum":1000,"followNum":100,"hasFollow":false},{"id":67,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/lol.jpg","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/lol.jpg","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"王者峡谷","intro":"高能时刻，峡谷精彩瞬间","feedNum":100,"tagId":7,"enterNum":1000,"followNum":100,"hasFollow":false},{"id":68,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/geometry-1044090_640.jpg","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/geometry-1044090_640.jpg","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"百年教育","intro":"百年大计，教育大业","feedNum":100,"tagId":8,"enterNum":1000,"followNum":100,"hasFollow":false},{"id":69,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/food-984441_640.jpg","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/food-984441_640.jpg","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"舌尖上的美食","intro":"舌尖上的美食，纵览让人垂涎三尺的美食","feedNum":100,"tagId":9,"enterNum":1000,"followNum":100,"hasFollow":false},{"id":70,"icon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/emotion-2167582_640.jpg","background":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/emotion-2167582_640.jpg","activityIcon":"https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png","title":"国宝熊猫","intro":"看起来憨厚，据说当年蚩尤骑着这货打天下","feedNum":100,"tagId":10,"enterNum":1000,"followNum":100,"hasFollow":false}]}
     */

    private int status;
    private String message;
    private DataBeanX data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 61
             * icon : https://pipijoke.oss-cn-hangzhou.aliyuncs.com/icon_etpack.png
             * background : https://pipijoke.oss-cn-hangzhou.aliyuncs.com/1111.png
             * activityIcon : https://pipijoke.oss-cn-hangzhou.aliyuncs.com/51f076f662ef40c99f056a41b130c516.png
             * title : 2019高光时刻
             * intro : 2019年那些事,有哪些最让你怀念呢？
             * feedNum : 100
             * tagId : 1
             * enterNum : 1000
             * followNum : 100
             * hasFollow : false
             */

            private int id;
            private String icon;
            private String background;
            private String activityIcon;
            private String title;
            private String intro;
            private int feedNum;
            private int tagId;
            private int enterNum;
            private int followNum;
            private boolean hasFollow;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public String getActivityIcon() {
                return activityIcon;
            }

            public void setActivityIcon(String activityIcon) {
                this.activityIcon = activityIcon;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public int getFeedNum() {
                return feedNum;
            }

            public void setFeedNum(int feedNum) {
                this.feedNum = feedNum;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public int getEnterNum() {
                return enterNum;
            }

            public void setEnterNum(int enterNum) {
                this.enterNum = enterNum;
            }

            public int getFollowNum() {
                return followNum;
            }

            public void setFollowNum(int followNum) {
                this.followNum = followNum;
            }

            public boolean isHasFollow() {
                return hasFollow;
            }

            public void setHasFollow(boolean hasFollow) {
                this.hasFollow = hasFollow;
            }
        }
    }
}
