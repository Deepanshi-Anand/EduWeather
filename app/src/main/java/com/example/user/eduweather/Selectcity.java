package com.example.user.eduweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by user on 21-06-2016.
 */



public class Selectcity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectcity);
        findViewById(R.id.button_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
String ed=((EditText)findViewById(R.id.editText_city)).getText().toString();
        if(ed.equals("")||ed.equals("null"))
        {
            Toast.makeText(Selectcity.this,"Please Enter your city",Toast.LENGTH_LONG);

        }
        getSharedPreferences("city",0).edit().putString("cityname",ed).apply();
        Intent intent=new Intent(Selectcity.this,MainWeather.class);
        startActivity(intent);
        //String city=getSharedPreferences("city",0).getString("cityname","delhi");
    }
}
