package com.phwang.go;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class About extends Activity {
    @Override
    protected void onCreate(Bundle savedInstancesState) {
       super.onCreate(savedInstancesState);
        Log.d("+++++eeeGo+++++", "About");
       setContentView(R.layout.about);
    }
}
