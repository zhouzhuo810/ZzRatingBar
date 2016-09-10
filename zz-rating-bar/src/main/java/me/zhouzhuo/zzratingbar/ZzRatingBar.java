package me.zhouzhuo.zzratingbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * A powerful RatingBar.
 *
 * @author Zz
 * @time 2016/9/10 13:22
 */
public class ZzRatingBar extends LinearLayout {

    private Context context;

    private int starCount;
    private boolean isClickEnable;
    private int rating;
    private int starSize;
    private int spacing;
    private int checkedStarDrawable;
    private int normalStarDrawable;
    private OnRatingChangedListener listener;

    private static final int DEFAULT_STAR_COUNT = 5;
    private static final int DEFAULT_RATING = 0;
    private static final boolean DEFAULT_IS_SEEKABLE = true;
    private static final int DEFAULT_STAR_SIZE = 20;
    private static final int DEFAULT_SPACING = 5;
    private static final int DEFAULT_CHECKED_STAR = R.drawable.default_star_checked;
    private static final int DEFAULT_NORMAL_STAR = R.drawable.default_star_normal;

    public ZzRatingBar(Context context) {
        super(context);
        init(context, null);
    }

    public ZzRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ZzRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ZzRatingBar);
            this.isClickEnable = array.getBoolean(R.styleable.ZzRatingBar_zrb_click_enable, DEFAULT_IS_SEEKABLE);
            this.rating = array.getInteger(R.styleable.ZzRatingBar_zrb_rating, DEFAULT_RATING);
            this.starCount = array.getInteger(R.styleable.ZzRatingBar_zrb_star_count, DEFAULT_STAR_COUNT);
            this.spacing = array.getDimensionPixelSize(R.styleable.ZzRatingBar_zrb_horizontal_spacing, DEFAULT_SPACING);
            this.starSize = array.getDimensionPixelSize(R.styleable.ZzRatingBar_zrb_star_dimension, DEFAULT_STAR_SIZE);
            this.checkedStarDrawable = array.getResourceId(R.styleable.ZzRatingBar_zrb_checked_star_res, DEFAULT_CHECKED_STAR);
            this.normalStarDrawable = array.getResourceId(R.styleable.ZzRatingBar_zrb_normal_star_res, DEFAULT_NORMAL_STAR);
            array.recycle();
        } else {
            this.isClickEnable = DEFAULT_IS_SEEKABLE;
            this.rating = DEFAULT_RATING;
            this.spacing = DEFAULT_SPACING;
            this.starCount = DEFAULT_STAR_COUNT;
            this.starSize = DEFAULT_STAR_SIZE;
            this.checkedStarDrawable = DEFAULT_CHECKED_STAR;
            this.normalStarDrawable = DEFAULT_NORMAL_STAR;
        }
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        removeAllViews();
        for (int i = 0; i < starCount; i++) {
            ImageView iv = new ImageView(context);
            LayoutParams params = new LinearLayout.LayoutParams(starSize, starSize);
            if (i != 0) {
                params.leftMargin = spacing;
            }
            iv.setLayoutParams(params);
            final int finalI = i + 1;
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isClickEnable) {
                        setRating(finalI);
                    }
                }
            });
            addView(iv);
        }
        setRating(rating);
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
        initView(this.context);
    }

    public boolean isClickEnable() {
        return isClickEnable;
    }

    public void setClickEnable(boolean isClickEnable) {
        this.isClickEnable = isClickEnable;
    }

    public int getRating() {
        return rating;
    }

    /**
     * set current rating number.
     *
     * @param rating rating number.
     */
    public void setRating(int rating) {
        if (rating > starCount) {
            rating = starCount;
        }
        int lastRating = this.rating;
        this.rating = rating;
        for (int i = 0; i < getChildCount(); i++) {
            ImageView iv = (ImageView) getChildAt(i);
            if (i < rating) {
                iv.setImageResource(checkedStarDrawable);
            } else {
                iv.setImageResource(normalStarDrawable);
            }
        }
        if (listener != null && lastRating != rating) {
            listener.onRatingChanged(rating, starCount);
        }
    }

    /**
     * set rating callback
     *
     * @param listener listener
     */
    public void setOnRatingChangedListener(OnRatingChangedListener listener) {
        this.listener = listener;
    }

    public interface OnRatingChangedListener {
        void onRatingChanged(int current, int count);
    }

    public void setStarSizeInPixel(int starSize) {
        this.starSize = starSize;
        for (int i = 0; i < getChildCount(); i++) {
            ImageView iv = (ImageView) getChildAt(i);
            iv.setLayoutParams(new LayoutParams(starSize, starSize));
        }
    }

    public void setNormalStarDrawable(int normalStarDrawable) {
        this.normalStarDrawable = normalStarDrawable;
        setRating(rating);
    }

    public void setCheckedStarDrawable(int checkedStarDrawable) {
        this.checkedStarDrawable = checkedStarDrawable;
        setRating(rating);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        rating = savedState.rating;
        setRating(rating);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.rating = rating;
        return savedState;
    }

    static class SavedState extends BaseSavedState {
        int rating;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            rating = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(rating);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
