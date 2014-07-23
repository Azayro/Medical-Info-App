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

//---------------------------------------------
//----------CONTACT FRAGMENT CLASS-------------
//----- View or edit contact details for ------
//------ Next of Kin and people the user ------
//---------------- cares for. -----------------
//---------------------------------------------

public class ContactFragment extends Fragment
{
	//----------VARIABLES----------
	// Edit text variables
	// Next of Kin/Contact Person
	private EditText editNOKName;
	private EditText editNOKRelation;
	private EditText editNOKAddress1;
	private EditText editNOKAddress2;
	private EditText editNOKAddress3;
	private EditText editNOKConNum;
	
	// Person that user cares for
	private EditText editCareForName;
	private EditText editCareForAddress1;
	private EditText editCareForAddress2;
	private EditText editCareForAddress3;
	private EditText editCareForConNum;
	
	public File contactFile;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Get current view
		View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
		
		editNOKName 		= (EditText) rootView.findViewById(R.id.nokNameEdit);
		editNOKRelation		= (EditText) rootView.findViewById(R.id.nokRelationEdit);
		editNOKAddress1 	= (EditText) rootView.findViewById(R.id.nokAddress1Edit);
		editNOKAddress2		= (EditText) rootView.findViewById(R.id.nokAddress2Edit);
		editNOKAddress3		= (EditText) rootView.findViewById(R.id.nokAddress3Edit);
		editNOKConNum 		= (EditText) rootView.findViewById(R.id.nokConNumEdit);
		
		editCareForName 	= (EditText) rootView.findViewById(R.id.careForNameEdit);
		editCareForAddress1 = (EditText) rootView.findViewById(R.id.careForAddress1Edit);
		editCareForAddress2	= (EditText) rootView.findViewById(R.id.careForAddress2Edit);
		editCareForAddress3	= (EditText) rootView.findViewById(R.id.careForAddress3Edit);
		editCareForConNum 	= (EditText) rootView.findViewById(R.id.careForConNumEdit);
		
		
		// Set up file and directory
		contactFile = new File("data/data/com.example.medicalinfo/contactFile.txt");
		// Load info
		loadInfo();
		
