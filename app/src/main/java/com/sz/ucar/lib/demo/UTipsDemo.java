package com.sz.ucar.lib.demo;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.sz.bw.cardock.business.base.activity.BaseActivity;
import com.sz.ucar.bwued.lib.UTips;
import com.sz.ucar.bwued.lib.dialog.BaseDialog;
import com.sz.ucar.bwued.lib.dialog.PopupDialog;
import com.sz.ucar.bwued.lib.dialog.bean.MenuBean;
import com.sz.ucar.bwued.lib.dialog.plugin.SimpleAdapter;
import com.sz.ucar.bwued.lib.forminput.UFormInputLayout;
import com.sz.ucar.bwued.lib.picker.OptionsBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UTipsDemo extends BaseActivity {

    private BaseActivity mContext;

    @Override
    public void initParms() {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context context) {
        mContext = this;
        List<OptionsBean> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OptionsBean bean = new OptionsBean();
            bean.setName("Pick" + i);
            data.add(bean);
        }

        final UFormInputLayout uFormInputLayout = findViewById(R.id.ufll_utips);
        uFormInputLayout.setTriggerSelectListener(new UFormInputLayout.TriggerSelectListener() {
            @Override
            public void onTriggerSelect(String key) {
                UTips.pick(UTipsDemo.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        uFormInputLayout.setValue(data.get(options1).getName());
                    }
                }, data);
            }
        });


        List<MenuBean> menuBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MenuBean bean = new MenuBean();
            bean.setName("Pop menu " + i);
            bean.setIcon(getDrawable(R.drawable.bizbase_base_error_icon));
        }
        findViewById(R.id.ulbt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UTips.toast(mContext, "button clicked");
                UTips.popup(mContext, uFormInputLayout, new PopupDialog.IItemClickWatcher() {
                    @Override
                    public void onClick(int position, MenuBean item, View view) {
                        UTips.toast(mContext, "Selected : " + item.getName());
                    }
                }, menuBeans);

            }
        });

        findViewById(R.id.ulbt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UTips.pickTime(mContext, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        UTips.toast(mContext, "time : " + date.toString());
                    }
                });


            }
        });


        ArrayList<String> lifts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lifts.add("Lifts : " + i);
        }
        findViewById(R.id.ulbt3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UTips.input();


            }
        });

        findViewById(R.id.ulbt4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UTips.lift(mContext, new SimpleAdapter.IItemClickWatcher() {
                    @Override
                    public boolean onClick(int position, CharSequence content, View view) {
                        return false;
                    }
                }, lifts, "LLifts");


            }
        });

        findViewById(R.id.ulbt5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UTips.loading(mContext);


            }
        });

        findViewById(R.id.ulbt6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UTips.confirm(mContext, new BaseDialog.ActionWatcher() {
                    @Override
                    public void onCreate(BaseDialog dialog) {
                        super.onCreate(dialog);
                    }

                    @Override
                    public boolean onNegative(BaseDialog dialog, BaseDialog.Result nResult) {
                        return super.onNegative(dialog, nResult);
                    }

                    @Override
                    public void onNeutral(BaseDialog dialog) {
                        super.onNeutral(dialog);
                    }

                    @Override
                    public boolean onPositive(BaseDialog dialog, BaseDialog.Result pResult) {
                        return super.onPositive(dialog, pResult);
                    }
                }, "Title", "Message");


            }
        });


        findViewById(R.id.ulbt7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UTips.share(mContext, new BaseDialog.ActionWatcher(),
                        "Title", true, true);

            }
        });

        this.headerView.setActionIcon(R.drawable.bizbase_ic_search);
        headerView.setActionListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UTips.toast(mContext, "search action");
            }
        });


    }


    @Override
    public int contentLayout() {
        return R.layout.demo_utips;
    }
}
