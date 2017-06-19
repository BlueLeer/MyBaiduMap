package com.leer.mybaidumap;

import android.graphics.Color;

import com.baidu.mapapi.map.TextOptions;

/**
 * Created by Leer on 2017/6/17.
 */

public class TextOverlayActivity extends BaseActivity {
    @Override
    public void childInit() {
        TextOptions textOptions = new TextOptions()
                .position(ConstantValues.XIECUNGONGYU)//文本覆盖物的位置
                .text("王乐的家")//文本内容
                .fontSize(20)//文本的大小
                .fontColor(Color.RED)//文本的颜色
                .bgColor(Color.GRAY);//文本的背景颜色
        mBaiduMap.addOverlay(textOptions);

    }
}
