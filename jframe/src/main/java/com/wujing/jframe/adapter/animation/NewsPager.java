package com.wujing.jframe.adapter.animation;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

public class NewsPager implements BaseAnimation{
    private  int DURATION = 1 * 700;
    public void setDURATION(int DURATION) {
        this.DURATION = DURATION;
    }
    @Override
    public Animator[] getAnimators(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "rotation", 1080,720,360,0).setDuration(DURATION);
        ObjectAnimator scaleY= ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(DURATION*3/2);
        ObjectAnimator scaleZ= ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.5f, 1).setDuration(DURATION);
        ObjectAnimator scaleA= ObjectAnimator.ofFloat(view,"scaleY",0.1f,0.5f,1).setDuration(DURATION);
        return new Animator[]{scaleX,scaleY,scaleZ,scaleA};
    }
}
