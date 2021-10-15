package com.example.onebeep;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class TimerFragment extends Fragment {

    Button spbtn;
    Button erbtn;
    TextView hnLabel;
    TextView mnLabel;
    TextView snLabel;
    TextView backgLabel;
    TextView c1label;
    TextView c2label;
    EditText editText;
    ProgressBar progressBar;
    CountDownTimer cdt;
    long timerDuration;
    Chronometer chronometer;
    long timerStartedTime;
    long timeLeft;
    // long millisUntilFinished;
    long timerDuration_final;
    long numberOfMillisecondsAtStart;
    long numberOfMillisecondsAtVeryStart;
    long realTimeAtStart;
    long realTimeAtPause;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    public TimerFragment() {
    }


//    public static TimerFragment newInstance(String param1, String param2) {
//        TimerFragment fragment = new TimerFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        createNotificationChannel();
        someActivityResultLauncher= registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request code
                            Intent data = result.getData();
                            String returnValue = data.getStringExtra("changedTime");
                            if (!(returnValue.equals("nochange"))) {
                                hnLabel.setText(String.format("%s", returnValue.substring(0, 2)));
                                mnLabel.setText(String.format("%s", returnValue.substring(2, 4)));
                                snLabel.setText(String.format("%s", returnValue.substring(4, 6)));
                                spbtn.setText(R.string.start);
                                erbtn.setText(R.string.edit);
                            }
                        }
                    }
                });


