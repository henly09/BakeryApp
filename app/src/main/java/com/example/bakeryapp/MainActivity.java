package com.example.bakeryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static String type, price1;
    static int Price;
    static EditText ordered, pieces, orderdate, ordertime, fullname, email, phonenumber, address;
    static Button insert, display, exit;
    static CheckBox delivery, pickup;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    dialog dialog = new dialog();
    dialog1 dialog1 = new dialog1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ssl.nuke();
//---------------------------------------------------------------- ID
        ordered = findViewById(R.id.ordered);
        pieces = findViewById(R.id.pieces);
        orderdate = findViewById(R.id.orderdate);
        ordertime = findViewById(R.id.ordertime);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phonenumber);
        address = findViewById(R.id.address);
//---------------------------------------------------------------- BUTTONS
        insert = findViewById(R.id.insert);
        display = findViewById(R.id.display);
        exit = findViewById(R.id.exit);
//---------------------------------------------------------------- CHECKBOX
        delivery = findViewById(R.id.delivery);
        pickup = findViewById(R.id.pickup);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayData();
            }
        });

    }

    private void exit(){
        finish();
        System.exit(0);
    }

    private void DisplayData(){
        startActivity(new Intent(getApplicationContext(),display.class));
    }

    private void InsertData() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        stringRequest = new StringRequest(
                Request.Method.POST,
                "https://192.168.1.12/bakery/insertdata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")){
                            Toast.makeText(
                                    getApplicationContext(),
                                    response,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();

                boolean trigger = true;

                switch (ordered.getText().toString()){
                    case "Pandesal":
                        Price = 2 * Integer.parseInt(pieces.getText().toString());
                        price1 = String.valueOf(Price);
                        trigger = false;
                        break;
                    case "Loaf Bread":
                        Price = 25 * Integer.parseInt(pieces.getText().toString());
                        price1 = String.valueOf(Price);
                        trigger = false;
                        break;
                    case "Choco Lanay":
                        Price = 3 * Integer.parseInt(pieces.getText().toString());
                        price1 = String.valueOf(Price);
                        trigger = false;
                        break;
                    case "Spanish Bread":
                        Price = 5 * Integer.parseInt(pieces.getText().toString());
                        price1 = String.valueOf(Price);
                        trigger = false;
                        break;
                    case "Turta":
                        Price = 5 * Integer.parseInt(pieces.getText().toString());
                        price1 = String.valueOf(Price);
                        trigger = false;
                        break;
                    case "Rye Bread":
                        Price = 5 * Integer.parseInt(pieces.getText().toString());
                        price1 = String.valueOf(Price);
                        trigger = false;
                        break;
                    case "Humburger Bun":
                        Price = 10 * Integer.parseInt(pieces.getText().toString());
                        price1 = String.valueOf(Price);
                        trigger = false;
                        break;
                    case "Breadsticks":
                        Price = 5 * Integer.parseInt(pieces.getText().toString());
                        price1 = String.valueOf(Price);
                        trigger = false;
                        break;
                    default:
                        dialog1.show(getSupportFragmentManager(), "dialog");
                        break;
                }

                if (delivery.isChecked() && pickup.isChecked()){
                    dialog.show(getSupportFragmentManager(), "dialog");
                }

                if (!delivery.isChecked() && !pickup.isChecked()){
                    dialog1.show(getSupportFragmentManager(), "dialog");
                }

                if (delivery.isChecked()){
                    type = "["+"Delivery"+"]";
                    trigger = false;
                }

                if (pickup.isChecked()){
                    type = "["+"Pickup"+"]";
                    trigger = false;
                }

                if (trigger == false) {

                    map.put("price", price1);
                    map.put("ordered", ordered.getText().toString());
                    map.put("pieces", pieces.getText().toString());
                    map.put("Type_", type);
                    map.put("orderdate", orderdate.getText().toString());
                    map.put("ordertime", ordertime.getText().toString());
                    map.put("fullname", fullname.getText().toString());
                    map.put("email", email.getText().toString());
                    map.put("phonenumber", phonenumber.getText().toString());
                    map.put("address", address.getText().toString());

                    return map;

                }

                return null;

            }
        };

        requestQueue.add(stringRequest);

    }

}