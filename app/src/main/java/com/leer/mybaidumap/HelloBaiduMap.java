package com.leer.mybaidumap;

import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.baidu.mapapi.map.BaiduMap;

/**
 * Created by Leer on 2017/6/17.
 */

public class HelloBaiduMap extends BaseActivity {
    @Override
    public void childInit() {
        mIv_change = (ImageView) findViewById(R.id.iv_change);

        mIv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupMenu = new PopupMenu(HelloBaiduMap.this,v);
                mPopupMenu.getMenuInflater().inflate(R.menu.popmenu, mPopupMenu.getMenu());
                mPopupMenu.show();

                mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.nomal:
                                //普通地图
                                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                                break;
                            case R.id.satellite:
                                //卫星地图
                                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                                break;
                            case R.id.blank:
                                //空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，
                                // 将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
                                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
                                break;
                            case R.id.triffical_on:
                                //开启交通图
                                mBaiduMap.setTrafficEnabled(true);
                                break;
                            case R.id.triffical_off:
                                //关闭交通图
                                mBaiduMap.setTrafficEnabled(false);
                                break;
                            case R.id.heatmap_on:
                                //开启热力图
                                mBaiduMap.setBaiduHeatMapEnabled(true);
                                break;
                            case R.id.heatmap_off:
                                //关闭热力图
                                mBaiduMap.setBaiduHeatMapEnabled(false);
                                break;
                        }
                        return true;
                    }
                });
            }
        });
    }
}
