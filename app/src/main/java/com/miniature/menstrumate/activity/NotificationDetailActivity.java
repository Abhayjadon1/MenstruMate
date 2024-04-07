package com.miniature.menstrumate.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.miniature.menstrumate.R;
import com.miniature.menstrumate.adepter.NotificationDetailsAdapter;
import com.miniature.menstrumate.model.Settings;
import com.miniature.menstrumate.utils.JCGSQLiteHelper;

public class NotificationDetailActivity extends AppCompatActivity {
    int activeUID;
    Cursor cursorListEntry;
    JCGSQLiteHelper db;
    int id;
    String nameString;
    String position;
    int thePosition;
    TextView txtTitleNotific;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        position = getIntent().getStringExtra("position");
        txtTitleNotific = (TextView) findViewById(R.id.txtTitleNotific);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new JCGSQLiteHelper(getApplicationContext());
        activeUID = db.readActiveUID();
        db.updateSettings(new Settings(id, activeUID, "tempalarm", position));
        if (position.equals("0")) {
            txtTitleNotific.setText(getString(R.string.period_alarm));
        } else if (position.equals("1")) {
            txtTitleNotific.setText(getString(R.string.fertility_alarm));
        } else if (position.equals("2")) {
            txtTitleNotific.setText(getString(R.string.ovulation_alarm));
        } else {
            thePosition = Integer.valueOf(position).intValue();
            nameString = db.selectNameNotification(activeUID, thePosition);
            txtTitleNotific.setText(nameString);
        }
        cursorListEntry = db.readDetailsAlarm(activeUID, Integer.valueOf(position).intValue());
        ListView notificationItems = (ListView) findViewById(R.id.listDefNotificationsDetails);
        notificationItems.setAdapter(new NotificationDetailsAdapter(this, cursorListEntry, 0));
    }
}
