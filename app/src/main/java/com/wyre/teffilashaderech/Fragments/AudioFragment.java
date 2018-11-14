package com.wyre.teffilashaderech.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.wyre.teffilashaderech.R;

import java.util.Timer;
import java.util.TimerTask;


public class AudioFragment extends Fragment {
    private ImageButton mBtnPlay;
    private Toolbar mToolbar;
    private SeekBar mSeekbar;
    private MediaPlayer mPlayer;
    private Timer mTimer;
    private boolean mTimerRunning = false;
    private boolean mPlaying = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        View v = inflater.inflate(R.layout.audio_fragment, container, false);
        mBtnPlay = v.findViewById(R.id.btn_play);
        mSeekbar = v.findViewById(R.id.seekBar);
        //set the support action bar in the fragment
        AppCompatActivity act = (AppCompatActivity) getActivity();
        act.setSupportActionBar((Toolbar) v.findViewById(R.id.toolbar2));
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar2);
        ActionBar actionbar = act.getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        //implement listeners for the views
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //move the media player to where we are holding
                if (mPlayer != null) {
                    mPlayer.seekTo(seekBar.getProgress());
                }
            }
        });
        mBtnPlay.setOnClickListener((n) -> {
            //if its not playing then start the player
            if(!mPlaying) {
                mPlaying = true;
                mBtnPlay.setImageResource(R.drawable.ic_stop_black_24dp);
                //intialize the media player if its null
                if (mPlayer == null) {
                    mPlayer = MediaPlayer.create(getContext(), R.raw.teffilashaderech);
                }
                mPlayer.start();
                //switch over the image of t
                mSeekbar.setMax(mPlayer.getDuration());
                //Create a timer that update the seekbar with the media players progress
                if (mTimerRunning == false) {
                    mTimerRunning = true;
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread((new Runnable() {
                                @Override
                                public void run() {
                                    if (mPlayer != null) {
                                        if (mPlayer.isPlaying()) {
                                            mSeekbar.setProgress(mPlayer
                                                    .getCurrentPosition());
                                        }
                                    } else {
                                        //since its null set the seekbar progress to 0
                                        mSeekbar.setProgress(0);
                                    }
                                }
                            }));
                        }
                    };
                    mTimer = new Timer();
                    mTimer.schedule(task, 1000, 1000);
                }
            }
            else {
                mPlaying = false;
                mBtnPlay.setImageResource(R.drawable.ic_play);
                mPlayer.pause();
                mPlayer.seekTo(0);
                mSeekbar.setProgress(0);
            }
        });
        return v;
    }
    @Override
    public void onDestroy() {
        //release the media player and fragment when the Fragment is destroyed as we only want to play audio when the user is on the screen
        if(mPlayer!=null) {
            mPlayer.release();
        }
        if(mTimer!=null) {
            mTimer.cancel();
        }
        super.onDestroy();
    }


}
