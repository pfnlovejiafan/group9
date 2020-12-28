package com.example.jpush_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

import static com.example.jpush_test.TagAliasOperatorHelper.ACTION_ADD;
import static com.example.jpush_test.TagAliasOperatorHelper.ACTION_CLEAN;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "cn.jiguang.demo.jpush.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;
    private EditText et_tag;
    private Button btn_set_tag;
    private Button btn_delete_tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JPushInterface.init(getApplicationContext());
        initView();
        registerMessageReceiver();
    }

    private void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public static int sequence = 1;

    @Override
    public void onClick(View v) {
        String tag = et_tag.getText().toString().trim();
        String alias = null;
        Set<String> tags = null;
        int action = -1;
        boolean isAliasAction = false;
        switch (v.getId()) {
            //添加tag
            case R.id.btn_set_tag:
                if (TextUtils.isEmpty(tag)) {
                    Toast.makeText(this, "tag不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                tags = getInPutTags();
                if (tags == null) {
                    return;
                }
                action = ACTION_ADD;
                break;
                //清除所有tag
            case R.id.btn_delete_tag:
                action = ACTION_CLEAN;
                break;
        }

        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = action;
        sequence++;
        if (isAliasAction) {
            tagAliasBean.alias = alias;
        } else {
            tagAliasBean.tags = tags;
        }
        tagAliasBean.isAliasAction = isAliasAction;
        TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(), sequence, tagAliasBean);
    }


    private Set<String> getInPutTags() {
        EditText tagEdit = (EditText) findViewById(R.id.et_tag);
        String tag = tagEdit.getText().toString().trim();
        // 检查 tag 的有效性
        if (TextUtils.isEmpty(tag)) {
            Toast.makeText(getApplicationContext(), "tag不能为空", Toast.LENGTH_SHORT).show();
            return null;
        }

        // ","隔开的多个 转换成 Set
        String[] sArray = tag.split(",");
        Set<String> tagSet = new LinkedHashSet<String>();
        for (String sTagItme : sArray) {
            if (!DeviceUtil.isValidTagAndAlias(sTagItme)) {
                Toast.makeText(getApplicationContext(), "格式不对", Toast.LENGTH_SHORT).show();
                return null;
            }
            tagSet.add(sTagItme);
        }
        if (tagSet.isEmpty()) {
            Toast.makeText(getApplicationContext(), "tag不能为空", Toast.LENGTH_SHORT).show();
            return null;
        }
        return tagSet;
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!DeviceUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    Log.e("TAG", showMsg.toString());
                }
            } catch (Exception e) {
            }
        }
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private void initView() {
        //获取appkey
        String appKey = DeviceUtil.getAppKey(getApplicationContext());
        Log.e("TAG", "appKey:" + appKey);
        //获取包名
        String packageName = getPackageName();
        Log.e("TAG", "PackageName:" + packageName);
        //获取deviceId
        String deviceId = DeviceUtil.getDeviceId(getApplicationContext());
        Log.e("TAG", "DeviceId:" + deviceId);
        et_tag = (EditText) findViewById(R.id.et_tag);
        et_tag.setOnClickListener(this);
        btn_set_tag = (Button) findViewById(R.id.btn_set_tag);
        btn_set_tag.setOnClickListener(this);
        btn_delete_tag = (Button) findViewById(R.id.btn_delete_tag);
        btn_delete_tag.setOnClickListener(this);
    }
}
