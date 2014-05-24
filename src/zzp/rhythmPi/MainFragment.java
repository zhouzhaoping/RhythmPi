package zzp.rhythmPi;

import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;


import zzp.rhythmPi.R;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {
	public MainFragment() {		
	}
	
	private int type;
	RelativeLayout rLayout;
	private ImageView []button = new ImageView[15];
	private int []buttonId = {R.id.button_main_1, R.id.button_main_2, R.id.button_main_3, R.id.button_main_4, R.id.button_main_5,
			R.id.button_main_6, R.id.button_main_7, R.id.button_main_8, R.id.button_main_9, R.id.button_main_10,
			R.id.button_main_11, R.id.button_main_12, R.id.button_main_13, R.id.button_main_14, R.id.button_main_15};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);
		
		findViews(rootView);
		showResults();
		setListensers();
		return rootView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    type = getArguments().getInt("KEY_TYPE");
	}
	
	private void findViews(View rootView)
	{		
		rLayout = (RelativeLayout) rootView.findViewById(R.id.frame_main);
		for (int i = 0; i < buttonId.length; ++i)
			button[i] = (ImageView) rootView.findViewById(buttonId[i]);	
	}
	private void showResults() 
	{
		switch(type)
		{
		case 1://techno
			rLayout.setBackgroundResource(R.drawable.back2);
			break;
		case 2://jazz
			rLayout.setBackgroundResource(R.drawable.back3);
			break;
		case 0:
		default://classic
			rLayout.setBackgroundResource(R.drawable.back1);
		}
	}	
	
	 private void setListensers() {
		 OnClickListener btnListener = new OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.d("press", "button");
					for (int i = 0; i < buttonId.length; ++i)
					{
						if (v.getId() == buttonId[i])
						{
							NetThread netThread = new NetThread(-1, type, i, -1);
							netThread.sendButton();
							break;
						}
					}
				}};
		for (int i = 0; i < buttonId.length; ++i)
			button[i].setOnClickListener(btnListener);
	 }
}
