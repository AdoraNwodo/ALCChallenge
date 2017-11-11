package com.example.nenneadora.alcchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by NenneAdora on 10/31/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1; //db version

    private static final String DATABASE_NAME = "WorldCurrency"; //db name

    //TABLE NAMES
    protected static final String TABLE_TOP_COUNTRIES = "top_countries";  //20 countries in list
    protected static final String TABLE_SELECTED_COUNTRIES = "selected_countries";  //countries in card view?

    //COLUMN NAMES FOR ALL TABLES
    private static final String KEY_ID = "country_id";
    private static final String KEY_COUNTRY_NAME = "country_name";
    private static final String KEY_CURRENCY = "currency";
    private static final String KEY_CURRENCY_ABBREVIATION = "currency_abbreviation";
    private static final String KEY_FLAG = "flag";
    private static final String KEY_CRYPTO_CURRENCY = "crypto_currency";
    private static final String KEY_SYMBOL = "symbol";

    private SQLiteDatabase dbase;

    private static final String CREATE_TABLE_TOP_COUNTRIES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_TOP_COUNTRIES + " ( "+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_COUNTRY_NAME + " TEXT, " + KEY_CURRENCY + " TEXT, " + KEY_FLAG + " INTEGER, "
            + KEY_CRYPTO_CURRENCY + " TEXT, " + KEY_SYMBOL + " TEXT, "
            + KEY_CURRENCY_ABBREVIATION + " TEXT)";

    private static final String CREATE_TABLE_SELECTED_COUNTRIES = "CREATE TABLE IF NOT EXISTS "
            + TABLE_SELECTED_COUNTRIES + " ( "+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_COUNTRY_NAME + " TEXT, " + KEY_CURRENCY + " TEXT, " + KEY_FLAG + " INTEGER, "
            + KEY_CRYPTO_CURRENCY+ " TEXT, " + KEY_SYMBOL + " TEXT, "
            + KEY_CURRENCY_ABBREVIATION + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        dbase = db;

        //drop tables if they exist
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TOP_COUNTRIES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SELECTED_COUNTRIES);

        //creating required tables
        db.execSQL(CREATE_TABLE_TOP_COUNTRIES);
        db.execSQL(CREATE_TABLE_SELECTED_COUNTRIES);
        addTopCountries();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);

    }

    private void addTopCountries(){

        CountryCurrency c1 = new CountryCurrency("Japan", "Japanese Yen", "JPY", R.drawable.japan, "BTC", "\u00A5");
        CountryCurrency c1_2 = new CountryCurrency("Japan", "Japanese Yen", "JPY", R.drawable.japan, "ETH", "\u00A5");
        CountryCurrency c2 = new CountryCurrency("United States of America", "United States Dollar", "USD", R.drawable.unitedstates, "BTC", "\u0024");
        CountryCurrency c22 = new CountryCurrency("United States of America", "United States Dollar", "USD", R.drawable.unitedstates, "ETH", "\u0024");
        CountryCurrency c3 = new CountryCurrency("Great Britain", "British Pound", "GBP", R.drawable.unitedkingdom, "BTC", "\u00A3");
        CountryCurrency c32 = new CountryCurrency("Great Britain", "British Pound", "GBP", R.drawable.unitedkingdom, "ETH", "\u00A3");
        CountryCurrency c4 = new CountryCurrency("Nigeria", "Naira", "NGN", R.drawable.nigeria, "BTC", "\u20A6");
        CountryCurrency c42 = new CountryCurrency("Nigeria", "Naira", "NGN", R.drawable.nigeria, "ETH", "\u20A6");
        CountryCurrency c5 = new CountryCurrency("South Africa", "South African Rand", "ZAR", R.drawable.south_africa, "BTC", "\u0052");
        CountryCurrency c52 = new CountryCurrency("South Africa", "South African Rand", "ZAR", R.drawable.south_africa, "ETH", "\u0052");
        CountryCurrency c6 = new CountryCurrency("Australia", "Australian Dollar", "AUD", R.drawable.australia, "BTC", "\u0024");
        CountryCurrency c62 = new CountryCurrency("Australia", "Australian Dollar", "AUD", R.drawable.australia, "ETH", "\u0024");
        CountryCurrency c7 = new CountryCurrency("Canada", "Canadian Dollar", "CAD", R.drawable.canada, "ETH", "\u0024");
        CountryCurrency c72 = new CountryCurrency("Canada", "Canadian Dollar", "CAD", R.drawable.canada, "BTC", "\u0024");
        CountryCurrency c8 = new CountryCurrency("European Union", "Euro", "EUR", R.drawable.europeanunion, "ETH", "\u20AC");
        CountryCurrency c82 = new CountryCurrency("European Union", "Euro", "EUR", R.drawable.europeanunion, "BTC", "\u20AC");
        CountryCurrency c9 = new CountryCurrency("China", "Chinese Offshore Yuan", "CNH", R.drawable.china, "ETH", "\u00A5");
        CountryCurrency c92 = new CountryCurrency("China", "Chinese Offshore Yuan", "CNH", R.drawable.china, "BTC", "\u00A5");
        CountryCurrency c10 = new CountryCurrency("Hong Kong", "Hong Kong Dollar", "HKD", R.drawable.hongkong, "ETH", "\u0024");
        CountryCurrency c102 = new CountryCurrency("Hong Kong", "Hong Kong Dollar", "HKD", R.drawable.hongkong, "BTC", "\u0024");
        CountryCurrency c11 = new CountryCurrency("Singapore", "Singapore Dollar", "SGD", R.drawable.singapore, "ETH", "\u0024");
        CountryCurrency c112 = new CountryCurrency("Singapore", "Singapore Dollar", "SGD", R.drawable.singapore, "BTC", "\u0024");
        CountryCurrency c12 = new CountryCurrency("Russia", "Russian Ruble", "RUB", R.drawable.russia, "ETH", "\u0440");
        CountryCurrency c122 = new CountryCurrency("Russia", "Russian Ruble", "RUB", R.drawable.russia, "BTC", "\u0440");
        CountryCurrency c13 = new CountryCurrency("Turkey", "Turkish Lira", "TRY", R.drawable.turkey, "ETH", "\u004C");
        CountryCurrency c132 = new CountryCurrency("Turkey", "Turkish Lira", "TRY", R.drawable.turkey, "BTC", "\u004C");
        CountryCurrency c14 = new CountryCurrency("Mexico", "Mexican Peso", "MXN", R.drawable.mexico, "ETH", "\u0024");
        CountryCurrency c142 = new CountryCurrency("Mexico", "Mexican Peso", "MXN", R.drawable.mexico, "BTC", "\u0024");
        CountryCurrency c15 = new CountryCurrency("Switzerland", "Swiss franc", "CHF", R.drawable.switzerland, "ETH", "\u0043");
        CountryCurrency c152 = new CountryCurrency("Switzerland", "Swiss franc", "CHF", R.drawable.switzerland, "BTC", "\u0043");
        CountryCurrency c16 = new CountryCurrency("New Zealand", "New Zealand Dollar", "NZD", R.drawable.newzealand, "ETH", "\u0024");
        CountryCurrency c162 = new CountryCurrency("New Zealand", "New Zealand Dollar", "NZD", R.drawable.newzealand, "BTC", "\u0024");
        CountryCurrency c17 = new CountryCurrency("Sweden", "Swedish Krona", "SEK", R.drawable.sweden, "ETH", "\u006B");
        CountryCurrency c172 = new CountryCurrency("Sweden", "Swedish Krona", "SEK", R.drawable.sweden, "BTC", "\u006B");
        CountryCurrency c18 = new CountryCurrency("Czech Republic", "Czech Koruna", "CZK", R.drawable.czechrepublic, "ETH", "\u004B");
        CountryCurrency c182 = new CountryCurrency("Czech Republic", "Czech Koruna", "CZK", R.drawable.czechrepublic, "BTC", "\u004B");
        CountryCurrency c19 = new CountryCurrency("Poland", "Polish Zloty", "PLN", R.drawable.poland, "ETH", "\u007A");
        CountryCurrency c192 = new CountryCurrency("Poland", "Polish Zloty", "PLN", R.drawable.poland, "BTC", "\u007A");
        CountryCurrency c20 = new CountryCurrency("Denmark", "Danish Krone", "DKK", R.drawable.denmark, "ETH", "\u006B");
        CountryCurrency c202 = new CountryCurrency("Denmark", "Danish Krone", "DKK", R.drawable.denmark, "BTC","\u006B");


        this.addAllTopCountries(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20
                                ,c1_2,c22,c32,c42,c52,c62,c72,c82,c92,c102,c112,c122,c132,c142,c152,c162,c172,c182,c192,c202
        );

    }

    private void addAllTopCountries(CountryCurrency... countries){

        for (CountryCurrency countryCurrency :
             countries) {

            ContentValues values = new ContentValues();
            values.put(KEY_COUNTRY_NAME, countryCurrency.getCountryName());
            values.put(KEY_CURRENCY, countryCurrency.getCurrency());
            values.put(KEY_CURRENCY_ABBREVIATION, countryCurrency.getCurrencyAbbreviation());
            values.put(KEY_FLAG, countryCurrency.getFlag());
            values.put(KEY_CRYPTO_CURRENCY, countryCurrency.getCryptoCurrency());
            values.put(KEY_SYMBOL, countryCurrency.getSymbol());

            // insert row
            dbase.insert(TABLE_TOP_COUNTRIES, null, values);

        }

    }


    public void addSelectedCountries(CountryCurrency countryCurrency){

        //prevent duplicate inserts
        Cursor c = dbase.rawQuery("SELECT " + KEY_COUNTRY_NAME + "," + KEY_CRYPTO_CURRENCY + " FROM " + TABLE_SELECTED_COUNTRIES
                + " WHERE " + KEY_COUNTRY_NAME + " = ? and " + KEY_CRYPTO_CURRENCY + " = ? ",
                new String[] {countryCurrency.getCountryName(), countryCurrency.getCryptoCurrency()});

        if(c.getCount() == 0) {     //only insert if cursor count is zero, meaning record is not in table

            ContentValues values = new ContentValues();
            values.put(KEY_COUNTRY_NAME, countryCurrency.getCountryName());
            values.put(KEY_CURRENCY, countryCurrency.getCurrency());
            values.put(KEY_CURRENCY_ABBREVIATION, countryCurrency.getCurrencyAbbreviation());
            values.put(KEY_FLAG, countryCurrency.getFlag());
            values.put(KEY_CRYPTO_CURRENCY, countryCurrency.getCryptoCurrency());
            values.put(KEY_SYMBOL, countryCurrency.getSymbol());


            // insert row
            dbase.insert(TABLE_SELECTED_COUNTRIES, null, values);
            dbase.delete(TABLE_TOP_COUNTRIES, KEY_ID + "=" + countryCurrency.getID(), null);

        }
    }

    public void deleteSelectedCountries(CountryCurrency countryCurrency){

        //delete selected countryCurrency
        dbase.delete(TABLE_SELECTED_COUNTRIES, KEY_ID+"="+ countryCurrency.getID(), null);


        Cursor c = dbase.rawQuery("SELECT " + KEY_COUNTRY_NAME + "," + KEY_CRYPTO_CURRENCY + " FROM " + TABLE_SELECTED_COUNTRIES
                        + " WHERE " + KEY_COUNTRY_NAME + " = ? and " + KEY_CRYPTO_CURRENCY + " = ? ",
                new String[] {countryCurrency.getCountryName(), countryCurrency.getCryptoCurrency()});

        if(c.getCount() == 0) {     //only insert if cursor count is zero, meaning record is not in table

            //readd top countryCurrency
            ContentValues values = new ContentValues();
            values.put(KEY_COUNTRY_NAME, countryCurrency.getCountryName());
            values.put(KEY_CURRENCY, countryCurrency.getCurrency());
            values.put(KEY_CURRENCY_ABBREVIATION, countryCurrency.getCurrencyAbbreviation());
            values.put(KEY_FLAG, countryCurrency.getFlag());
            values.put(KEY_CRYPTO_CURRENCY, countryCurrency.getCryptoCurrency());
            values.put(KEY_SYMBOL, countryCurrency.getSymbol());


            // insert row
            dbase.insert(TABLE_TOP_COUNTRIES, null, values);
        }

    }

    // delete selected - readd top
    // delete top (will be called in add selected)

    public List<CountryCurrency> getAllCurrencies(String TableName) {    //get top countries, or selected countries

        List<CountryCurrency> countryCurrencyList = new ArrayList<CountryCurrency>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TableName;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                CountryCurrency countryCurrency = new CountryCurrency();
                countryCurrency.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                countryCurrency.setCountryName(cursor.getString(cursor.getColumnIndex(KEY_COUNTRY_NAME)));
                countryCurrency.setCurrency(cursor.getString(cursor.getColumnIndex(KEY_CURRENCY)));
                countryCurrency.setCurrencyAbbreviation(cursor.getString(cursor.getColumnIndex(KEY_CURRENCY_ABBREVIATION)));
                countryCurrency.setFlag(cursor.getInt(cursor.getColumnIndex(KEY_FLAG)));
                countryCurrency.setCryptoCurrency(cursor.getString(cursor.getColumnIndex(KEY_CRYPTO_CURRENCY)));
                countryCurrency.setSymbol(cursor.getString(cursor.getColumnIndex(KEY_SYMBOL)));

                countryCurrencyList.add(countryCurrency);

            } while (cursor.moveToNext());
        }

        /*
        if (countryCurrencyList.size() > 0) {
            Collections.sort(countryCurrencyList, new Comparator<CountryCurrency>() {
                @Override
                public int compare(final CountryCurrency object1, final CountryCurrency object2) {
                    return object1.getCountryName().compareTo(object2.getCountryName());
                }
            });
        }
        */
        return countryCurrencyList; // return list of countries
    }

    public List<CountryCurrency> getAllCurrencies(String tableName, String cryptoCurrency) {    //overloaded method

        List<CountryCurrency> countryCurrencyList = new ArrayList<CountryCurrency>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tableName + " WHERE "+ KEY_CRYPTO_CURRENCY
                +" = \'"+cryptoCurrency+"\'";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                CountryCurrency countryCurrency = new CountryCurrency();
                countryCurrency.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                countryCurrency.setCountryName(cursor.getString(cursor.getColumnIndex(KEY_COUNTRY_NAME)));
                countryCurrency.setCurrency(cursor.getString(cursor.getColumnIndex(KEY_CURRENCY)));
                countryCurrency.setCurrencyAbbreviation(cursor.getString(cursor.getColumnIndex(KEY_CURRENCY_ABBREVIATION)));
                countryCurrency.setFlag(cursor.getInt(cursor.getColumnIndex(KEY_FLAG)));
                countryCurrency.setCryptoCurrency(cursor.getString(cursor.getColumnIndex(KEY_CRYPTO_CURRENCY)));
                countryCurrency.setSymbol(cursor.getString(cursor.getColumnIndex(KEY_SYMBOL)));

                countryCurrencyList.add(countryCurrency);

            } while (cursor.moveToNext());
        }

        if (countryCurrencyList.size() > 0) {
            Collections.sort(countryCurrencyList, new Comparator<CountryCurrency>() {
                @Override
                public int compare(final CountryCurrency object1, final CountryCurrency object2) {
                    return object1.getCountryName().compareTo(object2.getCountryName());
                }
            });
        }

        return countryCurrencyList; // return list of countries
    }
}
