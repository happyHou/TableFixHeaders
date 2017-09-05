package com.inqbarna.tablefixheaders.samples.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.samples.R;
import com.inqbarna.tablefixheaders.samples.view.ObservableScrollView;

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
 * Created by happhou on 2017/9/5.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    private final LayoutInflater mLayoutInflater;
    private final Context context;
    private List<List<String>> mdata;
    private ObservableScrollView.ScrollViewListener mListener;
    private View mHeaderView;
    private View mFooterView;

    public RecyclerViewAdapter(Context context, List<List<String>> mdata, ObservableScrollView.ScrollViewListener listener) {
        this.context = context;
        this.mdata = mdata;
        this.mLayoutInflater=LayoutInflater.from(context);
        this.mListener=listener;
    }


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mHeaderView != null)
            return TYPE_HEADER;
        if (position == getItemCount() - 1 && mFooterView != null)
            return TYPE_FOOTER;
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_list_item, parent, false);
//        return new RecyclerViewHolder(view);
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new RecyclerViewHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new RecyclerViewHolder(mFooterView);
        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_list_item, parent, false);
        return new RecyclerViewHolder(layout);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
            List<String> strings = mdata.get(position - 1);
            holder.title1.setText(strings.get(0));
            holder.title2.setText(strings.get(1));
            holder.title3.setText(strings.get(2));
            holder.title4.setText(strings.get(3));
            holder.title5.setText(strings.get(4));
            holder.leftItem.setText(strings.get(5));
            holder.horizontalScrollView.setScrollViewListener(mListener);
        } else if (getItemViewType(position) == TYPE_HEADER) {
            return;
        } else if (getItemViewType(position) == TYPE_FOOTER) {
            return;
        }
    }


    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return mdata.size();
        } else if (mHeaderView == null && mFooterView != null) {
            return mdata.size() + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return mdata.size() + 1;
        } else {
            return mdata.size() + 2;
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title1)
        TextView title1;
        @BindView(R.id.title2)
        TextView title2;
        @BindView(R.id.title3)
        TextView title3;
        @BindView(R.id.title4)
        TextView title4;
        @BindView(R.id.title5)
        TextView title5;
        @BindView(R.id.title6)
        TextView title6;
        @BindView(R.id.hor)
        ObservableScrollView horizontalScrollView;

        @BindView(R.id.leftItem)
        TextView leftItem;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }
            if (itemView == mFooterView) {
                return;
            }
            ButterKnife.bind(this, itemView);
        }
    }
}
