package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "TEST");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFagment())
                    .commit();
        }
        Log.i("On create", "On create");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("On pause", "On pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("On resume", "On resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("On start", "On start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("On stop", "On stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("On destroy", "On destroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.detailfragment, menu);
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
            startActivity(new Intent(this, SettingsActivity.class ));
        }
        if (id == R.id.preferred_location) {
            Intent intent = new Intent(Intent.ACTION_VIEW);

            SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
            String location = shared.getString(getString(R.string.settings_location_key),
                    getString(R.string.default_settings_location));

            Uri geoLocation  =Uri.parse("geo:0,0?").buildUpon()
                    .appendQueryParameter("q", location)
                    .build();

            intent.setData(geoLocation);
            if (intent.resolveActivity(getPackageManager()) != null) {
                Log.i("intentPreferred location", geoLocation.toString());
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
