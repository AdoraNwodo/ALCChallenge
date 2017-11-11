package com.example.nenneadora.alcchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class ConversionActivity extends AppCompatActivity {


    private String countryName, currencyAbbreviation, baseCurrency;
    private ImageView baseCurrencyImageView;
    private TextView currencyTextView;
    private EditText baseCurrencyEditText;
    private String BTC_URL = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=";
    private String ETH_URL = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=";
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private String url, value = "0";
    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
    private DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) numberFormat).getDecimalFormatSymbols();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent in = getIntent();
        countryName = in.getStringExtra("Country Name");
        currencyAbbreviation = in.getStringExtra("Currency Abbreviation");
        baseCurrency = in.getStringExtra("Base Currency");

        setTitle(currencyAbbreviation + " - " + baseCurrency + " Conversion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        baseCurrencyImageView = (ImageView) findViewById(R.id.baseCurrencyImageView);
        currencyTextView = (TextView) findViewById(R.id.currencyTextView);
        baseCurrencyEditText = (EditText) findViewById(R.id.baseCurrencyEditText);

        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalFormatSymbols);


        //initial textview text
        currencyTextView.setText(String.valueOf(currencyAbbreviation + " 0.00"));

        //initial editext placeholder
        baseCurrencyEditText.setHint("1 " + baseCurrency);

        //set imageview drawable and request url according to base currency
        if (baseCurrency.equals("BTC")) {
            baseCurrencyImageView.setImageDrawable(getResources().getDrawable(R.drawable.bitcoin, null));
            url = BTC_URL + currencyAbbreviation;
        } else {
            baseCurrencyImageView.setImageDrawable(getResources().getDrawable(R.drawable.ethereum, null));
            url = ETH_URL + currencyAbbreviation;
        }

        //send request
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        value = response.toString().replaceAll("\"", "")
                                .replace("{", "").replace("}", "").split(":")[1];

                        currencyTextView.setText(currencyAbbreviation + " "+numberFormat.format(Double.parseDouble(value)));   //2 decimal places

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Please connect to the internet and try again", Toast.LENGTH_LONG).show();
                    }
                }
        );

        mRequestQueue.add(stringRequest);

        baseCurrencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                decimalFormatSymbols.setCurrencySymbol("");
                ((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalFormatSymbols);

                if(!baseCurrencyEditText.getText().toString().isEmpty()) {
                    double baseCurrency = Double.parseDouble(baseCurrencyEditText.getText().toString());
                    double quoteCurrency = Double.parseDouble(value.toString());

                    double result = baseCurrency * quoteCurrency;
                    result = Math.round(result * 100);
                    result = result / 100;

                    currencyTextView.setText(currencyAbbreviation + " " + numberFormat.format(result));    //2 decimal places
                }else{
                    currencyTextView.setText(currencyAbbreviation + " " + numberFormat.format(Double.parseDouble(value)));   //if edit text is empty, reset.
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}

