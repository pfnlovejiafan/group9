package com.example.umeng_share;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_board;
    private Button btn_qq;
    private Button btn_wecaht;
    private Button btn_wecaht_q;
    private Button btn_ding;
    private Button btn_wechat_login;
    private Button btn_qq_login;
    private Button btn_sina_login;
    private Button btn_qq_zone;
    private Button btn_sina_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermisson();
    }

    private void checkPermisson() {
        PermissionsUtil.requestPermission(MainActivity.this, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permission) {
                initView();
            }

            @Override
            public void permissionDenied(@NonNull String[] permission) {

            }
        }, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE});
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        btn_board = (Button) findViewById(R.id.btn_board);
        btn_qq = (Button) findViewById(R.id.btn_qq);
        btn_wecaht = (Button) findViewById(R.id.btn_wecaht);
        btn_wecaht_q = (Button) findViewById(R.id.btn_wecaht_q);
        btn_ding = (Button) findViewById(R.id.btn_ding);
        btn_wechat_login = (Button) findViewById(R.id.btn_wechat_login);
        btn_qq_login = (Button) findViewById(R.id.btn_qq_login);
        btn_sina_login = (Button) findViewById(R.id.btn_sina_login);

        btn_board.setOnClickListener(this);
        btn_qq.setOnClickListener(this);
        btn_wecaht.setOnClickListener(this);
        btn_wecaht_q.setOnClickListener(this);
        btn_ding.setOnClickListener(this);
        btn_wechat_login.setOnClickListener(this);
        btn_qq_login.setOnClickListener(this);
        btn_sina_login.setOnClickListener(this);
        btn_qq_zone = (Button) findViewById(R.id.btn_qq_zone);
        btn_qq_zone.setOnClickListener(this);
        btn_sina_share = (Button) findViewById(R.id.btn_sina_share);
        btn_sina_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        UMWeb umWeb = new UMWeb("http://www.baodu.com");
        umWeb.setTitle("分享的子标题");
        umWeb.setThumb(new UMImage(MainActivity.this, R.drawable.ic_launcher));
        umWeb.setDescription("描述描述描述描述");
        switch (v.getId()) {
            //面板分享
            case R.id.btn_board:
                qq_board(umWeb);
                break;

            //QQ分享
            case R.id.btn_qq:
                share(umWeb, SHARE_MEDIA.QQ);
                break;

            //QQ分享
            case R.id.btn_qq_zone:
                share(umWeb, SHARE_MEDIA.QZONE);
                break;
            //微信好友分享
            case R.id.btn_wecaht:
                share(umWeb, SHARE_MEDIA.WEIXIN);
                break;

            //微信朋友圈分享
            case R.id.btn_wecaht_q:
                share(umWeb, SHARE_MEDIA.WEIXIN_CIRCLE);
                break;

            //新浪微博分享
            case R.id.btn_sina_share:
                share(umWeb, SHARE_MEDIA.SINA);
                break;

            //钉钉分享
            case R.id.btn_ding:
                share(umWeb, SHARE_MEDIA.DINGTALK);
                break;

            //微信登录
            case R.id.btn_wechat_login:
                umengDeleteOauth(SHARE_MEDIA.WEIXIN);
                shareLoginUmeng(SHARE_MEDIA.WEIXIN);
                break;
            //QQ登录
            case R.id.btn_qq_login:
                umengDeleteOauth(SHARE_MEDIA.QQ);
                shareLoginUmeng(SHARE_MEDIA.QQ);
                break;

            //新浪微博登录
            case R.id.btn_sina_login:
                umengDeleteOauth(SHARE_MEDIA.SINA);
                shareLoginUmeng(SHARE_MEDIA.SINA);
                break;

        }
    }

    //各个平台取消登录登录
    private void umengDeleteOauth(SHARE_MEDIA media) {
        UMShareAPI.get(this).deleteOauth(this, media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //开始登录
                Log.e("TAG", "开始取消登录");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                //取消登录成功 i=1
                Log.e("TAG", "取消登录成功");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                //登录出错
                Log.e("TAG", "取消登录异常" + throwable.getMessage());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                //取消登录
                Log.e("TAG", "取消登录");
            }
        });
    }

    //各个平台登录登录
    private void shareLoginUmeng(SHARE_MEDIA media) {
        UMShareAPI.get(this).getPlatformInfo(this, media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.e("TAG", "onStart登录开始: ");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                //sdk是6.4.5的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
                String uid = map.get("uid");
                String openid = map.get("openid");//微博没有
                String unionid = map.get("unionid");//微博没有
                String access_token = map.get("access_token");
                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
                String expires_in = map.get("expires_in");
                String name = map.get("name");//名称
                String gender = map.get("gender");//性别
                String iconurl = map.get("iconurl");//头像地址

                Log.e("TAG", "onComplete登录完成: " + openid);
                Log.e("TAG", "onComplete登录完成: " + unionid);
                Log.e("TAG", "onComplete登录完成: " + access_token);
                Log.e("TAG", "onComplete登录完成: " + refresh_token);
                Log.e("TAG", "onComplete登录完成: " + expires_in);
                Log.e("TAG", "onComplete登录完成: " + uid);
                Log.e("TAG", "onComplete登录完成: " + name);
                Log.e("TAG", "onComplete登录完成: " + gender);
                Log.e("TAG", "onComplete登录完成: " + iconurl);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                Log.e("TAG", "onError: " + "登录失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Toast.makeText(MainActivity.this, "登录取消", Toast.LENGTH_LONG).show();
                Log.e("TAG", "onError: " + "登录取消");
            }
        });
    }


    private void share(UMWeb umWeb, SHARE_MEDIA share_media) {
        new ShareAction(MainActivity.this)
                .withMedia(umWeb)
                .setPlatform(share_media)//传入平台
                .setCallback(shareListener)//回调监听器
                .share();
    }


    //调用SDK中面板分享
    private void qq_board(UMWeb umWeb) {
        new ShareAction(MainActivity.this).
                withText("携带的文字").
                withMedia(umWeb)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
                        SHARE_MEDIA.WXWORK,
                        SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.ALIPAY)
                .setCallback(shareListener).open();
    }


    //分享的结果回调监听器
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
            Log.e("TAG", "分享成功");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失败了" + t.getMessage(), Toast.LENGTH_LONG).show();

            Log.e("TAG", "分享失败" + t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
            Log.e("TAG", "分享取消");
        }
    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}