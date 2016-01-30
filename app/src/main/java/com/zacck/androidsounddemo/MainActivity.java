package com.zacck.androidsounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //declare it in  a public scope
    MediaPlayer mPlayer;
    //AudioManager
    AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setup the AUdiomanager
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int MaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);



        //make a mediaplayer instance with the file
        mPlayer = MediaPlayer.create(this, R.raw.cheering);


        //Audio Volume Bar
        SeekBar mSeekBar = (SeekBar)findViewById(R.id.mAudioSeekBar);
        mSeekBar.setMax(MaxVolume);
        mSeekBar.setProgress(currVolume);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(getPackageName()+" SeekBar Progress", progress+"");
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Audio Play progress Bar
        final SeekBar mAudioProgressBar = (SeekBar)findViewById(R.id.mProgressSeekBar);
        mAudioProgressBar.setMax(mPlayer.getDuration());
        mAudioProgressBar.setProgress(mPlayer.getCurrentPosition());
        //timer to control progress of play on play progress seekbar
        //schedule a task to runat every interval
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //update audioplayer position seekbar every second
                mAudioProgressBar.setProgress(mPlayer.getCurrentPosition());

            }
        },/*delay before timer startes */0,
                /*interval time before running again */100);

        mAudioProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                mPlayer.pause();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                mPlayer.start();

            }
        });


    }

    public void audioPlay(View view)
    {
        mPlayer.start();
    }

    public void audioPause(View view)
    {
        mPlayer.pause();
    }


}
