package com.leer.mybaidumap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.TextureMapView;
import com.leer.mybaidumap.utils.ToastUtil;

public abstract class BaseActivity extends Activity {
    protected TextureMapView mMapView;
    protected BaiduMap mBaiduMap;

    private BroadcastReceiver mSDKInitReceiver;
    protected ImageView mIv_change;
    protected PopupMenu mPopupMenu;
    protected Button mBtn_search;
    protected LinearLayout mLl_search;
    protected EditText mEt_search;
    protected EditText mEt_start;
    protected EditText mEt_end;
    protected Button mBt_bus;
    protected Button mBt_drive;
    protected Button mBt_walk;
    protected LinearLayout mLl_search_traffical;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();//初始化控件
        registerSDKinit();//设置SDK验证的回调

        //更改地图的中心点是"谢村公寓" 经度115.951981,纬度:28.696742
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(ConstantValues.XIECUNGONGYU);
        mBaiduMap.setMapStatus(mapStatusUpdate);

        MapStatusUpdate mapStatusUpdate1 = MapStatusUpdateFactory.zoomTo(15);//设置缩放级别为15
        mBaiduMap.setMapStatus(mapStatusUpdate1);

        childInit();
    }

    public abstract void childInit();

    private void init() {
        mMapView = (TextureMapView) findViewById(R.id.baidu_map_view);
        mBaiduMap = mMapView.getMap();//相当于mapview的管理者

        //PoISearchInBound所要使用到的控件
        mLl_search = (LinearLayout) findViewById(R.id.ll_search);
        mBtn_search = (Button) findViewById(R.id.btn_start_search);
        mEt_search = (EditText) findViewById(R.id.et_search_info);
        mLl_search.setVisibility(View.GONE);//将POI搜索用到的控件隐藏掉


        //TrafficPoiSearchActivity 所要使用到的控件
        mLl_search_traffical = (LinearLayout) findViewById(R.id.ll_search_traffical);
        mEt_start = (EditText) findViewById(R.id.et_start);
        mEt_end = (EditText) findViewById(R.id.et_end);
        mBt_bus = (Button) findViewById(R.id.bt_bus);
        mBt_drive = (Button) findViewById(R.id.bt_drive);
        mBt_walk = (Button) findViewById(R.id.bt_walk);
        mLl_search_traffical.setVisibility(View.GONE);
    }

    private void registerSDKinit() {
        mSDKInitReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();//获取监听到的广播
                switch (action) {
                    case SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR://网络错误
                        ToastUtil.showInCenter("网络错误");
                        break;
                    case SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR://key验证失败广播
                        ToastUtil.showInCenter("key验证失败");
                        break;
                    case SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK://key验证成功广播
                        ToastUtil.showInCenter("key验证成功");
                        break;
                }
                if (action.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {

                }
            }
        };

        //sdk验证action
        IntentFilter filter = new IntentFilter();
        filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);             // 网络错误
        filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);// key验证失败
        filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);// key验证成功

        registerReceiver(mSDKInitReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        unregisterReceiver(mSDKInitReceiver);//取消注册广播
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
