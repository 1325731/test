package com.example.a1325731.notes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    // Initializing
    private CircleView red;
    private CircleView orange;
    private CircleView yellow;
    private CircleView green;
    private CircleView cyan;
    private CircleView blue;
    private CircleView purple;
    private CircleView pink;
    private LinearLayout back;
    private Switch reminder;
    private EditText title;
    private EditText body;
    private EditText date;
    private EditText time;
    private Date dateTime;
    private Calendar c;
    private int reminderColor;

    // Date related variables
    private int startHour = 8;
    private int startMin  = 0;
    private int extraDay  = 1;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Grabbing the colors from the fragment
        red     = (CircleView) root.findViewById(R.id.color1_CircleView);
        orange  = (CircleView) root.findViewById(R.id.color2_CircleView);
        yellow  = (CircleView) root.findViewById(R.id.color3_CircleView);
        green   = (CircleView) root.findViewById(R.id.color4_CircleView);
        cyan    = (CircleView) root.findViewById(R.id.color5_CircleView);
        blue    = (CircleView) root.findViewById(R.id.color6_CircleView);
        purple  = (CircleView) root.findViewById(R.id.color7_CircleView);
        pink    = (CircleView) root.findViewById(R.id.color8_CircleView);

        back        = (LinearLayout) root.findViewById(R.id.back_LinearLayout);
        reminder    = (Switch) root.findViewById(R.id.reminder_Switch);
        title       = (EditText) root.findViewById(R.id.title_EditText);
        body        = (EditText) root.findViewById(R.id.body_EditText);

        // Initializing the time and date
        date = (EditText) root.findViewById(R.id.date_EditText);
        time = (EditText) root.findViewById(R.id.time_EditText);

        // These variables are useful for passing a complete date to MainActivity
        c = Calendar.getInstance();
        dateTime = c.getTime();

        dateSet(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH) + extraDay);
        timeSet(startHour,startMin);

        // Setting the colors
        red.setColor(getResources().getColor(R.color.base0A));      // FF9696
        orange.setColor(getResources().getColor(R.color.base0B));   // FFC896
        yellow.setColor(getResources().getColor(R.color.base0C));   // FFFF96
        green.setColor(getResources().getColor(R.color.base0D));    // 96FF96
        cyan.setColor(getResources().getColor(R.color.base0E));     // 96FFFF
        blue.setColor(getResources().getColor(R.color.base0F));     // 9696FF
        purple.setColor(getResources().getColor(R.color.base0G));   // C896FF
        pink.setColor(getResources().getColor(R.color.base0H));     // FF96C8

        // Having the colors switch the background onClick handler
        CircleViewHandler colorClickHandler = new CircleViewHandler();

        red.setOnClickListener(colorClickHandler);
        orange.setOnClickListener(colorClickHandler);
        yellow.setOnClickListener(colorClickHandler);
        green.setOnClickListener(colorClickHandler);
        cyan.setOnClickListener(colorClickHandler);
        blue.setOnClickListener(colorClickHandler);
        purple.setOnClickListener(colorClickHandler);
        pink.setOnClickListener(colorClickHandler);

        // OnClickListener for Date
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date initial = new Date();   // TODO: use previous value or "tomorrow at 8:00"
                // create and show the DatePicker with starting date
                DialogFragment dialogFragment = DatePickerDialogFragment.createDatePicker(
                        initial,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dateSet(year, month, dayOfMonth);
                            }
                        });
                dialogFragment.show(getFragmentManager(), "timePicker");
            }
        });

        // OnClickListener for Time
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date initial = new Date();   // TODO: use previous value or "tomorrow at 8:00"
                // create and show the TimePicker with starting time
                DialogFragment dialogFragment = TimePickerDialogFragment.create(
                        initial,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                               timeSet(hourOfDay, minute);
                            } });
                dialogFragment.show(getFragmentManager(), "timePicker");
            }
        });

        // Reminder Switch
        reminder.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    date.setVisibility(Switch.VISIBLE);
                    time.setVisibility(Switch.VISIBLE);
                }
                else {
                    date.setVisibility(Switch.GONE);
                    time.setVisibility(Switch.GONE);
                }
            }
        });

        return root;
    }

    private class CircleViewHandler implements CircleView.OnClickListener {

        @Override
        public void onClick(View v) {
            // This handler is CircleView specific, so we can cast the View to a Circle View
            CircleView color = (CircleView) v;

            reminderColor = color.getColor();
            back.setBackgroundColor(color.getColor());
        }
    }

    // dateSet function used for initialization and setting it
    public Date dateSet(int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Date d = cal.getTime();

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        dateTime = c.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        String dateStr = dateFormat.format(d);

        date.setText(dateStr);
        return d;
    }

    // timeSet function used for initialization and setting it
    public Date timeSet(int hourOfDay, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        Date d = cal.getTime();

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        dateTime = c.getTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String timeStr = timeFormat.format(d);

        time.setText(timeStr);
        return d;
    }

    // returns a Note object to MainActivity (used there)
    public Note getData() {

        Note note = new Note();

        note.setTitle(title.toString());
        note.setBody(body.toString());
        note.setCategory(reminderColor);
        note.setHasReminder(reminder.isChecked());
        note.setReminder(dateTime);

        return note;
    }
}

