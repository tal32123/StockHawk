package com.sam_chordas.android.stockhawk.service;

import com.sam_chordas.android.stockhawk.service.gson.Stocks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tal on 8/28/2016.
 */
    public interface RetrofitYQLInterface {
    public static final String BASE_URL = "https://query.yahooapis.com/";

    @GET("/v1/public/yql?")
        Call<Stocks> getStocks(
                @Query("q") String yqlCall,
                @Query("format") String json,
                @Query("diagnostics") String truth,
                @Query("env") String store,
                @Query("callback") String callback
        );
    }


