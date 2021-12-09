package com.example.mobilepaindiary.ui.home.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilepaindiary.R;
import com.example.mobilepaindiary.ui.home.MainActivity;

import java.util.Calendar;

//https://www.cnblogs.com/wuziyue/p/5470684.html
public class Clock extends AppCompatActivity {

    private TimePicker timePicker;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        AlarmManager alarmManager= (AlarmManager) getSystemService(Service.ALARM_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        timePicker = (TimePicker)findViewById(R.id.timePickerId);


        timePicker.setIs24HourView(true);//设置时间为24小时的 格式
        timePicker.setCurrentHour(10);//设置当前的小时
        timePicker.setCurrentMinute(10);//设置当前的分钟

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(Clock.this,
                        "the time you pick"+(hourOfDay+":"+minute),
                        Toast.LENGTH_SHORT).show();
                Calendar c = Calendar.getInstance();

                c.setTimeInMillis(System.currentTimeMillis());


                c.set(Calendar.HOUR, hourOfDay);

                c.set(Calendar.MINUTE, minute-2);

                alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

            }
        });
    }
}