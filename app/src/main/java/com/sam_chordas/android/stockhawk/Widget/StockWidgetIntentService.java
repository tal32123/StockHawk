package com.sam_chordas.android.stockhawk.Widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.ui.MyStocksActivity;

/**
 * Created by Tal on 9/5/2016.
 */
public class StockWidgetIntentService extends IntentService{


    public StockWidgetIntentService(){
        super("StockWidgetIntentService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(StockWidgetIntentService.class.getSimpleName(), "Handling intent");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, StockWidgetProvider.class));

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            Intent launchIntent = new Intent(this, MyStocksActivity.class);

            // Construct the RemoteViews object
            int layoutId = R.layout.stock_widget;
            RemoteViews views = new RemoteViews(this.getPackageName(), layoutId);

//            views.setEmptyView(R.id.widget_listview, R.id.widget_listview_emptyview);
//            views.setTextViewText(R.id.widget_listview, this.getString(R.string.appwidget_text));
            views.setTextViewText(R.id.widget_ticker, "APPL");
            views.setTextViewText(R.id.widget_percent, "1");
            views.setTextViewText(R.id.widget_change, "2");
//            views.setTextViewText(R.id.bid_prices, "99.0");
//            views.setTextViewText(R.id.changes, "10%");

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);


        }
    }
}
