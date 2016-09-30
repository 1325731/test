package ca.qc.johnabbott.cs.cs616.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class ColorPickerActivity extends AppCompatActivity {

    /*
     * return param
     */
    public static class param {
        public static final String initial_color = "INITIAL_COLOR";
    }

    public static class result {
        public static final String chosen_color = "CHOSEN_COLOR";
    }

    private ColorPickerFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Version 1:
        Intent intent = getIntent(); // gets the intent that launched the activity
        //int color = intent.getExtras().getInt("INITIAL_COLOR");
        //Version 2:
        int color = intent.getIntExtra(param.initial_color, Color.BLACK); // use BLACK if not given

        fragment = (ColorPickerFragment) getSupportFragmentManager().findFragmentById(R.id.colorPicker_Fragment);
        fragment.loadColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_picker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ok) {

            // get current color from fragment;
            int color = fragment.getColor();

            //Version 3:
            Intent intent = new Intent();
            intent.putExtra(result.chosen_color, color);
            // setting the returned color
            setResult(RESULT_OK, intent);

            //Version 1:
            finish();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