//        setContentView(R.layout.fragment_timer);
//        SharedPreferences sharedPref = TimerFragment.this.getPreferences(Context.MODE_PRIVATE);
//        String savedTime = sharedPref.getString("BeepOnceTimer_time", "000030");
        String savedTime = "000030";

        hnLabel = view.findViewById(R.id.hnLabel);
        mnLabel = view.findViewById(R.id.mnLabel);
        snLabel = view.findViewById(R.id.snLabel);
        backgLabel = view.findViewById(R.id.backgLabel);
        c1label = view.findViewById(R.id.c1Label);
        c2label = view.findViewById(R.id.c2Label);
        editText = view.findViewById(R.id.editText);
        spbtn = view.findViewById(R.id.spbtn);
        erbtn = view.findViewById(R.id.erbtn);

        progressBar = view.findViewById(R.id.progress);
        hnLabel.setText(savedTime.substring(0,2));
        mnLabel.setText(savedTime.substring(2,4));
        snLabel.setText(savedTime.substring(4,6));


        spbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (spbtn.getText().equals("Start")) {
                    startTimer();
                } else if (spbtn.getText().equals("Pause")) {
                    pauseTimer();
                } else if (spbtn.getText().equals("Resume")) {
                    resumeTimer();
                } else if (spbtn.getText().equals("Edit")) {
                    editTimer();
                } else if (spbtn.getText().equals("Reset")) {
                    resetTimer();
                } else if (spbtn.getText().equals("Save")) {
                    saveNewTimer();
                }
            }
        });
        erbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (erbtn.getText().equals("Reset")) {
                    resetTimer();
                } else if (erbtn.getText().equals("Edit")) {
                    editTimer();
                } else if (erbtn.getText().equals("Back")) {
                    hideEditText();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }


    private void saveNewTimer() {
        try {
            if (editText.getText().toString().length() > 6 || editText.getText().toString().length() < 6) {
                Toast.makeText(getContext(), "Invalid Value", Toast.LENGTH_SHORT).show();
            }
            if (Integer.parseInt(editText.getText().toString().substring(0, 2)) >= 0 && Integer.parseInt(editText.getText().toString().substring(0, 2)) <= 23 &&
                    Integer.parseInt(editText.getText().toString().substring(2, 4)) >= 0 && Integer.parseInt(editText.getText().toString().substring(2, 4)) <= 59 &&
                    Integer.parseInt(editText.getText().toString().substring(4, 6)) >= 0 && Integer.parseInt(editText.getText().toString().substring(4, 6)) <= 59
                    &&!(Integer.parseInt(editText.getText().toString().substring(0, 2)) == 0 && Integer.parseInt(editText.getText().toString().substring(2,4)) == 0 &&
                    Integer.parseInt(editText.getText().toString().substring(4,6)) == 0)) {

                String returnValue = editText.getText().toString();

                hnLabel.setText(String.format("%s", returnValue.substring(0, 2)));
                mnLabel.setText(String.format("%s", returnValue.substring(2, 4)));
                snLabel.setText(String.format("%s", returnValue.substring(4, 6)));
                hideEditText();


            } else {
                Toast.makeText(getContext(), "Please select a value between 000001 and 235959", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){
            Toast.makeText(getContext(), "Invalid Value", Toast.LENGTH_SHORT).show();
        }


    }
    private void hideEditText() {
        hnLabel.setVisibility(View.VISIBLE);
        mnLabel.setVisibility(View.VISIBLE);
        snLabel.setVisibility(View.VISIBLE);
        backgLabel.setVisibility(View.VISIBLE);
        c1label.setVisibility(View.VISIBLE);
        c2label.setVisibility(View.VISIBLE);
        editText.setVisibility(View.GONE);
        spbtn.setText(R.string.start);
        erbtn.setText(R.string.edit);
    }

    private void resumeTimer() {
        spbtn.setText(R.string.pause);
        erbtn.setText(R.string.reset);
        long timeLeft = numberOfMillisecondsAtStart - (realTimeAtPause - realTimeAtStart);
        numberOfMillisecondsAtStart = timeLeft;
        realTimeAtStart = System.currentTimeMillis();
        Log.i("HERE", ""+timeLeft);
        beginTimerWith(timeLeft);
    }


    private void resetTimer() {
        spbtn.setText(R.string.start);
        erbtn.setText(R.string.edit);
        try {
            cdt.cancel();
        }
        catch (Exception e){
        }
        progressBar.setProgress(0);
        long hours = numberOfMillisecondsAtVeryStart / (1000 * 60 * 60);
        long minutes = (numberOfMillisecondsAtVeryStart % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (numberOfMillisecondsAtVeryStart % (1000 * 60)) / (1000);
        String hourstring = String.valueOf(hours);
        String minutestring = String.valueOf(minutes);
        String secondstring = String.valueOf(seconds);
        if (hours < 10) {
            hourstring = "0" + hourstring;
        }
        if (minutes < 10) {
            minutestring = "0" + minutestring;
        }
        if (seconds < 10) {
            secondstring = "0" + secondstring;
        }
        hnLabel.setText(String.format("%s", hourstring));
        mnLabel.setText(String.format("%s", minutestring));
        snLabel.setText(String.format("%s", secondstring));

    }

    private void startTimer() {
        spbtn.setText(R.string.pause);
        erbtn.setText(R.string.reset);
        realTimeAtStart = System.currentTimeMillis();
        timeLeft = 0;
        numberOfMillisecondsAtStart = (Long.parseLong(hnLabel.getText().toString()) * 60 * 60 * 1000) + (Long.parseLong(mnLabel.getText().toString()) * 60 * 1000) + (Long.parseLong(snLabel.getText().toString()) * 1000);
        numberOfMillisecondsAtVeryStart = numberOfMillisecondsAtStart;
        progressBar.setMax((int) numberOfMillisecondsAtStart / 1000);
        beginTimerWith(numberOfMillisecondsAtStart);

    }
    private void editTimer() {

        hnLabel.setVisibility(View.GONE);
        mnLabel.setVisibility(View.GONE);
        snLabel.setVisibility(View.GONE);
        backgLabel.setVisibility(View.GONE);
        c1label.setVisibility(View.GONE);
        c2label.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);
        editText.setText("");
        spbtn.setText(R.string.save);
        erbtn.setText(R.string.back);


    }
    private void pauseTimer() {
        spbtn.setText(R.string.resume);
        erbtn.setText(R.string.reset);
        realTimeAtPause = System.currentTimeMillis();
        cdt.cancel();
    }
    private void beginTimerWith(long millis) {
        final long timerDuration_final = numberOfMillisecondsAtVeryStart;
        cdt = new CountDownTimer(millis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long millisUntilFinished_temp = millisUntilFinished;
                long hours = millisUntilFinished_temp / (1000 * 60 * 60);
                long minutes = (millisUntilFinished_temp % (1000 * 60 * 60)) / (1000 * 60);
                long seconds = (millisUntilFinished_temp % (1000 * 60)) / (1000);
                String hourstring = String.valueOf(hours);
                String minutestring = String.valueOf(minutes);
                String secondstring = String.valueOf(seconds);
                if (hours < 10) {
                    hourstring = "0" + hourstring;
                }
                if (minutes < 10) {
                    minutestring = "0" + minutestring;
                }
                if (seconds < 10) {
                    secondstring = "0" + secondstring;
                }
                hnLabel.setText(String.format("%s", hourstring));
                mnLabel.setText(String.format("%s", minutestring));
                snLabel.setText(String.format("%s", secondstring));
                progressBar.setProgress((int)(timerDuration_final / 1000 - millisUntilFinished / 1000)-1);

            }

            @Override
            public void onFinish() {
                if(Integer.parseInt(hnLabel.getText().toString())==0&&Integer.parseInt(mnLabel.getText().toString())==0&&Integer.parseInt(snLabel.getText().toString())==0) {
                    progressBar.setProgress(progressBar.getProgress() + 1);
                    MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.beep);
                    mp.start();
                    Toast.makeText(getContext(), "Time's Up!", Toast.LENGTH_LONG).show();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "One Beep - Timer")
                            .setSmallIcon(R.drawable.ic_baseline_alarm_on_24)
                            .setContentTitle("Time's Up!")
                            .setContentText("The timer has run out")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManager mNotificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(1, builder.build());
                    hnLabel.setText(String.format("%s", "00"));
                    mnLabel.setText(String.format("%s", "00"));
                    snLabel.setText(String.format("%s", "00"));

                }
            }
        }.start();



    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("One Beep - Timer", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }




}