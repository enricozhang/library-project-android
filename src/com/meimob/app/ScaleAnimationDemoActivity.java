package com.meimob.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class ScaleAnimationDemoActivity extends Activity implements OnClickListener{
    private ImageView top_left;
    private ImageView top_right;
    private ImageView bottom_left;
    private ImageView bottom_right;
    private ImageView center;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) { // 要使用findViewById,
        super.onCreate(savedInstanceState); // 做为使用者介面
    //    setContentView(R.layout.second);

        // 取得UI 介面中的View 物件
        // 取得View 物件后，再透过转换成实际的物件
//        top_left = (ImageView) findViewById(R.id.top_left);
//        top_right = (ImageView) findViewById(R.id.top_right);
//        bottom_left = (ImageView) findViewById(R.id.bottom_left);
//        bottom_right = (ImageView) findViewById(R.id.bottom_right);
//        center = (ImageView) findViewById(R.id.center);
        top_left.setOnClickListener(this);
        top_right.setOnClickListener(this);
        bottom_left.setOnClickListener(this);
        bottom_right.setOnClickListener(this);
        center.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//        case R.id.top_left:
//            topLeftScaleAnimation(v);
//            break;
//        case R.id.top_right:
//            topRightScaleAnimation(v);
//            break;
//        case R.id.bottom_left:
//            bottomLiftScaleAnimation(v);
//            break;
//        case R.id.bottom_right:
//            bottomRightScaleAnimation(v);
//            break;
//        case R.id.center:
//            centerScaleAnimation(v);
//
//            break;
        }
    }

    private Animation topLeftanimation;
    private boolean istopLeft = false;

    private void topLeftScaleAnimation(final View v) {

        if (!istopLeft) {

            topLeftanimation = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);

            istopLeft = true;

        } else {
            topLeftanimation = new ScaleAnimation(4.0f, 1.0f, 4.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);
            istopLeft = false;

        }

        // 动画开始到结束的执行时间(1000 = 1 秒)
        topLeftanimation.setDuration(2000);

        // 动画重复次数(-1 表示一直重复)
        // am.setRepeatCount(1);
        topLeftanimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                top_left.setEnabled(true);

            }
        });
        topLeftanimation.setFillAfter(true);
        // 图片配置动画
        top_left.setAnimation(topLeftanimation);

        // Help();
        // handler.sendEmptyMessageDelayed(topHand, 2000);
        // top_left.setLayoutParams(lp);
        // 动画开始
        topLeftanimation.startNow();

        top_left.setEnabled(false);
    }

    private static final int topHand = 6;

    private boolean isBottomLift = false;
    Animation bottomLiftScaleAnimation = null;

    private void bottomLiftScaleAnimation(View v) {

        if (!isBottomLift) {
            bottomLiftScaleAnimation = new ScaleAnimation(1.0f, 4.0f, 1.0f,
                    4.0f, Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 1.0f);
            isBottomLift = true;
        } else {
            bottomLiftScaleAnimation = new ScaleAnimation(4.0f, 1.0f, 4.0f,
                    1.0f, Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 1.0f);
            isBottomLift = false;
        }

        // 动画开始到结束的执行时间(1000 = 1 秒)
        bottomLiftScaleAnimation.setDuration(2000);

        // 动画重复次数(-1 表示一直重复)
        // bottomLiftScaleAnimation.setRepeatCount(-1);
        bottomLiftScaleAnimation.setFillAfter(true);
        // 图片配置动画
        bottom_left.setAnimation(bottomLiftScaleAnimation);
        bottomLiftScaleAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bottom_left.setEnabled(true);
            }
        });
        // 动画开始
        bottomLiftScaleAnimation.startNow();
        bottom_left.setEnabled(false);
    }

    private boolean isBottomRigth = false;
    Animation bottomRightScaleAnimation;

    private void bottomRightScaleAnimation(View v) {
        if (!isBottomRigth) {
            bottomRightScaleAnimation = new ScaleAnimation(1.0f, 4.0f, 1.0f,
                    4.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 1.0f);
            isBottomRigth = true;
        } else {
            bottomRightScaleAnimation = new ScaleAnimation(4.0f, 1.0f, 4.0f,
                    1.0f, Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 1.0f);
            isBottomRigth = false;
        }
        // 动画开始到结束的执行时间(1000 = 1 秒)
        bottomRightScaleAnimation.setDuration(2000);
        bottomRightScaleAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                bottom_right.setEnabled(true);

            }
        });

        // 动画重复次数(-1 表示一直重复)
        // bottomRightScaleAnimation.setRepeatCount(-1);
        bottomRightScaleAnimation.setFillAfter(true);
        // 图片配置动画
        bottom_right.setAnimation(bottomRightScaleAnimation);

        // 动画开始
        bottomRightScaleAnimation.startNow();

        bottom_right.setEnabled(false);
    }

    private Animation topRightScaleAnimation;
    private boolean isTopRight = false;

    private void topRightScaleAnimation(View v) {
        if (!isTopRight) {
            topRightScaleAnimation = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
                    Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);
            isTopRight = true;

        } else {
            topRightScaleAnimation = new ScaleAnimation(4.0f, 1.0f, 4.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f);
            isTopRight = false;
        }
        topRightScaleAnimation.setDuration(2000);

        // 动画重复次数(-1 表示一直重复)
        // topRightScaleAnimation.setRepeatCount(-1);

        // 图片配置动画
        top_right.setAnimation(topRightScaleAnimation);
        topRightScaleAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                top_right.setEnabled(true);

            }
        });

        // 动画开始
        topRightScaleAnimation.startNow();
        topRightScaleAnimation.setFillAfter(true);

        top_right.setEnabled(false);
    }

    private boolean isCenter = false;
    Animation centerScaleAnimation;

    private void centerScaleAnimation(View v) {
        if (!isCenter) {
            centerScaleAnimation = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            isCenter = true;
        } else {
            centerScaleAnimation = new ScaleAnimation(4.0f, 1.0f, 4.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            isCenter = false;
        }
        centerScaleAnimation.setDuration(2000);
        centerScaleAnimation.setFillAfter(true);
        // 动画重复次数(-1 表示一直重复)
        // centerScaleAnimation.setRepeatCount(-1);
        centerScaleAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                center.setEnabled(true);
                // TODO Auto-generated method stub

            }
        });
        // 图片配置动画
        center.setAnimation(centerScaleAnimation);

        // 动画开始
        centerScaleAnimation.startNow();

        center.setEnabled(false);
    }

}
