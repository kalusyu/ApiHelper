package com.sz.ucar.lib.demo.adapter;

import android.view.View;

/**
 * 可折叠适配器回调
 *
 * @author yuzhong.wu (yuzhong.wu@ucarinc.com)
 * @date 2019/10/11
 */
public interface ExpandViewCallback<T> {
    /**
     * 头部布局id，无头部时传-1
     *
     * @return 头部布局id
     */
    int getHeaderViewLayout();

    /**
     * 折叠按钮布局id
     *
     * @return 折叠按钮布局id
     */
    int getExpandViewLayout();

    /**
     * item项布局id
     *
     * @return item项布局id
     */
    int getItemViewLayout();

    /**
     * 超过多项时，折叠显示
     *
     * @return 超过多项时，折叠显示
     */
    int getExpandThreshold();

    /**
     * 头部布局绑定回调
     */
    void onBindHeaderView();

    /**
     * 折叠按钮布局绑定回调
     *
     * @param itemView 布局view
     * @param expanded 是否折叠
     */
    void onBindExpandView(View itemView, boolean expanded);

    /**
     * item布局绑定回调
     *
     * @param itemView 布局view
     * @param item     数据
     * @param itemPos  item位置
     * @param viewPos  布局位置
     */
    void onBindItemView(View itemView, T item, int itemPos, int viewPos);
}
