package com.inqbarna.tablefixheaders.samples;

import android.app.Activity;
import android.os.Bundle;
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
 */

public class DoubleListActivity extends Activity {
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private List<String> mLeftData = new ArrayList<>();
    private List<List<String>> mrightData = new ArrayList<>();

    {
        for (int i = 0; i < 20; i++) {
            mLeftData.add("left" + i);

        }
    }

    {
        for (int i = 0; i < 9; i++) {
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
        leftAdapter = new LeftAdapter(this, mLeftData);
        rightAdapter = new RightAdapter(this, mrightData);
        leftList.setAdapter(leftAdapter);
        rightList.setAdapter(rightAdapter);
    }
}
