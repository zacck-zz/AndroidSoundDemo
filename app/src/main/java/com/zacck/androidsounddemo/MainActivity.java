package com.zacck.androidsounddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //declare it in  a public scope
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //make a mediaplayer instance with the file
        mPlayer = MediaPlayer.create(this, R.raw.cheering);


    }

    public void audioPlay(View view)
    {
        mPlayer.start();
    }

    public void audioPause(View view)
    {
        mPlayer.stop();
    }


}
