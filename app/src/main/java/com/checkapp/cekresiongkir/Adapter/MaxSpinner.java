package com.checkapp.cekresiongkir.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Field;

@SuppressLint("AppCompatCustomView")
public class MaxSpinner extends Spinner {

    private android.widget.ListPopupWindow popupWindow;

    public MaxSpinner(Context context) {
        super(context);
    }

    public MaxSpinner(Context context, int mode) {
        super(context, mode);
    }

    public MaxSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaxSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    public MaxSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode) {
        super(context, attrs, defStyleAttr, defStyleRes, mode);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MaxSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, defStyleRes, mode, popupTheme);
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        super.setAdapter(adapter);
        try {
            @SuppressLint("DiscouragedPrivateApi") Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            popupWindow = (android.widget.ListPopupWindow) popup.get(this);
            popupWindow.setHeight(1000);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
