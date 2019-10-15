package com.sz.ucar.lib.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.ucar.bwued.lib.calendar.UCalenderView;
import com.sz.ucar.bwued.lib.menu.UDropDownMenuHelper;
import com.sz.ucar.bwued.lib.menu.combine.BaseCombine;
import com.sz.ucar.bwued.lib.menu.combine.BaseTextTabTwoListCombine;
import com.sz.ucar.bwued.lib.menu.combine.IconTabFilterCombine;
import com.sz.ucar.bwued.lib.menu.combine.SimpleTextTabOneListCombine;
import com.sz.ucar.bwued.lib.menu.combine.TextTabFilterCombine;
import com.sz.ucar.bwued.lib.menu.content.ILeftMenuItem;
import com.sz.ucar.bwued.lib.menu.content.SimpleMenuItem;
import com.sz.ucar.bwued.lib.menu.content.filter.entity.FilterDateGroup;
import com.sz.ucar.bwued.lib.menu.content.filter.entity.FilterDateItem;
import com.sz.ucar.bwued.lib.menu.content.filter.entity.FilterDateRecentItem;
import com.sz.ucar.bwued.lib.menu.content.filter.entity.FilterGroup;
import com.sz.ucar.bwued.lib.menu.content.filter.entity.FilterItem;

import java.util.ArrayList;
import java.util.List;

public class UDropDownMenuDemo extends AppCompatActivity {

    private List<BaseCombine> mCombines = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_drop_down_menu_layout);

        initCombined();
        View contentView = findViewById(R.id.flp_menu_container_layout);
        UDropDownMenuHelper menuHelper = new UDropDownMenuHelper(contentView);
        menuHelper.setupMenu(mCombines);
    }

    private void initCombined(){

        ArrayList<MainMenuItem> menuList1 = new ArrayList<MainMenuItem>();
        for (int i=0 ; i < 10 ; i++) {
            ArrayList<SimpleMenuItem> list = new ArrayList<SimpleMenuItem>();
            for (int j=0 ; j < 10 ; j++) {
                list.add(new SimpleMenuItem("菜单" + i + "-" + j, i + "-" + j));
            }
            menuList1.add(new MainMenuItem("主菜单"+i, ""+i, list));
        }


        BaseTextTabTwoListCombine<SimpleMenuItem, MainMenuItem> list1 =
                new BaseTextTabTwoListCombine<SimpleMenuItem, MainMenuItem>(this) {
            @Override
            public String getTitle(@Nullable MainMenuItem selectedLeftItem,
                                   @Nullable SimpleMenuItem selectedRightItem) {
                if (selectedLeftItem != null) {
                    return selectedLeftItem.mName;
                } else {
                    return "默认标题";
                }
            }

            @Override
            public boolean isSelected(@Nullable MainMenuItem selectedLeftItem,
                                      @Nullable SimpleMenuItem selectedRightItem) {
                return selectedLeftItem != null;
            }
        };
        list1.setData(menuList1);
        list1.setLeftSelectedMenu(menuList1.get(0));
        list1.setLeftParamKey("leftParams");
        list1.setRightParamKey("rightParams");
        mCombines.add(list1);

        ArrayList<SimpleMenuItem> list2 = new ArrayList<>();
        list2.add(new SimpleMenuItem("菜单2-1", "2-1"));
        list2.add(new SimpleMenuItem("菜单2-2", "2-2"));
        list2.add(new SimpleMenuItem("菜单2-3", "2-3"));
        SimpleTextTabOneListCombine combine = new SimpleTextTabOneListCombine(this);
        combine.setData(list2);
        combine.setSelectedMenu(list2.get(0));
        combine.setParamKey("oneList");
        mCombines.add(combine);


        mCombines.add(getTextCombine());



    }

    private IconTabFilterCombine getCombine(){
        return null;
    }

    private TextTabFilterCombine getTextCombine(){
        int width = getApplicationContext().getResources().getDimensionPixelSize(R.dimen.dd_dimen_143px);
        List<FilterGroup> filterData = makeFilterData();
        TextTabFilterCombine combine = new TextTabFilterCombine(this, "筛选", width);
        combine.setFilterGroupList(filterData);
        return combine;
    }

    private  List<FilterGroup> makeFilterData(){
        List<FilterGroup> list = new ArrayList<>();
        int tn1 = 3;
        int tn2 = 5;
        for (int i=0; i < tn1; i++) {
            FilterGroup group = new FilterGroup(i+"", "测试分组" +i);
            group.setMultiple(true);
            for (int j=0; j < tn2; j++) {
                group.addItem(new FilterItem((i * j)+"", "选项" + i + "-" +j));
            }
            list.add(group);
        }
        /*FilterDateGroup dateGroup = new FilterDateGroup("start", "end", "选择日期");
        dateGroup.setMultiple(false);
        dateGroup.addItem(FilterDateRecentItem.recent("今天", 0, true));
        dateGroup.addItem(FilterDateRecentItem.recent("近3天", 3, true));
        dateGroup.addItem(FilterDateRecentItem.recent("近7天", 7, true));
        FilterDateItem dateItem4 = new FilterDateItem(this, "1000", "自定义时间", true);
        UCalenderView.Attribute attribute = new UCalenderView.Attribute(this);
        attribute.pickSingleDate = false;
        dateItem4.setAttribute(attribute);
        dateGroup.addItem(dateItem4);
        list.add(dateGroup);*/
        return list;
    }

    private class MainMenuItem implements ILeftMenuItem<SimpleMenuItem> {

        private String mName;
        private String mCode;
        private List<SimpleMenuItem> mList;

        public MainMenuItem(String name,  String code, List<SimpleMenuItem> list) {
            mName = name;
            mCode = code;
            mList = list;
        }

        @Override
        public List<SimpleMenuItem> getSubMenuList() {
            return mList;
        }

        @Override
        public String getTitle() {
            return mName;
        }

        @Override
        public String getCode() {
            return mCode;
        }
    }
}
