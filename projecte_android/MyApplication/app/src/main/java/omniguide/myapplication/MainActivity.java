package omniguide.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sync1(View view) {
        // Do something when sync
        //Sync first Xiaomi

        //this.LBand = new XiaomiBand("C8:0F:10:0B:1F:9D");//8D



        TextView myText = (TextView) findViewById(R.id.textView2);
        myText.setText(this.LBand.state);
        //setContentView(R.layout.activity_sync2);*/
    }

    public void sync2(View view){
        //sync second xiaomi
        setContentView(R.layout.activity_guide);
    }

    public void skip(View view){
        //Nothing
        setContentView(R.layout.activity_guide);
    }

    public void left(View view){
        //Vibrate left
    }

    public void right(View view){
        //Vibrate right
    }

    public void obstacle(View view){
        //Vibrate both
    }

    public void destination(View view){
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 400 milliseconds
        v.vibrate(400);
    }


    public XiaomiBand LBand;
    public XiaomiBand RBand;
}
