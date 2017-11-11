package com.example.nenneadora.alcchallenge;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by NenneAdora on 11/7/2017.
 */

public class HomeCustomAdapter extends RecyclerView.Adapter<HomeCustomAdapter.HomeViewHolder>{

    public interface OnItemClickListener {
        void onItemClick(CountryCurrency countryCurrency);
    }


    private final OnItemClickListener listener;
    private final OnItemClickListener deleteListener;
    private String BTC_URL = "https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=";
    private String ETH_URL = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=";
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private Timer timer = new Timer();
    private int count = 0, toastCount = 0;
    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
    private DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) numberFormat).getDecimalFormatSymbols();


    private Context mCtx;
    public List<CountryCurrency> countryCurrencyList;

    public HomeCustomAdapter(Context mCtx, List<CountryCurrency> countryCurrencyList, OnItemClickListener listener, OnItemClickListener deleteListener) {
        this.mCtx = mCtx;
        this.countryCurrencyList = countryCurrencyList;
        this.listener = listener;
        this.deleteListener = deleteListener;

    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listlayouthome, null);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

        holder.bind(countryCurrencyList.get(position), listener, position);

        /*
        CountryCurrency country = countryCurrencyList.get(position);

        holder.country.setText(country.getCountryName());
        holder.currency.setText(country.getCurrency()+" ( "+country.getCurrencyAbbreviation()+" )");
        holder.rate.setText("00.00");
        */

    }

    @Override
    public int getItemCount() {
        return countryCurrencyList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

        private ImageView flag, garbage;
        private TextView country, currency, rate, base;


        public HomeViewHolder(View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.flag);
            garbage = itemView.findViewById(R.id.garbage);
            country = itemView.findViewById(R.id.countryName);
            currency = itemView.findViewById(R.id.currency);
            rate = itemView.findViewById(R.id.rate);
            base = itemView.findViewById(R.id.basecurrency);
        }

        public void bind(final CountryCurrency mCountryCurrency, final OnItemClickListener listener, final int position) {

            rate.setText(mCountryCurrency.getSymbol()+ " 00.00");
            country.setText(mCountryCurrency.getCountryName());
            currency.setText(mCountryCurrency.getCurrency()+" ( "+ mCountryCurrency.getCurrencyAbbreviation()+" )");
            base.setText(mCountryCurrency.getCryptoCurrency());
            flag.setImageDrawable(mCtx.getResources().getDrawable(mCountryCurrency.getFlag(),null));

            //send request
            String url = "";
            if(mCountryCurrency.getCryptoCurrency().equals("BTC")){
                url = BTC_URL+ mCountryCurrency.getCurrencyAbbreviation();
            }else {
                url = ETH_URL+ mCountryCurrency.getCurrencyAbbreviation();
            }

            mRequestQueue = Volley.newRequestQueue(mCtx);

            stringRequest = new StringRequest(Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String value = response.toString().replaceAll("\"", "")
                                    .replace("{", "").replace("}", "").split(":")[1];
                            //rate.setText(decimalFormat.format(Double.parseDouble(value)));  //2 decimal places
                            decimalFormatSymbols.setCurrencySymbol(mCountryCurrency.getSymbol()+" ");   //currency symbol
                            ((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalFormatSymbols);
                            rate.setText(numberFormat.format(Double.parseDouble(value)));  //2 decimal places

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            if(toastCount == 0) {   //prevent toast from showing for every card in recyclerview. Once is enough.
                                Toast.makeText(mCtx, "Please connect to the internet and try again", Toast.LENGTH_SHORT).show();
                                toastCount++;
                            }
                        }
                    }
            );

            if(isNetworkAvailable()) {
                mRequestQueue.add(stringRequest);
            }else{
                Toast.makeText(mCtx, "Please connect to the internet and try again", Toast.LENGTH_SHORT).show();
            }

            /*
            //send request every 10 minutes (6 times an hour)
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if(count < 6){
                        mRequestQueue.add(stringRequest);
                        count++;
                    }

                }
            }, 0, 10*60*1000);//10 Minutes

            */
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(mCountryCurrency);

                }

            });

            garbage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    deleteListener.onItemClick(mCountryCurrency);
                    countryCurrencyList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, countryCurrencyList.size());

                }

            });

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
