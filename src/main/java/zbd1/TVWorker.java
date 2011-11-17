package zbd1;

import java.util.HashSet;
import java.util.Set;

public class TVWorker extends Person {
	
	protected String tvStation;
	protected Set<TVProduction> tvProductions = new HashSet<TVProduction>();

	public Set<TVProduction> getTvProductions() {
		return tvProductions;
	}

	public void setTvProductions(Set<TVProduction> tvProductions) {
		this.tvProductions = tvProductions;
	}

	public TVWorker() {
		super();
	}
	
	public TVWorker(String tvStation) {
		super();
		this.tvStation = tvStation;
	}
	
	public TVWorker(String name, String surname, String tvStation){
		super(name, surname);
		this.tvStation = tvStation;
	}

	public String getTvStation() {
		return tvStation;
	}

	public void setTvStation(String tvStation) {
		this.tvStation = tvStation;
	}
	
}