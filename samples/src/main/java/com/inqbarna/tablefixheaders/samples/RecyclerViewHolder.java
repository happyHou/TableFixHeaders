package com.inqbarna.tablefixheaders.samples;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.samples.view.ObservableScrollView;

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
 * Created by happhou on 2017/9/5.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title1)
    public TextView title1;
    @BindView(R.id.title2)
    public TextView title2;
    @BindView(R.id.title3)
    public TextView title3;
    @BindView(R.id.title4)
    public TextView title4;
    @BindView(R.id.title5)
    public TextView title5;
    @BindView(R.id.title6)
    public TextView title6;
    @BindView(R.id.hor)
    public ObservableScrollView horizontalScrollView;

    @BindView(R.id.leftItem)
    public TextView leftItem;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
