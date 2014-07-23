package com.example.medicalinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class CreateNewPin extends ActionBarActivity 
{
	//----------VARIABLES----------
	// Private
	
	// Edit text variables
	private EditText editNewPin;
	private EditText editConfirmPin;
	
	// Button variables
	private Button btnSetPin;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_pin);

		// Link edit text fields
		editNewPin		= (EditText) findViewById(R.id.newPinCreateEdit);
		editConfirmPin	= (EditText) findViewById(R.id.confirmPinCreateEdit);
		
		// Link buttons
		btnSetPin	= (Button) findViewById(R.id.setPinCreateBtn);
		
		btnSetPin.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				String newPin = editNewPin.getText().toString();
				String confirmPin = editConfirmPin.getText().toString();
				
				if(newPin.equals(confirmPin))
				{
					try
					{
						// Create new file
						File pinNoFile = new File("data/data/com.example.medicalinfo/pinNoFile.txt");
						pinNoFile.createNewFile();
					
						// Create file stream
						FileOutputStream pinFOut = new FileOutputStream(pinNoFile);
												
						// Write pin to file
						pinFOut.write(newPin.getBytes());
						
						// Close file
						pinFOut.close();
						
						// Toast to say the pin as been set
						Toast.makeText(CreateNewPin.this, "Pin has been set", Toast.LENGTH_SHORT).show();
						
						// Exit activity
						finish();
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					// Toast to say the pins do not match
					Toast.makeText(CreateNewPin.this, "Pins do not match", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		return false;
	}
}
