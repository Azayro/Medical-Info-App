package com.example.medicalinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.medicalinfo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

//---------------------------------------------
//----------DETAILS FRAGMENT CLASS-------------
//----View personal details or edit details----
//------based on whether edit mode is on.------
//---------------------------------------------

public class DetailsFragment extends Fragment
{
	//----------VARIABLES----------
	// Edit text variables
	private EditText editName;
	private EditText editAddress1;
	private EditText editAddress2;
	private EditText editAddress3;
	private EditText editPostcode;
	private EditText editDOB;
	private EditText editBloodGroup;
	
	// File variable
	public File detailsFile;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Get current view
		View rootView = inflater.inflate(R.layout.fragment_details, container, false);
		
		// Link edit text fields
		editName 		= (EditText) rootView.findViewById(R.id.nameEdit);
		editAddress1 	= (EditText) rootView.findViewById(R.id.addressLine1Edit);
		editAddress2 	= (EditText) rootView.findViewById(R.id.addressLine2Edit);
		editAddress3 	= (EditText) rootView.findViewById(R.id.addressLine3Edit);
		editPostcode 	= (EditText) rootView.findViewById(R.id.postcodeEdit);
		editDOB 		= (EditText) rootView.findViewById(R.id.dobEdit);
		editBloodGroup 	= (EditText) rootView.findViewById(R.id.bloodGroupEdit);
		
		// Set up file and directory
		detailsFile = new File("data/data/com.example.medicalinfo/detailsFile.txt");
		// Load information
		loadInfo();
		
		return rootView;
	}
	
	// Set to edit mode
	public void setToEditMode(boolean editMode)
	{
		// Check that the first variable has been initialised
		if(editName != null)
		{
			// Set to editable
			editName.setFocusable(editMode);
			editName.setFocusableInTouchMode(editMode);
			
			editAddress1.setFocusable(editMode);
			editAddress1.setFocusableInTouchMode(editMode);
			
			editAddress2.setFocusable(editMode);
			editAddress2.setFocusableInTouchMode(editMode);
			
			editAddress3.setFocusable(editMode);
			editAddress3.setFocusableInTouchMode(editMode);
			
			editPostcode.setFocusable(editMode);
			editPostcode.setFocusableInTouchMode(editMode);
			
			editDOB.setFocusable(editMode);
			editDOB.setFocusableInTouchMode(editMode);
			
			editBloodGroup.setFocusable(editMode);
			editBloodGroup.setFocusableInTouchMode(editMode);
		}
	}
	
	public void saveInfo()
	{
		if(editName != null)
		{
			try
			{
				// Create new file
				detailsFile.createNewFile();
				
				// Create file out stream
				FileOutputStream detailsFOut = new FileOutputStream(detailsFile);
				
				String tempSave = "";
				
				// Write each of the edit text fields to the file
				// "\n" is used to separate each field
				
				tempSave += editName.getText().toString();
				tempSave += "\n";
				
				tempSave += editAddress1.getText().toString();
				tempSave += "\n";
				tempSave += editAddress2.getText().toString();
				tempSave += "\n";
				tempSave += editAddress3.getText().toString();
				tempSave += "\n";
				tempSave += editPostcode.getText().toString();
				tempSave += "\n";
				
				tempSave += editDOB.getText().toString();
				tempSave += "\n";
				
				tempSave += editBloodGroup.getText().toString();
				tempSave += "\n";
				
				// Test toast
				//Toast.makeText(getActivity(), "-"+tempSave, Toast.LENGTH_SHORT).show();
				
				// Write to the file
				detailsFOut.write(tempSave.getBytes());
				
				// Close the file
				detailsFOut.close();
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
		if(detailsFile.exists() && editName != null)
		{
			try
			{
				// Temp variable
				String tempLoad = "";
				
				// File in stream
				FileInputStream detailsFIn = new FileInputStream(detailsFile);
				// Create a buffer reader to load in information from a file
				BufferedReader detailsBufferReader = new BufferedReader(new InputStreamReader(detailsFIn));
				
				// Load each detail
				tempLoad = detailsBufferReader.readLine();
				editName.setText(tempLoad);
				
				tempLoad = detailsBufferReader.readLine();
				editAddress1.setText(tempLoad);
				tempLoad = detailsBufferReader.readLine();
				editAddress2.setText(tempLoad);
				tempLoad = detailsBufferReader.readLine();
				editAddress1.setText(tempLoad);
				tempLoad = detailsBufferReader.readLine();
				editPostcode.setText(tempLoad);
				
				tempLoad = detailsBufferReader.readLine();
				editDOB.setText(tempLoad);
				
				tempLoad = detailsBufferReader.readLine();
				editBloodGroup.setText(tempLoad);
				
				// Test toast
				//Toast.makeText(getActivity(), "Loaded:"+tempLoad, Toast.LENGTH_SHORT).show();
				// Close file
				detailsFIn.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
