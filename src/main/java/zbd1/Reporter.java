package zbd1;

import java.util.HashSet;
import java.util.Set;

public class Reporter extends TVWorker {

	private String speciality;
	private Set<Reportage> reportages = new HashSet<Reportage>();
	
	public Reporter() {
		super();
	}
	
	public Reporter(String speciality) {
		super();
		this.speciality = speciality;
	}
	
	public Reporter(String name, String surname, String tvStation, String speciality){
		super(name, surname, tvStation);
		this.speciality = speciality;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public Set<Reportage> getReportages() {
		return reportages;
	}

	public void setReportages(Set<Reportage> reportages) {
		this.reportages = reportages;
	}
	
	public String toString(){
		return "Reporter: " + 
				"\n name: " + this.getName() + 
				"\n surname: " + this.getSurname() + 
				"\n TVStation: " + this.getTvStation() + 
				"\n speciality: " + this.getSpeciality() + 
				"\n reportages: " + this.getReportages().toString();
	}

}