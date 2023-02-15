package com.calleridname.calldetailcallhistory.numberlocation_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.calleridname.calldetailcallhistory.R;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@SuppressLint("WrongConstant")

public class SlidingUpPanelLayout extends ViewGroup {
    private static final float DEFAULT_ANCHOR_POINT = 1.0f;
    private static final int[] DEFAULT_ATTRS = {16842927};
    private static final boolean DEFAULT_CLIP_PANEL_FLAG = true;
    private static final int DEFAULT_FADE_COLOR = -1728053248;
    private static final int DEFAULT_MIN_FLING_VELOCITY = 400;
    private static final boolean DEFAULT_OVERLAY_FLAG = false;
    private static final int DEFAULT_PANEL_HEIGHT = 68;
    private static final int DEFAULT_PARALLAX_OFFSET = 0;
    private static final int DEFAULT_SHADOW_HEIGHT = 4;
    private static PanelState DEFAULT_SLIDE_STATE = PanelState.COLLAPSED;
    public static final String SLIDING_STATE = "sliding_state";
    private static final String TAG = SlidingUpPanelLayout.class.getSimpleName();
    public float mAnchorPoint;
    private ScrollableViewHelper mCallLocatorScrollableViewHelper;
    private boolean mClipPanel;
    private int mCoveredFadeColor;
    private final Paint mCoveredFadePaint;
    public ViewDragHelper mDragHelper;
    private View mDragView;
    private int mDragViewResId;
    private OnClickListener mFadeOnClickListener;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsScrollableViewHandlingTouch;
    public boolean mIsSlidingUp;
    private boolean mIsTouchEnabled;
    public boolean mIsUnableToDrag;
    private PanelState mLastNotDraggingSlideState;
    private View mMainView;
    private int mMinFlingVelocity;
    private boolean mOverlayContent;
    private int mPanelHeight;
    private final List<PanelSlideListener> mPanelSlideListeners;
    private int mParallaxOffset;
    private float mPrevMotionX;
    private float mPrevMotionY;
    private View mScrollableView;
    private int mScrollableViewResId;
    private Drawable mShadowDrawable;
    private int mShadowHeight;
    public float mSlideOffset;
    public int mSlideRange;
    public PanelState mSlideState;
    public View mSlideableView;
    private final Rect mTmpRect;

    public interface PanelSlideListener {
        void onPanelSlide(View view, float f);

        void onPanelStateChanged(View view, PanelState panelState, PanelState panelState2);
    }

    public enum PanelState {
        EXPANDED,
        COLLAPSED,
        ANCHORED,
        HIDDEN,
        DRAGGING
    }

    public static class SimplePanelSlideListener implements PanelSlideListener {
        public void onPanelSlide(View view, float f) {
        }

        public void onPanelStateChanged(View view, PanelState panelState, PanelState panelState2) {
        }
    }

    static class AnonymousClass2 {
        static final int[] f389xe2f6554b;

