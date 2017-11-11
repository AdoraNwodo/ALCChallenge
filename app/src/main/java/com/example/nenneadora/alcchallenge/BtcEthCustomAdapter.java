package com.example.nenneadora.alcchallenge;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by NenneAdora on 11/7/2017.
 */

public class BtcEthCustomAdapter extends RecyclerView.Adapter<BtcEthCustomAdapter.BtcEthViewHolder>{


    public interface OnItemClickListener {
        void onItemClick(CountryCurrency countryCurrency);
    }

    private final OnItemClickListener listener;

    private Context mCtx;
    private List<CountryCurrency> countryCurrencyList;

    public BtcEthCustomAdapter(Context mCtx, List<CountryCurrency> countryCurrencyList, OnItemClickListener listener) {
        this.mCtx = mCtx;
        this.countryCurrencyList = countryCurrencyList;
        this.listener = listener;
    }

    @Override
    public BtcEthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listlayout, null);

        return new BtcEthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BtcEthViewHolder holder, int position) {

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

    class BtcEthViewHolder extends RecyclerView.ViewHolder{

        private ImageView flag;
        private TextView country, currency, rate;


        public BtcEthViewHolder(View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.flag);
            country = itemView.findViewById(R.id.countryName);
            currency = itemView.findViewById(R.id.currency);
            //rate = itemView.findViewById(R.id.rate);
        }

        public void bind(final CountryCurrency mCountryCurrency, final OnItemClickListener listener, final int position) {

            country.setText(mCountryCurrency.getCountryName());
            currency.setText(mCountryCurrency.getCurrency()+" ( "+ mCountryCurrency.getCurrencyAbbreviation()+" )");
            flag.setImageDrawable(mCtx.getResources().getDrawable(mCountryCurrency.getFlag(),null));
            //rate.setText("00.00");

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    listener.onItemClick(mCountryCurrency);

                    countryCurrencyList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, countryCurrencyList.size());

                }

            });


        }
    }
}
