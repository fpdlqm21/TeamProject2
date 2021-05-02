package com.hansung.android.teamproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MonthViewActivity extends AppCompatActivity {

    GridView gridview;
    Adapter adapter;
    ArrayList daylist;
    Calendar mCal;
    int year, month, date = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCal = Calendar.getInstance(); //Calendar 객체 생성
        Intent intent = getIntent(); //다른 액티비티에서 전달받을 인텐트 객체생성
        year= intent.getIntExtra("year", -1); //받아올 키와 값(디폴트) 설정
        month = intent.getIntExtra("month", -1);

        if(year == -1 || month == -1)
        {
            year = mCal.get(Calendar.YEAR);
            month = mCal.get(Calendar.MONTH);
        }
        setDate(year, month, date); //setDate method에 파라미터 전달
        gridview = findViewById(R.id.gridview);
        adapter = new Adapter(this, daylist);
        gridview.setAdapter(adapter); //adapter 연결

        TextView yearmonthTv = findViewById(R.id.yearmonth); //yearmonth 텍스트뷰 객체생성
        yearmonthTv.setText(year + "년" +(month+1)+"월");

        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            //다음버튼을 위한 리스너
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MonthViewActivity.class);
                if(month < 11) //해가 지나지 않을경우
                {
                    month++;
                    intent.putExtra("year", year); //intent 객체로 보낼 정보
                    intent.putExtra("month", month);
                    startActivity(intent); //Activity 실행
                }
                else //해가 지날경우
                {
                    year++;
                    month = 0;
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    startActivity(intent);
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            //이전버튼을 위한 리스너
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MonthViewActivity.class); //다음 액티비티로 전달할 intent 객체생성

                if(month > 0) //해가 지나지않을경우
                {
                    month--;
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    startActivity(intent);
                }
                else //해가 지날경우
                {
                    year--;
                    month = 11;
                    intent.putExtra("year", year);
                    intent.putExtra("month", month);
                    startActivity(intent);
                }
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MonthViewActivity.this, year+"." +(month+1)+"."+(position-mCal.get(Calendar.DAY_OF_WEEK)+2),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setDate(int year, int month, int date) {

        daylist = new ArrayList();
        mCal.set(year, month, date); //calendar 객체의 날짜 설정

        for(int i=1; i<mCal.get(Calendar.DAY_OF_WEEK); i++)
        {
            daylist.add(""); //1일과 시작요일을 맞추기위한 공백추가
        }
        for(int i=0; i<mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) { //달의 마지막 날짜까지 반복
            daylist.add(i+1); //리스트에 추가
        }
    }
}