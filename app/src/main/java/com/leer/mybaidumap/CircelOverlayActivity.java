package com.leer.mybaidumap;


import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;

/**
 * Created by Leer on 2017/6/17.
 */

public class CircelOverlayActivity extends BaseActivity {
    @Override
    public void childInit() {

        CircleOptions circleOptions = new CircleOptions(); //圆形覆盖物
        circleOptions.center(ConstantValues.XIECUNGONGYU)  //设置圆心
                .radius(2000)            //设置半径
                .fillColor(0x443c3c3c)  //设置填充颜色
                .stroke(new Stroke(20, 0x55FF0000));  // 线条宽度、颜色
        mBaiduMap.addOverlay(circleOptions);

    }

}
