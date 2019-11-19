package com.sz.ucar.lib.demo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sz.bw.cardock.business.base.activity.BaseActivity;
import com.sz.ucar.lib.demo.adapter.ExpandViewCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FolderRecyclerVIewActivity extends BaseActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    public void initParms() {

    }

    @Override
    public void initView(View view) {
        recyclerView = findViewById(R.id.rv_folder);

    }

    @Override
    public void doBusiness(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new MyAdapter();
        List<Item> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Item item = new Item();
            item.type = new Random().nextInt(100) % 2 == 0 ? 0 : 1;
            item.itemType = 0;
            item.name = "数据项" + i;
            data.add(item);
        }
        Item item = new Item();
        item.itemType = 1;
        item.type = -1;
        data.add(item);

        adapter.setData(data);
        recyclerView.setLayoutManager(manager);
        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);

        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(adapter);
    }

    class Item {
        int type;
        String name;
        int itemType;
    }

    @Override
    public int contentLayout() {
        return R.layout.demo_folder_recyclerview_layout;
    }


    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private int ITME_TYPE_CONTENT = 1000;
        private int ITME_TYPE_EXPAND = 1001;

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

            if (viewHolder instanceof MyViewHoder) {
                MyViewHoder  hodler = (MyViewHoder)viewHolder;
                if (isExpanded() && position >= mTypeOne.size()) {
                    //Log.e("ybw", "position = " + position + "," + (position - mTypeOne.size()));
                    hodler.textView.setText(mTypeTwo.get(position - mTypeOne.size()).name);
                } else {
                    //Log.e("ybw", "position = " + position);
                    hodler.textView.setText(mTypeOne.get(position).name);
                }
            } else if (viewHolder instanceof ItemViewHoder) {
                ItemViewHoder  hodler = (ItemViewHoder)viewHolder;
                hodler.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.setExpanded(!adapter.isExpanded());
                        if (adapter.isExpanded()) {
                            hodler.textView.setText("收起已取消方案");
                        } else {
                            hodler.textView.setText("查看已取消方案");
                        }
                    }
                });
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            Log.e("ybw" , "viewType = " + viewType);
            if (viewType == ITME_TYPE_CONTENT) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_expand_item, viewGroup, false);
                return new MyViewHoder(view);
            } else if (viewType == ITME_TYPE_EXPAND){
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_expand_layout, viewGroup, false);
                return new ItemViewHoder(view);
            }
            return null;
        }




        @Override
        public int getItemViewType(int position) {
            if (isExpanded()) {
                if (position == mTypeOne.size() + mTypeTwo.size()) {
                    return ITME_TYPE_EXPAND;
                }
            } else {
                if (position == mTypeOne.size()){
                    return ITME_TYPE_EXPAND;
                }
            }
            return ITME_TYPE_CONTENT;
        }

        @Override
        public int getItemCount() {
            if (isExpanded()) {
                return mTypeOne.size() + mTypeTwo.size() + 1;
            }
            return mTypeOne.size() + 1;
        }

        private List<Item> mTypeOne = new ArrayList<>();
        private List<Item> mTypeTwo = new ArrayList<>();
        private boolean isExpanded;

        public boolean isExpanded() {
            return isExpanded;
        }

        public void setExpanded(boolean expanded) {
            isExpanded = expanded;
            notifyDataSetChanged();
        }

        public void setData(List<Item> data) {
            for (Item item : data) {
                if (item.type == 0) {
                    mTypeOne.add(item);
                } else if(item.type == 1) {
                    mTypeTwo.add(item);
                }
            }
        }
    }

    class MyViewHoder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_expand_item);
        }
    }

    class ItemViewHoder extends RecyclerView.ViewHolder {

        TextView textView;

        public ItemViewHoder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_expand_text);
        }
    }

}


