package com.sasa.callrecorder.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.axet.androidlibrary.activities.AppCompatThemeActivity;
import com.sasa.callrecorder.R;
import com.sasa.callrecorder.app.CallApplication;
import com.sasa.callrecorder.services.RecordingService;

public class SettingInfoActivity extends AppCompatThemeActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static String ENABLE = MainActivity.class.getCanonicalName() + ".ENABLE";
    public static final String PREFERENCE_STORAGE_NAME = "clblob";
    public static final String PREFERENCE_STORAGE_KEY = "x0xF73QV1BaElCZOGmtJum+6hvoB8+yKkJ97hcnKloPmmb1+3lDLqeq3yGEBoipeFf3NK5K+cXU6JMACRVVQbg==";
    @Override
    public int getAppTheme() {
        return CallApplication.getTheme(this, R.style.RecThemeLight_NoActionBar, R.style.RecThemeDark_NoActionBar);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting_info);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText storageName   = (EditText)findViewById(R.id.storageID);
        final EditText storagekey   = (EditText)findViewById(R.id.storageKey);
        Button settingBtn = (Button)findViewById(R.id.settingInfo);
        final SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);

        storageName.setHint(shared.getString(PREFERENCE_STORAGE_NAME,"clblob"));
        storagekey.setHint(shared.getString(PREFERENCE_STORAGE_KEY,"x0xF73QV1BaElCZOGmtJum+6hvoB8+yKkJ97hcnKloPmmb1+3lDLqeq3yGEBoipeFf3NK5K+cXU6JMACRVVQbg=="));

        settingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor e = shared.edit();
                String str1 = storageName.getText().toString();
                String str2 = storagekey.getText().toString();
                if(str1.equals("") && str2.equals("")){
                    onBackPressed();
                    return;
                }
                else if(str1.equals("") || str2.equals(""))
                    return;

                e.putString(PREFERENCE_STORAGE_NAME, str1);
                e.putString(PREFERENCE_STORAGE_KEY, str2);
                e.apply();
                onBackPressed();
            }
        });



        Intent intent = getIntent();
        openIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        openIntent(intent);
    }

    @SuppressLint("RestrictedApi")
    void openIntent(Intent intent) {
        String a = intent.getAction();
        if (a != null && a.equals(ENABLE)) {
            MenuBuilder m = new MenuBuilder(this);
//            MenuItem item = m.add(Menu.NONE, R.id.action_call, Menu.NONE, "");
//            item.setEnabled(RecordingService.isEnabled(this));
//            onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");

        invalidateOptionsMenu();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
