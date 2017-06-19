package com.leer.mybaidumap.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.leer.mybaidumap.MyApplication;

/**
 * Created by Leer on 2017/6/17.
 */

public class ToastUtil {

    /**
     * 显示吐司到屏幕底部
     *
     * @param msg 需要显示的吐司消息内容
     */
    public static void show(String msg) {
        Toast.makeText(UIUtils.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示吐司到屏幕中央
     *
     * @param msg 需要显示的吐司消息内容
     */
    public static void showInCenter(String msg) {
        Toast toast = Toast.makeText(UIUtils.getContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
