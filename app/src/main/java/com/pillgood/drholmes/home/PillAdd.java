package com.pillgood.drholmes.home;

import static android.content.ContentValues.TAG;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pillgood.drholmes.MainActivity;
import com.pillgood.drholmes.R;
import com.pillgood.drholmes.noti.AlertReceiver;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PillAdd extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;
    private FirebaseFirestore mFirebaseFirestore;

    private EditText write_medicine_name;
    private Button count_minus, count_plus, medicine_add, medicine_del;
    private TextView count_num;
    private TimePicker medicine_timepicker;

    private int count;
    private int h, m;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pill_add);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("pill");
        mFirebaseFirestore = FirebaseFirestore.getInstance();

        write_medicine_name = findViewById(R.id.write_medicine_name);
//        final String pillName = write_medicine_name.getText().toString().trim();
        count_minus = (Button) findViewById(R.id.count_minus);
        count_plus = (Button) findViewById(R.id.count_plus);
        medicine_add = (Button) findViewById(R.id.medicine_add);
        medicine_del = (Button) findViewById(R.id.medicine_del);

        count_num = (TextView) findViewById(R.id.count_num);
        count_num.setText(count+"");

        medicine_timepicker = (TimePicker) findViewById(R.id.medicine_timepicker);
        medicine_timepicker.setIs24HourView(true);

        medicine_timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            Calendar c = Calendar.getInstance();
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                h = hour;
                m = minute;

                Log.d(TAG, "## onTimeSet ## ");
                Calendar c = Calendar.getInstance();

                c.set(Calendar.HOUR_OF_DAY, h);
                c.set(Calendar.MINUTE, m);
                c.set(Calendar.SECOND, 0);

                //알람설정정
                startAlarm(c);
            }
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//                Log.d(TAG, "## onTimeSet ## ");
//                Calendar c = Calendar.getInstance();
//
//                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                c.set(Calendar.MINUTE, minute);
//                c.set(Calendar.SECOND, 0);
//
//                //알람설정정
//                startAlarm(c);
//            }
        });

        count_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                count = 0;
                count--;
                count_num.setText(count+"");
            }
        });
        count_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                count = 0;
                count++;
                count_num.setText(count+"");
            }
        });
        medicine_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "알람 추가", Toast.LENGTH_LONG).show();
                Map<String, Object> pill = new HashMap<>();
                pill.put("pillHour", h + "시");
                pill.put("pillMinute", m + "분");
                pill.put("pillCount", count + "정");
                pill.put("pillName", write_medicine_name.getText().toString());
                mFirebaseFirestore.collection("Pills")
                        .add(pill)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "ERROR..", e);
                            }
                        });
                Intent intent = new Intent(PillAdd.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        medicine_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "알람 취소", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PillAdd.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 알람 시작
     * @param c 시간
     */
    private void startAlarm( Calendar c) {
        Log.d(TAG, "## startAlarm ## ");
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE, 1);
        }

        //RTC_WAKE : 지정된 시간에 기기의 절전 모드를 해제하여 대기 중인 인텐트를 실행
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}
