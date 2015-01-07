package com.tonyjs.hashtagram.ui.widget;

import android.content.Context;
import android.graphics.*;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.android.volley.toolbox.NetworkImageView;
import com.tonyjs.hashtagram.io.model.insta.InstaImageSpec;
import com.tonyjs.hashtagram.io.request.volley.RequestQueueManager;
import com.tonyjs.hashtagram.util.UiUtils;

/**
 * Created by orcpark on 2014. 7. 1..
 */
public class GradientNetworkImageView extends NetworkImageView {

    private static final int MAX_HEIGHT = 360;
    public static final String GRADIENT_COLOR = "#991d1d1d";

    private int mMaxHeight = UiUtils.getDPFromPixelSize(getContext(), MAX_HEIGHT);

    public GradientNetworkImageView(Context context) {
        super(context);
    }

    public GradientNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private int mWidth;
//    private boolean mIsMeasured = false;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        Log.e("jsp", "onMeasure");
//        mWidth = MeasureSpec.getSize(widthMeasureSpec);
//        if (mSpec != null) {
////        if (mSpec != null && !mIsMeasured) {
//            setSize(mWidth, mSpec);
////            mIsMeasured = true;
//        } else {
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        }
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    private void setSize(int width, InstaImageSpec spec) {
//        Log.d("jsp", "spec Height = " + spec.getHeight());
        int height = getResizeHeight(width, spec);
        if (height > mMaxHeight) {
            height = mMaxHeight;
        }
//        Log.e("jsp", "height = " + height);
        setMeasuredDimension(width, height);
    }

    protected int getResizeHeight(int width, InstaImageSpec spec) {
        if (width == 0 || spec == null) {
            return 0;
        }

        int specWidth = spec.getWidth();
        int specHeight = spec.getHeight();

//        Log.e("jsp", "specWidth = " + specWidth);
//        Log.e("jsp", "specHeight = " + specHeight);

        if (specWidth == 0 || specHeight == 0) {
            return 0;
        }

        float ratio = specHeight / (float) specWidth;

        int newHeight = (int) (width * ratio);
//        Log.e("jsp", "newHeight - " + newHeight);
        return newHeight;
    }

    private InstaImageSpec mSpec;

    public void setImageSpec(InstaImageSpec imageSpec) {
        if (imageSpec == null) {
            return;
        }

        mSpec = imageSpec;

        String imageUrl = imageSpec.getUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            setImageUrl(imageUrl,
                    RequestQueueManager.getInstance(getContext()).getImageLoader());
        }

//        if (mWidth > 0) {
//            setSize(mWidth, mSpec);
//        }
//        invalidate();
//        measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.i("jsp", "onDraw - " + mShowingNull);
        if (getDrawable() != null) {
            Shader shader = new LinearGradient(0, 0, 0, getHeight(),
                    Color.TRANSPARENT, Color.parseColor(GRADIENT_COLOR),
                    Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            canvas.drawRect(new RectF(0, 0, getRight(), getBottom()), paint);
        } else {
            Paint paint = new Paint();
            paint.setColor(Color.TRANSPARENT);
            canvas.drawRect(new RectF(0, 0, getRight(), getBottom()), paint);
        }
    }

    private boolean mShowingNull = false;
    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mShowingNull = bm != null;

//        invalidate();
    }
}