package com.example.uibestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Guanyu extends Activity{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guanyu);
    }
}
