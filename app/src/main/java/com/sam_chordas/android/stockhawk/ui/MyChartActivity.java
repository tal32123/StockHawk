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
}
