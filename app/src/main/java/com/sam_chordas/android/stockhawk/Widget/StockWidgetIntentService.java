package com.sam_chordas.android.stockhawk.Widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.ui.MyStocksActivity;

/**
 * Created by Tal on 9/5/2016.
 */
public class StockWidgetIntentService extends IntentService{


    //define necessary columns
    private static final String[] QUOTE_COLUMNS = {
            QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
            QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP};

    private static final int INDEX_STOCK_ID = 0;
    private static final int INDEX_SYMBOL = 1;
    private static final int INDEX_BIDPRICE = 2;
    private static final int INDEX_PERCENT_CHANGE = 3;
    private static final int INDEX_CHANGE = 4;
    private static final int INDEX_ISUP = 5;



    public StockWidgetIntentService(){
        super("StockWidgetIntentService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //Retrieve all widget ids which are needed to update
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, StockWidgetProvider.class));

        //Get data from content provider
        Uri uri = QuoteProvider.Quotes.CONTENT_URI;
        Cursor data = getContentResolver().query(
                uri,
                QUOTE_COLUMNS,
                QuoteColumns.ISCURRENT + " = ?",
                new String[]{"1"},
                null
        );

        if (data == null){
            return;
        }
        if (!data.moveToFirst()){
            data.close();
            return;
        }
        Log.i(StockWidgetIntentService.class.getSimpleName(), DatabaseUtils.dumpCursorToString(data));
        int stockID = data.getInt(INDEX_STOCK_ID);
        String stockSymbol = data.getString(INDEX_SYMBOL);
        String bidPrice = data.getString(INDEX_BIDPRICE);
        String percentChange = data.getString(INDEX_PERCENT_CHANGE);
        String change = data.getString(INDEX_CHANGE);
        int isUP = data.getInt(INDEX_ISUP);

        data.close();


        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            Intent launchIntent = new Intent(this, MyStocksActivity.class);

            // Construct the RemoteViews object
            int layoutId = R.layout.stock_widget;
            RemoteViews views = new RemoteViews(this.getPackageName(), layoutId);

//            views.setEmptyView(R.id.widget_listview, R.id.widget_listview_emptyview);

            views.setTextViewText(R.id.widget_symbol, stockSymbol);
            views.setTextViewText(R.id.widget_change, percentChange);
            views.setTextViewText(R.id.widget_bid_price, bidPrice);
            if(isUP == 1){

            }
            else;


            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);


        }
    }
}
