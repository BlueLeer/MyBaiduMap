package com.leer.mybaidumap;

import android.support.annotation.NonNull;
import android.view.View;

import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;

/**
 * Created by Leer on 2017/6/18.
 */

public class POISearchInBound extends POISearchBaseActivity {
    @Override
    protected void poiSearchInit() {

        mLl_search.setVisibility(View.VISIBLE);
        mBtn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mEt_search.getText().toString();
                PoiBoundSearchOption option = getPoiBoundSearchOption(s);
                mPoiSearch.searchInBound(option);
            }
        });
    }

    @NonNull
    private PoiBoundSearchOption getPoiBoundSearchOption(String keyword) {
        PoiBoundSearchOption option = new PoiBoundSearchOption();
        LatLngBounds.Builder builder = new LatLngBounds.Builder()
                .include(ConstantValues.XIECUNGONGYU).include(ConstantValues.BAYISQUARE)
                .include(ConstantValues.QIUSHUISQUARE).include(ConstantValues.WANDASQUARE);//搜索的范围

        option.keyword(keyword);
        option.bound(builder.build());
        return option;
    }
}
