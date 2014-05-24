package zzp.rhythmPi;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class SettingActivity extends Activity {

	private ImageView button_back;
	private Button button_confirm;
	private SeekBar seekBar; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);
		 
		findViews();
		showResults();
		setListensers();
	}
	
	
	private void findViews()
	{		
		button_back = (ImageView) findViewById(R.id.go_back_button);
		button_confirm = (Button) findViewById(R.id.setting_change_button);
		seekBar = (SeekBar) findViewById(R.id.volumn_seekBar);
	}
	private void showResults() {
		seekBar.setMax(10);//设置最大刻度
		seekBar.setProgress(MainActivity.sounds);//设置当前刻度
	 }
	private void setListensers() 
	{
		button_back.setOnClickListener(goBack);
		button_confirm.setOnClickListener(confirm);
	 }
	
	private Button.OnClickListener goBack = new Button.OnClickListener()
	{
		public void onClick(View v)
		{
			 SettingActivity.this.finish();
		}
	}; 	
	
	private Button.OnClickListener confirm = new Button.OnClickListener()
	 {
		  public void onClick(View v)
		 {
			  Toast.makeText(SettingActivity.this, "当前音量值： " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
			  NetThread netThread = new NetThread(-1, -1, -1, seekBar.getProgress());
			  netThread.sendVolumn();
			  MainActivity.sounds = seekBar.getProgress();
			  SettingActivity.this.finish();
		 }
	 }; 
}
