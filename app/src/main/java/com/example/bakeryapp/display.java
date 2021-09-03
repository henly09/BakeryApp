package com.example.bakeryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class display extends AppCompatActivity {

    ListView displayorder;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    ArrayList<order> orderdisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ssl.nuke();
        orderdisplay = new ArrayList<>();
        displayorder = findViewById(R.id.displayorder);
        loadorder();
    }

    private void loadorder() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest = new StringRequest(
                Request.Method.GET,
                "https://192.168.1.12/bakery/display.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            //JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray(response);

                            for(int i = 0; i < jsonArray.length();i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                orderdisplay.add(
                                        new order(
                                                jsonObject.getInt("ID"),
                                                jsonObject.getString("fullname"),
                                                jsonObject.getString("ordered"),
                                                jsonObject.getInt("price")
                                        )
                                );



                            }

                            ListAdapter adapter = new orderadapter(
                                    getApplicationContext(),
                                    orderdisplay

                            );

                            displayorder.setAdapter(adapter);


                            Log.v("JSON Array:", jsonArray.toString());
                            //Log.v("LENGTH:",String.valueOf(obj.length()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }


        );

        requestQueue.add(stringRequest);


    }
}