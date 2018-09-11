package com.example.administrator.playandroid.weight;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by a on 2018/3/21.
 *
 */

public class AnimationUtil {
    public static RotateAnimation UpAnim(){
        RotateAnimation animation = new RotateAnimation(0, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(500);
        return animation;
    }


    public static RotateAnimation downAnim(){
        RotateAnimation animation = new RotateAnimation(-180f, -360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(true);
        animation.setDuration(500);
        return animation;
    }
}
