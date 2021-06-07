package com.hansung.android.teamproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WeekGridAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater minflater;
    private ArrayList box;

    public WeekGridAdapter(Context context, int activity_list_item, ArrayList<String> box)
    {
        this.box = box;
        mContext = context;
        minflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = minflater.inflate(R.layout.weekbox, parent, false);
        }

        TextView box = convertView.findViewById(R.id.box); //텍스트뷰 연결

        return convertView;
    }
}
