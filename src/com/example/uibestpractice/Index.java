package com.example.uibestpractice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
public class Index extends Activity{
	private ListView msgListView;
	private EditText inputText;
	private Button send;
	private MsgAdapter adapter;
	private List<Msg> msgList = new ArrayList<Msg>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initMsgs();
        adapter = new MsgAdapter(Index.this,R.layout.msg_item,msgList);
        inputText = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        msgListView = (ListView)findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());  
        send.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String content = inputText.getText().toString();
        		if(!"".equals(content)){
        			String k="http://www.tuling123.com/openapi/api?key=a5a28d4dc9db003c009f5c49f8a53714&info="+content;
        			Msg msg1 = new Msg(content ,Msg.TYPE_SENT);
        			msgList.add(msg1);
        			adapter.notifyDataSetChanged();
        			msgListView.setSelection(msgList.size());
        			inputText.setText("");
        			
        			k=posturl(k);
        			char c[]=k.toCharArray();
        			k=chuli(c);
        			Msg msg2 = new Msg(k ,Msg.TYPE_RECEIVED);
        			msgList.add(msg2);
        			adapter.notifyDataSetChanged();
        			msgListView.setSelection(msgList.size());
        			inputText.setText("");
        		}
        	}
        });
    }
public String chuli(char c[]){
	int k=0;
	String a="";
	for(int i=0;i<c.length-3;i++)
	{
		if(k>=5)a+=c[i];
		if(c[i]=='"')k++;
	}
	if(a=="")a="这句话好难懂，让我再思考一下！";
	return a;
}
public String chuli2(char c[]){
	String a="";
	int k=0;
	for(int i=0;i<c.length;i++){
		if(k==3&&c[i]!='"')a+=c[i];
		if(c[i]=='"')k++;
	}
	return a;
}
    private void initMsgs() {
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build()); 
    	String k=posturl("http://api.hitokoto.us/rand");
    	char c[]=k.toCharArray();
    	k=chuli2(c);
		Msg msg = new Msg(k,Msg.TYPE_RECEIVED);
		msgList.add(msg);
	}

    public String posturl(String url){
        InputStream is = null;
        String result = "";

        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }catch(Exception e){
            return "Fail to establish http connection!"+e.toString();
        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();

            result=sb.toString();
        }catch(Exception e){
            return "Fail to convert net stream!";
        }

        return result;
    }
}
