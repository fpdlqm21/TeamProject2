package com.hansung.android.teamproject2;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@Link Fragment} subclass.
 * use the {@Link MonthCalendarFragment#newInstace} factory method to
 * create an intace of this fragment.
 */

public class MonthCalendarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    static ArrayList daylist;
    static Calendar mCal;
    static int date = 1;
    static GridView gridView;

    public MonthCalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instace of
     * this fragment using the procided parameters.
     *
     * @return Anew intance of fragment MonthCalendarFragment
     */

    // TODO: Rename and change types and number of parameters
    public static MonthCalendarFragment newInstance(int year, int month) {
        MonthCalendarFragment fragment = new MonthCalendarFragment();

        mCal = Calendar.getInstance();
        setDate(year, month, date);

        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        fragment.setArguments(args);
        return fragment;
    }

    public static void setDate(int year, int month, int date) {

        daylist = new ArrayList();
        mCal.set(year, month, date); //calendar 객체의 날짜 설정

        for(int i=1; i<mCal.get(Calendar.DAY_OF_WEEK); i++)
        {
            daylist.add(" "); //1일과 시작요일을 맞추기위한 공백추가
        }
        for(int i=0; i<mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) { //달의 마지막 날짜까지 반복
            daylist.add(i+1); //리스트에 추가
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_month_calendar,
                container, false);
        gridView = rootView.findViewById(R.id.gridview);
        GridAdapter adapter = new GridAdapter(getActivity(),android.R.layout.simple_list_item_1,
                daylist);
        gridView.setAdapter((GridAdapter) adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "testToast"+position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

}