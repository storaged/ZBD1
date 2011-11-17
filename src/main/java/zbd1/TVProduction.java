package zbd1;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class TVProduction{
	
	Long id;

	protected Set<Date> date = new HashSet<Date>();
	protected Set<TVWorker> tvWorkers = new HashSet<TVWorker>();

	public Set<TVWorker> getTvWorkers() {
		return tvWorkers;
	}

	public void setTvWorkers(Set<TVWorker> tvWorkers) {
		this.tvWorkers = tvWorkers;
	}

	public TVProduction() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Date> getDate() {
		return date;
	}

	public void setDate(Set<Date> date) {
		this.date = date;
	}

	public void addDate(Date d){
		this.date.add(d);
	}
	
	public void removeDate(Date d){
		this.date.remove(d);
	}
}