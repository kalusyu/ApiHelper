package com.sz.ucar.bwued.lib.demo;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * @author android SZZC plugin template
 */
@Implements(TextUtils.class)
public class ShadowTextUtils {
    @Implementation
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }
}
