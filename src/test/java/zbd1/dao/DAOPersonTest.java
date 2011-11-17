package zbd1.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import zbd1.Actor;
import zbd1.Episode;
import zbd1.Person;
import zbd1.Reporter;
import zbd1.TVSeries;
import zbd1.TVWorker;
import zbd1.dao.DAOPerson;
import zbd1.Rating;

public class DAOPersonTest {

	private static final Logger log = LoggerFactory.getLogger(DAOPerson.class);
	DAOPerson dp;
	
	static long idPerson;
	static long idTVWorker;
	static long idActor;
	static long idReporter;
	
	
	@Before
	public void before(){
		dp = new DAOPerson();
	}
	
	@Test
	public void simpleCreatePersonTest() {
		Person p = new Person();
		p.setName("Hudson");
		p.setSurname("Josh");
		
		dp.createPerson(p);
		Assert.assertNotNull(p.getId());
		idPerson = p.getId();
		log.info("Person's id after save is: " + idPerson);
	}
	
	@Test
	public void simpleCreateTVWorkerTest() {
		TVWorker w = new TVWorker();
		w.setName("Tomsky");
		w.setSurname("Jackob");
		w.setTvStation("V Chanel");
		
		dp.createPerson(w);
		Assert.assertNotNull(w.getId());
		idTVWorker = w.getId();
		log.info("TVWorker's id after save is: " + idTVWorker);
	}
	
	@Test
	public void simpleCreateReporterTest() {
		Reporter r = new Reporter();
		r.setName("Robinson");
		r.setSurname("Matthews");
		r.setTvStation("GNews");
		r.setSpeciality("Nature");
		
		dp.createPerson(r);
		Assert.assertNotNull(r.getId());
		idReporter = r.getId();
		log.info("Reporter's id after save is: " + idReporter);
	}
	
	@Test
	public void simpleCreateActorTest() {
		Actor a = new Actor();
		a.setName("Hudson");
		a.setSurname("Josh");
		a.setRating(Rating.AVERAGE);
		a.setTvStation("TV Stop");
		
		dp.createPerson(a);
		Assert.assertNotNull(a.getId());
		idActor = (long) a.getId();
		log.info("!!Actor id after save is: " + a.getId().toString());
	}
	
	@Test
	public void simpleUpdatePersonTest(){
		
		Person p = dp.findPerson(idPerson);
		log.info("Person from db is: " + p.toString());
		
		p.setName("Newnamed");
		dp.createPerson(p);
		
		p = dp.findPerson(idPerson);
		log.info("Person after update is: " + p.toString());
		
	}
	
	@Test
	public void simpleUpdateTVWorkerTest(){
		
		TVWorker w = dp.findTVWorker(idTVWorker);
		log.info("TVWorker from db is: " + w.toString());
		
		w.setName("Notonly");
		w.setTvStation("Omina");
		dp.createPerson(w);
		
		w = dp.findTVWorker(idTVWorker);
		log.info("TVWorker after update is: " + w.toString());
		
	}
	
	@Test
	public void simpleUpdateReporterTest(){
		
		Reporter r = dp.findReporter(idReporter);
		log.info("Reporter from db is: " + r.toString());
		
		r.setName("Notonly");
		r.setTvStation("Omina");
		dp.createPerson(r);
		
		r = dp.findReporter(idReporter);
		log.info("Reporter after update is: " + r.toString());
		
	}
	
	
	@Test
	public void simpleUpdateActorTest(){
		
		Actor a = dp.findActor(idActor);
		log.info("Actor form db is: " + a.toString());
		
		a.setTvStation("TV Start");
		dp.createPerson(a);
		
		a = dp.findActor(idActor);
		log.info("Actor after update is: " + a.toString());
		
	}
	
	@Test
	public void findPersonTest(){
		Person p1 = dp.findPerson(idPerson);
		Assert.assertNotNull(p1);
		log.info("Person found: " + p1.toString());
	}
	
	@Test
	public void findTVworkerTest(){
		TVWorker a1 = dp.findTVWorker(idTVWorker);
		Assert.assertNotNull(a1);
		log.info("TVWorker found: " + a1.toString());
	}
	
	@Test
	public void findReporterTest(){
		Reporter a1 = dp.findReporter(idReporter);
		Assert.assertNotNull(a1);
		log.info("Reporter found: " + a1.toString());
	}
	
	@Test
	public void findActorTest(){
		Actor a1 = dp.findActor(idActor);
		Assert.assertNotNull(a1);
		log.info("Actor found: " + a1.toString());
	}
	
	@Test
	public void findAllPeople(){
		List<Person> list = dp.findAllPeople();
		Assert.assertEquals(4, list.toArray().length);
		log.info("All people found: " + list.toString());
	}
	
	@Test
	public void findAllTVWorkers(){
		List<TVWorker> list = dp.findAllTVWorkers();
		Assert.assertEquals(3, list.toArray().length);
		log.info("All TVWorkers found: " + list.toString());
	}
	
	@Test
	public void findAllReporters(){
		List<Reporter> list = dp.findAllReporters();
		Assert.assertEquals(1, list.toArray().length);
		log.info("All Reporters found: " + list.toString());
	}
	
