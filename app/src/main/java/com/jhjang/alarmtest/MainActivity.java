package com.jhjang.alarmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    ImageView imgAlarm;
    TextView txtTimer;
    EditText editTimer;
    Button btnCancel;
    Button btnStart;

    CountDownTimer countDownTimer;
    int remainingTime;
    int timeInterval = 1000;
    long second;
    boolean checkTimer = false;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgAlarm = findViewById(R.id.imgAlarm);
        txtTimer = findViewById(R.id.txtTimer);
        editTimer = findViewById(R.id.editTimer);
        btnCancel = findViewById(R.id.btnCancel);
        btnStart = findViewById(R.id.btnStart);

        mp = MediaPlayer.create(this, R.raw.alarm);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enterTime = editTimer.getText().toString();
                final Long second = Long.parseLong(enterTime)*1000;


                if (checkTimer==false) {
                    countDownTimer = new CountDownTimer(second,timeInterval) {
                        @Override
                        public void onTick(long l) {
                            remainingTime = (int)l /1000;
                            txtTimer.setText(""+remainingTime);
                            Log.i("Timettt",""+remainingTime);
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(MainActivity.this, "시간이 종료되었습니다.",Toast.LENGTH_SHORT).show();
                           mp.start();
                            YoYo.with(Techniques.Shake)
                                    .duration(300)
                                    .repeat(1)
                                    .playOn(findViewById(R.id.imgAlarm));

                        }
                    };
                    countDownTimer.start();
                    checkTimer = true;
                }else {
                    Toast.makeText(MainActivity.this, "이미실행중이다 ㅂㅅ아 종료하고 해라", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enterTime = editTimer.getText().toString();
                final Long second = Long.parseLong(enterTime);

                countDownTimer.cancel();
                checkTimer = false;
                txtTimer.setText(""+second);
            }
        });



    }

}
