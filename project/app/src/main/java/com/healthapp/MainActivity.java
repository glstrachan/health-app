package com.healthapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.healthapp.databinding.ActivityMainBinding;

import java.util.Random;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_log, R.id.navigation_info).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void submitEntry(View view){

        EditText txtBox = findViewById(R.id.Hours);
        double Hours = Double.parseDouble(txtBox.getText().toString());

        txtBox = findViewById(R.id.Activity);
        String Activity = txtBox.getText().toString();

        RadioGroup radioGroup = findViewById(R.id.Intensity);
        int intensityID = radioGroup.getCheckedRadioButtonId();
        DataInstance.Intensity Intensity = DataInstance.Intensity.LOW;
        if(intensityID == R.id.Low)
            Intensity = DataInstance.Intensity.LOW;
        else if(intensityID == R.id.Medium)
            Intensity = DataInstance.Intensity.MEDIUM;
        else if(intensityID == R.id.High)
            Intensity = DataInstance.Intensity.HIGH;

        radioGroup = findViewById(R.id.Quality);
        int qualityID = radioGroup.getCheckedRadioButtonId();
        DataInstance.Quality Quality = DataInstance.Quality.POOR;
        if(qualityID == R.id.Poor)
            Quality = DataInstance.Quality.POOR;
        else if(qualityID == R.id.Good)
            Quality = DataInstance.Quality.GOOD;
        else if(qualityID == R.id.Excellent)
            Quality = DataInstance.Quality.EXCELLENT;

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String today = df.format(date);

        DataInstance dataInstance = new DataInstance(date, Hours, Activity, Intensity, Quality);
        LogData log = Input.recoverSave(this);
        log.addDataInstance(dataInstance);

        Output.save(log, this);
        Output.exportLog(log, this);

        Toast.makeText(this, "Log Added", Toast.LENGTH_SHORT).show();
    }

    public void exportLog(View view){
        LogData log = new LogData();
        log.quickSortEntries();

        Output.save(log, this);
        Output.exportLog(log, this);

        Toast.makeText(this, "Log Exported", Toast.LENGTH_SHORT).show();

    }

    public void changeEntry(View view){
        EditText txtBox = findViewById(R.id.numEntry);
        int n = Integer.parseInt(txtBox.getText().toString()) - 1;
        LogData log = new LogData();

        if(log.getDataInstance(n) == null) {
            Toast.makeText(this, "Invalid Entry", Toast.LENGTH_SHORT).show();
            return;
        }

        TextView textView = findViewById(R.id.LogEntry);
        textView.setText("Log Entry " + (n+1));

        textView = findViewById(R.id.display_date);
        textView.setText(log.getDataInstance(n).getDate().toString());

        textView = findViewById(R.id.display_hours);
        textView.setText(Double.toString(log.getDataInstance(n).getHours()));

        textView = findViewById(R.id.display_activity);
        textView.setText(log.getDataInstance(n).getActivity());

        textView = findViewById(R.id.display_intensity);
        textView.setText(log.getDataInstance(n).intensityToString(log.getDataInstance(n).getIntensity()));

        textView = findViewById(R.id.display_quality);
        textView.setText(log.getDataInstance(n).qualityToString(log.getDataInstance(n).getQuality()));
    }

    public void deleteEntry(View view){
        EditText txtBox = findViewById(R.id.numEntry);
        int n = Integer.parseInt(txtBox.getText().toString()) - 1;
        LogData log = new LogData();

        if(log.getDataInstance(n) == null){
            Toast.makeText(this, "Invalid Entry", Toast.LENGTH_SHORT).show();
            return;
        }

        log.removeDataInstance(n);
        Output.save(log, this);
        Output.exportLog(log, this);

        Toast.makeText(this, "Entry Deleted", Toast.LENGTH_SHORT).show();

    }

    public void changeOnClick(View v){
        TextView text = findViewById(R.id.activity);
        Button butt = findViewById(R.id.button);

        RadioGroup rad = findViewById(R.id.type);
        int radioButtonID = rad.getCheckedRadioButtonId();

        String[][] words = {{"Running","Cycling","Swimming","Walking"},
                            {"Stretching","Dancing","Gymnastics","Yoga"},
                            {"Weight Lifting","Gardening","Rock Climbing","Resistance Training"},
                            {"Planking","Squats","Push Ups","Sit Ups"}};

        Random r = new Random();
        int i = r.nextInt(words.length);

        if(radioButtonID == -1){
            num = r.nextInt(words[0].length);
        }else
        if(radioButtonID == R.id.Cardio){
            num = 0;
        }else
        if(radioButtonID == R.id.Flex){
            num = 1;
        }else
        if(radioButtonID == R.id.MS){
            num = 2;
        }else
        if(radioButtonID == R.id.ME){
            num = 3;
        }

        text.setText(words[num][i]);

    }
}
