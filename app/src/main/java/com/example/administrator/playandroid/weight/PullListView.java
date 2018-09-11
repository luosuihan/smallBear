package com.example.administrator.playandroid.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.playandroid.R;
import com.example.administrator.playandroid.util.LogUtil;


/**
 * Created by Administrator on 2018/9/11.
 * 首页下拉刷新 。。
 */

public class PullListView extends ListView implements AbsListView.OnScrollListener{

    private View mHeadView;
    private FrameLayout flHeader;
    private ProgressBar pbHeader;
    private ImageView arrowHeader;
    private TextView stateHeader;
    private int downY;
    private int moveY;
    //headerView的3个状态
    public static final int DOWN_REFRESH = 1;
    public static final int RELEASE_REFRESH = 2;
    public static final int REFRESHING = 3;
    private int currentStateHeader = DOWN_REFRESH;
    private int measuredHeight;//头布局高度
    //footerView的2种状态
    public static final int LOAD_MORE = 4;
    public static final int LOAD_MORE_ING = 5;
    public int currentStateFooter = LOAD_MORE;

    //
    private View footerView;
    private TextView tvFooter;
    private int footerViewHeight;
    public PullListView(Context context) {
        super(context);
        initView();
    }

    public PullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PullListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initHeaderView();
        initFooterView();
    }
    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.footer_view, null);
        tvFooter = footerView.findViewById(R.id.tv_footer);

        footerView.measure(0, 0);
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);

        addFooterView(footerView);
    }
    private void initHeaderView() {
        mHeadView = View.inflate(getContext(), R.layout.home_header_view, null);
        flHeader = (FrameLayout)findViewById(R.id.fl_header);
        pbHeader = (ProgressBar)findViewById(R.id.pb_header);
        arrowHeader = (ImageView)findViewById(R.id.iv_arrow_header);
        stateHeader = (TextView)findViewById(R.id.tv_state_header);
        mHeadView.measure(0, 0);
        measuredHeight = mHeadView.getMeasuredHeight();
        LogUtil.e("measuredHeight = "+ measuredHeight);
        mHeadView.setPadding(0,-measuredHeight,0,0);
        addHeaderView(mHeadView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                LogUtil.e("downY = "+downY);
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) ev.getY();
                int istace = moveY - downY;
                LogUtil.e("istace = "+istace);
                if (istace > 0 && getFirstVisiblePosition() == 0 && currentStateHeader != REFRESHING){

                    int paddingTop = -measuredHeight + istace;
                    LogUtil.e("getFirstVisiblePosition() = "+getFirstVisiblePosition()+"  paddingTop = "+paddingTop);
                    if(paddingTop < 0){
                        currentStateHeader = DOWN_REFRESH ;
                        stateHeader.setText("下拉刷新");
                    }else if(paddingTop > 0){
                        currentStateHeader = RELEASE_REFRESH;
                        stateHeader.setText("释放刷新");
                    }
                    if (paddingTop > 3 * measuredHeight) {
                        mHeadView.setPadding(0, 3 * measuredHeight, 0, 0);
                    } else {
                        mHeadView.setPadding(0, paddingTop, 0, 0);
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(currentStateHeader == RELEASE_REFRESH){
                    currentStateHeader = REFRESHING ;
                    mHeadView.setPadding(0,0,0,0);
                    stateHeader.setText("刷新中");
                    if(mOnRefreshListener !=null){
                        mOnRefreshListener.refresh();
                    }
                }else if(currentStateHeader == DOWN_REFRESH){
                    mHeadView.setPadding(0, -measuredHeight, 0, 0);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void initView(){

    }
    /**
     * 结束下拉刷新
     */
    public void finishRefresh() {
        mHeadView.setPadding(0, -measuredHeight, 0, 0);
        currentStateHeader = DOWN_REFRESH;
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_FLING || scrollState == SCROLL_STATE_IDLE){
            if (scrollState == SCROLL_STATE_FLING || scrollState == SCROLL_STATE_IDLE) {
                if (getLastVisiblePosition() == getCount() - 1 && currentStateFooter == LOAD_MORE) {
                    currentStateFooter = LOAD_MORE_ING;
                    footerView.setPadding(0, 0, 0, 0);
                    setSelection(getCount());
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.loadMore();
                    }
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    //定义下拉刷新接口
    public interface OnRefreshListener{
        void refresh();
    }
    private OnRefreshListener mOnRefreshListener;
    public void setOnRefreshListener(OnRefreshListener listener){
        this.mOnRefreshListener = listener ;
    }
    /**
     * 加载更多回调
     */
    public interface OnLoadMoreListener {
        void loadMore();
    }

    private OnLoadMoreListener onLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void finishLoadMore() {
        currentStateFooter = LOAD_MORE;
        footerView.setPadding(0, -footerViewHeight, 0, 0);
    }
}
