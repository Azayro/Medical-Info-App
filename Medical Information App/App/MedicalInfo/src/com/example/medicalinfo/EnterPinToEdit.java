package com.example.medicalinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
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

public class EnterPinToEdit extends ActionBarActivity 
{
	//----------VARIABLES----------
	// Private
	// Edit text variable
	private EditText pinToEdit;
	
	// Button variable
	private Button btnToEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_pin_to_edit);

		// Link edit text field
		pinToEdit	= (EditText) findViewById(R.id.pinToEdit);
		
		// Link button
		btnToEdit	= (Button) findViewById(R.id.editBtn);
		
		btnToEdit.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				File pinNoFile = new File("data/data/com.example.medicalinfo/pinNoFile.txt");
				
				try
				{
					// Temp variables for storing the pin on file
					int temp;
					String currentPin = "";
					
					// Create file in stream
					FileInputStream pinFIn = new FileInputStream(pinNoFile);
					
					// Load the pin from the file
					while((temp = pinFIn.read()) != -1)
					{
						currentPin += Character.toString((char)temp);
					}
					
					// Close the in file
					pinFIn.close();
					
					// If the pin is correct
					if(currentPin.equals(pinToEdit.getText().toString()))
					{
						// Toast displaying correct pin
						Toast.makeText(EnterPinToEdit.this, "Correct pin, edit mode enabled", Toast.LENGTH_SHORT).show();
						// Set to correct and enable editing
						
						// Set intent result
						Intent intent = new Intent();
						setResult(1,intent);
						
						// Close activity
						finish();
					}
					else
					{
						// Post a toast for incorrect pin
						Toast.makeText(EnterPinToEdit.this, "Incorrect pin", Toast.LENGTH_SHORT).show();
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
