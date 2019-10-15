package com.sz.ucar.lib.demo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 可折叠列表适配器
 *
 * @author yuzhong.wu (yuzhong.wu@ucarinc.com)
 * @date 2019/9/25
 */
public class ExpandListAdapter extends RecyclerView.Adapter<ExpandListAdapter.MyHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_EXPAND_VIEW = 2;

    private boolean expanded;
    private List items = new ArrayList<>();
    private ExpandViewCallback callback;

    public ExpandListAdapter(ExpandViewCallback<?> callback) {
        this.callback = callback;
    }

    public void setData( List items) {
        if (this.items == items) {
            return;
        }
        this.items.clear();
        if (items != null) {
            this.items.addAll(items);
        }
        notifyDataSetChanged();
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(callback.getHeaderViewLayout(), viewGroup, false);
            return new MyHolder(view);
        }

        if (viewType == TYPE_EXPAND_VIEW) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(callback.getExpandViewLayout(), viewGroup, false);
            view.setVisibility(View.VISIBLE);
            return new MyHolder(view);
        }

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(callback.getItemViewLayout(), viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder( MyHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == TYPE_HEADER) {
            callback.onBindHeaderView();
            return;
        }

        if (viewType == TYPE_EXPAND_VIEW) {
            callback.onBindExpandView(holder.itemView, expanded);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setExpanded(!expanded);
                }
            });
            return;
        }

        int realPos = getRealPos(position);
        callback.onBindItemView(holder.itemView, items.get(realPos), realPos, position);
    }

    private int getRealPos(int position) {
        if (hasExpandView() && position > getExpandViewPos()) {
            return position - (hasHeader() ? 1 : 0) - 1;
        } else {
            return position - (hasHeader() ? 1 : 0);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasHeader() && position == 0) {
            return TYPE_HEADER;
        }

        if (hasExpandView() && position == getExpandViewPos()) {
            return TYPE_EXPAND_VIEW;
        }

        return TYPE_ITEM;
    }

    private boolean hasHeader() {
        return callback.getHeaderViewLayout() > 0;
    }

    private int getExpandViewPos() {
        return hasHeader() ? callback.getExpandThreshold() + 1 : callback.getExpandThreshold();
    }

    @Override
    public int getItemCount() {
        int count = items.size();

        if (count == 0) {
            return 0;
        }

        if (!hasExpandView()) {
            return count + (hasHeader() ? 1 : 0);
        }

        if (expanded) {
            return count + (hasHeader() ? 1 : 0) + 1;
        }

        return Math.min(count, callback.getExpandThreshold()) + (hasHeader() ? 2 : 1);
    }

    private boolean hasExpandView() {
        return items.size() > callback.getExpandThreshold();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private MyHolder( View itemView) {
            super(itemView);
        }
    }
}
