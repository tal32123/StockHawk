package com.sam_chordas.android.stockhawk.service;

import com.sam_chordas.android.stockhawk.service.gson.Stocks;
import com.squareup.okhttp.Callback;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Tal on 8/26/2016.
 */
public class RetrofitYQL{
    public static final String BASE_URL = "https://query.yahooapis.com/";
//    private Call<Stocks> call;
    public interface YQLService {
        @GET("/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22{symbol}%22%20and%20startDate%20%3D%20%222009-09-11%22%20and%20endDate%20%3D%20%222010-03-10%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=")
        Call<Stocks> getStocks(
                @Path("symbol") String symbol
        );
    }



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    YQLService yqlService = retrofit.create(YQLService.class);
    Call<Stocks> call =yqlService.getStocks("YHOO");
    call.enqueue(new Callback<Stocks>() {
    }
}
