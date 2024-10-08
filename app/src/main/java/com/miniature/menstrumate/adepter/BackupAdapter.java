package com.miniature.menstrumate.adepter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miniature.menstrumate.R;
import com.miniature.menstrumate.model.Settings;
import com.miniature.menstrumate.utils.JCGSQLiteHelper;

public class BackupAdapter extends ArrayAdapter<String> {
    private final Activity context;
    JCGSQLiteHelper db;
    int id;
    private final Integer[] imgid;
    private final String[] itemname;
    String key;
    TextView optionalText;
    Settings selectedSettings;
    int uid;
    String value;

    public BackupAdapter(Activity context2, String[] itemname2, Integer[] imgid2) {
        super(context2, R.layout.item_settings, itemname2);
        context = context2;
        itemname = itemname2;
        imgid = imgid2;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = context.getLayoutInflater().inflate(R.layout.item_settings, (ViewGroup) null, true);
        TextView extratxt = (TextView) rowView.findViewById(R.id.txt2Settings);
        optionalText = (TextView) rowView.findViewById(R.id.optionalText);
        ((TextView) rowView.findViewById(R.id.txt1Settings)).setText(itemname[position]);
        ((ImageView) rowView.findViewById(R.id.txtDate)).setImageResource(imgid[position].intValue());
        db = new JCGSQLiteHelper(getContext().getApplicationContext());
        if (position == 2) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.backup_des_cloud));
        }
        if (position == 1) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.backup_des_email));
        }
        if (position == 0) {
            extratxt.setText(getContext().getApplicationContext().getString(R.string.backup_des_sd));
        }
        return rowView;
    }

    public void initializeSettings() {
        id = selectedSettings.getId();
        uid = selectedSettings.getUid();
        key = selectedSettings.getKey();
        value = selectedSettings.getValueKey();
    }
}
