package com.example.personalizedlearningexperienceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.model.SetupIntent;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpgradeActivity extends AppCompatActivity {

    String SECRET_KEY = "sk_test_51PLLueFByppeSEnE6QcFZi15nIQP0MqdazST6lTxFncznuz6I0AXdL2iVNoGQ6L9hfYFJTCZS2vFPxyfj5yG0lGH00wNmKB8GQ";
    String PUBLISH_KEY = "pk_test_51PLLueFByppeSEnEJxHY0pHjQJuT4lQRPmGDgtUCNvvlOgicVoYe5Jy6fLlr12BklWZYqbigINOIoRyrqUSYTqrO00tlkofaMP";

    PaymentSheet paymentSheet;

    String customerID;
    String EphiricalKey;
    String ClientSecret;

    String price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upgrade_activity);

        // Initialize views
        ImageView backBtnIcon2 = findViewById(R.id.backBtnIcon2);
        TextView upgradeTv1 = findViewById(R.id.upgradeTv1);
        TextView upgradeTv2 = findViewById(R.id.upgradeTv2);
        TextView tvStarter = findViewById(R.id.tvStarter);
        TextView upgradeDescTv = findViewById(R.id.upgradeDescTv);
        CardView purchaseBtnCard = findViewById(R.id.purchaseBtnCard);
        TextView purchaseBtnText = findViewById(R.id.purchaseBtnText);
        TextView tvIntermediate = findViewById(R.id.tvIntermediate);
        TextView upgradeDescTv2 = findViewById(R.id.upgradeDescTv2);
        CardView purchaseBtnCard2 = findViewById(R.id.purchaseBtnCard2);
        TextView purchaseBtnText2 = findViewById(R.id.purchaseBtnText2);
        TextView tvAdvanced = findViewById(R.id.tvAdvanced);
        TextView upgradeDescTv3 = findViewById(R.id.upgradeDescTv3);
        CardView purchaseBtnCard3 = findViewById(R.id.purchaseBtnCard3);
        TextView purchaseBtnText3 = findViewById(R.id.purchaseBtnText3);

        //Toast.makeText(UpgradeActivity.this, "Working till here", Toast.LENGTH_SHORT).show();

        PaymentConfiguration.init(UpgradeActivity.this, PUBLISH_KEY);

        paymentSheet = new PaymentSheet(UpgradeActivity.this, paymentSheetResult -> {

            OnPaymentResult(paymentSheetResult);
        });


        // Set onClickListener for back button
        backBtnIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UpgradeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Set onClickListener for Starter Purchase
        purchaseBtnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = "5";
                StartRequests();
            }
        });

        // Set onClickListener for Intermediate Purchase
        purchaseBtnCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = "10";
                StartRequests();
            }
        });

        // Set onClickListener for Advanced Purchase
        purchaseBtnCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = "15";
                StartRequests();
            }
        });
    }

    private void OnPaymentResult(PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            Toast.makeText(this, "payment success", Toast.LENGTH_SHORT).show();
        }
    }

    private void StartRequests() { //The chain of requests first start by creating a customer
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/customers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            customerID = object.getString("id");
                            Toast.makeText(UpgradeActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
                            GetEphiricalKey(customerID);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpgradeActivity.this, "1st Request didn't work", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SECRET_KEY);
                return header;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(UpgradeActivity.this);
        requestQueue.add(stringRequest);
    }

    private void GetEphiricalKey(String customerID) { //Create an Ephemeral Key for the Customer
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/ephemeral_keys",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            EphiricalKey = object.getString("id");
                            //Toast.makeText(UpgradeActivity.this, EphiricalKey, Toast.LENGTH_SHORT).show();

                            GetClientSecret(customerID, EphiricalKey);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpgradeActivity.this, "2nd Request didn't work", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SECRET_KEY);
                header.put("Stripe-Version", "2024-04-10");
                return header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("customer", customerID);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(UpgradeActivity.this);
        requestQueue.add(stringRequest);
    }

    private void GetClientSecret(String customerID, String ephiricalKey) { //Create a PaymentIntent for the Customer
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/payment_intents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            ClientSecret= object.getString("client_secret");

                            PaymentFlow();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpgradeActivity.this, "3rd Request didn't work", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SECRET_KEY);
                return header;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("customer", customerID);
                params.put("amount", price +"00");
                params.put("currency", "aud");
                params.put("automatic_payment_methods[enabled]", "true");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(UpgradeActivity.this);
        requestQueue.add(stringRequest);
    }

    private void PaymentFlow() { //Initiate the payment screen appearing. This appears after the chain of 3 requests

        paymentSheet.presentWithPaymentIntent(
                ClientSecret, new PaymentSheet.Configuration( "Learning Company"
                ,new PaymentSheet.CustomerConfiguration(
                        customerID,
                        EphiricalKey
                ))
        );

    }
}
