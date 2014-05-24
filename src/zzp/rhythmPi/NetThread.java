package zzp.rhythmPi;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;




import android.util.Log;

public class NetThread{
	
	int password;
	int type;
	int buttonId;
	int volumn;
	static String url;
	
	String requestString;
	String retSrc;
	
	NetThread(int _password, int _type, int _buttonId, int _volumn){
		password = _password;
		type = _type;
		buttonId = _buttonId;
		volumn = _volumn;
	}
	
	public void sendButton(){
		try{
			requestString = url + "?type=" + type + "&" + "id=" + buttonId;
			Thread t = new Thread(connect);
			t.start();
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void sendVolumn(){
		try{
			requestString = url + "?volum=" + volumn;
			Thread t = new Thread(connect);
			t.start();
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public boolean sendPassword(){
		try{
			requestString = url + "?password=" + password;
			Thread t = new Thread(connect2);
			t.start();
			t.join();
		} catch (Exception e) {
            e.printStackTrace();
        }
		if (retSrc.equals("yes"))
			return true;
		return false;
	}
	
	public Runnable connect = new Runnable(){
		@Override
		public void run(){
			try{
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet(requestString);
				Log.d("send button", requestString);
				// 发送请求
				client.execute(request);
			} catch (Exception e) {
                //Toast.makeText(MainFrame.this,"无法链接网络！", 1).show();
                e.printStackTrace();
            }
		}
		
	};
	
	public Runnable connect2 = new Runnable(){
		@Override
		public void run(){
			try{
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet(requestString);
				Log.d("send button", requestString);
				// 发送请求
				HttpResponse httpResponse = client.execute(request);
				retSrc = EntityUtils.toString(httpResponse.getEntity());
			} catch (Exception e) {
                //Toast.makeText(MainFrame.this,"无法链接网络！", 1).show();
                e.printStackTrace();
            }
		}
	};
}