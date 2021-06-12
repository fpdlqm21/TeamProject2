package com.hansung.android.teamproject2;

//주간달력 gridview에 격자로 표시위한 adapter
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
    private String[] BOX = new String[170];

    public WeekGridAdapter(Context context, int activity_list_item, String[] box)
    {
        for(int i=0; i<168; i++) {
            this.BOX[i] = box[i];
        }
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
        box.setText(" ");
        return convertView;
    }
}
