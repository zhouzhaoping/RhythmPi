package zzp.rhythmPi;

import zzp.rhythmPi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.activity_login);
	 findViews();
	 showResults();
	 setListensers(); 
	}
	 	 
	 private Button button_goback;
	 private EditText view_username;
	 private EditText view_password;
	 	 
	 private void findViews()
	 {
		 button_goback = (Button) findViewById(R.id.signin_button);
		 view_username = (EditText) findViewById(R.id.username_edit);
		 view_password = (EditText) findViewById(R.id.password_edit);
	 }
	 private void showResults() {

	 }
	 
	 private void setListensers() {
		 button_goback.setOnClickListener(backMain);
	 }
	 
	 private Button.OnClickListener backMain = new Button.OnClickListener()
	 {
		  public void onClick(View v)
		 {
			  String username = view_username.getText().toString();
			  String password = view_password.getText().toString();
			  username = "162.105.171.3";
			  password = "123456";
			  NetThread.url = "http://" + username + "/cgi-bin/handler.py";
			  //NetThread netThread = new NetThread(password, -1, -1, -1);
			  Intent intent = new Intent();
			  intent.setClass(LoginActivity.this, MainActivity.class);
			  Bundle bundle = new Bundle();
			  bundle.putString("KEY_USERNAME", username);
			  bundle.putString("KEY_PASSWORD", password);
			  intent.putExtras(bundle);
			  startActivity(intent);
			  Toast.makeText(LoginActivity.this, "登录成功！", 
		                 Toast.LENGTH_SHORT).show(); 
			  LoginActivity.this.finish();
			  /*
			  if (netThread.sendPassword())
			  {
			   		Intent intent = new Intent();
					  intent.setClass(LoginActivity.this, MainActivity.class);
					  Bundle bundle = new Bundle();
					  bundle.putString("KEY_USERNAME", username);
					  bundle.putString("KEY_PASSWORD", password);
					  intent.putExtras(bundle);
					  startActivity(intent);
					  Toast.makeText(LoginActivity.this, "登录成功！", 
				                 Toast.LENGTH_SHORT).show(); 
					  LoginActivity.this.finish();
			  } else {
				  NetThread.url = "http://162.105.171.246/cgi-bin/handler.py";
				  Intent intent = new Intent();
				  intent.setClass(LoginActivity.this, MainActivity.class);
				  Bundle bundle = new Bundle();
				  bundle.putString("KEY_USERNAME", username);
				  bundle.putString("KEY_PASSWORD", password);
				  intent.putExtras(bundle);
				  startActivity(intent);
				  
				  Toast.makeText(LoginActivity.this, "IP地址或密码错误！", 
			                 Toast.LENGTH_SHORT).show(); 
				  LoginActivity.this.finish();
			  }*/
			  /*
			  if (username.equals("") || password.equals(""))
			  {
				  Toast.makeText(LoginActivity.this, "IP地址或密码不能为空！", 
			                 Toast.LENGTH_SHORT).show(); 
			  }
			  else if (username.equals("162.105.171.246") && password.equals("123456"))
			{
				// Close this Activity
				  Intent intent = new Intent();
				  intent.setClass(LoginActivity.this, MainActivity.class);
				  Bundle bundle = new Bundle();
				  bundle.putString("KEY_USERNAME", username);
				  bundle.putString("KEY_PASSWORD", password);
				  intent.putExtras(bundle);
				  startActivity(intent);
				  LoginActivity.this.finish();
			  } else {
				  Toast.makeText(LoginActivity.this, "IP地址或密码错误！", 
			                 Toast.LENGTH_SHORT).show(); 
			  }
			  */
		 }
	 }; 
}
