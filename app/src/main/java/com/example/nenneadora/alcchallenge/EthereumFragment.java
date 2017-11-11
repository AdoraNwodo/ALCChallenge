package com.example.nenneadora.alcchallenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class EthereumFragment extends Fragment {

    RecyclerView recyclerView;
    BtcEthCustomAdapter adapter;
    List<CountryCurrency> countryCurrencyList;

    public EthereumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_ethereum, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.ETHRecyclerView);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final DatabaseHelper db = new DatabaseHelper(getContext()); //database

        countryCurrencyList = db.getAllCurrencies(DatabaseHelper.TABLE_TOP_COUNTRIES, "ETH");

        recyclerView.setAdapter(new BtcEthCustomAdapter(getActivity(), countryCurrencyList, new BtcEthCustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CountryCurrency countryCurrency) {

                db.addSelectedCountries(countryCurrency);   //add to card that shows on homepage
                Toast.makeText(getContext(), "New Card Added", Toast.LENGTH_SHORT).show();
            }
        }));

        recyclerView.setFocusable(false);
        recyclerView.clearFocus();
        //recyclerView.setNestedScrollingEnabled(false);

        return rootView;
    }



}
