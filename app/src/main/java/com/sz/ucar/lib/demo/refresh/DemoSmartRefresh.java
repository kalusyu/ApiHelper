package com.sz.ucar.lib.demo.refresh;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sz.bw.cardock.business.base.activity.BaseActivity;
import com.sz.ucar.lib.demo.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/8
 **/
public class DemoSmartRefresh extends BaseActivity {

    BaseQuickAdapter<String, BaseViewHolder> adapter;

    @Override
    public void initParms() {

    }

    @Override
    public void initView(View view) {
    }

    @Override
    public void doBusiness(Context context) {
        setTitle("SmartRefreshLayout");
        RefreshLayout refreshLayout = findViewById(R.id.smart_refresh);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                ArrayList<String> data = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    data.add("CCCC " + i);
                }
                adapter.addData(data);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

                refreshLayout.finishLoadMore(2000);
                ArrayList<String> data = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    data.add("BBB " + i);
                }
                adapter.addData(data);
            }
        });

        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));

        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.FixedBehind));

        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            data.add("AAA " + i);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.u_view_list_item_simple, data) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_content, item);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int contentLayout() {
        return R.layout.demo_smart_refresh_layout;
    }

    // <editor-fold desc="my editor description">

    public void add() {

    }

    // </editor-fold>
}
