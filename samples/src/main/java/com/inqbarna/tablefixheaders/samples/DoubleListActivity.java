package com.inqbarna.tablefixheaders.samples;

import android.app.Activity;
import android.os.Bundle;
import android.test.LoaderTestCase;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;

import com.inqbarna.tablefixheaders.samples.adapters.LeftAdapter;
import com.inqbarna.tablefixheaders.samples.adapters.RightAdapter;

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

public class DoubleListActivity extends Activity {
    private static final String TAG = "DoubleListActivity";
    private static int toucInt = 0;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private List<String> mLeftData = new ArrayList<>();
    private List<List<String>> mrightData = new ArrayList<>();
    View clickSource;
    View touchSource;
    int delaY=0;


    int offset = 0;

    {
        for (int i = 0; i < 20; i++) {
            mLeftData.add("left" + i);

        }
    }

    {
        for (int i = 0; i < 20; i++) {
            List<String> strings = new ArrayList<>();
            for (int i1 = 0; i1 < 5; i1++) {
                strings.add("右边" + i + "的" + i1);
            }
            mrightData.add(strings);
        }
    }


    @BindView(R.id.left_list)
    ListView leftList;
    @BindView(R.id.right_list)
    ListView rightList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_list);
        ButterKnife.bind(this);
        this.initListView();
        this.synScrollcListView();
    }

    private void initListView() {
        leftAdapter = new LeftAdapter(this, mLeftData);
        rightAdapter = new RightAdapter(this, mrightData);
        leftList.setAdapter(leftAdapter);
        rightList.setAdapter(rightAdapter);
    }

    private void synScrollcListView() {
        leftList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (touchSource == null)
                    touchSource = v;

                if (v == touchSource) {
                    rightList.dispatchTouchEvent(event);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        clickSource = v;
                        touchSource = null;
                    }
                }

                return false;
            }
        });
        rightList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (touchSource == null)
                    touchSource = v;

                if (v == touchSource) {
                    leftList.dispatchTouchEvent(event);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        clickSource = v;
                        touchSource = null;
                    }

                    if (event.getAction()== MotionEvent.ACTION_MOVE) {

                    }
                    if (event.getAction()== MotionEvent.ACTION_MOVE) {

                    }




                }

                return false;
            }
        });

        leftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent == clickSource) {
                    // Do something with the ListView was clicked
                    Log.e(TAG, "onItemClick: " + position);
                }
            }
        });
        leftList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view == clickSource)
                    rightList.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });


        rightList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view == clickSource)
                    leftList.setSelectionFromTop(firstVisibleItem, view.getChildAt(0).getTop() + offset);
                // TODO: 2017/9/5 偏移右侧的listview
                int firstVisibleRow = view.getFirstVisiblePosition();
                int lastVisibleRow = view.getLastVisiblePosition();

//                for (int i = firstVisibleRow; i <= lastVisibleRow; i++) {
//                    Write your code here(allocation/deallocation/store in array etc.)
//                    Log.e(TAG, "onScroll: "+view.getItemAtPosition(i) );
//                }
                int i = lastVisibleRow - firstVisibleRow;
                for (int i1 = 0; i1 < i; i1++) {
//                    ((HorizontalScrollView)view.getChildAt(i1)).
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });

    }


}
