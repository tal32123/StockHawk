package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.view.LineChartView;
import com.sam_chordas.android.stockhawk.R;

public class MyChartActivity extends Activity {
    /*
     * Created by Tal Charnes
     * Credit to Williamchart for the library used in this class for building the chart
     */
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mContext = getApplicationContext();
        createChart();
    }

    private void createChart(){

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

    //start querying data from API
    // Trailing slash is needed

//    public interface YQLService {
//        @GET("users/{user}/repos")
//        Call<List<Repo>> listRepos(@Path("user") String user);
//    }
//    public static final String BASE_URL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22YHOO%22%20and%20startDate%20%3D%20%222009-09-11%22%20and%20endDate%20%3D%20%222010-03-10%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();
//
//    YQLService service = retrofit.create(YQLService.class);

}
