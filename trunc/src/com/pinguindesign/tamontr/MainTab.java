package com.pinguindesign.tamontr;

import java.lang.reflect.InvocationTargetException;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TabHost;

public class MainTab extends TabActivity {
	
	private static final String TASK_VIEW_TAG = "TASK_VIEW";
	private static final String MONEY_EDIT_TAG = "MONEY_EDIT";
	private static final int TABS_COUNT = 0;
	private static final int TAB_SCREEN_HEIGHT = 50;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {

    	
        super.onCreate(savedInstanceState);
        
        Resources res = getResources();
        TabHost tabHost = getTabHost();  	// The activity TabHost
        TabHost.TabSpec spec = null;  		// Reusable TabSpec for each tab
        Intent intent = null;				// Reusable Intent for each tab
	
        //Tasks tab
        intent = new Intent().setClass(this, TaskListView.class);        
        spec = tabHost.newTabSpec(TASK_VIEW_TAG)
        		.setIndicator("",null) 
        		.setContent(intent);
        		
        tabHost.addTab(spec);
        
        //Money tab
        intent = new Intent().setClass(this, MoneyListView.class);
        spec = tabHost.newTabSpec(MONEY_EDIT_TAG)
        		.setIndicator("", null) 
        		.setContent(intent);
        tabHost.addTab(spec);
        
        //Init some tab design
        initTabWigetView();
        setInitialBgrTab();
    }
	
	
	private void setInitialBgrTab() {
		//TODO After designers do background of tabs
		/*
			getTabHost().getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.task_tab); 
        	getTabHost().getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.money_tab);  
		*/
	}
	
	private void initTabWigetView(){
		DisplayMetrics displaymetrics = new DisplayMetrics(); 
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        
        for (int i = 0; i < TABS_COUNT; i++)	{        	
        	getTabHost().getTabWidget().getChildAt(i).getLayoutParams().height = (int)(TAB_SCREEN_HEIGHT * scale);      
        }
   
        getTabHost().getTabWidget().setPadding(0, 5, 0, 0);
        removeTabStrips();
	}
	
	/*
     * Hack to remove bottom stripe WORKS ONLY ver.>2.2
     */
	private void removeTabStrips(){
		try {
				(getTabWidget().getClass().getDeclaredMethod("setStripEnabled", boolean.class))
				.invoke(getTabWidget(), false);
			} catch (IllegalArgumentException e) {
	
				e.printStackTrace();
			} catch (SecurityException e) {
	
				e.printStackTrace();
			} catch (IllegalAccessException e) {
		 				e.printStackTrace();
			} catch (InvocationTargetException e) {
	
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
	
				e.printStackTrace();
			}
	}
	
	
}