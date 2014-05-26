package zzp.rhythmPi;

import java.util.Locale;

import zzp.rhythmPi.R;


import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    /**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	long firstTime=0;
	static int sounds = 0;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showResults();
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	 private void showResults() {
		 Bundle bunde = this.getIntent().getExtras();
		 String username = bunde.getString("KEY_USERNAME");
		 String password = bunde.getString("KEY_PASSWORD");
		 //NetThread.url = "http://" + username + "/handler.py";
		 String welcome = "欢迎来到RhythmyPi(" + username + ")的音乐世界！"; 
		 Toast.makeText(MainActivity.this, welcome, 
                 Toast.LENGTH_SHORT).show(); 
	 }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item){
		Intent intent;
		switch(item.getItemId()){
            case R.id.action_settings:
            	intent = new Intent();
				intent.setClass(this, SettingActivity.class);
				startActivity(intent);
            	return true;
            case R.id.action_about:
            	intent = new Intent();
				intent.setClass(this, AboutActivity.class);
				startActivity(intent);
            	return true; 
            case R.id.action_relogin:
            	intent = new Intent();
				intent.setClass(this, LoginActivity.class);
				startActivity(intent);
				this.finish();
            	return true; 
            case R.id.action_logout:
            	Toast.makeText(this, "感谢使用！", 
		                 Toast.LENGTH_SHORT).show(); 
            	this.finish();
            	return true; 
        }	
        return false;
    }
	
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment;
			Bundle args;
			switch(position)
			{
			case 0:
				fragment = new MainFragment();
				args = new Bundle();
				args.putInt("KEY_TYPE", 0);
				fragment.setArguments(args);
				break;
			case 1:
				fragment = new MainFragment();
				args = new Bundle();
				args.putInt("KEY_TYPE", 1);
				fragment.setArguments(args);
				break;
			case 2:
				fragment = new MainFragment();
				args = new Bundle();
				args.putInt("KEY_TYPE", 2);
				fragment.setArguments(args);
				break;
			default:
				fragment = new MainFragment();
				args = new Bundle();
				args.putInt("KEY_TYPE", 0);
				fragment.setArguments(args);
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1);
			case 1:
				return getString(R.string.title_section2);
			case 2:
				return getString(R.string.title_section3);
			}
			return null;
		}
	}

	
	@Override 
    public boolean onKeyUp(int keyCode, KeyEvent event) { 
        if (keyCode == KeyEvent.KEYCODE_BACK) { 
            long secondTime = System.currentTimeMillis(); 
            if (secondTime - firstTime > 1600) {//如果两次按键时间间隔大于1600毫秒，则不退出 
                Toast.makeText(MainActivity.this, "再按一次退出程序...", 
                        Toast.LENGTH_SHORT).show(); 
                firstTime = secondTime;//更新firstTime 
                return true; 
            } else { 
                System.exit(0);//否则退出程序 
            } 
        } 
        return super.onKeyUp(keyCode, event); 
    } 
	
	@Override
	public boolean onKeyDown (int keyCode, KeyEvent event) {
	
		switch (keyCode) {
			case KeyEvent.KEYCODE_VOLUME_DOWN:
				Toast.makeText (this, "当前音量值： " + sounds, Toast.LENGTH_SHORT).show();
				if (sounds > 0)
				{
					sounds--;
					NetThread netThread = new NetThread(null, -1, -1, sounds);
					  netThread.sendVolumn();
				}
				return true;
			case KeyEvent.KEYCODE_VOLUME_UP:
				Toast.makeText (this, "当前音量值： " + sounds, Toast.LENGTH_SHORT).show();
				if (sounds < 10)
				{
					sounds++;
					 NetThread netThread = new NetThread(null, -1, -1, sounds);
					  netThread.sendVolumn();
				}
				return true;
		}
		return super.onKeyDown (keyCode, event);
	}
}
