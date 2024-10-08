package com.miniature.menstrumate.pill_reminder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.miniature.menstrumate.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class ReminderAddActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private static final String KEY_ACTIVE = "active_key";
    private static final String KEY_DATE = "date_key";
    private static final String KEY_REPEAT = "repeat_key";
    private static final String KEY_REPEAT_NO = "repeat_no_key";
    private static final String KEY_REPEAT_TYPE = "repeat_type_key";
    private static final String KEY_TIME = "time_key";
    private static final String KEY_TITLE = "title_key";
    private static final long milDay = 86400000;
    private static final long milHour = 3600000;
    private static final long milMinute = 60000;
    private static final long milMonth = 2592000000L;
    private static final long milWeek = 604800000;
    private String mActive;
    private Calendar mCalendar;
    private String mDate;
    private TextView mDateText;
    private int mDay;
    private FloatingActionButton mFAB1;
    private FloatingActionButton mFAB2;
    private int mHour;
    private int mMinute;
    private int mMonth;
    private String mRepeat;

    public String mRepeatNo;

    public TextView mRepeatNoText;

    public TextView mRepeatText;
    private long mRepeatTime;

    public String mRepeatType;

    public TextView mRepeatTypeText;
    private String mTime;
    private TextView mTimeText;

    public String mTitle;

    public EditText mTitleText;
    private Toolbar mToolbar;
    private int mYear;


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.pill_add_reminder);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        this.mTitleText = (EditText) findViewById(R.id.reminder_title);
        this.mDateText = (TextView) findViewById(R.id.set_date);
        this.mTimeText = (TextView) findViewById(R.id.set_time);
        this.mRepeatText = (TextView) findViewById(R.id.set_repeat);
        this.mRepeatNoText = (TextView) findViewById(R.id.set_repeat_no);
        this.mRepeatTypeText = (TextView) findViewById(R.id.set_repeat_type);
        this.mFAB1 = (FloatingActionButton) findViewById(R.id.starred1);
        this.mFAB2 = (FloatingActionButton) findViewById(R.id.starred2);
        setSupportActionBar(this.mToolbar);
        getSupportActionBar().setTitle((int) R.string.title_activity_add_reminder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.mActive = "true";
        this.mRepeat = "true";
        this.mRepeatNo = Integer.toString(1);
        this.mRepeatType = "Minute";
        Calendar instance = Calendar.getInstance();
        this.mCalendar = instance;
        this.mHour = instance.get(11);
        this.mMinute = this.mCalendar.get(12);
        this.mYear = this.mCalendar.get(1);
        this.mMonth = this.mCalendar.get(2) + 1;
        this.mDay = this.mCalendar.get(5);
        this.mDate = this.mDay + "/" + this.mMonth + "/" + this.mYear;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mHour);
        sb.append(":");
        sb.append(this.mMinute);
        this.mTime = sb.toString();
        this.mTitleText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String unused = ReminderAddActivity.this.mTitle = charSequence.toString().trim();
                ReminderAddActivity.this.mTitleText.setError((CharSequence) null);
            }
        });
        this.mDateText.setText(this.mDate);
        this.mTimeText.setText(this.mTime);
        this.mRepeatNoText.setText(this.mRepeatNo);
        this.mRepeatTypeText.setText(this.mRepeatType);
        TextView textView = this.mRepeatText;
        textView.setText("Every " + this.mRepeatNo + " " + this.mRepeatType + "(s)");
        if (bundle != null) {
            String string = bundle.getString(KEY_TITLE);
            this.mTitleText.setText(string);
            this.mTitle = string;
            String string2 = bundle.getString(KEY_TIME);
            this.mTimeText.setText(string2);
            this.mTime = string2;
            String string3 = bundle.getString(KEY_DATE);
            this.mDateText.setText(string3);
            this.mDate = string3;
            String string4 = bundle.getString(KEY_REPEAT);
            this.mRepeatText.setText(string4);
            this.mRepeat = string4;
            String string5 = bundle.getString(KEY_REPEAT_NO);
            this.mRepeatNoText.setText(string5);
            this.mRepeatNo = string5;
            String string6 = bundle.getString(KEY_REPEAT_TYPE);
            this.mRepeatTypeText.setText(string6);
            this.mRepeatType = string6;
            this.mActive = bundle.getString(KEY_ACTIVE);
        }
        if (this.mActive.equals("false")) {
            this.mFAB1.setVisibility(View.VISIBLE);
            this.mFAB2.setVisibility(View.GONE);
        } else if (this.mActive.equals("true")) {
            this.mFAB1.setVisibility(View.GONE);
            this.mFAB2.setVisibility(View.VISIBLE);
        }
    }


    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence(KEY_TITLE, this.mTitleText.getText());
        bundle.putCharSequence(KEY_TIME, this.mTimeText.getText());
        bundle.putCharSequence(KEY_DATE, this.mDateText.getText());
        bundle.putCharSequence(KEY_REPEAT, this.mRepeatText.getText());
        bundle.putCharSequence(KEY_REPEAT_NO, this.mRepeatNoText.getText());
        bundle.putCharSequence(KEY_REPEAT_TYPE, this.mRepeatTypeText.getText());
        bundle.putCharSequence(KEY_ACTIVE, this.mActive);
    }

    public void setTime(View view) {
        Calendar instance = Calendar.getInstance();
        TimePickerDialog newInstance = TimePickerDialog.newInstance(this, instance.get(11), instance.get(12), false);
        newInstance.setThemeDark(false);
        newInstance.show(getSupportFragmentManager(), "Timepickerdialog");
    }

    public void setDate(View view) {
        Calendar instance = Calendar.getInstance();
        DatePickerDialog.newInstance(this, instance.get(1), instance.get(2), instance.get(5)).show(getSupportFragmentManager(), "Datepickerdialog");
    }


    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i2, int i3) {
        int i4 = i2 + 1;
        this.mDay = i3;
        this.mMonth = i4;
        this.mYear = i;
        String str = i3 + "/" + i4 + "/" + i;
        this.mDate = str;
        this.mDateText.setText(str);
    }

    public void selectFab1(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.starred1);
        this.mFAB1 = floatingActionButton;
        floatingActionButton.setVisibility(View.GONE);
        FloatingActionButton floatingActionButton2 = (FloatingActionButton) findViewById(R.id.starred2);
        this.mFAB2 = floatingActionButton2;
        floatingActionButton2.setVisibility(View.VISIBLE);
        this.mActive = "true";
    }

    public void selectFab2(View view) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.starred2);
        this.mFAB2 = floatingActionButton;
        floatingActionButton.setVisibility(View.GONE);
        FloatingActionButton floatingActionButton2 = (FloatingActionButton) findViewById(R.id.starred1);
        this.mFAB1 = floatingActionButton2;
        floatingActionButton2.setVisibility(View.VISIBLE);
        this.mActive = "false";
    }

    public void onSwitchRepeat(View view) {
        if (((Switch) view).isChecked()) {
            this.mRepeat = "true";
            TextView textView = this.mRepeatText;
            textView.setText("Every " + this.mRepeatNo + " " + this.mRepeatType + "(s)");
            return;
        }
        this.mRepeat = "false";
        this.mRepeatText.setText(R.string.repeat_off);
    }

    public void selectRepeatType(View view) {
        final String[] strArr = {"Minute", "Hour", "Day", "Week", "Month"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Type");
        builder.setItems(strArr, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String unused = ReminderAddActivity.this.mRepeatType = strArr[i];
                ReminderAddActivity.this.mRepeatTypeText.setText(ReminderAddActivity.this.mRepeatType);
                TextView access$500 = ReminderAddActivity.this.mRepeatText;
                access$500.setText("Every " + ReminderAddActivity.this.mRepeatNo + " " + ReminderAddActivity.this.mRepeatType + "(s)");
            }
        });
        builder.create().show();
    }

    public void setRepeatNo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Number");
        final EditText editText = new EditText(this);
        editText.setInputType(2);
        builder.setView(editText);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText.getText().toString().length() == 0) {
                    String unused = ReminderAddActivity.this.mRepeatNo = Integer.toString(1);
                    ReminderAddActivity.this.mRepeatNoText.setText(ReminderAddActivity.this.mRepeatNo);
                    TextView access$500 = ReminderAddActivity.this.mRepeatText;
                    access$500.setText("Every " + ReminderAddActivity.this.mRepeatNo + " " + ReminderAddActivity.this.mRepeatType + "(s)");
                    return;
                }
                String unused2 = ReminderAddActivity.this.mRepeatNo = editText.getText().toString().trim();
                ReminderAddActivity.this.mRepeatNoText.setText(ReminderAddActivity.this.mRepeatNo);
                TextView access$5002 = ReminderAddActivity.this.mRepeatText;
                access$5002.setText("Every " + ReminderAddActivity.this.mRepeatNo + " " + ReminderAddActivity.this.mRepeatType + "(s)");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    public void saveReminder() {
        int addReminder = new ReminderDatabase(this).addReminder(new Reminder(this.mTitle, this.mDate, this.mTime, this.mRepeat, this.mRepeatNo, this.mRepeatType, this.mActive));
        Calendar calendar = this.mCalendar;
        int i = this.mMonth - 1;
        this.mMonth = i;
        calendar.set(2, i);
        this.mCalendar.set(1, this.mYear);
        this.mCalendar.set(5, this.mDay);
        this.mCalendar.set(11, this.mHour);
        this.mCalendar.set(12, this.mMinute);
        this.mCalendar.set(13, 0);
        if (this.mRepeatType.equals("Minute")) {
            this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milMinute;
        } else if (this.mRepeatType.equals("Hour")) {
            this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milHour;
        } else if (this.mRepeatType.equals("Day")) {
            this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milDay;
        } else if (this.mRepeatType.equals("Week")) {
            this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milWeek;
        } else if (this.mRepeatType.equals("Month")) {
            this.mRepeatTime = ((long) Integer.parseInt(this.mRepeatNo)) * milMonth;
        }
        if (this.mActive.equals("true")) {
            if (this.mRepeat.equals("true")) {
                new AlarmReceiver().setRepeatAlarm(getApplicationContext(), this.mCalendar, addReminder, this.mRepeatTime);
            } else if (this.mRepeat.equals("false")) {
                new AlarmReceiver().setAlarm(getApplicationContext(), this.mCalendar, addReminder);
            }
        }
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_reminder1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
            return true;
        } else if (itemId != R.id.save_reminder) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            this.mTitleText.setText(this.mTitle);
            if (this.mTitleText.getText().toString().length() == 0) {
                this.mTitleText.setError("Reminder Title cannot be blank!");
            } else {
                saveReminder();
            }
            return true;
        }
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {


        this.mHour = hourOfDay;
        this.mMinute = minute;
        if (minute < 10) {
            this.mTime = hourOfDay + ":0" + minute;
        } else {
            this.mTime = hourOfDay + ":" + minute;
        }
        this.mTimeText.setText(this.mTime);
    }

}