		// Call next of kin
		editNOKConNum.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// Check to make sure the field is not focusable (In edit mode if focusable)
				if(!editNOKConNum.isFocusable() && !editNOKConNum.isFocusableInTouchMode())
				{
					try
					{
						// Check to make sure an number is in the field
						if(!(editNOKConNum.getText().toString().length() == 0))
						{
							// Make call
							String uri = "tel:"+editNOKConNum.getText().toString();
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
		
		// Call person the user cares for
		editCareForConNum.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// Check to make sure the field is not focusable (In edit mode if focusable)
				if(!editCareForConNum.isFocusable() && !editCareForConNum.isFocusableInTouchMode())
				{
					try
					{
						// Check to make sure an number is in the field
						if(!(editCareForConNum.getText().toString().length() == 0))
						{
							// Make call
							String uri = "tel:"+editCareForConNum.getText().toString();
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
	
	// Set to edit mode
	public void setToEditMode(boolean editMode)
	{
		// Check that the first variable has been initialised
		if(editNOKName != null)
		{
			// Set to editable
			// Next of Kin/Contact Person
			editNOKName.setFocusable(editMode);
			editNOKName.setFocusableInTouchMode(editMode);
			
			editNOKRelation.setFocusable(editMode);
			editNOKRelation.setFocusableInTouchMode(editMode);
			
			editNOKAddress1.setFocusable(editMode);
			editNOKAddress1.setFocusableInTouchMode(editMode);
			
			editNOKAddress2.setFocusable(editMode);
			editNOKAddress2.setFocusableInTouchMode(editMode);
			
			editNOKAddress3.setFocusable(editMode);
			editNOKAddress3.setFocusableInTouchMode(editMode);
			
			editNOKConNum.setFocusable(editMode);
			editNOKConNum.setFocusableInTouchMode(editMode);
			
			// Care for person
			editCareForName.setFocusable(editMode);
			editCareForName.setFocusableInTouchMode(editMode);
			
			editCareForAddress1.setFocusable(editMode);
			editCareForAddress1.setFocusableInTouchMode(editMode);
			
			editCareForAddress2.setFocusable(editMode);
			editCareForAddress2.setFocusableInTouchMode(editMode);
			
			editCareForAddress3.setFocusable(editMode);
			editCareForAddress3.setFocusableInTouchMode(editMode);
			
			editCareForConNum.setFocusable(editMode);
			editCareForConNum.setFocusableInTouchMode(editMode);
		}
	}
	
	public void saveInfo()
	{
		if(editNOKName != null)
		{
			try
			{
				// Create new file
				contactFile.createNewFile();
				
				// Create file out stream
				FileOutputStream contactFOut = new FileOutputStream(contactFile);
				
				String tempSave = "";
				
				// Add each text field to a temporary save variable
				// "\n" is used to separate each field
				
				// Add Next of kin to temp variable
				tempSave += editNOKName.getText().toString();
				tempSave += "\n";
				
				tempSave += editNOKRelation.getText().toString();
				tempSave += "\n";
				
				tempSave += editNOKAddress1.getText().toString();
				tempSave += "\n";
				tempSave += editNOKAddress2.getText().toString();
				tempSave += "\n";
				tempSave += editNOKAddress3.getText().toString();
				tempSave += "\n";
				
				tempSave += editNOKConNum.getText().toString();
				tempSave += "\n";
				
				// Add care for person to temp variable
				tempSave += editCareForName.getText().toString();
				tempSave += "\n";
				
				tempSave += editCareForAddress1.getText().toString();
				tempSave += "\n";
				tempSave += editCareForAddress2.getText().toString();
				tempSave += "\n";
				tempSave += editCareForAddress3.getText().toString();
				tempSave += "\n";
				
				tempSave += editCareForConNum.getText().toString();
				tempSave += "\n";
				
				// Test toast
				//Toast.makeText(getActivity(), "-"+tempSave, Toast.LENGTH_SHORT).show();
				
				// Write to the file
				contactFOut.write(tempSave.getBytes());
				
				// Close the file
				contactFOut.close();
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
		if(contactFile.exists() && editNOKName != null)
		{
			try
			{
				// Temp variable
				String tempLoad = "";
				
				// File in stream
				FileInputStream contactFIn = new FileInputStream(contactFile);
				// Create a buffer reader to load in information from a file
				BufferedReader contactBufferReader = new BufferedReader(new InputStreamReader(contactFIn));
				
				// Load each detail
				// Load next of kin
				tempLoad = contactBufferReader.readLine();
				editNOKName.setText(tempLoad);
				
				tempLoad = contactBufferReader.readLine();
				editNOKRelation.setText(tempLoad);
				
				tempLoad = contactBufferReader.readLine();
				editNOKAddress1.setText(tempLoad);
				tempLoad = contactBufferReader.readLine();
				editNOKAddress2.setText(tempLoad);
				tempLoad = contactBufferReader.readLine();
				editNOKAddress3.setText(tempLoad);
				
				tempLoad = contactBufferReader.readLine();
				editNOKConNum.setText(tempLoad);
				
				// Load care for 
				tempLoad = contactBufferReader.readLine();
				editCareForName.setText(tempLoad);
				
				tempLoad = contactBufferReader.readLine();
				editCareForAddress1.setText(tempLoad);
				tempLoad = contactBufferReader.readLine();
				editCareForAddress2.setText(tempLoad);
				tempLoad = contactBufferReader.readLine();
				editCareForAddress3.setText(tempLoad);
				
				tempLoad = contactBufferReader.readLine();
				editCareForConNum.setText(tempLoad);
				
				// Test toast
				//Toast.makeText(getActivity(), "Loaded:"+tempLoad, Toast.LENGTH_SHORT).show();
				// Close file
				contactFIn.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
