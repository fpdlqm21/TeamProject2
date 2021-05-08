package com.hansung.android.teamproject2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class WeekCalendarAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS = 1000; //얼마나 스와이핑
    public WeekCalendarAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) { //fragment 번호

        int year = 2021;
        int month = position+4;
        int Dposition = position;

        return WeekCalendarFragment.newInstance(year, month, Dposition);

    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    } //페이지 개수 반환
}
