package com.sz.ucar.lib.demo.customview;

import android.content.Context;
import android.view.View;

import com.sz.bw.cardock.business.base.activity.BaseActivity;
import com.sz.ucar.lib.demo.R;
import com.sz.ucar.lib.demo.customview.view.UNodeProgressbar;

import java.util.ArrayList;
import java.util.List;

public class UNodeProgressbarActivity extends BaseActivity {

    @Override
    public void initParms() {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context context) {
        UNodeProgressbar progressbar = findViewById(R.id.u_node_progress_bar);
        List<UNodeProgressbar.Node> nodes = new ArrayList<>();
        UNodeProgressbar.Node node = progressbar.new  Node();
        node.setType(1);
        node.setText("已创建");
        node.setTime("2019/10/22 13:53");
        nodes.add(node);

        node = progressbar.new  Node();
        node.setType(0);
        node.setText("完成");
        //node.setTime("2019/10/22 13:53");
        nodes.add(node);

        progressbar.setNodes(nodes);
    }

    @Override
    public int contentLayout() {
        return R.layout.demo_node_progress;
    }
}
