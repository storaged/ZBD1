package zbd1.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import zbd1.Episode;
import zbd1.TVSeries;

public class DAOEpisodeTest {

	private static final Logger log = LoggerFactory.getLogger(DAOPerson.class);
	DAOEpisode de;
	
	static long idEpisode;
	static long idTVSeries;
	
	@Before
	public void before(){
		de = new DAOEpisode();
	}
	
	@Test
	public void simpleCreateEpisodeTest() {
		
		DAOTVProduction dtv = new DAOTVProduction();
		TVSeries tvs = new TVSeries("Spartacus"); 
		dtv.createTVProduction(tvs);
		idTVSeries = tvs.getId();
		
		Episode e = new Episode();
		e.setNumber(1);
		e.setSeason(1);
		e.setTvSeries(tvs);
		
		de.createEpisode(e);
		Assert.assertNotNull(e.getId());
		idEpisode = e.getId();
		log.info("Episode's id after save is: " + idEpisode);
	}
	
	@Test
	public void simpleUpdateEpisodeTest(){
		
		Episode e1 = de.findEpisode(idEpisode);
		log.info("News from db is: " + e1.toString());
		
		e1.setNumber(3);
		de.createEpisode(e1);
		
		Episode e2 = de.findEpisode(idEpisode);
		log.info("News after update is: " + e2.toString());
		
	}
	
	@Test
	public void findEpisodeTest(){
		Episode e1 = de.findEpisode(idEpisode);
		Assert.assertNotNull(e1);
		log.info("Episode found: " + e1.toString());
	}

	@Test
	public void findAllEpisodesTest(){
		
		DAOTVProduction dtv = new DAOTVProduction();
		TVSeries tvs = dtv.findTVSeries(idTVSeries);
		
		Episode e = new Episode();
		e.setNumber(1);
		e.setSeason(2);
		e.setTvSeries(tvs);
		de.createEpisode(e);
		
		List<Episode> list = de.findAllEpisodes();
		Assert.assertEquals(2, list.toArray().length);
		log.info("All Epsiodes found: " + list.toString());
	}
	

}
