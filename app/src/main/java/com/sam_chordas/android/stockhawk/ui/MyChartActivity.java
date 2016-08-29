package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.view.LineChartView;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.service.RetrofitYQLInterface;
import com.sam_chordas.android.stockhawk.service.gson.Quote;
import com.sam_chordas.android.stockhawk.service.gson.Stocks;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyChartActivity extends Activity {
    /*
     * Created by Tal Charnes
     * Credit to Williamchart for the library used in this class for building the chart
     */
    Context mContext;
    private String startDate;
    private String endDate;
    private String symbol;
    private final String JSONFORMAT = "json";
    private final String DIAGNOSTICS = "true";
    private final String ENV = "store://datatables.org/alltableswithkeys";
    private final String CALLBACK = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mContext = getApplicationContext();
        createChart();
    }

    private void createChart(){
        //start retrofit transaction
        Call<Stocks> call;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitYQLInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitYQLInterface retrofitYQLInterface = retrofit.create(RetrofitYQLInterface.class);
        StringBuilder yqlCallSB = new StringBuilder();
        symbol = "YHOO";
        startDate = "2016-08-01";
        endDate = "2016-08-11";


        yqlCallSB.append("select * from yahoo.finance.historicaldata where symbol = \"")
        .append(symbol)
        .append("\" and startDate = \"")
        .append(startDate)
        .append("\" and endDate = \"")
        .append(endDate)
        .append("\"");
        String yqlCall = yqlCallSB.toString();

        call = retrofitYQLInterface.getStocks(yqlCall, JSONFORMAT, DIAGNOSTICS, ENV, CALLBACK);
        Log.i("Retrofit url = ", call.request().url().toString());
        //// TODO: 8/28/2016 implement onResponse by parsing data and throwing it into points on graph
        call.enqueue(new retrofit2.Callback<Stocks>() {
            @Override
            public void onResponse(Call<Stocks> call, Response<Stocks> response) {

                Stocks stocks = response.body();
                List<Quote> getQuoteList = stocks.getQuery().getResults().getQuote();
                String closeprice = getQuoteList.get(0).getClose();
                String x = getQuoteList.get(0).getSymbol();
                int responseCode = response.code();
                Toast.makeText(getApplicationContext(), closeprice + x + responseCode, Toast.LENGTH_SHORT).show();
                String testString = "";
                for (int i = 0; i < getQuoteList.size(); i++){
                   testString = testString + stocks.getQuery().getResults().getQuote().get(i).getClose();
                    Log.i("teststring = ", testString);
                }
            }

            @Override
            public void onFailure(Call<Stocks> call, Throwable t) {
                Log.e("getStocks threw: ", t.getMessage());
            }
        });


        String[] labels = new String[]{"1", "2", "3"};
        float[] values = new float[]{(float)9.9, (float)1.0, (float) 3.9};
        LineSet dataset = new LineSet(labels, values);
        dataset.addPoint(new Point("4", (float)9.9));
        dataset.addPoint(new Point("5", (float)8.9));
        dataset.addPoint(new Point("6", (float)7.9));


        dataset.setColor(ContextCompat.getColor(mContext, R.color.material_green_700));

        LineChartView lineChartView = (LineChartView)findViewById(R.id.linechart);
        lineChartView.addData(dataset);
        lineChartView.setAxisColor(ContextCompat.getColor(mContext, R.color.md_divider_white));
        lineChartView.setLabelsColor(0xffffffff);
        lineChartView.show();
    }
}
