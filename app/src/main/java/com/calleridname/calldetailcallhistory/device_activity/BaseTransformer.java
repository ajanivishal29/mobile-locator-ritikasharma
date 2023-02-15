package com.calleridname.calldetailcallhistory.device_activity;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

public abstract class BaseTransformer implements ViewPager.PageTransformer {
    public boolean hideOffscreenPages() {
        return true;
    }

    public boolean isPagingEnabled() {
        return false;
    }

    public void onPostTransform(View view, float f) {
    }

    public abstract void onTransform(View view, float f);

    public void transformPage(View view, float f) {
        onPreTransform(view, f);
        onTransform(view, f);
        onPostTransform(view, f);
    }

    public void onPreTransform(View view, float f) {
        float width = (float) view.getWidth();
        float f2 = 0.0f;
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setTranslationY(0.0f);
        view.setTranslationX(isPagingEnabled() ? 0.0f : (-width) * f);
        if (hideOffscreenPages()) {
            if (f > -1.0f && f < 1.0f) {
                f2 = 1.0f;
            }
            view.setAlpha(f2);
            return;
        }
        view.setAlpha(1.0f);
    }
}
