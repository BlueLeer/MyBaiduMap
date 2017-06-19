package com.leer.mybaidumap;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.leer.mybaidumap.overlayutil.PoiOverlay;
import com.leer.mybaidumap.utils.ToastUtil;

/**
 * Created by Leer on 2017/6/17.
 */

public abstract class POISearchBaseActivity extends BaseActivity implements OnGetPoiSearchResultListener {
    protected PoiSearch mPoiSearch;
    private PoiOverlay mPoiOverlay;

    @Override
    public void childInit() {
        // 因为其它搜索也需要这个实例，所以放在父类初始，这样的话子类就不需要再实例化
        mPoiSearch = PoiSearch.newInstance();

        //检索结果回调
        mPoiSearch.setOnGetPoiSearchResultListener(this);

        mPoiOverlay = new PoiOverlay(mBaiduMap) {
            @Override
            public boolean onPoiClick(int index) {
                //设置点击搜索结果的标志的处理逻辑,应该交给子类去做
                return POISearchBaseActivity.this.onPoiClick(index);
            }
        };
        //PoiOverlay实现了OnMarkerClickListener接口
        // 但是,PoiOverlay没有实现PoiOverlay实现了OnMarkerClickListener接口中的接口方法onPoiClick()
        mBaiduMap.setOnMarkerClickListener(mPoiOverlay);

        //PoiSearch需要具体的搜索参数,交给子类去处理
        poiSearchInit();
    }

    protected abstract void poiSearchInit();

    //点击每一个兴趣点结果的地图标志,弹出一条吐司
    protected boolean onPoiClick(int index) {
        PoiInfo poiInfo = mPoiOverlay.getPoiResult().getAllPoi().get(index);
        ToastUtil.show(poiInfo.name + " , " + poiInfo.phoneNum);

        return true;
    }

    //poi查询结果回调
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        mPoiOverlay.setData(poiResult);
        mPoiOverlay.addToMap();
        mPoiOverlay.zoomToSpan();//只在一页显示
    }

    //poi 详情查询结果回调,子类可以覆盖
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult){}


    //Poi 室内检索回调,子类开一覆盖
    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {}

}
