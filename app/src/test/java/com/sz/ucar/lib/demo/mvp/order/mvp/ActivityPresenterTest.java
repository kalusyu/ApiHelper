package com.sz.ucar.lib.demo.mvp.order.mvp;

import com.sz.ucar.lib.demo.mvp.order.activity.OrderActivity;
import com.sz.ucar.lib.demo.mvp.order.mapi.OrderInfo;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.shadows.ShadowLog;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author android SZZC plugin template
 */
public class ActivityPresenterTest {

    private ActivityPresenter mPresenter;
    private OrderActivity activityView;
    private OrderInfo mInfo;

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
        activityView = mock(OrderActivity.class);
        mInfo = mock(OrderInfo.class);
        mPresenter = new ActivityPresenter(activityView);
        mPresenter.attachView(activityView);
    }

    @Test
    public void getDataOKTest(){
        mPresenter.onData(mInfo);
        verify(activityView).showLoading(false);
        verify(activityView).updateView(mInfo);
    }

    @Test
    public void getDataErrTest(){
        mPresenter.onError(true,"Not Found");
        verify(activityView).showLoading(false);
        verify(activityView).showErrorMsg(true,"Not Found");
    }
}
