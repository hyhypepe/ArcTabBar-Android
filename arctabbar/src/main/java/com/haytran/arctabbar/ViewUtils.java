package com.haytran.arctabbar;

import android.content.res.Resources;
import android.util.TypedValue;

public class ViewUtils {

    public static int dp2px(Resources resource, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resource.getDisplayMetrics());
    }

    public static float px2dp(Resources resource, float px) {
        return (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, resource.getDisplayMetrics());
    }
}
