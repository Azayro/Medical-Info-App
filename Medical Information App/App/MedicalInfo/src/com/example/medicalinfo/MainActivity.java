package com.example.medicalinfo;

import java.io.File;

import tabswiper.adapater.TabsPagerAdapter;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.annotation.SuppressLint;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements TabListener
{
	//----------VARIABLE----------
	// Private
	
	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private android.app.ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Main", "Personal", "Doctor", "Medical", "Contact" };
    
    // Boolean to change between edit and view
    private Boolean editMode = false;
    
    //----------METHODS----------
    // Private
    private void editMode(boolean toggle)
    {
    	// Update the mode boolean
    	editMode = toggle;
    	
    	// Get instance of the classes to toggle
    	DetailsFragment detailsFragment = (DetailsFragment) mAdapter.getItem(1);
    	DoctorFragment doctorFragment = (DoctorFragment) mAdapter.getItem(2);
    	MedicalFragment medicalFragment = (MedicalFragment) mAdapter.getItem(3);
    	ContactFragment contactFragment = (ContactFragment) mAdapter.getItem(4);
    	
    	// Send through the mode boolean to the class method
    	detailsFragment.setToEditMode(editMode);
    	doctorFragment.setToEditMode(editMode);
    	medicalFragment.setToEditMode(editMode);
    	contactFragment.setToEditMode(editMode);
    	
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// Set default layout
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initialisation

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
        for (String tab_name : tabs) 
        {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }
		
        // Makes the respective tab selected when swiped
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() 
        {
            @Override
            public void onPageSelected(int position) 
            {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
                
                editModeForPage(position);
            }
         
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) 
            {
            }
         
            @Override
            public void onPageScrollStateChanged(int arg0) 
            {
            }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		// Setup file for pin number
		File pinNoFile = new File("data/data/com.example.medicalinfo/pinNoFile.txt");
		
		switch (item.getItemId())
		{
		case R.id.action_about:
			// Setup intent for about activity
			Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
			
			// Call about activity
			startActivity(aboutIntent);
			return true;
		case R.id.action_edit:
			if(pinNoFile.exists())
			{
				// Toggle between edit and view
				if(editMode)
				{
					editMode(!editMode);
					saveInfo();
				}
				else
				{
					// Setup intent for pinToEdit activity
					Intent pinToEditIntent = new Intent(MainActivity.this, EnterPinToEdit.class);
					
					// Call pinToEdit activity
					startActivityForResult(pinToEditIntent, 1);
				}
			}
			else
			{
				// Setup intent for create pin activity
				Intent createPinIntent = new Intent(MainActivity.this, CreateNewPin.class);
				
				// Call create new pin activity
				startActivity(createPinIntent);
			}
			return true;
		case R.id.action_changepin:
			
			// If a file exists then go to change pin activity
			if(pinNoFile.exists())
			{
				// Setup intent for change pin activity
				Intent changePinIntent = new Intent(MainActivity.this, ChangePinActivity.class);
				
				// Call change pin activity
				startActivity(changePinIntent);
			}
			// If not file exists then go to create pin activity
			else
			{
				// Setup intent for create pin activity
				Intent createPinIntent = new Intent(MainActivity.this, CreateNewPin.class);
				
				// Call create new pin activity
				startActivity(createPinIntent);
			}
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}

	}
	
	// Added to deal with the Activity result for checking if the pin is correct when entering edit mode
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		// check if the result code is correct
		if (resultCode==1)
		{
			editMode(true);
		}
	}
	
	// Save all the information
	private void saveInfo()
	{
		DetailsFragment detailsFragment = (DetailsFragment) mAdapter.getItem(1);
		DoctorFragment doctorFragment = (DoctorFragment) mAdapter.getItem(2);
		MedicalFragment medicalFragment = (MedicalFragment) mAdapter.getItem(3);
		ContactFragment contactFragment = (ContactFragment) mAdapter.getItem(4);
		
		detailsFragment.saveInfo();
		doctorFragment.saveInfo();
		medicalFragment.saveInfo();
		contactFragment.saveInfo();
	}

	@Override
	public void onTabReselected(android.app.ActionBar.Tab arg0,
			android.app.FragmentTransaction arg1) 
	{
		// TODO Auto-generated method stub
		
	}

	// Modified for swiping of tabs
	@Override
	public void onTabSelected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) 
	{
		// TODO Auto-generated method stub
		// Allows for swiping between tabs
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(android.app.ActionBar.Tab tab,
			android.app.FragmentTransaction ft) 
	{
		// TODO Auto-generated method stub
				
	}
	
	// Sets edit mode for each page when the page is selected
	public void editModeForPage(int page)
	{
		switch(page)
		{
		case 0:
			DetailsFragment detailsFragment = (DetailsFragment) mAdapter.getItem(1);
			detailsFragment.setToEditMode(editMode);
			return;
		case 1:
			DoctorFragment doctorFragment = (DoctorFragment) mAdapter.getItem(2);
			doctorFragment.setToEditMode(editMode);
			return;
		case 2:
			MedicalFragment medicalFragment = (MedicalFragment) mAdapter.getItem(3);
			medicalFragment.setToEditMode(editMode);
			return;
		case 3:
			ContactFragment contactFragment = (ContactFragment) mAdapter.getItem(4);
			contactFragment.setToEditMode(editMode);
			return;
			
		}
		
	}
}
