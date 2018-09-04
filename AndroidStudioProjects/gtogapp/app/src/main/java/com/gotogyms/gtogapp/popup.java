package com.gotogyms.gtogapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Menu;

/**
 * Created by Jyothsna on 7/11/2017.
 */

public class popup extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.udetails_window);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
