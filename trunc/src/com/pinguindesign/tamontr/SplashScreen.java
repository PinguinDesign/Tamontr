package com.pinguindesign.tamontr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {
	
	private static final int DELAY = 100;
    private static final int WELCOME_SCREEN_DISPLAY = 3000;  // ms to show splash
	private Thread _welcomeThread;
	private boolean _isBackBtnPressed = false;
	
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.splash_screen);
	        
	        /** create a thread to show splash up to splash time */
	        _welcomeThread = new Thread() {
	        	int wait = 0;
	        	@Override
	        	public void run() {
	        		try {
	        			super.run();
	        			/**
	        			 * use while to get the splash time. Use sleep() to increase
	        			 * the wait variable for every 100L.
	        			 */
	        			while (wait < WELCOME_SCREEN_DISPLAY) {
	        			if(_isBackBtnPressed)
	        			{
	        				break;
	        			}
	        			sleep(DELAY);
	        			wait += DELAY;
	        			}
	        		} 
	        		catch (Exception e) {
	        			System.out.println("EXc=" + e);
	        		} 
	        		finally {
	        			/**
	        			 * Called after splash times up. Do some action after splash
	        			 * times up. Here we moved to another main activity class
	        			 */
	        			if(_isBackBtnPressed)
	        			{
	        				return;
	        			}
	        			startActivity(new Intent(SplashScreen.this, MainTab.class));
	        			finish();
	        		}
	        	}
	        };
	        _welcomeThread.start();	        
	  }

	@Override
	public void onBackPressed() {
		_isBackBtnPressed = true;
		super.onBackPressed();
	}
}
