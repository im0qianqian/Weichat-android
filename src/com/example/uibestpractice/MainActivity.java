package com.example.uibestpractice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index);
        Button button1 = (Button)findViewById(R.id.start);
        Button button2 = (Button)findViewById(R.id.guanyu1);
        Button button3 = (Button)findViewById(R.id.zhuye);
        button1.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent intent = new Intent(MainActivity.this,Index.class);
        		startActivity(intent);
        	}
        });
        button2.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent intent = new Intent(MainActivity.this,Guanyu.class);
        		startActivity(intent);
        	}
        });
        button3.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent intent = new Intent(MainActivity.this,Zhuye.class);
        		startActivity(intent);
        	}
        });
	}
}
