package com.inqbarna.tablefixheaders.samples.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.samples.R;

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
 */

public class RightAdapter extends BaseAdapter {

    private Activity mActivity;
    private List<List<String>> mdata;
    private final LayoutInflater inflater;

    public RightAdapter(Activity mActivity, List<List<String>> mdata) {
        this.mdata = mdata;
        inflater = LayoutInflater.from(mActivity);
    }

    @Override
    public int getCount() {
        return mdata == null ? 0 : mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.right_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        List<String> strings = mdata.get(position);
        holder.title1.setText(strings.get(0));
        holder.title2.setText(strings.get(1));
        holder.title3.setText(strings.get(2));
        holder.title4.setText(strings.get(3));
        holder.title5.setText(strings.get(4));
        return convertView;
    }


    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
