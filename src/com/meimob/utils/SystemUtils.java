package com.meimob.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class SystemUtils {
    /**
     * 
     *隐藏输入法
     * @param context
     * @param v
     */
    public static void hideKey(Context context, TextView v){
	InputMethodManager imm=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
