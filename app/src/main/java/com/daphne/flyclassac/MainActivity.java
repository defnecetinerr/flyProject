package com.daphne.flyclassac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Calendar current;
    Spinner spinner;
    TextView txtTimeZoneTime, txtCurrentTime, timezone;
    long milliSeconds;
    ArrayAdapter<String> idAdapter;
    SimpleDateFormat sdf;
    Date resultDate;
    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.FrameLayout1,new fragmentBirinci());
        ft.add(R.id.FrameLayout2,new fragmentIkinci());

        ft.commit();


        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        timezone= findViewById(R.id.timezone);
        txtCurrentTime= findViewById(R.id.txtCurrentTime);
        txtTimeZoneTime= findViewById(R.id.txtTimeZoneTime);

        String[] idArray = TimeZone.getAvailableIDs();

        sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");
        idAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, idArray);
        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(idAdapter);
        getGMTtime();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getGMTtime();
                String selectedID = (String) (parent.getItemAtPosition(position));
                TimeZone timeZone = TimeZone.getTimeZone(selectedID);
                String TimeZoneName = timeZone.getDisplayName();

                int TimeZoneOffset = timeZone.getRawOffset () /(60*1000);

                int hrs = TimeZoneOffset / 60;
                int mins = TimeZoneOffset % 60;

                /*https://docs.oracle.com/javase/tutorial/datetime/iso/timezones.html*/

                milliSeconds = milliSeconds + timeZone.getRawOffset();

                resultDate = new Date(milliSeconds);

                timezone.setText(TimeZoneName + " : GMT" + hrs + " : " + mins);
                txtTimeZoneTime.setText("" + sdf.format(resultDate));
                milliSeconds = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setAdapter(idAdapter);
        getGMTtime();
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getGMTtime();
                String selectedID = (String) (parent.getItemAtPosition(position));
                TimeZone timeZone = TimeZone.getTimeZone(selectedID);
                String TimeZoneName = timeZone.getDisplayName();

                int TimeZoneOffset = timeZone.getRawOffset () /(60*1000);

                int hrs = TimeZoneOffset / 60;
                int mins = TimeZoneOffset % 60;

                milliSeconds = milliSeconds + timeZone.getRawOffset();

                resultDate = new Date(milliSeconds);

                timezone.setText(TimeZoneName + " : GMT" + hrs + " : " + mins);
                txtTimeZoneTime.setText("" + sdf.format(resultDate));
                milliSeconds = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    private void getGMTtime(){
        current = Calendar.getInstance();
        txtCurrentTime.setText("" + current.getTime());

        milliSeconds = current.getTimeInMillis();

        TimeZone tzCurrent = current.getTimeZone();
        int offset = tzCurrent.getRawOffset();
        if(tzCurrent.inDaylightTime(new Date())){
            offset = offset + tzCurrent.getDSTSavings();
        }
        milliSeconds = milliSeconds - offset;
        resultDate = new Date(milliSeconds);
        System.out.println(sdf.format(resultDate));


    }

}