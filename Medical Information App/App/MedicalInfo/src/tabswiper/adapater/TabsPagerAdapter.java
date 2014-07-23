package tabswiper.adapater;

import com.example.medicalinfo.DetailsFragment;
import com.example.medicalinfo.DoctorFragment;
import com.example.medicalinfo.MainScreenFragment;
import com.example.medicalinfo.MedicalFragment;
import com.example.medicalinfo.ContactFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
//----------TABS PAGER ADAPTER----------
// This class has been added to allow
// for tabs at the top of the application

public class TabsPagerAdapter extends FragmentPagerAdapter 
{
	//----------VARIABLES----------
	private MainScreenFragment mScreenFragment;
	private DetailsFragment detailsFragment;
	private DoctorFragment doctorFragment;
	private MedicalFragment medicalFragment;
	private ContactFragment contactFragment;
 
	// Constructor
    public TabsPagerAdapter(FragmentManager fm) 
    {
        super(fm);
        
        // Set up the other tabs activities
        mScreenFragment = new MainScreenFragment();
        detailsFragment = new DetailsFragment();
        doctorFragment = new DoctorFragment();
        medicalFragment = new MedicalFragment();
        contactFragment = new ContactFragment();
    }
 
    // Method to manage which tab is in use, using a switch case statement
    @Override
    public Fragment getItem(int index) 
    {
        switch (index) 
        {
        case 0:
        	// Main Screen fragment activity
        	return mScreenFragment;
        case 1:
            // Personal Details fragment activity
            return detailsFragment;
        case 2:
            // Doctor fragment activity
            return doctorFragment;
        case 3:
            // Medical fragment activity
            return medicalFragment;
        case 4:
        	// Contact fragment activity
        	return contactFragment;
        }
 
        return null;
    }
 
    @Override
    public int getCount() 
    {
        // get item count - equal to number of tabs
        return 5;
    }
 
}