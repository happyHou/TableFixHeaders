package com.inqbarna.tablefixheaders.samples.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

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

public class LeftAdapter extends BaseAdapter {

    private Activity mActivity;
    private List<String> mdata;
    private final LayoutInflater inflater;

    public LeftAdapter(Activity mActivity, List<String> mdata) {
        this.mActivity = mActivity;
        this.mdata = mdata;
        this.inflater = LayoutInflater.from(mActivity);
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
        TextView textView = new TextView(mActivity);
        textView.setText(mdata.get(position));
        textView.setTextSize(22);
        return textView;
    }
}
