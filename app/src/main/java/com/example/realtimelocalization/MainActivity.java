package com.example.realtimelocalization;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button changeLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeLang = findViewById(R.id.ChangeLanguage);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showchangelanguagedialog();
            }
        });
    }

    private void showchangelanguagedialog() {

        final String[] listitems = {"English","हिंदी","मराठी"};
        final AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
        mbuilder.setTitle("Change Language");
        mbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i==0)
                {
                    setLocal("En");
                    recreate();
                }
                else if (i==1)
                {
                    setLocal("hi");
                    recreate();
                }
                else if(i==2)
                {
                    setLocal("mr");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog dialog = mbuilder.create();
        mbuilder.show();
    }

    private void setLocal(String en) {
        Locale locale=new Locale(en);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        //SharedPreferences.Editor editor = getSharedPreferences("Setting",MODE_PRIVATE).edit();
       //editor.putString("My_Lang",en);
        // editor.apply();
    }

    public void loadLocale()
    {
        SharedPreferences preferences = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocal(language);
    }
}
