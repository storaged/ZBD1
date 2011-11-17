package zbd1.dao;

import java.util.List;

import zbd1.Episode;

public class DAOEpisode extends DAOClass {

	/**
	 * creating new DAOEpisode object.
	 * Witch allows user to work with Episode objects 
	 */
	public DAOEpisode() {
        super();
    }

    /**
     * Create a new Episode in the database
     * or update already existing one.
     * @param episode
     */
    public void createEpisode(Episode episode) {
        super.save(episode);
    }


    /**
     * Delete a detached Episode from the database.
     * @param episode
     */
    public void deleteEpisode(Episode episode) {
        super.delete(episode);
    }

    /**
     * Find a Episode by its primary key (id).
     * @param id
     * @return episode
     */
    public Episode findEpisode(Long id) {
        return (Episode) super.find(Episode.class, id);
    }
    
    /**
     * Finds all Episodes in the database.
     * @return list of Episodes
     */
    @SuppressWarnings("unchecked")
	public List<Episode> findAllEpisodes(){
        return (List<Episode>) super.findAll(Episode.class);
    }
    
}
