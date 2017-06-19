package com.leer.mybaidumap;

import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.leer.mybaidumap.utils.ToastUtil;

/**
 * Created by Leer on 2017/6/17.
 */

public class MarkerOverlayActivity extends BaseActivity {


    private View mPopView;
    private TextView mTv_title;

    @Override
    public void childInit() {
        initMaker();
        mBaiduMap.setOnMarkerClickListener(mMarkerClickListener);
        //设置maker拖拽监听
        mBaiduMap.setOnMarkerDragListener(mOnMarkerDragListener);
    }

    private void initMaker() {
        LatLng latLng = ConstantValues.XIECUNGONGYU;
        LatLng makerll1 = new LatLng(latLng.latitude + 0.01, latLng.longitude + 0.01);
        LatLng makerll2 = new LatLng(latLng.latitude + 0.02, latLng.longitude + 0.05);
        LatLng makerll3 = new LatLng(latLng.latitude + 0.04, latLng.longitude + 0.01);
        LatLng makerll4 = new LatLng(latLng.latitude + 0.03, latLng.longitude + 0.012);

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                .position(makerll1)//设置maker的位置
                .title("marker_1");
        mBaiduMap.addOverlay(markerOptions);

        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                .position(makerll2)//设置maker的位置
                .title("marker_2");
        mBaiduMap.addOverlay(markerOptions);

        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                .position(makerll3)//设置maker的位置
                .title("marker_3");
        mBaiduMap.addOverlay(markerOptions);

        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon))
                .position(makerll4)//设置maker的位置
                .title("marker_4");
        mBaiduMap.addOverlay(markerOptions);



    }

    //地图上的标志的点击的监听器
    private BaiduMap.OnMarkerClickListener mMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            //点击的时候显示一个泡泡
            if (mPopView == null) {
                mPopView = View.inflate(MarkerOverlayActivity.this, R.layout.view_pop, null);
                mTv_title = (TextView) mPopView.findViewById(R.id.tv_title);
                mMapView.addView(mPopView, createParms(marker.getPosition()));//根据点击的maker的position的坐标,添加一个popView
            } else {
                mMapView.updateViewLayout(mPopView,createParms(marker.getPosition()));
            }
            mTv_title.setText(marker.getTitle());
            return true;
        }
    };

    private BaiduMap.OnMarkerDragListener mOnMarkerDragListener = new BaiduMap.OnMarkerDragListener() {
        //maker拖拽开始
        @Override
        public void onMarkerDragStart(Marker marker) {
            mMapView.updateViewLayout(mPopView,createParms(marker.getPosition()));
        }

        //marker正在拖拽中
        @Override
        public void onMarkerDrag(Marker marker) {
            mMapView.updateViewLayout(mPopView,createParms(marker.getPosition()));
        }

        //maker拖拽结束
        @Override
        public void onMarkerDragEnd(Marker marker) {
            mMapView.updateViewLayout(mPopView,createParms(marker.getPosition()));
        }

    };


    //BaiduMap有封装好了的LayoutParams,可以直接用来使用
    private MapViewLayoutParams createParms(LatLng position) {
        MapViewLayoutParams.Builder builder = new MapViewLayoutParams.Builder();
        builder.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode); // 指定坐标类型为经纬度
        builder.position(position);//设置位置
        builder.yOffset(-25);//y的偏移,为了不遮挡maker

        return builder.build();
    }
}
