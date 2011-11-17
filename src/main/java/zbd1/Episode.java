package zbd1;

import java.util.HashSet;
import java.util.Set;

public class Episode {

	Long id;

	private int season;
	private int number;
	private Set<Actor> cast = new HashSet<Actor>();
	private TVSeries tvSeries;
	
	public Episode() {
	
	}
	
	public Episode(int season, int number) {
		super();
		this.season = season;
		this.number = number;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Set<Actor> getCast() {
		return cast;
	}

	public void setCast(Set<Actor> cast) {
		this.cast = cast;
	}
	
	public TVSeries getTvSeries() {
		return tvSeries;
	}

	public void setTvSeries(TVSeries tvSeries) {
		this.tvSeries = tvSeries;
	}
	
	public void addActor(Actor a){
		boolean found = false;
		long newId = -1;
		for(Actor a1 : this.cast){
			if(newId < a1.getId())
				newId = a1.getId();
			if(a1.getId()==a.getId()){
				this.cast.remove(a1);
				found = true;
				break;
			}
		}
		if(!found)
			this.cast.add(a);
	}
	
	public void removeActor(Actor a){
		a.getEpisodes().remove(this);
		for(Actor a1 : this.cast){
			if(a1.getId()==a.getId()){
				this.cast.remove(a1);
				break;
			}
		}
	}
	
	public String toString(){
		return "\nEpisode: " + 
				"\n Title: " + this.getTvSeries().toString() +
				"\n season no: " + this.getSeason() + 
				"\n episode no: " + this.getNumber() + 
				"\n cast: " + this.getCast().toString();
	}
	
}