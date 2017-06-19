package com.leer.mybaidumap;

import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.leer.mybaidumap.overlayutil.DrivingRouteOverlay;
import com.leer.mybaidumap.overlayutil.TransitRouteOverlay;
import com.leer.mybaidumap.overlayutil.WalkingRouteOverlay;
import com.leer.mybaidumap.utils.ToastUtil;

/**
 * Created by Leer on 2017/6/18.
 */

public class TrafficPoiSearchActivity extends BaseActivity implements OnGetRoutePlanResultListener, View.OnClickListener {

    private RoutePlanSearch mRoutePlanSearch;

    @Override
    public void childInit() {
        mLl_search_traffical.setVisibility(View.VISIBLE);
        mRoutePlanSearch = RoutePlanSearch.newInstance();
        mRoutePlanSearch.setOnGetRoutePlanResultListener(this);
        mBt_bus.setOnClickListener(this);
        mBt_drive.setOnClickListener(this);
        mBt_walk.setOnClickListener(this);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                ToastUtil.show("marker :" + marker.getPosition());
                return true;
            }
        });
    }

    private void startSearch(String startStr, String endStr, String mode) {
        switch (mode) {
            case "drive":
                DrivingRoutePlanOption option = new DrivingRoutePlanOption();
                option.currentCity("南昌");
                //南昌市的citycode是:163
                PlanNode startNode = PlanNode.withCityCodeAndPlaceName(163, startStr);
                PlanNode endNode = PlanNode.withCityCodeAndPlaceName(163, endStr);
                option.from(startNode);
                option.to(endNode);
                mRoutePlanSearch.drivingSearch(option);
                break;
            case "bus":
                TransitRoutePlanOption option1 = new TransitRoutePlanOption();
                option1.city("南昌");
                PlanNode startNode1 = PlanNode.withCityCodeAndPlaceName(163, startStr);
                PlanNode endNode1 = PlanNode.withCityCodeAndPlaceName(163, endStr);
                option1.from(startNode1);
                option1.to(endNode1);
                mRoutePlanSearch.transitSearch(option1);
                break;
            case "walk":
                WalkingRoutePlanOption option2 = new WalkingRoutePlanOption();
                PlanNode startNode2 = PlanNode.withCityCodeAndPlaceName(163, startStr);
                PlanNode endNode2 = PlanNode.withCityCodeAndPlaceName(163, endStr);
                option2.from(startNode2);
                option2.to(endNode2);
                mRoutePlanSearch.walkingSearch(option2);
                break;
        }
    }

    //步行路线搜索结果回调
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
        WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaiduMap);
        WalkingRouteLine walkingRouteLine = walkingRouteResult.getRouteLines().get(0);
        overlay.setData(walkingRouteLine);
        mBaiduMap.setOnMarkerClickListener(overlay);
        overlay.addToMap();
        overlay.zoomToSpan();
    }

    //公交地铁路线搜索结果回调
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
        TransitRouteOverlay overlay = new TransitRouteOverlay(mBaiduMap);
        overlay.setData(transitRouteResult.getRouteLines().get(0));
        overlay.addToMap();
        overlay.zoomToSpan();//在一个屏幕中显示
    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    //驾车路线搜索回调
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
        DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(mBaiduMap);
        drivingRouteOverlay.setData(drivingRouteResult.getRouteLines().get(0));//驾车路线有多条,这里选择第一条就好
        drivingRouteOverlay.addToMap();
        drivingRouteOverlay.zoomToSpan();//在一个屏幕中显示
        mBaiduMap.setOnMarkerClickListener(drivingRouteOverlay);
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String startStr = mEt_start.getText().toString();
        String endStr = mEt_end.getText().toString();

        switch (v.getId()) {
            case R.id.bt_drive:
                startSearch(startStr, endStr, "drive");
                break;
            case R.id.bt_bus:
                startSearch(startStr, endStr, "bus");
                break;
            case R.id.bt_walk:
                startSearch(startStr, endStr, "walk");
                break;
        }
    }
}
