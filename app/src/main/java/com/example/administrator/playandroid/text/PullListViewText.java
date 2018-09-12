package com.example.administrator.playandroid.text;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.util.LogUtil;

/**
 * Created by Administrator on 2018/9/12.
 */

public class PullListViewText extends ListView implements AbsListView.OnScrollListener {
    private View inflate;
    private View mHeadView;
    private ImageView ivHead;
    private ProgressBar pbHead;
    private TextView tvHead;
    private int downY;
    private int moveY;
    //定义下拉三种状态值
    private final int DOWN_REFRESH = 1;  //下拉刷新
    private final int RELEASE_REFRESH = 2; //释放刷新
    private final int REFRESHING = 3;
    private int currentState = DOWN_REFRESH;//当前状态是下拉刷新
    private int measuredHeight;
    private View mFootView;
    private ProgressBar pbFoot;
    private TextView tvFoot;
    private int footMeasuredHeight;
    //定义上拉加载状态值
    public final  int LOAD_MORE=4;
    public final  int LOAD_MORE_REFRESHING = 5;
    private  int currentState2 = LOAD_MORE;

    public PullListViewText(Context context) {
        super(context);
        initHeanView(context);
        initFootView(context);
    }

    public PullListViewText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeanView(context);
        initFootView(context);
    }

    public PullListViewText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeanView(context);
        initFootView(context);
        this.setOnScrollListener(this);
    }
    //添加头布局
    private void initHeanView(Context context) {
        mHeadView = View.inflate(context, R.layout.text_head, null);
        ivHead = mHeadView.findViewById(R.id.text_head_iv);
        pbHead = mHeadView.findViewById(R.id.text_head_pb);
        tvHead = mHeadView.findViewById(R.id.text_head_tv);
        mHeadView.measure(0,0); // text
        measuredHeight = mHeadView.getMeasuredHeight();
        mHeadView.setPadding(0,-measuredHeight,0,0);
        addHeaderView(mHeadView);
    }
    //添加脚布局

    private void initFootView(Context context){
        mFootView = View.inflate(context, R.layout.text_foot, null);
        pbFoot = mFootView.findViewById(R.id.text_foot_pb);
        tvFoot = mFootView.findViewById(R.id.text_foot_tv);
        mFootView.measure(0,0);
        footMeasuredHeight = mFootView.getMeasuredHeight();
        mFootView.setPadding(0,-footMeasuredHeight,0,0);
        addFooterView(mFootView);
    }


    /*
           * getFirstVisiblePosition()
           * 作用：判断返回的listviewItem是不是第一个
           * */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) ev.getY();
                int instate = moveY - downY;
                LogUtil.e("instate = "+instate);
                if(instate > 0 && getFirstVisiblePosition() == 0 && currentState != REFRESHING){
                    int paddingTop = -measuredHeight + instate;
                    LogUtil.e("paddingTop = "+paddingTop);
                    if(paddingTop < 0 && currentState != DOWN_REFRESH){
                        currentState = DOWN_REFRESH;
                        tvHead.setText("下拉刷新");
                    }else if(paddingTop > 0 && currentState != RELEASE_REFRESH){
                        currentState = RELEASE_REFRESH;
                        tvHead.setText("释放刷新");
                    }
                    //下拉的最大高度
                    int maxTop = getMeasuredHeight() / 3;
                    if(maxTop < paddingTop){
                        mHeadView.setPadding(0,maxTop,0,0);
                    }else{
                        mHeadView.setPadding(0,paddingTop,0,0);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (currentState == DOWN_REFRESH){
                    mHeadView.setPadding(0,-measuredHeight,0,0);
                }else if(currentState == RELEASE_REFRESH){
                    currentState = REFRESHING;
                    mHeadView.setPadding(0,0,0,0);
                    pbHead.setVisibility(View.VISIBLE);
                    ivHead.setVisibility(View.GONE);
                    tvHead.setText("刷新中 。。。 ");
                    if(mPullTextListener != null){
                        mPullTextListener.refresh();
                    }
                }
                break;
        }



        return super.onTouchEvent(ev);
    }

    /**
     * 结束刷新
     */
    public void finishRefresh() {
        mHeadView.setPadding(0, -measuredHeight, 0, 0);
        currentState = DOWN_REFRESH;
        ivHead.setVisibility(View.VISIBLE);
        pbHead.setVisibility(View.GONE);
    }
    /*
    *结束上拉加载
    */
    public void finishLoadMore(){
        mFootView.setPadding(0,-footMeasuredHeight,0,0);
        currentState2 = LOAD_MORE;
    }

    private OnPullListViewListenerText mPullTextListener;
    private OnPullListViewLoadMoreListenerText mLoadMoreTextListener;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_FLING || scrollState == SCROLL_STATE_IDLE) {
            if (getLastVisiblePosition() == getCount() - 1  &&  currentState2==LOAD_MORE) {
                currentState2= LOAD_MORE_REFRESHING;
                mFootView.setPadding(0, 0, 0, 0);
                setSelection(getCount());
                if ( mLoadMoreTextListener != null) {
                    mLoadMoreTextListener.upload();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        LogUtil.e("onScroll(...)");
    }

    //定义下拉监听事件
    public interface OnPullListViewListenerText{
        void refresh();
    }
    public void setOnRefreshListener(OnPullListViewListenerText listener){
        this.mPullTextListener = listener ;
    }

    //定义上拉加载监听事件
    public interface OnPullListViewLoadMoreListenerText{
        void upload();
    }
    public void setOnLoadMoreListener(OnPullListViewLoadMoreListenerText loadMoreListener){
        this.mLoadMoreTextListener = loadMoreListener;
    }
}
