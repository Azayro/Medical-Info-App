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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

//--------------------------------------------
//----------MEDICAL FRAGMENT CLASS------------
//----View medical details or edit details----
//------based on whether edit mode is on.-----
//--------------------------------------------

public class MedicalFragment extends Fragment
{
	//----------VARIABLES----------
	// Edit text variables
	// Medication
	private EditText editMeds1;
	private EditText editMeds2;
	private EditText editMeds3;
	private EditText editMeds4;
	private EditText editMeds5;
	
	// Medical problems
	private EditText editMedProbs1;
	private EditText editMedProbs2;
	private EditText editMedProbs3;
	private EditText editMedProbs4;
	private EditText editMedProbs5;
	private EditText editMedProbs6;
	private EditText editMedProbs7;
	
	// Check box variables
	private CheckBox cbBSLInterp;
	private CheckBox cbLipRead;
	private CheckBox cbHearingAid;
	
	// Carer edit text variables
	private EditText editCarerName;
	private EditText editCarerAddress1;
	private EditText editCarerAddress2;
	private EditText editCarerAddress3;
	private EditText editCarerConNum;
	
	// File variable
	public File medicalFile;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Get current view
		View rootView = inflater.inflate(R.layout.fragment_medical, container, false);
		
		// Link edit text fields
		// Medication
		editMeds1			= (EditText) rootView.findViewById(R.id.meds1Edit);
		editMeds2			= (EditText) rootView.findViewById(R.id.meds2Edit);
		editMeds3			= (EditText) rootView.findViewById(R.id.meds3Edit);
		editMeds4			= (EditText) rootView.findViewById(R.id.meds4Edit);
		editMeds5			= (EditText) rootView.findViewById(R.id.meds5Edit);
		// Medical problems
		editMedProbs1		= (EditText) rootView.findViewById(R.id.medProbs1Edit);
		editMedProbs2		= (EditText) rootView.findViewById(R.id.medProbs2Edit);
		editMedProbs3		= (EditText) rootView.findViewById(R.id.medProbs3Edit);
		editMedProbs4		= (EditText) rootView.findViewById(R.id.medProbs4Edit);
		editMedProbs5		= (EditText) rootView.findViewById(R.id.medProbs5Edit);
		editMedProbs6		= (EditText) rootView.findViewById(R.id.medProbs6Edit);
		editMedProbs7		= (EditText) rootView.findViewById(R.id.medProbs7Edit);
		
		// Link check box fields
		cbBSLInterp			= (CheckBox) rootView.findViewById(R.id.BSLInterpreterCB);
		cbLipRead			= (CheckBox) rootView.findViewById(R.id.lipReadCB);
		cbHearingAid		= (CheckBox) rootView.findViewById(R.id.hearingAidCB);
		
		// Link carer text fields
		editCarerName		= (EditText) rootView.findViewById(R.id.myCarerNameEdit);
		editCarerAddress1	= (EditText) rootView.findViewById(R.id.myCarerAddress1Edit);
		editCarerAddress2	= (EditText) rootView.findViewById(R.id.myCarerAddress2Edit);
		editCarerAddress3	= (EditText) rootView.findViewById(R.id.myCarerAddress3Edit);
		editCarerConNum		= (EditText) rootView.findViewById(R.id.myCarerConNumEdit);
		
		// Set up file and directory
		medicalFile = new File("data/data/com.example.medicalinfo/medicalFile.txt");
		
		// Load info
		loadInfo();
		
