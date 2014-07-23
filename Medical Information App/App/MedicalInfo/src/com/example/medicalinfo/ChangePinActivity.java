package com.example.medicalinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
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

public class ChangePinActivity extends ActionBarActivity 
{
	//----------VARIABLES----------
	// Private
	private EditText editCurrentPin;
	private EditText editNewPin;
	private EditText editConfirmPin;
	
	private Button btnSetNewPin;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pin);
		
		// Link edit text fields
		editCurrentPin	= (EditText) findViewById(R.id.currentPinEdit);
		editNewPin		= (EditText) findViewById(R.id.newPinEdit);
		editConfirmPin	= (EditText) findViewById(R.id.confirmPinEdit);
		
		btnSetNewPin = (Button) findViewById(R.id.setNewPinBtn);
		
		btnSetNewPin.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				File pinNoFile = new File("data/data/com.example.medicalinfo/pinNoFile.txt");
				try
				{
					// Temp variables for storing string from file
					int temp;
					String pinNo = "";
					
					// Create file in stream
					FileInputStream pinFIn = new FileInputStream(pinNoFile);
			
					// Loop through the file while there are still characters to be read
					while ((temp = pinFIn.read()) != -1)
					{
						pinNo += Character.toString((char)temp);
					}
					
					// Close file in stream
					pinFIn.close();
					
					// If the current pin matches the pin entered
					if(editCurrentPin.getText().toString().equals(pinNo))
					{
						// Store the entered new pin to compare to the confirming pin
						String newPin = editNewPin.getText().toString();
						
						// Confirm that the pins match
						if(editConfirmPin.getText().toString().equals(newPin))
						{
							// Create file out stream
							FileOutputStream pinFOut = new FileOutputStream(pinNoFile);
							
							// Write the new pin to the file
							pinFOut.write(pinNo.getBytes());
							
							// Close the file out stream
							pinFOut.close();
							
							// Successful toast
							Toast.makeText(ChangePinActivity.this,  "Successfully changed pin", Toast.LENGTH_SHORT).show();
							
							// Leave activity
							finish();
						}
						else
						{
							// Unsuccessful toast
							Toast.makeText(ChangePinActivity.this,  "New pins do not match", Toast.LENGTH_SHORT).show();
						}
					}
					else
					{
						// Unsuccessful toast
						Toast.makeText(ChangePinActivity.this,  "Current pin incorrect", Toast.LENGTH_SHORT).show();
					}
					
				}
				catch (IOException e) 
				{
					e.printStackTrace();
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
