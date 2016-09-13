package com.example.a1325731.notes;

import android.app.TimePickerDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
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

        red.setColor(255,204,204);
        orange.setColor(255,229,204);
        yellow.setColor(255,255,204);
        green.setColor(204,255,204);
        cyan.setColor(204,255,255);
        blue.setColor(204,204,255);
        purple.setColor(229,204,255);
        pink.setColor(255,204,229);

        CircleViewHandler colorClickHandler = new CircleViewHandler();

        red.setOnClickListener(colorClickHandler);
        orange.setOnClickListener(colorClickHandler);
        yellow.setOnClickListener(colorClickHandler);
        green.setOnClickListener(colorClickHandler);
        cyan.setOnClickListener(colorClickHandler);
        blue.setOnClickListener(colorClickHandler);
        purple.setOnClickListener(colorClickHandler);
        pink.setOnClickListener(colorClickHandler);

        return root;
    }
    private class CircleViewHandler implements CircleView.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast toast = Toast.makeText(v.getContext(), "Hey",Toast.LENGTH_SHORT);
            toast.show();
            CircleView color = (CircleView) v;
         back.setBackgroundColor(color.getColor());
        }
    }
}
