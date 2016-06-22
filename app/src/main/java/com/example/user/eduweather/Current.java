package com.example.user.eduweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by my hp on 3/2/2016.
 */
public class Current extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.current,container,false);


        final TextView placetv=(TextView)v.findViewById(R.id.currentplacetv1);
        final  TextView temptv=(TextView)v.findViewById(R.id.currenttemptv2);
        final TextView desctv= (TextView)v.findViewById(R.id.currentdesctv3);

        String url="http://api.openweathermap.org/data/2.5/weather?q="+getActivity().getApplicationContext().getSharedPreferences("city",0).getString("cityname","delhi")+"&appid=439e15a744ab1bdd20c2681a1f177074";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("data", response);
                        try {


                            JSONObject jsonobject = new JSONObject(response);
                            JSONObject mainData = jsonobject.getJSONObject("main");
                            //JSONObject weather = new JSONObject(jsonobject.getString("weather"));
                            JSONArray items=jsonobject.getJSONArray("weather");
                            String description = items.getJSONObject(0).getString("description");
                            double temp = Double.parseDouble(mainData.getString("temp"));
                            int tempInCelcius = (int) (temp - 272.15);
                            String placename = jsonobject.getString("name");


                            placetv.setText(placename);
                            temptv.setText("Temperature In Celcius: "+String.valueOf(tempInCelcius));
                            desctv.setText("Weather condition: "+description);

                        } catch (JSONException e)
                            {
                            e.printStackTrace();
                            }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                     //   pd.hide();
                        Toast.makeText(getActivity().getApplicationContext(), "its not working", Toast.LENGTH_LONG).show();
                        Log.v("error",error.toString());
                    }
                }) ;

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);

        int socketTimeout = 20000;//20 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}