package com.example.uibestpractice;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Zhuye extends Activity{
	private WebView webview;
	@SuppressLint("SetJavaScriptEnabled") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.zhuye);
        webview = (WebView)findViewById(R.id.web_view);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
        	public boolean shouldOverrideUrlLoading(WebView view,String url){
        		view.loadUrl(url);
        		return true;
        	}
        });
        webview.loadUrl("http://imqxms.kuaizhan.com");
    }
}
