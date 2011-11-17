package zbd1;

import java.util.HashSet;
import java.util.Set;


public class Actor extends TVWorker {
	
	private Rating rating;

	private Set<Episode> episodes = new HashSet<Episode>();
	
	public Set<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Set<Episode> episodes) {
		this.episodes = episodes;
	}

	public Actor(){
		super();
	}
	
	public Actor(Rating rating) {
		super();
		this.rating = rating;
	}

	public Actor(String name, String surname, String tvStation, Rating rating){
		super(name, surname, tvStation);
		this.rating = rating;
	}
	
	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
		
	public void addEpisode(Episode e){
		this.episodes.add(e);
	}
	
	public void removeEpisode(Episode e){
		this.episodes.remove(e);
	}
	
	public String toString() {
		
		return "\nActor:" +
				"\n name: " + this.getName() + 
				"\n surname: " + this.getSurname() + 
				"\n TVStation: " + this.getTvStation() + 
				"\n played in " + this.getEpisodes().size() + " episode(s)";
	}
	
}