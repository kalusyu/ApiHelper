package com.sz.ucar.lib.demo.mvp.order.mvp;

import com.sz.ucar.bwued.lib.demo.free.LoggingInterceptor;
import com.sz.ucar.bwued.lib.demo.free.RxJavaRule;
import com.sz.ucar.bwued.lib.demo.free.ServerApi;
import com.sz.ucar.bwued.lib.demo.free.ShadowTextUtils;
import com.sz.ucar.lib.demo.mvp.order.mapi.OrderInfo;
import com.sz.ucar.lib.demo.mvp.order.mapi.OrderRequest;
import com.sz.ucar.common.base.IDataSource;
import com.sz.ucar.common.base.BaseContext;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.support.membermodification.MemberModifier;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import io.appflate.restmock.JVMFileParser;
import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.RESTMockServerStarter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.appflate.restmock.utils.RequestMatchers.pathEndsWith;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author android SZZC plugin template
 */
@RunWith(RobolectricTestRunner.class)
@Config(shadows = {ShadowTextUtils.class},sdk = 27)
public class OrderModelTest {
    private static final String REQUEST_TYPE = "data";
    private static final String REQUEST_OK_JSON = "order/data_ok.json";
    private static final String REQUEST_ERR_JSON = "order/data_err.json";

    private OrderModel mModel;
    private IDataSource.IDataSourceListener mPresenter;
    private OrderInfo info;

    @Rule
    public RxJavaRule rule = new RxJavaRule();

    @Before
    public void setUp() throws Exception{
        //初始化RESTMock
        RESTMockServerStarter.startSync(new JVMFileParser());
        //打开日志
        ShadowLog.stream = System.out;
        //使用Mockito生成context
        BaseContext context = mock(BaseContext.class);
        mModel = new OrderModel(context);
        //使用Mockito生成OrderRequest对象
        OrderRequest request = mock(OrderRequest.class);
        //使用PowerMock给mModel的私有变量requestBean赋值
        MemberModifier.field(OrderModel.class, "requestBean").set(mModel, request);
        //使用Mockito生成ActivityPresenter对象
        mPresenter = mock(IDataSource.IDataSourceListener.class);

        //创建OkHttpClient，添加日志输出拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        //retrofit创建，关键点在“baseUrl(RESTMockServer.getUrl())”
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(RESTMockServer.getUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerApi api = retrofit.create(ServerApi.class);

        //为getBaseURL设置返回值，原代码返回null会引发异常
        when(request.getBaseURL()).thenReturn(RESTMockServer.getUrl());
        //返回true，跳过DynamicKey
        when(request.skipCheckKeyValid()).thenReturn(true);
        //替换真实请求
        when(request.getObservable(any(Retrofit.class))).thenReturn(api.getData(REQUEST_TYPE));
    }

    @Test
    public void getDataOKTest() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation){
                //获取请求返回的响应数据
                info = invocation.getArgument(0);
                return null;
            }
        }).when(mPresenter).onData(any(OrderInfo.class));
        RESTMockServer.whenGET(pathEndsWith(REQUEST_TYPE)).thenReturnFile(200, REQUEST_OK_JSON);
        mModel.sendRequest(mPresenter);
		//可以在此处断言请求获取的字段是否正确
        //assertEquals("997106",info.getOrderId());
        //assertEquals("2018-12-06",info.getOrderTime());
        verify(mPresenter).onData(any(OrderInfo.class));
    }

    @Test
    public void getDataErrTest() {
        RESTMockServer.whenGET(pathEndsWith(REQUEST_TYPE)).thenReturnFile(404, REQUEST_ERR_JSON);
        mModel.sendRequest(mPresenter);
        verify(mPresenter).onError(any());
    }
}