	@Test
	public void findAllActors(){
		Actor a1 = new Actor();
		a1.setName("Alpha");
		
		Actor a2 = new Actor();
		a2.setName("Beta");
		
		Actor a3 = new Actor();
		a3.setName("Gamma");
		
		List<Actor> list1 = new ArrayList<Actor>();
		list1.add(a1);
		list1.add(a2);
		list1.add(a3);
		
		dp.createPerson(a1);
		dp.createPerson(a2);
		dp.createPerson(a3);
		
		log.info("New actors added: " + list1.toString());
		
		List<Actor> list = dp.findAllActors();
		Assert.assertEquals(4, list.toArray().length);
		log.info("All Actors found: " + list.toString());
	}
		
	@Test
	public void simpleDeleteActorTest(){
		
		Actor a1 = dp.findActor(idActor);
				
		dp.deletePerson(a1);
		
		Actor a2 = dp.findActor(idActor);
		Assert.assertNull(a2);
		
	}
	
	@Test
	public void simpleDeletePersonTest(){
		
		Person a1 = dp.findPerson(idPerson);
				
		dp.deletePerson(a1);
		
		Person a2 = dp.findPerson(idPerson);
		Assert.assertNull(a2);
		
	}
	
	@Test
	public void simpleDeleteTVWorkerTest(){
		
		TVWorker a1 = dp.findTVWorker(idTVWorker);
				
		dp.deletePerson(a1);
		
		TVWorker a2 = dp.findTVWorker(idTVWorker);
		Assert.assertNull(a2);
		
	}
	
	@Test
	public void simpleDeleteReporterTest(){
		
		Reporter a1 = dp.findReporter(idReporter);
				
		dp.deletePerson(a1);
		
		Reporter a2 = dp.findReporter(idReporter);
		Assert.assertNull(a2);
		
	}
	
	@Test
	public void actorEpisodeDeleteTest(){
		DAOTVProduction dtv = new DAOTVProduction();
		DAOEpisode de = new DAOEpisode();
		
		Actor a1 = new Actor(Rating.GOOD);
		a1.setName("Tish");
		a1.setSurname("Mary");
		a1.setTvStation("E!");
		Actor a2 = new Actor(Rating.HORRIBLE);
		a2.setName("Kerish");
		a2.setSurname("Tom");
		a2.setTvStation("Its");
		Actor a3 = new Actor(Rating.GOOD);
		a3.setName("Goodman");
		a3.setSurname("Tobby");
		a3.setTvStation("Moth");
		Actor a4 = new Actor(Rating.GOOD);
		a4.setName("Iron");
		a4.setSurname("Gersh");
		a4.setTvStation("Blur");
		
		dp.createPerson(a1);
		dp.createPerson(a2);
		dp.createPerson(a3);
		dp.createPerson(a4);
		
		long idActor = a1.getId();
		
		Set<Actor> cast1 = new HashSet<Actor>();
		cast1.add(a1);
		cast1.add(a2);
		cast1.add(a3);
		cast1.add(a4);
		
		Set<Actor> cast2 = new HashSet<Actor>();
		cast2.add(a1);
		cast2.add(a2);
		
		Episode e1 = new Episode(1, 1);
		e1.setCast(cast1);
		
		Episode e2 = new Episode(1, 2);
		e2.setCast(cast2);
		
		de.createEpisode(e1);
		de.createEpisode(e2);
		
		Set<Episode> se1 = new HashSet<Episode>();
		se1.add(e1);
		se1.add(e2);
		
		TVSeries tvs1 = new TVSeries("Richmonds");
		tvs1.setEpisodes(se1);
		dtv.createTVProduction(tvs1);
		long idTVSeries = tvs1.getId(); 
		
		log.info("\n Structure before any operations:");
		log.info("\n Actors:\n" + dp.findAllActors().toString());
		log.info("\n TVSeries:\n" + dtv.findAllTVSeries().toString());
		log.info("\n Episodes:\n" + de.findAllEpisodes().toString());
		
		de.deleteEpisode(e2);

		log.info("\n Structure after deleting episode e2");
		log.info("\n Actors:\n" + dp.findAllActors().toString());
		log.info("\n TVSeries:\n" + dtv.findAllTVSeries().toString());
		log.info("\n Episodes:\n" + de.findAllEpisodes().toString());
		
		Actor ad = dp.findActor(idActor);
		dp.deleteActor(ad);
		
		log.info("\n Structure after deleting actor a1");
		log.info("\n Actors:\n" + dp.findAllActors().toString());
		log.info("\n TVSeries:\n" + dtv.findAllTVSeries().toString());
		log.info("\n Episodes:\n" + de.findAllEpisodes().toString());
		
		TVSeries tvs2 = dtv.findTVSeries(idTVSeries);
		dtv.deleteTVProduction(tvs2);
		
		log.info("\n Structure after deleting actor tvs2");
		log.info("\n Actors:\n" + dp.findAllActors().toString());
		log.info("\n TVSeries:\n" + dtv.findAllTVSeries().toString());
		log.info("\n Episodes:\n" + de.findAllEpisodes().toString());
		
	}
	
}
