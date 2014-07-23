package com.example.medicalinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.medicalinfo.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

//--------------------------------------------
//----------DOCTOR FRAGMENT CLASS-------------
//----View doctors details or edit details----
//------based on whether edit mode is on.-----
//--------------------------------------------

public class DoctorFragment extends Fragment
{
	//----------VARIABLES----------
	// Edit text variables
	private EditText editDocName;
	private EditText editPractAddress1;
	private EditText editPractAddress2;
	private EditText editPractAddress3;
	private EditText editPractPostcode;
	private EditText editDocContactNum;
	
	// File variable
	public File doctorFile;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Get current view
		View rootView = inflater.inflate(R.layout.fragment_doctor, container, false);
		
		// Link edit text fields
		editDocName			= (EditText) rootView.findViewById(R.id.doctorNameEdit);
		editPractAddress1	= (EditText) rootView.findViewById(R.id.practiceAddress1Edit);
		editPractAddress2	= (EditText) rootView.findViewById(R.id.practiceAddress2Edit);
		editPractAddress3	= (EditText) rootView.findViewById(R.id.practiceAddress3Edit);
		editPractPostcode	= (EditText) rootView.findViewById(R.id.practicePostcodeEdit);
		editDocContactNum	= (EditText) rootView.findViewById(R.id.docConNumEdit);
		
		// Set up file and directory
		doctorFile = new File("data/data/com.example.medicalinfo/doctorFile.txt");
		// Load info
		loadInfo();
		
		// Call doctors number
		editDocContactNum.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// Check to make sure the field is not focusable (In edit mode if focusable)
				if(!editDocContactNum.isFocusable() && !editDocContactNum.isFocusableInTouchMode())
				{
					try
					{
						// Check to make sure an number is in the field
						if(!(editDocContactNum.getText().toString().length() == 0))
						{
							// Make call
							String uri = "tel:"+editDocContactNum.getText().toString();
							Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
							
							startActivity(callIntent);
						}
						else
						{
							// Toast to let user know they are in edit mode
							Toast.makeText(getActivity(), "No number to call", Toast.LENGTH_SHORT).show();
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
			}
		});
		
		return rootView;
	}
	
	// Set edit mode
	public void setToEditMode(boolean editMode)
	{
		// Check that the first variable has been initialised
		if(editDocName != null)
		{
			// Set to editable
			editDocName.setFocusable(editMode);
			editDocName.setFocusableInTouchMode(editMode);
			
			editPractAddress1.setFocusable(editMode);
			editPractAddress1.setFocusableInTouchMode(editMode);
			
			editPractAddress2.setFocusable(editMode);
			editPractAddress2.setFocusableInTouchMode(editMode);
			
			editPractAddress3.setFocusable(editMode);
			editPractAddress3.setFocusableInTouchMode(editMode);
			
			editPractPostcode.setFocusable(editMode);
			editPractPostcode.setFocusableInTouchMode(editMode);
			
			editDocContactNum.setFocusable(editMode);
			editDocContactNum.setFocusableInTouchMode(editMode);
		}
	}
	
	public void saveInfo()
	{
		if(editDocName != null)
		{
			try
			{
				// Create new file
				doctorFile.createNewFile();
				
				// Create file out stream
				FileOutputStream doctorFOut = new FileOutputStream(doctorFile);
				
				String tempSave = "";
				
				// Write each of the edit text fields to the file
				// "\n" is used to separate each field
				
				tempSave += editDocName.getText().toString();
				tempSave += "\n";
				
				tempSave += editPractAddress1.getText().toString();
				tempSave += "\n";
				tempSave += editPractAddress2.getText().toString();
				tempSave += "\n";
				tempSave += editPractAddress3.getText().toString();
				tempSave += "\n";
				tempSave += editPractPostcode.getText().toString();
				tempSave += "\n";
				
				tempSave += editDocContactNum.getText().toString();
				tempSave += "\n";
				
				// Test toast
				//Toast.makeText(getActivity(), "-"+tempSave, Toast.LENGTH_SHORT).show();
				
				// Write to the file
				doctorFOut.write(tempSave.getBytes());
				
				// Close the file
				doctorFOut.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void loadInfo()
	{
		// Load only if a file exists and there is info to be loaded
		if(doctorFile.exists() && editDocName != null)
		{
			try
			{
				// Temp variable
				String tempLoad = "";
				
				// File in stream
				FileInputStream doctorFIn = new FileInputStream(doctorFile);
				// Create a buffer reader to load in information from a file
				BufferedReader doctorBufferReader = new BufferedReader(new InputStreamReader(doctorFIn));
				
				// Load each detail
				tempLoad = doctorBufferReader.readLine();
				editDocName.setText(tempLoad);
				
				tempLoad = doctorBufferReader.readLine();
				editPractAddress1.setText(tempLoad);
				tempLoad = doctorBufferReader.readLine();
				editPractAddress2.setText(tempLoad);
				tempLoad = doctorBufferReader.readLine();
				editPractAddress3.setText(tempLoad);
				tempLoad = doctorBufferReader.readLine();
				editPractPostcode.setText(tempLoad);
				
				tempLoad = doctorBufferReader.readLine();
				editDocContactNum.setText(tempLoad);
				
				// Test toast
				//Toast.makeText(getActivity(), "Loaded:"+tempLoad, Toast.LENGTH_SHORT).show();
				// Close file
				doctorFIn.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}