        static {
            int[] iArr = new int[PanelState.values().length];
            f389xe2f6554b = iArr;
            iArr[PanelState.EXPANDED.ordinal()] = 1;
            iArr[PanelState.ANCHORED.ordinal()] = 2;
            iArr[PanelState.HIDDEN.ordinal()] = 3;
            try {
                iArr[PanelState.COLLAPSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }

        AnonymousClass2() {
        }
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        private DragHelperCallback() {
        }

        public boolean tryCaptureView(View view, int i) {
            if (SlidingUpPanelLayout.this.mIsUnableToDrag || view != SlidingUpPanelLayout.this.mSlideableView) {
                return false;
            }
            return SlidingUpPanelLayout.DEFAULT_CLIP_PANEL_FLAG;
        }

        public void onViewDragStateChanged(int i) {
            if (SlidingUpPanelLayout.this.mDragHelper != null && SlidingUpPanelLayout.this.mDragHelper.getViewDragState() == 0) {
                SlidingUpPanelLayout slidingUpPanelLayout = SlidingUpPanelLayout.this;
                slidingUpPanelLayout.mSlideOffset = slidingUpPanelLayout.computeSlideOffset(slidingUpPanelLayout.mSlideableView.getTop());
                SlidingUpPanelLayout.this.applyParallaxForCurrentSlideOffset();
                if (SlidingUpPanelLayout.this.mSlideOffset == 1.0f) {
                    SlidingUpPanelLayout.this.updateObscuredViewVisibility();
                    SlidingUpPanelLayout.this.setPanelStateInternal(PanelState.EXPANDED);
                } else if (SlidingUpPanelLayout.this.mSlideOffset == 0.0f) {
                    SlidingUpPanelLayout.this.setPanelStateInternal(PanelState.COLLAPSED);
                } else if (SlidingUpPanelLayout.this.mSlideOffset < 0.0f) {
                    SlidingUpPanelLayout.this.setPanelStateInternal(PanelState.HIDDEN);
                    SlidingUpPanelLayout.this.mSlideableView.setVisibility(4);
                } else {
                    SlidingUpPanelLayout.this.updateObscuredViewVisibility();
                    SlidingUpPanelLayout.this.setPanelStateInternal(PanelState.ANCHORED);
                }
            }
        }

        public void onViewCaptured(View view, int i) {
            SlidingUpPanelLayout.this.setAllChildrenVisible();
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            SlidingUpPanelLayout.this.onPanelDragged(i2);
            SlidingUpPanelLayout.this.invalidate();
        }

        public void onViewReleased(View view, float f, float f2) {
            int i;
            if (SlidingUpPanelLayout.this.mIsSlidingUp) {
                f2 = -f2;
            }
            int i2 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
            if (i2 > 0 && SlidingUpPanelLayout.this.mSlideOffset <= SlidingUpPanelLayout.this.mAnchorPoint) {
                SlidingUpPanelLayout slidingUpPanelLayout = SlidingUpPanelLayout.this;
                i = slidingUpPanelLayout.computePanelTopPosition(slidingUpPanelLayout.mAnchorPoint);
            } else if (i2 <= 0 || SlidingUpPanelLayout.this.mSlideOffset <= SlidingUpPanelLayout.this.mAnchorPoint) {
                int i3 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
                if (i3 < 0 && SlidingUpPanelLayout.this.mSlideOffset >= SlidingUpPanelLayout.this.mAnchorPoint) {
                    SlidingUpPanelLayout slidingUpPanelLayout2 = SlidingUpPanelLayout.this;
                    i = slidingUpPanelLayout2.computePanelTopPosition(slidingUpPanelLayout2.mAnchorPoint);
                } else if (i3 < 0 && SlidingUpPanelLayout.this.mSlideOffset < SlidingUpPanelLayout.this.mAnchorPoint) {
                    i = SlidingUpPanelLayout.this.computePanelTopPosition(0.0f);
                } else if (SlidingUpPanelLayout.this.mSlideOffset >= (SlidingUpPanelLayout.this.mAnchorPoint + 1.0f) / 2.0f) {
                    i = SlidingUpPanelLayout.this.computePanelTopPosition(1.0f);
                } else if (SlidingUpPanelLayout.this.mSlideOffset >= SlidingUpPanelLayout.this.mAnchorPoint / 2.0f) {
                    SlidingUpPanelLayout slidingUpPanelLayout3 = SlidingUpPanelLayout.this;
                    i = slidingUpPanelLayout3.computePanelTopPosition(slidingUpPanelLayout3.mAnchorPoint);
                } else {
                    i = SlidingUpPanelLayout.this.computePanelTopPosition(0.0f);
                }
            } else {
                i = SlidingUpPanelLayout.this.computePanelTopPosition(1.0f);
            }
            if (SlidingUpPanelLayout.this.mDragHelper != null) {
                SlidingUpPanelLayout.this.mDragHelper.settleCapturedViewAt(view.getLeft(), i);
            }
            SlidingUpPanelLayout.this.invalidate();
        }

        public int getViewVerticalDragRange(View view) {
            return SlidingUpPanelLayout.this.mSlideRange;
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            int computePanelTopPosition = SlidingUpPanelLayout.this.computePanelTopPosition(0.0f);
            int computePanelTopPosition2 = SlidingUpPanelLayout.this.computePanelTopPosition(1.0f);
            if (SlidingUpPanelLayout.this.mIsSlidingUp) {
                return Math.min(Math.max(i, computePanelTopPosition2), computePanelTopPosition);
            }
            return Math.min(Math.max(i, computePanelTopPosition), computePanelTopPosition2);
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        private static final int[] ATTRS = {16843137};
        public float weight = 0.0f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.weight = f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ATTRS);
            if (obtainStyledAttributes != null) {
                this.weight = obtainStyledAttributes.getFloat(0, 0.0f);
                obtainStyledAttributes.recycle();
            }
        }
    }

