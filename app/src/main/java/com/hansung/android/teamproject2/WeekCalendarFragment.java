package com.hansung.android.teamproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeekCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekCalendarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    private int mParam3;
    static ArrayList daylist;
    static Calendar mCal;
    static int date = 1;
    static GridView gridView;
    static int y, m;
    static int num;
    static int last;

    public WeekCalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment WeekCalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeekCalendarFragment newInstance(int year, int month, int Dposition) {
        WeekCalendarFragment fragment = new WeekCalendarFragment();

        mCal = Calendar.getInstance();
        setDate(year, month, Dposition);

        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        args.putInt(ARG_PARAM3, Dposition);
        fragment.setArguments(args);

        return fragment;
    }

    public static void setDate(int year, int month, int Dposition) {

        daylist = new ArrayList();
        int date=1;
        last=0;
        num=0;
        int lastN = mCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        mCal.set(year, month, date); //calendar 객체의 날짜 설정

        y=year;
        m=month;

        while(num<7)
        {
            for(int i=1; i<mCal.get(Calendar.DAY_OF_WEEK); i++)
            {
                daylist.add(" "); //1일과 시작요일을 맞추기위한 공백추가
                num++;
            }

            for(int i=0; i<lastN; i++) { //달의 마지막 날짜까지 반복
                daylist.add(i+1); //리스트에 추가
                last=i+1;
                num++;
            }
        }



        for(int i=last; i<lastN; i++) { //달의 마지막 날짜까지 반복
            daylist.add(i+1); //리스트에 추가
            last=i+1;
            num++;
        }

        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
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
                Toast.makeText(getActivity(), "position= "+position,
                        Toast.LENGTH_SHORT).show();
                System.out.println("position= "+position);
            }
        });
        ((MonthViewActivity) getActivity()).getSupportActionBar().setTitle(y+"년 "+(m+1)+"월");
        return rootView;
    }
}