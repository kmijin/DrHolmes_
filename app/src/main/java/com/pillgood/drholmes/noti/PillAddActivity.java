package com.pillgood.drholmes.noti;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pillgood.drholmes.MainActivity;
import com.pillgood.drholmes.R;

public class PillAddActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    View.OnClickListener cl;
    EditText write_medicine_name;
    Button count_minus, count_plus, medicine_add, medicine_del;
    TextView count_num;
    TimePicker medicine_timepicker;
    Intent intent;

    int count = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_home_pill_add);

        write_medicine_name = (EditText) findViewById(R.id.write_medicine_name);

        count_minus = (Button) findViewById(R.id.count_minus);
        count_plus = (Button) findViewById(R.id.count_plus);
        medicine_add = (Button) findViewById(R.id.medicine_add);
        medicine_del = (Button) findViewById(R.id.medicine_del);

        count_num = (TextView) findViewById(R.id.count_num);
        count_num.setText(count+"");

        medicine_timepicker = (TimePicker) findViewById(R.id.medicine_timepicker);

        medicine_timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                // 오전 / 오후 를 확인하기 위한 if 문
                if (hour > 12) {
                    hour -= 12;
                    databaseReference.child("drholmesNoti").child("time").push().setValue(Integer.toString(hour)+
                            Integer.toString(minute));
                } else {
                    databaseReference.child("drholmesNoti").child("time").push().setValue(Integer.toString(hour)+
                            Integer.toString(minute));
                }
            }
        });

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch ( view.getId() ) {
                    case R.id.count_minus :
                        count--;
                        count_num.setText(count+"");
                        break;
                    case R.id.count_plus :
                        count++;
                        count_num.setText(count+"");
                        if (count == 2) {
                            break;
                        }
                        break;
                    case R.id.medicine_add :
                        Toast.makeText(getApplicationContext(), "알람 추가", Toast.LENGTH_LONG).show();
                        intent = new Intent(PillAddActivity.this, MainActivity.class);
                        databaseReference.child("drholmesNoti").child("count").push().setValue(count_num);
                        break;
                    case R.id.medicine_del :
                        Toast.makeText(getApplicationContext(), "알람 취소", Toast.LENGTH_LONG).show();
                        intent = new Intent(PillAddActivity.this, MainActivity.class);
                        break;
                }
            }
        };
        count_minus.setOnClickListener(cl);
        count_plus.setOnClickListener(cl);
        medicine_add.setOnClickListener(cl);
        medicine_del.setOnClickListener(cl);
    }

}
