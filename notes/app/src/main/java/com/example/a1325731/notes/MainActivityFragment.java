package com.example.a1325731.notes;

import android.app.TimePickerDialog;
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
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

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
    private EditText date;
    private EditText time;

    /*
    Date d = new Date();
    Calender cal = calender.getInstance();
    cal.setTime(d);
    int year = cal.get(Calender.YEAR);
    year++;
    cal.set(Calender.YEAR,year);
    d = cal.getTime();
     */

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //DatePicker date = new DatePicker();
        //TimePickerDialog time = new TimePickerDialog();

        // Colors and setting them
        red = (CircleView) root.findViewById(R.id.color1_CircleView);
        orange = (CircleView) root.findViewById(R.id.color2_CircleView);
        yellow = (CircleView) root.findViewById(R.id.color3_CircleView);
        green = (CircleView) root.findViewById(R.id.color4_CircleView);
        cyan = (CircleView) root.findViewById(R.id.color5_CircleView);
        blue = (CircleView) root.findViewById(R.id.color6_CircleView);
        purple = (CircleView) root.findViewById(R.id.color7_CircleView);
        pink = (CircleView) root.findViewById(R.id.color8_CircleView);
        back = (LinearLayout) root.findViewById(R.id.back_LinearLayout);
        reminder = (Switch) root.findViewById(R.id.reminder_Switch);
        date = (EditText) root.findViewById(R.id.date_EditText);
        time = (EditText) root.findViewById(R.id.time_EditText);

        red.setColor(255,150,150);
        orange.setColor(255,200,150);
        yellow.setColor(255,255,150);
        green.setColor(150,255,150);
        cyan.setColor(150,255,255);
        blue.setColor(150,150,255);
        purple.setColor(200,150,255);
        pink.setColor(255,150,200);

        CircleViewHandler colorClickHandler = new CircleViewHandler();

        red.setOnClickListener(colorClickHandler);
        orange.setOnClickListener(colorClickHandler);
        yellow.setOnClickListener(colorClickHandler);
        green.setOnClickListener(colorClickHandler);
        cyan.setOnClickListener(colorClickHandler);
        blue.setOnClickListener(colorClickHandler);
        purple.setOnClickListener(colorClickHandler);
        pink.setOnClickListener(colorClickHandler);

        // Reminder switch
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
         back.setBackgroundColor(color.getColor());
        }
    }
}
