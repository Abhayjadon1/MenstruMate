package com.miniature.menstrumate.servise;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.legacy.content.WakefulBroadcastReceiver;

public class  MenstruMateAlarmReceiver extends BroadcastReceiver {
    String sGiorniPrima;
    String sTipo;
    Intent service;

    public void onReceive(Context context, Intent intent) {
        sTipo = intent.getStringExtra("tipo");
        sGiorniPrima = intent.getStringExtra("giorniprima");
        service = new Intent(context,  MenstruMateSchedulingService.class);
        service.putExtra("tipo", sTipo);
        service.putExtra("giorniprima", sGiorniPrima);
        WakefulBroadcastReceiver.startWakefulService(context, service);
    }
}
