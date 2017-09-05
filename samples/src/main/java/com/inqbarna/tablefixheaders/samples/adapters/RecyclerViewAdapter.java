package com.inqbarna.tablefixheaders.samples.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inqbarna.tablefixheaders.samples.R;
import com.inqbarna.tablefixheaders.samples.RecyclerViewHolder;
import com.inqbarna.tablefixheaders.samples.view.ObservableScrollView;

import java.util.List;


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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private final LayoutInflater mLayoutInflater;
    private final Context context;
    private List<List<String>> mdata;
    private ObservableScrollView.ScrollViewListener mListener;

    public RecyclerViewAdapter(Context context, List<List<String>> mdata, ObservableScrollView.ScrollViewListener listener) {
        this.context = context;
        this.mdata = mdata;
        this.mLayoutInflater=LayoutInflater.from(context);
        this.mListener=listener;
    }




    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_list_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        List<String> strings = mdata.get(position);
        holder.title1.setText(strings.get(0));
        holder.title2.setText(strings.get(1));
        holder.title3.setText(strings.get(2));
        holder.title4.setText(strings.get(3));
        holder.title5.setText(strings.get(4));
        holder.leftItem.setText(strings.get(5));
        holder.horizontalScrollView.setScrollViewListener(mListener);

        holder.title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: "+position );
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
}
