package com.inqbarna.tablefixheaders.samples;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.inqbarna.tablefixheaders.samples.adapters.RecyclerViewAdapter;
import com.inqbarna.tablefixheaders.samples.view.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGAStickyNavLayout;


/**
 * ===============================
 * 版本：1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史
 * <p/>
 * Created by happhou on 2017/9/4.
 * https://stackoverflow.com/questions/12342419/android-scrolling-2-listviews-together
 * https://stackoverflow.com/questions/6509068/synchronize-two-horizontal-scroll-view-android
 * https://stackoverflow.com/questions/7870546/synchronizing-two-horizontal-scroll-views-in-android
 * https://stackoverflow.com/questions/13130389/how-to-get-items-currently-displayed-in-adapterview
 * http://www.jianshu.com/p/991062d964cf
 */

public class DoubleListActivity extends Activity implements ObservableScrollView.ScrollViewListener, BGARefreshLayout.BGARefreshLayoutDelegate {
    private static final String TAG = "DoubleListActivity";
    private List<List<String>> mrightData = new ArrayList<>();
    @BindView(R.id.right_list)
    RecyclerView mListView;
    @BindView(R.id.rl_modulename_refresh)
    BGARefreshLayout bgaRefreshLayout;

    private RecyclerViewAdapter adapter;
    private int x;
    private int y;

    private int visibleCount;
    private LinearLayoutManager layoutManager;


    {
        for (int i = 0; i < 20; i++) {
            List<String> strings = new ArrayList<>();
            for (int i1 = 0; i1 < 6; i1++) {
                if (i1==0) {
                    strings.add("left" + i + "的" + i1);
                }else {
                    strings.add("right" + i + "的" + i1);
                }
            }
            mrightData.add(strings);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_list);
        ButterKnife.bind(this);
        this.initListView();
        initRefreshLayout();
        this.synScrollListView();
    }

    private void initRefreshLayout() {
        bgaRefreshLayout.setDelegate(this);


        bgaRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(this,true));


    }

    private void initListView() {
        mListView.setLayoutManager(new LinearLayoutManager(this));//这里
        layoutManager = (LinearLayoutManager) mListView.getLayoutManager();
        adapter=new RecyclerViewAdapter(this,mrightData,this);
        mListView.setAdapter(adapter);
        setHeaderView(mListView);
        setFooterView(mListView);

    }

    private void synScrollListView() {
        mListView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                    visibleCount=layoutManager.findLastVisibleItemPosition()-layoutManager.findFirstVisibleItemPosition();
            }
        });
    }

    private void syncHorizontalScrollView(){
        for (int count = visibleCount; count >=0; count--) {
            ObservableScrollView viewById = (ObservableScrollView) mListView.getChildAt(count).findViewById(R.id.hor);
            if (viewById!=null) {
                viewById.scrollTo(x,y);
            }
        }

    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        this.x = x;
        this.y = y;

        syncHorizontalScrollView();

    }
    private void setHeaderView(RecyclerView view){
        View header = LayoutInflater.from(this).inflate(R.layout.header_layout, view, false);
         adapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view){
        View footer = LayoutInflater.from(this).inflate(R.layout.footer_layout, view, false);
        adapter.setFooterView(footer);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
