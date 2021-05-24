package com.hansung.android.teamproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeekViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeekViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WeekViewFragment newInstance(String param1, String param2) { //팩토리 메소드, 객체 인스턴스를 만들어 넘겨줌
        WeekViewFragment fragment = new WeekViewFragment(); //객체 생성
        Bundle args = new Bundle(); //인자 값을 저장할 번들 객체
        args.putString(ARG_PARAM1, param1); //번들에 키, 값 설정
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args); //인자값을 저장한 번들 객체를 프래그먼트로 전달
        return fragment; //프래그먼트 반환
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //프래그먼트의 레이아웃 생성
        View rootView = inflater.inflate(R.layout.fragment_week_view, container,false);
        ViewPager2 vpPager = rootView.findViewById(R.id.WvpPager);
        FragmentStateAdapter adapter = new WeekCalendarAdapter(this);
        vpPager.setAdapter(adapter);
        vpPager.setCurrentItem(500, false);
        // Inflate the layout for this fragment
        return rootView;
    }
}