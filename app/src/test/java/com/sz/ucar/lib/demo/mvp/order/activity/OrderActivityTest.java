package com.sz.ucar.lib.demo.mvp.order.activity;

import com.sz.ucar.bwued.lib.demo.free.BuildConfig;
import com.sz.ucar.bwued.lib.demo.free.ShadowToastUtil;
import com.sz.ucar.bwued.lib.demo.free.R;
import com.sz.ucar.bwued.lib.demo.free.base.view.widget.recyclerload.recyclerview.LrecyclerView;
import com.sz.ucar.bwued.lib.demo.free.base.view.widget.recyclerload.recyclerview.LrecyclerViewAdapter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * @author android SZZC plugin template
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,sdk = 27,shadows = {ShadowToastUtil.class})
public class OrderActivityTest {
    OrderActivity activity;

    @Before
    public void setUp(){
        activity = Robolectric.setupActivity(OrderActivity.class);
    }

    @Test
    public void showErrorMsgTest(){
        Assert.assertNotNull(activity);

        activity.showErrorMsg(true,new Throwable("服务器错误"));
        assertEquals("服务器错误", ShadowToastUtil.getToastMessage());

        activity.showErrorMsg(true,"请求失败");
        assertEquals("请求失败，请检查网络或稍后重试", ShadowToastUtil.getToastMessage());

        activity.showErrorMsg(false,"无更多数据");
        assertEquals("无更多数据", ShadowToastUtil.getToastMessage());

        activity.showErrorMsg(false,new Throwable("服务器错误"));
        assertEquals("请求失败，请检查网络或稍后重试", ShadowToastUtil.getToastMessage());
    }
    @Test
    public void ListTest(){
        LrecyclerView lrecyclerView = activity.findViewById(R.id.recyclerview);
        assertEquals(0,((LrecyclerViewAdapter)lrecyclerView.getAdapter()).getInnerAdapter().getItemCount());
    }
}
