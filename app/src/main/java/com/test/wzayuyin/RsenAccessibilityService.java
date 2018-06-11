package com.test.wzayuyin;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.test.bdtts.TTS_BD;

/**
 * Created name : wjp
 * time :  2018/06/11 10:00.
 */

public class RsenAccessibilityService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        // setServiceInfo();//这个方法同样可以实现xml中的配置信息

    }

    @Override
    public boolean onUnbind(Intent intent) {
        //关闭服务时,调用
        return super.onUnbind(intent);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //当窗口发生的事件是我们配置监听的事件时,会回调此方法.会被调用多次

        if (event.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED)
            return;
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData s = cm.getPrimaryClip();
        TTS_BD.getInstance(this).speak(s.getItemAt(0).getText().toString());
        Log.i("T_LOG", event.toString());
        Log.i("T_LOG",s.getItemAt(0).getText().toString());
    }

    @Override
    public void onInterrupt() {
        //当服务要被中断时调用.会被调用多次
        TTS_BD.getInstance(this).stop();
    }
}
