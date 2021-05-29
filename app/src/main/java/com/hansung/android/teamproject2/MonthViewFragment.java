package com.hansung.android.teamproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class MonthViewFragment extends Fragment {

    static ArrayList daylist; //날짜를 담을 리스트
    static Calendar mCal; //캘린더 객체
    static int date = 1;
    static int y, m;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "index";
    private int mIndex;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MonthViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment MonthViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthViewFragment newInstance(int index) { //팩토리 메소드
        MonthViewFragment fragment = new MonthViewFragment(); //프래그먼트 객체 생성

        Bundle args = new Bundle(); //인자를 위한 번들객체
        args.putInt(ARG_PARAM1, index); //index를 담음
        fragment.setArguments(args); //프래그먼트에 번들객체 담음
        return fragment; //프래그먼트 반환
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //레이아웃 생성
        View rootView = inflater.inflate(R.layout.fragment_month_view, container,false);
        ViewPager2 vpPager = rootView.findViewById(R.id.MvpPager); //vpager2 객체 연결
        FragmentStateAdapter adapter = new MonthCalendarAdapter(this); //apdater연결
        vpPager.setAdapter(adapter);
        vpPager.setCurrentItem(120, false);
        ((MonthViewActivity) getActivity()).getSupportActionBar().setTitle(2021+"년 "+5+"월");
        vpPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position){

                if(position>=92 && position<=103)
                {
                    int year=2019;

                    int pos = (position+5)%12;
                    int month = pos;
                    if(month==0)
                        month=12;

                    ((MonthViewActivity) getActivity()).getSupportActionBar().setTitle(year+"년 "+(month)+"월");
                }

                if(position>=104 && position<=115)
                {
                    int year=2020;

                    int pos = (position+5)%12;
                    int month = pos;
                    if(month==0)
                        month=12;

                    ((MonthViewActivity) getActivity()).getSupportActionBar().setTitle(year+"년 "+(month)+"월");
                }



                if(position>=116 && position<=127)
                {
                    int year=2021;
                    int pos = (position+5)%12;
                    int month = pos;
                    if(month==0)
                        month=12;

                    ((MonthViewActivity) getActivity()).getSupportActionBar().setTitle(year+"년 "+(month)+"월");
                }


                if(position>=128 && position<=139)
                {
                    int year=2022;
                    int pos = (position+5)%12;
                    int month = pos;
                    if(month==0)
                         month=12;

                    ((MonthViewActivity) getActivity()).getSupportActionBar().setTitle(year+"년 "+(month)+"월");
                }

                if(position>=140 && position<=152)
                {
                    int year=2023;
                    int pos = (position+5)%12;
                    int month = pos;
                    if(month==0)
                        month=12;

                    ((MonthViewActivity) getActivity()).getSupportActionBar().setTitle(year+"년 "+(month)+"월");
                }










                

            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    public static void setDate(int year, int month, int date) {

        daylist = new ArrayList();
        y=year;
        m=month+4;
        mCal.set(year, m, date); //calendar 객체의 날짜 설정

        for(int i = 1; i<mCal.get(Calendar.DAY_OF_WEEK); i++)
        {
            daylist.add(" "); //1일과 시작요일을 맞추기위한 공백추가
        }
        for(int i=0; i<mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) { //달의 마지막 날짜까지 반복
            daylist.add(i+1); //리스트에 추가
        }
    }
}