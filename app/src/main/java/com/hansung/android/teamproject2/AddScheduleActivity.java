package com.hansung.android.teamproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;

public class AddScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        //버튼 객체 생성
        Button findbtn = findViewById(R.id.findbtn);
        Button savebtn = findViewById(R.id.svaebtn);
        Button cancelbtn = findViewById(R.id.cancelbtn);
        Button deletebtn = findViewById(R.id.deletebtn);

        //시작, 종료시간 스크롤뷰 객체 생성
        ScrollView start_hour = findViewById(R.id.start_hour);
        ScrollView start_minute = findViewById(R.id.start_minute);
        ScrollView start_AMPM = findViewById(R.id.start_AMPM);

        String[] hours = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] minutes = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40",
                "41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"};
        String[] AMPM = {"AM", "PM"};


        //이벤트 처리
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}