    public SlidingUpPanelLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlidingUpPanelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingUpPanelLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMinFlingVelocity = 400;
        this.mCoveredFadeColor = DEFAULT_FADE_COLOR;
        this.mCoveredFadePaint = new Paint();
        this.mPanelHeight = -1;
        this.mShadowHeight = -1;
        this.mParallaxOffset = -1;
        this.mOverlayContent = false;
        this.mClipPanel = DEFAULT_CLIP_PANEL_FLAG;
        this.mDragViewResId = -1;
        this.mCallLocatorScrollableViewHelper = new ScrollableViewHelper();
        PanelState panelState = DEFAULT_SLIDE_STATE;
        this.mSlideState = panelState;
        this.mLastNotDraggingSlideState = panelState;
        this.mAnchorPoint = 1.0f;
        this.mIsScrollableViewHandlingTouch = false;
        this.mPanelSlideListeners = new CopyOnWriteArrayList();
        this.mFirstLayout = DEFAULT_CLIP_PANEL_FLAG;
        this.mTmpRect = new Rect();
        if (isInEditMode()) {
            this.mShadowDrawable = null;
            this.mDragHelper = null;
            return;
        }
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DEFAULT_ATTRS);
            if (obtainStyledAttributes != null) {
                setGravity(obtainStyledAttributes.getInt(0, 0));
                obtainStyledAttributes.recycle();
            }
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingUpPanelLayout);
            if (obtainStyledAttributes2 != null) {
                this.mPanelHeight = obtainStyledAttributes2.getDimensionPixelSize(7, -1);
                this.mShadowHeight = obtainStyledAttributes2.getDimensionPixelSize(11, -1);
                this.mParallaxOffset = obtainStyledAttributes2.getDimensionPixelSize(8, -1);
                this.mMinFlingVelocity = obtainStyledAttributes2.getInt(4, 400);
                this.mCoveredFadeColor = obtainStyledAttributes2.getColor(3, DEFAULT_FADE_COLOR);
                this.mDragViewResId = obtainStyledAttributes2.getResourceId(2, -1);
                this.mScrollableViewResId = obtainStyledAttributes2.getResourceId(10, -1);
                this.mOverlayContent = obtainStyledAttributes2.getBoolean(6, false);
                this.mClipPanel = obtainStyledAttributes2.getBoolean(1, DEFAULT_CLIP_PANEL_FLAG);
                this.mAnchorPoint = obtainStyledAttributes2.getFloat(0, 1.0f);
                this.mSlideState = PanelState.values()[obtainStyledAttributes2.getInt(5, DEFAULT_SLIDE_STATE.ordinal())];
                int resourceId = obtainStyledAttributes2.getResourceId(9, -1);
                Interpolator loadInterpolator = resourceId != -1 ? AnimationUtils.loadInterpolator(context, resourceId) : null;
                obtainStyledAttributes2.recycle();
                float f = context.getResources().getDisplayMetrics().density;
                if (this.mPanelHeight == -1) {
                    this.mPanelHeight = (int) ((68.0f * f) + 0.5f);
                }
                if (this.mShadowHeight == -1) {
                    this.mShadowHeight = (int) ((4.0f * f) + 0.5f);
                }
                if (this.mParallaxOffset == -1) {
                    this.mParallaxOffset = (int) (0.0f * f);
                }
                if (this.mShadowHeight > 0) {
                    this.mShadowDrawable = null;
                } else if (this.mIsSlidingUp) {
                    this.mShadowDrawable = getResources().getDrawable(R.drawable.realocation_above_shadow);
                } else {
                    this.mShadowDrawable = getResources().getDrawable(R.drawable.realocation_below_shadow);
                }
                setWillNotDraw(false);
                ViewDragHelper create = ViewDragHelper.create(this, 0.5f, loadInterpolator, new DragHelperCallback());
                this.mDragHelper = create;
                create.setMinVelocity(((float) this.mMinFlingVelocity) * f);
                this.mIsTouchEnabled = DEFAULT_CLIP_PANEL_FLAG;
            }
        }
        float f2 = context.getResources().getDisplayMetrics().density;
        setWillNotDraw(false);
        ViewDragHelper create2 = ViewDragHelper.create(this, 0.5f, (Interpolator) null, new DragHelperCallback());
        this.mDragHelper = create2;
        create2.setMinVelocity(((float) this.mMinFlingVelocity) * f2);
        this.mIsTouchEnabled = DEFAULT_CLIP_PANEL_FLAG;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        int i = this.mDragViewResId;
        if (i != -1) {
            setDragView(findViewById(i));
        }
        int i2 = this.mScrollableViewResId;
        if (i2 != -1) {
            setScrollableView(findViewById(i2));
        }
    }

    public void setGravity(int i) {
        if (i == 48 || i == 80) {
            this.mIsSlidingUp = i == 80 ? DEFAULT_CLIP_PANEL_FLAG : false;
            if (!this.mFirstLayout) {
                requestLayout();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("gravity must be set to either top or bottom");
    }

    public void setCoveredFadeColor(int i) {
        this.mCoveredFadeColor = i;
        requestLayout();
    }

    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    public void setTouchEnabled(boolean z) {
        this.mIsTouchEnabled = z;
    }

    public boolean isTouchEnabled() {
        if (!this.mIsTouchEnabled || this.mSlideableView == null || this.mSlideState == PanelState.HIDDEN) {
            return false;
        }
        return DEFAULT_CLIP_PANEL_FLAG;
    }

    public void setPanelHeight(int i) {
        if (getPanelHeight() != i) {
            this.mPanelHeight = i;
            if (!this.mFirstLayout) {
                requestLayout();
            }
            if (getPanelState() == PanelState.COLLAPSED) {
                smoothToBottom();
                invalidate();
            }
        }
    }

    public void smoothToBottom() {
        smoothSlideTo(0.0f, 0);
    }

    public int getShadowHeight() {
        return this.mShadowHeight;
    }

    public void setShadowHeight(int i) {
        this.mShadowHeight = i;
        if (!this.mFirstLayout) {
            invalidate();
        }
    }

    public int getPanelHeight() {
        return this.mPanelHeight;
    }

    public int getCurrentParallaxOffset() {
        int max = (int) (((float) this.mParallaxOffset) * Math.max(this.mSlideOffset, 0.0f));
        return this.mIsSlidingUp ? -max : max;
    }

    public void setParallaxOffset(int i) {
        this.mParallaxOffset = i;
        if (!this.mFirstLayout) {
            requestLayout();
        }
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public void setMinFlingVelocity(int i) {
        this.mMinFlingVelocity = i;
    }

    public void addPanelSlideListener(PanelSlideListener panelSlideListener) {
        synchronized (this.mPanelSlideListeners) {
            this.mPanelSlideListeners.add(panelSlideListener);
        }
    }

    public void removePanelSlideListener(PanelSlideListener panelSlideListener) {
        synchronized (this.mPanelSlideListeners) {
            this.mPanelSlideListeners.remove(panelSlideListener);
        }
    }

    public void setFadeOnClickListener(OnClickListener onClickListener) {
        this.mFadeOnClickListener = onClickListener;
    }

    private long mLastClickTime = 0;

    public void setDragView(View view) {
        View view2 = this.mDragView;
        if (view2 != null) {
            view2.setOnClickListener((OnClickListener) null);
        }
        this.mDragView = view;
        if (view != null) {
            view.setClickable(DEFAULT_CLIP_PANEL_FLAG);
            this.mDragView.setFocusable(false);
            this.mDragView.setFocusableInTouchMode(false);
            this.mDragView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        return;
                    }
                    if (SlidingUpPanelLayout.this.isEnabled() && SlidingUpPanelLayout.this.isTouchEnabled()) {
                        if (SlidingUpPanelLayout.this.mSlideState == PanelState.EXPANDED || SlidingUpPanelLayout.this.mSlideState == PanelState.ANCHORED) {
                            SlidingUpPanelLayout.this.setPanelState(PanelState.COLLAPSED);
                        } else if (SlidingUpPanelLayout.this.mAnchorPoint < 1.0f) {
                            SlidingUpPanelLayout.this.setPanelState(PanelState.ANCHORED);
                        } else {
                            SlidingUpPanelLayout.this.setPanelState(PanelState.EXPANDED);
                        }
                    }
                }
            });
        }
    }

    public void setDragView(int i) {
        this.mDragViewResId = i;
        setDragView(findViewById(i));
    }

    public void setScrollableView(View view) {
        this.mScrollableView = view;
    }

    public void setScrollableViewHelper(ScrollableViewHelper scrollableViewHelper) {
        this.mCallLocatorScrollableViewHelper = scrollableViewHelper;
    }

    public void setAnchorPoint(float f) {
        if (f > 0.0f && f <= 1.0f) {
            this.mAnchorPoint = f;
            this.mFirstLayout = DEFAULT_CLIP_PANEL_FLAG;
            requestLayout();
        }
    }

    public float getAnchorPoint() {
        return this.mAnchorPoint;
    }

    public void setOverlayed(boolean z) {
        this.mOverlayContent = z;
    }

    public boolean isOverlayed() {
        return this.mOverlayContent;
    }

    public void setClipPanel(boolean z) {
        this.mClipPanel = z;
    }

    public boolean isClipPanel() {
        return this.mClipPanel;
    }

    public void dispatchOnPanelSlide(View view) {
        synchronized (this.mPanelSlideListeners) {
            for (PanelSlideListener onPanelSlide : this.mPanelSlideListeners) {
                onPanelSlide.onPanelSlide(view, this.mSlideOffset);
            }
        }
    }

    public void dispatchOnPanelStateChanged(View view, PanelState panelState, PanelState panelState2) {
        synchronized (this.mPanelSlideListeners) {
            for (PanelSlideListener onPanelStateChanged : this.mPanelSlideListeners) {
                onPanelStateChanged.onPanelStateChanged(view, panelState, panelState2);
            }
        }
        sendAccessibilityEvent(32);
    }

    public void updateObscuredViewVisibility() {
        int i;
        int i2;
        int i3;
        int i4;
        if (getChildCount() != 0) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            View view = this.mSlideableView;
            int i5 = 0;
            if (view == null || !hasOpaqueBackground(view)) {
                i4 = 0;
                i3 = 0;
                i2 = 0;
                i = 0;
            } else {
                i4 = this.mSlideableView.getLeft();
                i3 = this.mSlideableView.getRight();
                i2 = this.mSlideableView.getTop();
                i = this.mSlideableView.getBottom();
            }
            View childAt = getChildAt(0);
            int max = Math.max(paddingLeft, childAt.getLeft());
            int max2 = Math.max(paddingTop, childAt.getTop());
            int min = Math.min(width, childAt.getRight());
            int min2 = Math.min(height, childAt.getBottom());
            if (max >= i4 && max2 >= i2 && min <= i3 && min2 <= i) {
                i5 = 4;
            }
            childAt.setVisibility(i5);
        }
    }

    public void setAllChildrenVisible() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }


    private static boolean hasOpaqueBackground(View view) {
        Drawable background = view.getBackground();
        if (background == null || background.getOpacity() != -1) {
            return false;
        }
        return DEFAULT_CLIP_PANEL_FLAG;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = DEFAULT_CLIP_PANEL_FLAG;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = DEFAULT_CLIP_PANEL_FLAG;
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode != 1073741824 && mode != Integer.MIN_VALUE) {
            throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
        } else if (mode2 == 1073741824 || mode2 == Integer.MIN_VALUE) {
            int childCount = getChildCount();
            if (childCount == 2) {
                this.mMainView = getChildAt(0);
                View childAt = getChildAt(1);
                this.mSlideableView = childAt;
                if (this.mDragView == null) {
                    setDragView(childAt);
                }
                if (this.mSlideableView.getVisibility() != 0) {
                    this.mSlideState = PanelState.HIDDEN;
                }
                int paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
                int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
                for (int i7 = 0; i7 < childCount; i7++) {
                    View childAt2 = getChildAt(i7);
                    LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != 8 || i7 != 0) {
                        if (childAt2 == this.mMainView) {
                            i4 = (this.mOverlayContent || this.mSlideState == PanelState.HIDDEN) ? paddingTop : paddingTop - this.mPanelHeight;
                            i3 = paddingLeft - (layoutParams.leftMargin + layoutParams.rightMargin);
                        } else {
                            i4 = childAt2 == this.mSlideableView ? paddingTop - layoutParams.topMargin : paddingTop;
                            i3 = paddingLeft;
                        }
                        if (layoutParams.width == -2) {
                            i5 = MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
                        } else if (layoutParams.width == -1) {
                            i5 = MeasureSpec.makeMeasureSpec(i3, 1073741824);
                        } else {
                            i5 = MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
                        }
                        if (layoutParams.height == -2) {
                            i6 = MeasureSpec.makeMeasureSpec(i4, Integer.MIN_VALUE);
                        } else {
                            if (layoutParams.weight > 0.0f && layoutParams.weight < 1.0f) {
                                i4 = (int) (((float) i4) * layoutParams.weight);
                            } else if (layoutParams.height != -1) {
                                i4 = layoutParams.height;
                            }
                            i6 = MeasureSpec.makeMeasureSpec(i4, 1073741824);
                        }
                        childAt2.measure(i5, i6);
                        View view = this.mSlideableView;
                        if (childAt2 == view) {
                            this.mSlideRange = view.getMeasuredHeight() - this.mPanelHeight;
                        }
                    }
                }
                setMeasuredDimension(size, size2);
                return;
            }
            throw new IllegalStateException("Sliding up panel layout must have exactly 2 children!");
        } else {
            throw new IllegalStateException("Height must have an exact value or MATCH_PARENT");
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (this.mFirstLayout) {
            int i5 = AnonymousClass2.f389xe2f6554b[this.mSlideState.ordinal()];
            if (i5 == 1) {
                this.mSlideOffset = 1.0f;
            } else if (i5 == 2) {
                this.mSlideOffset = this.mAnchorPoint;
            } else if (i5 != 3) {
                this.mSlideOffset = 0.0f;
            } else {
                this.mSlideOffset = computeSlideOffset(computePanelTopPosition(0.0f) + (this.mIsSlidingUp ? this.mPanelHeight : -this.mPanelHeight));
            }
        }
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() != 8 || (i6 != 0 && !this.mFirstLayout)) {
                int measuredHeight = childAt.getMeasuredHeight();
                int computePanelTopPosition = childAt == this.mSlideableView ? computePanelTopPosition(this.mSlideOffset) : paddingTop;
                if (!this.mIsSlidingUp && childAt == this.mMainView && !this.mOverlayContent) {
                    computePanelTopPosition = computePanelTopPosition(this.mSlideOffset) + this.mSlideableView.getMeasuredHeight();
                }
                int i7 = layoutParams.leftMargin + paddingLeft;
                childAt.layout(i7, computePanelTopPosition, childAt.getMeasuredWidth() + i7, measuredHeight + computePanelTopPosition);
            }
        }
        if (this.mFirstLayout) {
            updateObscuredViewVisibility();
        }
        applyParallaxForCurrentSlideOffset();
        this.mFirstLayout = false;
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i2 != i4) {
            this.mFirstLayout = DEFAULT_CLIP_PANEL_FLAG;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mIsScrollableViewHandlingTouch || !isTouchEnabled()) {
            this.mDragHelper.abort();
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float abs = Math.abs(x - this.mInitialMotionX);
        float abs2 = Math.abs(y - this.mInitialMotionY);
        int touchSlop = this.mDragHelper.getTouchSlop();
        if (actionMasked == 0) {
            this.mIsUnableToDrag = false;
            this.mInitialMotionX = x;
            this.mInitialMotionY = y;
            if (!isViewUnder(this.mDragView, (int) x, (int) y)) {
                this.mDragHelper.cancel();
                this.mIsUnableToDrag = DEFAULT_CLIP_PANEL_FLAG;
                return false;
            }
        } else if (actionMasked != 1 && actionMasked == 2 && abs2 > ((float) touchSlop) && abs > abs2) {
            this.mDragHelper.cancel();
            this.mIsUnableToDrag = DEFAULT_CLIP_PANEL_FLAG;
            return false;
        } else if (this.mDragHelper.isDragging()) {
            this.mDragHelper.processTouchEvent(motionEvent);
            return DEFAULT_CLIP_PANEL_FLAG;
        } else {
            float f = (float) touchSlop;
            if (abs2 <= f && abs <= f && this.mSlideOffset > 0.0f && !isViewUnder(this.mSlideableView, (int) this.mInitialMotionX, (int) this.mInitialMotionY) && this.mFadeOnClickListener != null) {
                playSoundEffect(0);
                this.mFadeOnClickListener.onClick(this);
                return DEFAULT_CLIP_PANEL_FLAG;
            }
        }
        return this.mDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || !isTouchEnabled()) {
            return super.onTouchEvent(motionEvent);
        }
        try {
            this.mDragHelper.processTouchEvent(motionEvent);
            return DEFAULT_CLIP_PANEL_FLAG;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (!isEnabled() || !isTouchEnabled() || (this.mIsUnableToDrag && actionMasked != 0)) {
            this.mDragHelper.abort();
            return super.dispatchTouchEvent(motionEvent);
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (actionMasked == 0) {
            this.mIsScrollableViewHandlingTouch = false;
            this.mPrevMotionX = x;
            this.mPrevMotionY = y;
        } else if (actionMasked == 2) {
            float f = y - this.mPrevMotionY;
            this.mPrevMotionX = x;
            this.mPrevMotionY = y;
            if (Math.abs(x - x) > Math.abs(f)) {
                return super.dispatchTouchEvent(motionEvent);
            }
            if (!isViewUnder(this.mScrollableView, (int) this.mInitialMotionX, (int) this.mInitialMotionY)) {
                return super.dispatchTouchEvent(motionEvent);
            }
            boolean z = this.mIsSlidingUp;
            int i = -1;
            if (((float) (z ? 1 : -1)) * f <= 0.0f) {
                if (z) {
                    i = 1;
                }
                if (f * ((float) i) < 0.0f) {
                    if (this.mSlideOffset < 1.0f) {
                        this.mIsScrollableViewHandlingTouch = false;
                        return onTouchEvent(motionEvent);
                    }
                    if (!this.mIsScrollableViewHandlingTouch && this.mDragHelper.isDragging()) {
                        this.mDragHelper.cancel();
                        motionEvent.setAction(0);
                    }
                    this.mIsScrollableViewHandlingTouch = DEFAULT_CLIP_PANEL_FLAG;
                    return super.dispatchTouchEvent(motionEvent);
                }
            } else if (this.mCallLocatorScrollableViewHelper.getScrollableViewScrollPosition(this.mScrollableView, z) > 0) {
                this.mIsScrollableViewHandlingTouch = DEFAULT_CLIP_PANEL_FLAG;
                return super.dispatchTouchEvent(motionEvent);
            } else {
                if (this.mIsScrollableViewHandlingTouch) {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.setAction(3);
                    super.dispatchTouchEvent(obtain);
                    obtain.recycle();
                    motionEvent.setAction(0);
                }
                this.mIsScrollableViewHandlingTouch = false;
                return onTouchEvent(motionEvent);
            }
        } else if (actionMasked == 1 && this.mIsScrollableViewHandlingTouch) {
            this.mDragHelper.setDragState(0);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean isViewUnder(View view, int i, int i2) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int[] iArr2 = new int[2];
        getLocationOnScreen(iArr2);
        int i3 = iArr2[0] + i;
        int i4 = iArr2[1] + i2;
        if (i3 < iArr[0] || i3 >= iArr[0] + view.getWidth() || i4 < iArr[1] || i4 >= iArr[1] + view.getHeight()) {
            return false;
        }
        return DEFAULT_CLIP_PANEL_FLAG;
    }

    public int computePanelTopPosition(float f) {
        View view = this.mSlideableView;
        int measuredHeight = view != null ? view.getMeasuredHeight() : 0;
        int i = (int) (f * ((float) this.mSlideRange));
        if (this.mIsSlidingUp) {
            return ((getMeasuredHeight() - getPaddingBottom()) - this.mPanelHeight) - i;
        }
        return (getPaddingTop() - measuredHeight) + this.mPanelHeight + i;
    }

    public float computeSlideOffset(int i) {
        int computePanelTopPosition = computePanelTopPosition(0.0f);
        return (this.mIsSlidingUp ? (float) (computePanelTopPosition - i) : (float) (i - computePanelTopPosition)) / ((float) this.mSlideRange);
    }

    public PanelState getPanelState() {
        return this.mSlideState;
    }

    public void setPanelState(PanelState panelState) {
        PanelState panelState2;
        if (this.mDragHelper.getViewDragState() == 2) {
            Log.d(TAG, "View is settling. Aborting animation.");
            this.mDragHelper.abort();
        }
        if (panelState == null || panelState == PanelState.DRAGGING) {
            throw new IllegalArgumentException("Panel state cannot be null or DRAGGING.");
        } else if (!isEnabled()) {
        } else {
            if ((this.mFirstLayout || this.mSlideableView != null) && panelState != (panelState2 = this.mSlideState) && panelState2 != PanelState.DRAGGING) {
                if (this.mFirstLayout) {
                    setPanelStateInternal(panelState);
                    return;
                }
                if (this.mSlideState == PanelState.HIDDEN) {
                    this.mSlideableView.setVisibility(0);
                    requestLayout();
                }
                int i = AnonymousClass2.f389xe2f6554b[panelState.ordinal()];
                if (i == 1) {
                    smoothSlideTo(1.0f, 0);
                } else if (i == 2) {
                    smoothSlideTo(this.mAnchorPoint, 0);
                } else if (i == 3) {
                    smoothSlideTo(computeSlideOffset(computePanelTopPosition(0.0f) + (this.mIsSlidingUp ? this.mPanelHeight : -this.mPanelHeight)), 0);
                } else if (i == 4) {
                    smoothSlideTo(0.0f, 0);
                }
            }
        }
    }

    public void setPanelStateInternal(PanelState panelState) {
        PanelState panelState2 = this.mSlideState;
        if (panelState2 != panelState) {
            this.mSlideState = panelState;
            dispatchOnPanelStateChanged(this, panelState2, panelState);
        }
    }

    public void applyParallaxForCurrentSlideOffset() {
        if (this.mParallaxOffset > 0) {
            ViewCompat.setTranslationY(this.mMainView, (float) getCurrentParallaxOffset());
        }
    }

    public void onPanelDragged(int i) {
        if (this.mSlideState != PanelState.DRAGGING) {
            this.mLastNotDraggingSlideState = this.mSlideState;
        }
        setPanelStateInternal(PanelState.DRAGGING);
        this.mSlideOffset = computeSlideOffset(i);
        applyParallaxForCurrentSlideOffset();
        dispatchOnPanelSlide(this.mSlideableView);
        LayoutParams layoutParams = (LayoutParams) this.mMainView.getLayoutParams();
        int height = ((getHeight() - getPaddingBottom()) - getPaddingTop()) - this.mPanelHeight;
        if (this.mSlideOffset <= 0.0f && !this.mOverlayContent) {
            layoutParams.height = this.mIsSlidingUp ? i - getPaddingBottom() : ((getHeight() - getPaddingBottom()) - this.mSlideableView.getMeasuredHeight()) - i;
            if (layoutParams.height == height) {
                layoutParams.height = -1;
            }
            this.mMainView.requestLayout();
        } else if (layoutParams.height != -1 && !this.mOverlayContent) {
            layoutParams.height = -1;
            this.mMainView.requestLayout();
        }
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        boolean z;
        int save = canvas.save();
        View view2 = this.mSlideableView;
        if (view2 == null || view2 == view) {
            z = super.drawChild(canvas, view, j);
        } else {
            canvas.getClipBounds(this.mTmpRect);
            if (!this.mOverlayContent) {
                if (this.mIsSlidingUp) {
                    Rect rect = this.mTmpRect;
                    rect.bottom = Math.min(rect.bottom, this.mSlideableView.getTop());
                } else {
                    Rect rect2 = this.mTmpRect;
                    rect2.top = Math.max(rect2.top, this.mSlideableView.getBottom());
                }
            }
            if (this.mClipPanel) {
                canvas.clipRect(this.mTmpRect);
            }
            z = super.drawChild(canvas, view, j);
            int i = this.mCoveredFadeColor;
            if (i != 0) {
                float f = this.mSlideOffset;
                if (f > 0.0f) {
                    this.mCoveredFadePaint.setColor((i & ViewCompat.MEASURED_SIZE_MASK) | (((int) (((float) ((-16777216 & i) >>> 24)) * f)) << 24));
                    canvas.drawRect(this.mTmpRect, this.mCoveredFadePaint);
                }
            }
        }
        canvas.restoreToCount(save);
        return z;
    }

    public boolean smoothSlideTo(float f, int i) {
        if (!isEnabled() || this.mSlideableView == null) {
            return false;
        }
        int computePanelTopPosition = computePanelTopPosition(f);
        ViewDragHelper viewDragHelper = this.mDragHelper;
        View view = this.mSlideableView;
        if (!viewDragHelper.smoothSlideViewTo(view, view.getLeft(), computePanelTopPosition)) {
            return false;
        }
        setAllChildrenVisible();
        ViewCompat.postInvalidateOnAnimation(this);
        return DEFAULT_CLIP_PANEL_FLAG;
    }

    public void computeScroll() {
        ViewDragHelper viewDragHelper = this.mDragHelper;
        if (viewDragHelper != null && viewDragHelper.continueSettling(DEFAULT_CLIP_PANEL_FLAG)) {
            if (!isEnabled()) {
                this.mDragHelper.abort();
            } else {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }
    }

    public void draw(Canvas canvas) {
        View view;
        int i;
        int i2;
        super.draw(canvas);
        if (this.mShadowDrawable != null && (view = this.mSlideableView) != null) {
            int right = view.getRight();
            if (this.mIsSlidingUp) {
                i2 = this.mSlideableView.getTop() - this.mShadowHeight;
                i = this.mSlideableView.getTop();
            } else {
                i2 = this.mSlideableView.getBottom();
                i = this.mSlideableView.getBottom() + this.mShadowHeight;
            }
            this.mShadowDrawable.setBounds(this.mSlideableView.getLeft(), i2, right, i);
            this.mShadowDrawable.draw(canvas);
        }
    }

    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        int i4;
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i5 = i2 + scrollX;
                if (i5 >= childAt.getLeft() && i5 < childAt.getRight() && (i4 = i3 + scrollY) >= childAt.getTop() && i4 < childAt.getBottom()) {
                    if (canScroll(childAt, DEFAULT_CLIP_PANEL_FLAG, i, i5 - childAt.getLeft(), i4 - childAt.getTop())) {
                        return DEFAULT_CLIP_PANEL_FLAG;
                    }
                }
            }
        }
        if (!z || !ViewCompat.canScrollHorizontally(view, -i)) {
            return false;
        }
        return DEFAULT_CLIP_PANEL_FLAG;
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof LayoutParams) || !super.checkLayoutParams(layoutParams)) {
            return false;
        }
        return DEFAULT_CLIP_PANEL_FLAG;
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putSerializable(SLIDING_STATE, this.mSlideState != PanelState.DRAGGING ? this.mSlideState : this.mLastNotDraggingSlideState);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            PanelState panelState = (PanelState) bundle.getSerializable(SLIDING_STATE);
            this.mSlideState = panelState;
            if (panelState == null) {
                panelState = DEFAULT_SLIDE_STATE;
            }
            this.mSlideState = panelState;
            parcelable = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(parcelable);
    }
}
