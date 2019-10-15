package com.sz.ucar.lib.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sz.bw.cardock.business.base.loader.listener.OnModelLoadListener;
import com.sz.bw.cardock.business.base.search.BaseSearchListActivity;
import com.sz.bw.cardock.business.base.search.SimpleListModel;

import java.util.List;

public class SearchDemo extends BaseSearchListActivity {

    @Nullable
    @Override
    public BaseQuickAdapter getAdapter() {
        BaseQuickAdapter adapter = new BaseQuickAdapter(R.layout.bizbase_demo_activity_list_loader_item) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {
                helper.setText(R.id.item_tv, item.toString());
            }
        };
        return adapter;
    }

    @Nullable
    @Override
    public SimpleListModel getDataSource(List list) {
        return new SimpleListModel(list) {
            @Override
            public void load(OnModelLoadListener onModelLoadListener) {


            }
        };
    }

    @Override
    public String getHistorySaveKey() {
        return "u_search";
    }
}
