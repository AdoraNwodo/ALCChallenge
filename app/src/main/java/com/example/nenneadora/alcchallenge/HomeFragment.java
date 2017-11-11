package com.example.nenneadora.alcchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by NenneAdora on 11/7/2017.
 */

public class HomeFragment extends Fragment {

    private TextView createCardTextView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HomeCustomAdapter adapter;
    private List<CountryCurrency> countryCurrencyList;
    private int newListSize;
    private DatabaseHelper db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), NewCardActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        createCardTextView = (TextView) rootView.findViewById(R.id.create_card);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.HomeRecyclerView);

        initRecyclerView();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                initRecyclerView(); // Refresh items on swipe

                mSwipeRefreshLayout.setRefreshing(false);   // Stop refresh animation
            }
        });


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //onresume is being called twice, so i'm using this but tweaking it a little
        //reload page no matter what regardless of result

        initRecyclerView();

    }

    private void initRecyclerView(){

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = new DatabaseHelper(getContext()); //database

        countryCurrencyList = db.getAllCurrencies(DatabaseHelper.TABLE_SELECTED_COUNTRIES);
        newListSize = countryCurrencyList.size();

        if(countryCurrencyList.size() > 0)  { //if cards exist, hide textview that tells users to create cards
            createCardTextView.setVisibility(View.GONE);
        }else if (countryCurrencyList.size() <= 0){
            createCardTextView.setVisibility(View.VISIBLE);
        }

        adapter = new HomeCustomAdapter(getActivity(),
                countryCurrencyList,
                new HomeCustomAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CountryCurrency countryCurrency) {
                        Intent intent = new Intent(getContext(), ConversionActivity.class);
                        intent.putExtra("Country Name", countryCurrency.getCountryName());
                        intent.putExtra("Currency Abbreviation", countryCurrency.getCurrencyAbbreviation());
                        intent.putExtra("Base Currency", countryCurrency.getCryptoCurrency());
                        startActivity(intent);
                    }
                },
                new HomeCustomAdapter.OnItemClickListener(){

                    @Override
                    public void onItemClick(CountryCurrency countryCurrency) {
                        db.deleteSelectedCountries(countryCurrency);
                        newListSize--;

                        if(newListSize > 0)  { //if cards exist, hide textview that tells users to create cards
                            createCardTextView.setVisibility(View.GONE);
                        }else if(newListSize <= 0){
                            createCardTextView.setVisibility(View.VISIBLE);
                        }

                        Toast.makeText(getActivity(), "Card deleted!", Toast.LENGTH_SHORT).show();
                    }

                }
        );

        recyclerView.setAdapter(adapter);
        recyclerView.setFocusable(false);
        recyclerView.clearFocus();

    }

}
