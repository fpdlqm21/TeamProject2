package com.hansung.android.teamproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList daylist;
    private Calendar mCal;
    private LayoutInflater minflater;

    public GridAdapter(Context context, int activity_list_item, ArrayList<Integer> daylist)
    {
        this.daylist = daylist;
        mContext = context;
        minflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return daylist.size();
    }

    @Override
    public Object getItem(int position) {
        return daylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = minflater.inflate(R.layout.date, parent, false);
        }

        TextView day = convertView.findViewById(R.id.day); //텍스트뷰 연결
        day.setText(daylist.get(position)+""); //텍스트설정

        return convertView;
    }
}
