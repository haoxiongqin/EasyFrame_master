package com.wujing.jframe.adapter;

import android.animation.Animator;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.wujing.jframe.adapter.animation.AlphaInAnimation;
import com.wujing.jframe.adapter.animation.BaseAnimation;
import com.wujing.jframe.adapter.animation.NewsPager;
import com.wujing.jframe.adapter.animation.ScaleInAnimation;
import com.wujing.jframe.adapter.animation.SlideInBottomAnimation;
import com.wujing.jframe.adapter.animation.SlideInLeftAnimation;
import com.wujing.jframe.adapter.animation.SlideInRightAnimation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    //Animation
    /**
     * Use with {@link #openLoadAnimation}
     */
    public static final int ALPHAIN = 0x00000001;
    /**
     * Use with {@link #openLoadAnimation}
     */
    public static final int SCALEIN = 0x00000002;
    /**
     * Use with {@link #openLoadAnimation}
     */
    public static final int SLIDEIN_BOTTOM = 0x00000003;
    /**
     * Use with {@link #openLoadAnimation}
     */
    public static final int SLIDEIN_LEFT = 0x00000004;

    /**
     * Use with {@link #openLoadAnimation}
     */
    public static final int SLIDEIN_RIGHT = 0x00000005;
    public static final int NewsPagers = 0x00000006;

    public static final int HEADER_VIEW = 0x00000111;
    public static final int LOADING_VIEW = 0x00000222;
    public static final int FOOTER_VIEW = 0x00000333;
    public static final int EMPTY_VIEW = 0x00000555;
    @IntDef({ALPHAIN, SCALEIN, SLIDEIN_BOTTOM, SLIDEIN_LEFT, SLIDEIN_RIGHT,NewsPagers})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationType {
    }

    private boolean mFirstOnlyEnable = true;
    private boolean mOpenAnimationEnable = false;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mDuration = 300;
    private int mLastPosition = -1;

    private BaseAnimation mCustomAnimation;
    private BaseAnimation mSelectAnimation = new AlphaInAnimation();

    protected List<T> dataLists;

    public BaseRecyclerViewAdapter(){
        this(new ArrayList<T>());
    }

    public BaseRecyclerViewAdapter(@NonNull List<T> dataLists){
        this.dataLists = dataLists;
    }

    /**
     * 获取指定位置的数据
     * @param position
     * @return
     */
    public T getItem(int position){
        return dataLists.get(position);
    }

    /**
     * 获取数据集合
     * @return
     */
    public List<T> getDataLists(){
        return dataLists;
    }

    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（会清空以前的集合数据）
     * @param datas
     */
    public void setDataLists(List<T> datas) {
        dataLists.clear();
        if (datas != null && !datas.isEmpty()) {
            dataLists.addAll(datas);
        }
        notifyDataSetChanged();
    }
    /**
     * 添加数据条目
     *
     * @param data
     */
    public void add(T data) {
        add(dataLists.indexOf(data),data);
    }
    /**
     * 在指定位置添加数据条目
     *
     * @param position
     * @param data
     */
    public void add(int position, T data) {
        dataLists.add(position,data);
        notifyItemInserted(position);
    }
    /**
     * 添加数据条目集合
     *
     * @param datas
     */
    public void addAll(List<T> datas) {
        dataLists.addAll(datas);
        notifyDataSetChanged();
    }
    /**
     * 在指定位置添加数据条目集合
     *
     * @param position
     * @param datas
     */
    public void addAll(int position,List<T> datas) {
        dataLists.addAll(position,datas);
        notifyDataSetChanged();
    }
    /**
     * 删除指定索引数据条目
     *
     * @param position
     */
    public void remove(int position) {
        dataLists.remove(position);
        notifyItemRemoved(position);
    }
    /**
     * 删除指定数据条目
     *
     * @param data
     */
    public void remove(T data) {
        remove(dataLists.indexOf(data));
    }


    /**
     * 替换指定索引的数据条目
     *
     * @param position
     * @param newData
     */
    public void replace(int position, T newData) {
        dataLists.set(position, newData);
        notifyItemChanged(position);
    }

    /**
     * 替换指定数据条目
     *
     * @param oldData
     * @param newData
     */
    public void replace(T oldData, T newData) {
        replace(dataLists.indexOf(oldData), newData);
    }

    /**
     * 交换两个数据条目的位置
     *
     * @param fromPosition
     * @param toPosition
     */
    public void move(int fromPosition, int toPosition) {
        Collections.swap(dataLists, fromPosition, toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    /**
     * 清空
     */
    public void clear() {
        dataLists.clear();
        notifyDataSetChanged();
    }

    /**
     * add animation when you want to show time
     *
     * @param holder
     */
    private void addAnimation(RecyclerView.ViewHolder holder) {
        if (mOpenAnimationEnable) {
            if (!mFirstOnlyEnable || holder.getLayoutPosition() > mLastPosition) {
                BaseAnimation animation = null;
                if (mCustomAnimation != null) {
                    animation = mCustomAnimation;
                } else {
                    animation = mSelectAnimation;
                }
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    startAnim(anim, holder.getLayoutPosition());
                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }

    /**
     * set anim to start when loading
     *
     * @param anim
     * @param index
     */
    protected void startAnim(Animator anim, int index) {
        anim.setDuration(mDuration).start();
        anim.setInterpolator(mInterpolator);
    }
    /**
     * Set the view animation type.
     *
     * @param animationType One of {@link #ALPHAIN}, {@link #SCALEIN}, {@link #SLIDEIN_BOTTOM},
     *                      {@link #SLIDEIN_LEFT}, {@link #SLIDEIN_RIGHT}.
     */
    public void openLoadAnimation(@AnimationType int animationType) {
        this.mOpenAnimationEnable = true;
        mCustomAnimation = null;
        switch (animationType) {
            case ALPHAIN:
                mSelectAnimation = new AlphaInAnimation();
                break;
            case SCALEIN:
                mSelectAnimation = new ScaleInAnimation();
                break;
            case SLIDEIN_BOTTOM:
                mSelectAnimation = new SlideInBottomAnimation();
                break;
            case SLIDEIN_LEFT:
                mSelectAnimation = new SlideInLeftAnimation();
                break;
            case SLIDEIN_RIGHT:
                mSelectAnimation = new SlideInRightAnimation();
                break;
            case NewsPagers:
                mSelectAnimation = new NewsPager();
                break;
            default:
                break;
        }
    }
    /**
     * Called when a view created by this adapter has been attached to a window.
     * simple to solve item will layout using all
     * {@link #setFullSpan(RecyclerView.ViewHolder)}
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        int type = holder.getItemViewType();
        if (type == EMPTY_VIEW || type == HEADER_VIEW || type == FOOTER_VIEW || type == LOADING_VIEW) {
            setFullSpan(holder);
        } else {
            addAnimation(holder);
        }
    }
    protected void setFullSpan(RecyclerView.ViewHolder holder) {
        if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder
                    .itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }
    /**
     * Set Custom ObjectAnimator
     *
     * @param animation ObjectAnimator
     */
    public void openLoadAnimation(BaseAnimation animation) {
        this.mOpenAnimationEnable = true;
        this.mCustomAnimation = animation;
    }

    /**
     * To open the animation when loading
     */
    public void openLoadAnimation() {
        this.mOpenAnimationEnable = true;
    }

    /**
     * {@link #addAnimation(RecyclerView.ViewHolder)}
     *
     * @param firstOnly true just show anim when first loading false show anim when load the data every time
     */
    public void isFirstOnly(boolean firstOnly) {
        this.mFirstOnlyEnable = firstOnly;
    }
}
