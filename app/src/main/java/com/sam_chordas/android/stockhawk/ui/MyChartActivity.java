package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.db.chart.listener.OnEntryClickListener;
import com.db.chart.model.LineSet;
import com.db.chart.view.LineChartView;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.service.RetrofitYQLInterface;
import com.sam_chordas.android.stockhawk.service.gson.Quote;
import com.sam_chordas.android.stockhawk.service.gson.Stocks;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyChartActivity extends Activity {
    /*
     * Created by Tal Charnes
     * Credit to Williamchart for the library used in this class for building the chart
     * Credit to Retrofit for helping with API calls
     */
    Context mContext;
    private String startDate;
    private String endDate;
    private String symbol;
    private final String JSONFORMAT = "json";
    private final String DIAGNOSTICS = "true";
    private final String ENV = "store://datatables.org/alltableswithkeys";
    private final String CALLBACK = "";
    private String[] labels;
    private float[] values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mContext = getApplicationContext();
        Intent myIntent = getIntent();
        symbol = myIntent.getStringExtra(getString(R.string.symbol));
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


        startDate = getStartDate();
        endDate = getEndDate();


        yqlCallSB.append("select * from yahoo.finance.historicaldata where symbol = \"")
        .append(symbol)
        .append("\" and startDate = \"")
        .append(startDate)
        .append("\" and endDate = \"")
        .append(endDate)
        .append("\"");
        String yqlCall = yqlCallSB.toString();

        call = retrofitYQLInterface.getStocks(yqlCall, JSONFORMAT, DIAGNOSTICS, ENV, CALLBACK);

        call.enqueue(new retrofit2.Callback<Stocks>() {
            @Override
            public void onResponse(Call<Stocks> call, Response<Stocks> response) {
                if(response != null){
                    Log.i(getString(R.string.retrofit_failure), "failure");
                }
                Stocks stocks = response.body();
                List<Quote> getQuoteList = stocks.getQuery().getResults().getQuote();
                String x = getQuoteList.get(0).getSymbol();
                labels = new String[getQuoteList.size()];
                values = new float[getQuoteList.size()];

                int minY = (int)Float.parseFloat(stocks.getQuery().getResults().getQuote().get(1).getClose());
                int maxY = (int) Float.parseFloat(stocks.getQuery().getResults().getQuote().get(1).getClose());

                    for(int i = 0; i < getQuoteList.size(); i++) {
                        String closePrice = stocks.getQuery().getResults().getQuote().get(i).getClose();
                        String date = formatDateString(stocks.getQuery().getResults().getQuote().get(i).getDate());
                        //get min/max Y values in order to set Y Axis
                        minY = Math.min(minY, (int)Float.parseFloat(closePrice));
                        maxY = Math.max(maxY, (int)Float.parseFloat(closePrice));
                        //need to put in the values opposite from how they are read
                        labels[getQuoteList.size()-1-i] = date;
                        values[getQuoteList.size()-1-i] = Float.parseFloat(closePrice);
                    }
                //format the table
                LineSet dataset = new LineSet(labels, values);
                dataset.setColor(ContextCompat.getColor(mContext, R.color.material_green_700));
                LineChartView lineChartView = (LineChartView)findViewById(R.id.linechart);
                lineChartView.addData(dataset);
                lineChartView.setAxisColor(ContextCompat.getColor(mContext, R.color.md_divider_white));
                lineChartView.setLabelsColor(0xffffffff);
                dataset.setDotsRadius(18);
                dataset.setDotsColor(ContextCompat.getColor(mContext, R.color.material_green_700));
                lineChartView.setOnEntryClickListener(new OnEntryClickListener() {
                    @Override
                    public void onClick(int setIndex, int entryIndex, Rect rect) {
                        StringBuilder priceString = new StringBuilder();
                        priceString.append("$")
                                    .append(values[entryIndex]);
                        Toast.makeText(getApplicationContext(), priceString, Toast.LENGTH_SHORT).show();
                    }
                });


                lineChartView.setAxisBorderValues(minY-1, maxY+1);
                //display table
                lineChartView.show();
            }

            @Override
            public void onFailure(Call<Stocks> call, Throwable t) {
                Log.e(getString(R.string.getstocks_failure), t.getMessage());
            }
        });
    }


    public String getEndDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = mdformat.format(calendar.getTime());
        return endDate;
    }

    public String getStartDate(){
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -7);
        Date startDate = cal.getTime();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        String startString = mdformat.format(startDate);
        return startString;
    }
    public String formatDateString(String date){
        return date.substring(5);
    }
}
