package zbd1;

import java.util.HashSet;
import java.util.Set;

public class TVSeries extends TVProduction {
	
	private String title;
	private Set<Episode> episodes = new HashSet<Episode>();

	public TVSeries() {
		super();
	}

	public TVSeries(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Set<Episode> episodes) {
		this.episodes = episodes;
	}
	
	public String toString(){
		return this.getTitle();
	}
	
	public void addEpisode(Episode e){
		this.episodes.add(e);
	}
	
	public void removeEpisode(Episode e){
		this.episodes.remove(e);
	}
	
}