		// Call carers number
		editCarerConNum.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// Check to make sure the field is not focusable (In edit mode if focusable)
				if(!editCarerConNum.isFocusable() && !editCarerConNum.isFocusableInTouchMode())
				{
					try
					{
						// Check to make sure an number is in the field
						if(!(editCarerConNum.getText().toString().length() == 0))
						{
							// Make call
							String uri = "tel:"+editCarerConNum.getText().toString();
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
		if(editMeds1 != null)
		{
			// Set to editable
			//----------MEDICATION----------
			editMeds1.setFocusable(editMode);
			editMeds1.setFocusableInTouchMode(editMode);
			
			editMeds2.setFocusable(editMode);
			editMeds2.setFocusableInTouchMode(editMode);
			
			editMeds3.setFocusable(editMode);
			editMeds3.setFocusableInTouchMode(editMode);
			
			editMeds4.setFocusable(editMode);
			editMeds4.setFocusableInTouchMode(editMode);
			
			editMeds5.setFocusable(editMode);
			editMeds5.setFocusableInTouchMode(editMode);
			
			//----------MEDICAL PROBLEMS----------
			
			editMedProbs1.setFocusable(editMode);
			editMedProbs1.setFocusableInTouchMode(editMode);
			
			editMedProbs2.setFocusable(editMode);
			editMedProbs2.setFocusableInTouchMode(editMode);
			
			editMedProbs3.setFocusable(editMode);
			editMedProbs3.setFocusableInTouchMode(editMode);
			
			editMedProbs4.setFocusable(editMode);
			editMedProbs4.setFocusableInTouchMode(editMode);
			
			editMedProbs5.setFocusable(editMode);
			editMedProbs5.setFocusableInTouchMode(editMode);
			
			editMedProbs6.setFocusable(editMode);
			editMedProbs6.setFocusableInTouchMode(editMode);
			
			editMedProbs7.setFocusable(editMode);
			editMedProbs7.setFocusableInTouchMode(editMode);
			
			//----------CHECK BOXES----------
			cbBSLInterp.setClickable(editMode);
			
			cbLipRead.setClickable(editMode);
			
			cbHearingAid.setClickable(editMode);
			
			//----------CARER DETAILS----------
			editCarerName.setFocusable(editMode);
			editCarerName.setFocusableInTouchMode(editMode);
			
			editCarerAddress1.setFocusable(editMode);
			editCarerAddress1.setFocusableInTouchMode(editMode);
			
			editCarerAddress2.setFocusable(editMode);
			editCarerAddress2.setFocusableInTouchMode(editMode);
			
			editCarerAddress3.setFocusable(editMode);
			editCarerAddress3.setFocusableInTouchMode(editMode);
			
			editCarerConNum.setFocusable(editMode);
			editCarerConNum.setFocusableInTouchMode(editMode);
		}
	}
	
	public void saveInfo()
	{
		if(editMeds1 != null)
		{
			try
			{
				// Create new file
				medicalFile.createNewFile();
				
				// Create file out stream
				FileOutputStream medicalFOut = new FileOutputStream(medicalFile);
				
				String tempSave = "";
				
				// Add each text fields text to a temporary save variable
				// "\n" is used to separate each field
				
				// Medication
				tempSave += editMeds1.getText().toString();
				tempSave += "\n";
				tempSave += editMeds2.getText().toString();
				tempSave += "\n";
				tempSave += editMeds3.getText().toString();
				tempSave += "\n";
				tempSave += editMeds4.getText().toString();
				tempSave += "\n";
				tempSave += editMeds5.getText().toString();
				tempSave += "\n";
				
				// Medical problems
				tempSave += editMedProbs1.getText().toString();
				tempSave += "\n";
				tempSave += editMedProbs2.getText().toString();
				tempSave += "\n";
				tempSave += editMedProbs3.getText().toString();
				tempSave += "\n";
				tempSave += editMedProbs4.getText().toString();
				tempSave += "\n";
				tempSave += editMedProbs5.getText().toString();
				tempSave += "\n";
				tempSave += editMedProbs6.getText().toString();
				tempSave += "\n";
				tempSave += editMedProbs7.getText().toString();
				tempSave += "\n";
				
				// Check boxes
				// If the checkbox is checked then store 1 as a boolean representation
				if(cbBSLInterp.isChecked())
				{
					tempSave += "1";
				}
				else
				{
					tempSave += "0";
				}
				tempSave += "\n";
				if(cbLipRead.isChecked())
				{
					tempSave += "1";
				}
				else
				{
					tempSave += "0";
				}
				tempSave += "\n";
				if(cbHearingAid.isChecked())
				{
					tempSave += "1";
				}
				else
				{
					tempSave += "0";
				}
				tempSave += "\n";
				
				// Carers details
				tempSave += editCarerName.getText().toString();
				tempSave += "\n";
				
				tempSave += editCarerAddress1.getText().toString();
				tempSave += "\n";
				tempSave += editCarerAddress2.getText().toString();
				tempSave += "\n";
				tempSave += editCarerAddress3.getText().toString();
				tempSave += "\n";
				
				tempSave += editCarerConNum.getText().toString();
				tempSave += "\n";
				
				// Test toast
				//Toast.makeText(getActivity(), "-"+tempSave, Toast.LENGTH_SHORT).show();
				
				// Write to the file
				medicalFOut.write(tempSave.getBytes());
				
				// Close the file
				medicalFOut.close();
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
		if(medicalFile.exists() && editMeds1 != null)
		{
			try
			{
				// Temp variable
				String tempLoad = "";
				
				// File in stream
				FileInputStream medicalFIn = new FileInputStream(medicalFile);
				// Create a buffer reader to load in information from a file
				BufferedReader medicalBufferReader = new BufferedReader(new InputStreamReader(medicalFIn));
				
				// Load each detail
				// Medication
				tempLoad = medicalBufferReader.readLine();
				editMeds1.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMeds2.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMeds3.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMeds4.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMeds5.setText(tempLoad);
				
				// Medical Problem
				tempLoad = medicalBufferReader.readLine();
				editMedProbs1.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMedProbs2.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMedProbs3.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMedProbs4.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMedProbs5.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMedProbs6.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editMedProbs7.setText(tempLoad);
				
				// Check boxes
				tempLoad = medicalBufferReader.readLine();
				if(tempLoad == "1")
				{
					cbBSLInterp.setChecked(true);
				}
				tempLoad = medicalBufferReader.readLine();
				if(tempLoad == "1")
				{
					cbLipRead.setChecked(true);
				}
				tempLoad = medicalBufferReader.readLine();
				if(tempLoad == "1")
				{
					cbHearingAid.setChecked(true);
				}
				
				// Carer details
				tempLoad = medicalBufferReader.readLine();
				editCarerName.setText(tempLoad);
				
				tempLoad = medicalBufferReader.readLine();
				editCarerAddress1.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editCarerAddress2.setText(tempLoad);
				tempLoad = medicalBufferReader.readLine();
				editCarerAddress3.setText(tempLoad);
				
				tempLoad = medicalBufferReader.readLine();
				editCarerConNum.setText(tempLoad);
				
				// Test toast
				//Toast.makeText(getActivity(), "Loaded:"+tempLoad, Toast.LENGTH_SHORT).show();
				// Close file
				medicalFIn.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}