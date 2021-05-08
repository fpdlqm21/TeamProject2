package com.hansung.android.teamproject2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class MonthCalendarAdapter extends FragmentStateAdapter { //MvPager에 페이지 정보를 전달할 adapter
    private static int NUM_ITEMS = 1000;

    public MonthCalendarAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int pos = position-500; //500부터 시작으로 설정해서 계산하기 편하게
        int year = 2021;
        int month = position;

        System.out.println("year="+year+"month="+month);
        return MonthCalendarFragment.newInstance(year, month);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }

}
