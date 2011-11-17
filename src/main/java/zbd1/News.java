package zbd1;

import java.util.HashSet;
import java.util.Set;

public class News extends TVProduction {
	
	private long audience;
	
	// ---- for one-to-many relation --- //
	private Set<Reportage> reportages = new HashSet<Reportage>();
	
	// ---- for one-to-one relation ---- //
	/* 
	private Reportage reportage;
	
	public Reportage getReportage() {
		return reportage;
	}

	public void setReportage(Reportage reportage) {
		this.reportage = reportage;
	} 
	*/

	public News() {
		super();
	}

	public News(long audience) {
		super();
		this.audience = audience;
	}

	public long getAudience() {
		return audience;
	}

	public void setAudience(long audience) {
		this.audience = audience;
	}
	
	public Set<Reportage> getReportages() {
		return reportages;
	}

	public void setReportages(Set<Reportage> reportages) {
		this.reportages = reportages;
	}
	
	public void addReportage(Reportage r){
		this.reportages.add(r);		
	}
	
	public void removeReportage(Reportage r){
		this.reportages.remove(r);
	}
	
	public String toString(){
		return "News: " + "" +
				"\n audience: " + this.getAudience() + 
				"\n was on air on " + this.getDate().toString(); 
	}
	
	
}