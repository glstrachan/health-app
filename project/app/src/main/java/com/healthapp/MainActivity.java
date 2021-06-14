package com.healthapp;

import android.app.Activity;
import android.os.Bundle;
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

        EditText txtBox = findViewById(R.id.Date);
        String Date = txtBox.getText().toString();

        txtBox = findViewById(R.id.Hours);
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

        DataInstance dataInstance = new DataInstance(Date, Hours, Activity, Intensity, Quality);
        LogData log = Input.recoverSave(this);
        log.addDataInstance(dataInstance);
        log.setFirstName("Bob");
        
        Output.save(log, this);
        Output.exportLog(log, this);

        Toast.makeText(this, "Log Added", Toast.LENGTH_SHORT).show();
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
