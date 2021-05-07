package com.hansung.android.teamproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class MonthViewFragment extends Fragment {

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
    public static MonthViewFragment newInstance(int index) {
        MonthViewFragment fragment = new MonthViewFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        fragment.setArguments(args);
        return fragment;
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
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_month_view, container,false);
        ViewPager2 vpPager = rootView.findViewById(R.id.MvpPager);
        FragmentStateAdapter adapter = new MonthCalendarAdapter(this);
        vpPager.setAdapter(adapter);
        // Inflate the layout for this fragment
        return rootView;
    }
}