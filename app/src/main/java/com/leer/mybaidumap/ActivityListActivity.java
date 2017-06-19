package com.leer.mybaidumap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by Leer on 2017/6/17.
 */

public class ActivityListActivity extends ListActivity {

    private ClassAndName[] mClassAndNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();


    }

    private class MyAdapter extends ArrayAdapter {

        public MyAdapter(Context context, int resource, Object[] objects) {
            super(context, resource, objects);
        }
    }

    private void initData() {
        mClassAndNames = new ClassAndName[]{new ClassAndName(HelloBaiduMap.class, "Hello:Baidu Map")
                , new ClassAndName(CircelOverlayActivity.class, "圆形覆盖物"),
                new ClassAndName(TextOverlayActivity.class, "文本覆盖物"),
                new ClassAndName(MarkerOverlayActivity.class, "标志覆盖物"),
                new ClassAndName(POISearchInBound.class, "搜索兴趣点"),
                new ClassAndName(TrafficPoiSearchActivity.class, "交通搜索"),
        new ClassAndName(LocationActivity.class,"定位")};

        MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, mClassAndNames);
        setListAdapter(adapter);//给listActivity设置Adapter,实际上是给ListActivity当中的ListView设置Adapter

    }

    //当ListView的item被点击的时候会自动调用这个方法
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ClassAndName classAndName = mClassAndNames[position];

        Intent i = new Intent(this, classAndName.getClazz());
        startActivity(i);
    }

    private class ClassAndName {
        private Class<?> clazz;
        private String name;

        public ClassAndName(Class<?> clazz, String name) {
            this.clazz = clazz;
            this.name = name;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
