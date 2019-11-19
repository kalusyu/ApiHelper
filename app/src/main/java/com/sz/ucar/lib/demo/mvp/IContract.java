package com.sz.ucar.lib.demo.mvp;

import com.sz.ucar.common.base.IPresenter;
import com.sz.ucar.common.base.IView;

public abstract class IContract {

    interface IIView extends IView<IPresenter>{

    }

    interface IIPresenter extends IPresenter<IView>{

    }
}
