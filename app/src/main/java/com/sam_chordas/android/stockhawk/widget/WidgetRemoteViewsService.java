package com.sam_chordas.android.stockhawk.widget;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.support.v4.content.ContextCompat;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;

/**
 * Created by Tal on 9/5/2016.
 * This will be a remote views that controls the data being shown in the stock widget
 */
public class WidgetRemoteViewsService extends RemoteViewsService {
    public String LOG_TAG = WidgetRemoteViewsService.class.getSimpleName();

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
    private Cursor data;


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {

            @Override
            public void onCreate() {
                //nothing to do here
            }

            @Override
            public void onDataSetChanged() {
                if (data != null) {
                    data.close();
                }


                //Get data from content provider
                final long identityToken = Binder.clearCallingIdentity();

                Uri uri = QuoteProvider.Quotes.CONTENT_URI;
                data = getContentResolver().query(
                        uri,
                        QUOTE_COLUMNS,
                        QuoteColumns.ISCURRENT + " = ?",
                        new String[]{"1"},
                        null
                );
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }

            }

            @Override
            public int getCount() {
                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.list_item_quote);


                int stockID = data.getInt(INDEX_STOCK_ID);
                String stockSymbol = data.getString(INDEX_SYMBOL);
                String bidPrice = data.getString(INDEX_BIDPRICE);
                String percentChange = data.getString(INDEX_PERCENT_CHANGE);
                String change = data.getString(INDEX_CHANGE);
                int isUP = data.getInt(INDEX_ISUP);

                String description = makeDescriptionString(stockSymbol, percentChange, bidPrice);

                setRemoteContentDescription(views, description);


                views.setTextViewText(R.id.stock_symbol, stockSymbol);
                views.setTextViewText(R.id.bid_price, bidPrice);
                if (isUP == 1) {
                    views.setTextColor(R.id.change, ContextCompat.getColor(getApplicationContext(), R.color.material_green_700));
                } else {
                    views.setTextColor(R.id.change, ContextCompat.getColor(getApplicationContext(), R.color.material_red_700));
                }
                views.setTextViewText(R.id.change, percentChange);

                final Intent chartIntent = new Intent();
               chartIntent.putExtra("symbol", stockSymbol);
                chartIntent.setData(QuoteProvider.Quotes.CONTENT_URI);
                views.setOnClickFillInIntent(R.id.widget_list_item, chartIntent);


                return views;
            }
            private void setRemoteContentDescription(RemoteViews views, String description) {
                views.setContentDescription(R.layout.list_item_quote, description);
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.list_item_quote);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {

                if (data.moveToPosition(position))
                return data.getLong(INDEX_STOCK_ID);
                return position;
            }


            @Override
            public boolean hasStableIds() {
                //Change to false if same id ends up referring to different object. Shouldn't be necessary.
                return true;
            }
        };

           // return null;

    }
    public String makeDescriptionString(String stockSymbol, String percentChange, String bidPrice){
        StringBuilder description = new StringBuilder();
        for(int i = 0; i < stockSymbol.length(); i++){
            description.append(stockSymbol.charAt(i));
            description.append(" ");
        }
        description.append("This stock has changed");
        description.append(percentChange);
        description.append("The current bid price is");
        description.append(bidPrice);

        return description.toString();
    }
}
