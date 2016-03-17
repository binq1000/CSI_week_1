package csi.fhict.org.csi_week_1;

import android.graphics.drawable.Drawable;
import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Criminal implements Serializable{

	public String name;
	public String gender;
	public String description;
	public int age;
	
	public List<Crime> crimes;
	
//	public Drawable mugshot;
	public int imageID;
//	public Location lastKnownLocation;
	public double latitude;
	public double longitude;
	
	public int getBountyInDollars() {
		int bounty = 0;
		for(Crime crime : crimes) {
			bounty += crime.bountyInDollars;
		}
		
		return bounty;
	}
}
