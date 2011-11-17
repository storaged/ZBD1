package zbd1.dao;

import java.util.List;

import zbd1.Episode;
import zbd1.Person;
import zbd1.TVWorker;
import zbd1.Reporter;
import zbd1.Actor;

public class DAOPerson extends DAOClass{
	
	/**
	 * creating new DAOPerson object.
	 * Witch allows user to work with Person object and its subclass objects including 
	 * TVWorker, Actor and Reporter
	 */
	public DAOPerson() {
        super();
    }

    /**
     * Create a new Person into the database
     * or update already existing one.
     * (or object of type Person's subclass)
     * @param person
     */
    public void createPerson(Person person) {
        super.save(person);
    }


    /**
     * Delete a detached Person from the database.
     * (or object of type Person's subclass)
     * @param person
     */
    public void deletePerson(Person person) {
        super.delete(person);
    }
    
    public void deleteActor(Actor actor){
    	DAOEpisode de = new DAOEpisode();
    	log.info("STARTING SESSION");
    	
    	log.info("!!!! Actor: " + actor.toString() + actor.getEpisodes().toString());
    	for(Episode e : actor.getEpisodes()){
    		log.info("\n>> >> " + e.toString());
    		actor.removeEpisode(e);
    		
    		de.createEpisode(e);
    	}
    	
    	super.delete(actor);
    }

    /**
     * Find a Person by its primary key (id).
     * @param id
     * @return Person
     */
    public Person findPerson(Long id) {
        return (Person) super.find(Person.class, id);
    }
    
    /**
     * Find a TVWorker by its primary key (id).
     * @param id
     * @return TVWorker
     */
    public TVWorker findTVWorker(Long id) {
        return (TVWorker) super.find(TVWorker.class, id);
    }
    
    /**
     * Find a Reporter by its primary key (id).
     * @param id
     * @return Reporter
     */
    public Reporter findReporter(Long id) {
        return (Reporter) super.find(Reporter.class, id);
    }

    /**
     * Find an Actor by its primary key (id).
     * @param id
     * @return Reporter
     */
    public Actor findActor(Long id) {
        return (Actor) super.find(Actor.class, id);
    }
    
    /**
     * Finds all People in the database.
     * @return list of people
     */
    @SuppressWarnings("unchecked")
	public List<Person> findAllPeople(){
        return (List<Person>) super.findAll(Person.class);
    }
    
    /**
     * Finds all TVWorkers in the database.
     * @return list of TVWorkers
     */
    @SuppressWarnings("unchecked")
	public List<TVWorker> findAllTVWorkers(){
        return (List<TVWorker>) super.findAll(TVWorker.class);
    }
    
    /**
     * Finds all Reporters in the database.
     * @return list of reporters
     */
    @SuppressWarnings("unchecked")
	public List<Reporter> findAllReporters(){
        return (List<Reporter>) super.findAll(Reporter.class);
    }
    
    /**
     * Finds all Actors in the database.
     * @return list of actors
     */
    @SuppressWarnings("unchecked")
	public List<Actor> findAllActors(){
        return (List<Actor>) super.findAll(Actor.class);
    }

}
