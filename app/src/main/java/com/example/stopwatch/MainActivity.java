package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textview;
    MediaPlayer media;
    Button button;
    CountDownTimer count;
    boolean playing = false;

    public void pressed(View view){

        if(playing==true){

            button.setText("Play");
            textview.setText("0:30");
            seekBar.setProgress(30);
            seekBar.setEnabled(true);
            if(count != null) {
                count.cancel();
            }
            playing=false;
        }
        else{
            playing= true;
            button.setText("Stop");
            seekBar.setEnabled(false);
            count = new CountDownTimer(seekBar.getProgress()*1000 + 100,1000){
                @Override
                public void onTick(long l) {
                    updateTimer((int)l/1000);
                }

                @Override
                public void onFinish() {
                    Log.i("Method finished","hey");
                    MediaPlayer media = MediaPlayer.create(getApplicationContext(),R.raw.timeup);
                    media.start();
                }
            }.start();


        }
    }
    public void updateTimer(int timeRemaining){
        int minute= timeRemaining/60;
        int second=timeRemaining-(minute*60);
        textview.setText(minute+":"+second);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button=(Button)findViewById(R.id.button);
        seekBar= (SeekBar) findViewById(R.id.seekBar);
        textview= (TextView) findViewById(R.id.textView);
        seekBar.setMax(600);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar.setProgress(i);
                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}