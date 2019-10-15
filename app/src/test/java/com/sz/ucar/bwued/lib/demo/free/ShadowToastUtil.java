package com.sz.ucar.bwued.lib.demo.free;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.Log;

import com.sz.ucar.bwued.lib.demo.free.base.view.toast.ToastUtil;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * @author android SZZC plugin template
 */
@Implements(ToastUtil.class)
public class ShadowToastUtil {

    private static String message;

    @Implementation
    public static void showToast(Context context, @IdRes int resId, boolean isShort, boolean... isShowSucessImage) {
        if (null == context) {
            return;
        }
        message = null;
        try {
            message = context.getString(resId);
        } catch (Exception e) {
            Log.e("ToastUtil", Log.getStackTraceString(e));
        }

        if (isShowSucessImage.length > 0) {
            showToast(context, message, isShort, isShowSucessImage[0]);
        } else {
            showToast(context, message, isShort, false);
        }

    }
    @Implementation
    public static void showToast(Context context, CharSequence msg, boolean isShort, boolean... isShowSucessImage) {
        message = msg.toString();
    }

    public static String getToastMessage(){
        return message;
    }
}
