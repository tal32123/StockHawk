package com.sam_chordas.android.stockhawk.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.sam_chordas.android.stockhawk.R;

public class MyToastBroadcastReceiver extends BroadcastReceiver {
    public MyToastBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, R.string.invalid_stock_string, Toast.LENGTH_SHORT).show();
    }
}
