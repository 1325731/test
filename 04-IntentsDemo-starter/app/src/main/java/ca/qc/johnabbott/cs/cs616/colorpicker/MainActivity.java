package ca.qc.johnabbott.cs.cs616.colorpicker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MainActivityFragment mainActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mainActivityFragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.main_Fragment);
        mainActivityFragment.SetOnCircleChosen (new MainActivityFragment.OnCircleChosen() {
            @Override
           public void onCircleChosen(int num, int currentColor) {
                Intent intent = new Intent(MainActivity.this, ColorPickerActivity.class);
                intent.putExtra(ColorPickerActivity.param.initial_color, currentColor);
                startActivityForResult(intent, num);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_test) {

            // Version 1:
            Intent intent = new Intent(this, ColorPickerActivity.class);
            //intent.putExtra("INITIAL_COLOR", Color.GREEN); // TODO: swag
            // Version 2:
            intent.putExtra(ColorPickerActivity.param.initial_color, Color.RED); // TODO: swag
            //startActivity(intent);

            // Version 3:
            startActivityForResult(intent,6); //second parameter is request code (do not use -1)

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int color = data.getIntExtra(ColorPickerActivity.result.chosen_color, Color.TRANSPARENT);
            MainActivityFragment fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.main_Fragment);

            if (requestCode == 6)
                for (int i=0; i<6; i++)
                    fragment.setCircleColor(i,color);
            else
                fragment.setCircleColor(requestCode, color);
        }

    }
}
