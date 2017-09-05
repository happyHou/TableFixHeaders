package com.inqbarna.tablefixheaders.samples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.inqbarna.tablefixheaders.samples.adapters.RightAdapter;
import com.inqbarna.tablefixheaders.samples.view.ObservableScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


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
 */

public class DoubleListActivity extends Activity implements ObservableScrollView.ScrollViewListener {
    private static final String TAG = "DoubleListActivity";
    private RightAdapter rightAdapter;
    private List<String> mLeftData = new ArrayList<>();
    private List<List<String>> mrightData = new ArrayList<>();
    @BindView(R.id.right_list)
    ListView rightList;
    private int x;
    private int y;

    private int visibleCount;


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
        this.synScrollListView();
    }

    private void initListView() {
        rightAdapter = new RightAdapter(this, mrightData, this);
        rightList.setAdapter(rightAdapter);
    }

    private void synScrollListView() {


        rightList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int firstVisibleRow = view.getFirstVisiblePosition();
                int lastVisibleRow = view.getLastVisiblePosition();
                visibleCount = lastVisibleRow - firstVisibleRow;
                syncHorizontalScrollView();
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });

    }

    private void syncHorizontalScrollView(){
        for (int count = visibleCount; count >=0; count--) {
            ObservableScrollView viewById = (ObservableScrollView) rightList.getChildAt(count).findViewById(R.id.hor);
            viewById.scrollTo(x,y);
        }

    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        this.x = x;
        this.y = y;
        syncHorizontalScrollView();

    }
}
