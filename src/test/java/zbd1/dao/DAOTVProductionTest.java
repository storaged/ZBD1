package zbd1.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.TestName;

import zbd1.Episode;
import zbd1.News;
import zbd1.Reportage;
import zbd1.Reporter;
import zbd1.TVSeries;

public class DAOTVProductionTest {

	private static final Logger log = LoggerFactory.getLogger(DAOTVProduction.class);
	DAOTVProduction dp;
	
	@Rule
	public TestName tn = new TestName();
	
	static long idTVSeries;
	static long idNews;
	static int noTest=2;
	
	@Before
	public void before(){
		log.info("\n[START-OF-TEST] " + this.getClass().getDeclaredMethods()[noTest].toString());
		dp = new DAOTVProduction();
	}
	
	@After
	public void after(){
		log.info("\n[END-OF-TEST] " + this.getClass().getDeclaredMethods()[noTest].toString());
		noTest++;
	}
	
	@Test
	public void simpleCreateTVSeriesTest() {
		log.trace("begin {}",tn.getMethodName());
		DAOEpisode de = new DAOEpisode();
		Episode e1 = new Episode(1, 1);
		de.createEpisode(e1);
		
		Episode e2 = new Episode(1, 2);
		de.createEpisode(e2);
		
		Episode e3 = new Episode(1, 3);
		de.createEpisode(e3);
		
		Set<Episode> se = new HashSet<Episode>();
		se.add(e1);
		se.add(e2);
		se.add(e3);
		
		Date d1 = Calendar.getInstance().getTime();		
		Set<Date> sd = new HashSet<Date>();
		sd.add(d1);
		
		TVSeries s = new TVSeries();
		s.setTitle("Friends");
		s.setEpisodes(se);
		s.setDate(sd);
		
		dp.createTVProduction(s);
		Assert.assertNotNull(s.getId());
		idTVSeries = s.getId();
		log.info("TVSeries' id after save is: " + idTVSeries);
	}
	
	@Test
	public void simpleCreateNewsTest() {
		News n = new News();
		n.setAudience(200000);
		Date d1 = Calendar.getInstance().getTime();		
		Set<Date> sd = new HashSet<Date>();
		sd.add(d1);
		n.setDate(sd);
		
		dp.createTVProduction(n);
		Assert.assertNotNull(n.getId());
		idNews = n.getId();
		log.info("News' id after save is: " + idTVSeries);
	}
	
	@Test
	public void simpleUpdateTVSeriesTest(){
		
		TVSeries s1 = dp.findTVSeries(idTVSeries);
		log.info("TVSeries from db is: " + s1.toString());
		
		s1.setTitle("Hopeful glory");
		dp.createTVProduction(s1);
		
		TVSeries s2 = dp.findTVSeries(idTVSeries);
		log.info("TVSeries after update is: " + s2.toString());
		
	}

	@Test
	public void simpleUpdateNewsTest(){
		
		News s1 = dp.findNews(idNews);
		log.info("News from db is: " + s1.toString());
		
		s1.setAudience(250000);
		dp.createTVProduction(s1);
		
		News s2 = dp.findNews(idNews);
		log.info("News after update is: " + s2.toString());
		
	}
	
	@Test
	public void findTVSeriesTest(){
		TVSeries p1 = dp.findTVSeries(idTVSeries);
		Assert.assertNotNull(p1);
		log.info("TVSeries found: " + p1.toString());
	}
	
	@Test
	public void findNewsTest(){
		News p1 = dp.findNews(idNews);
		Assert.assertNotNull(p1);
		log.info("News found: " + p1);
	}

	@Test
	public void findAllTVSeries(){
		TVSeries s = new TVSeries();
		s.setTitle("Doomies");
		dp.createTVProduction(s);
		
		List<TVSeries> list = dp.findAllTVSeries();
		Assert.assertEquals(2, list.toArray().length);
		log.info("All TVSeries found: " + list.toString());
	}
	
	@Test
	public void findAllNews(){
		News s = new News();
		s.setAudience(40000);
		dp.createTVProduction(s);
		
		List<News> list = dp.findAllNews();
		Assert.assertEquals(2, list.toArray().length);
		log.info("All News found: " + list.toString());
	}
	
	@Test
	public void findLatestNewsTest(){
		
		DAOPerson dper = new DAOPerson();
		DAOReportage dr = new DAOReportage();
		
		Reportage r = new Reportage();
		r.setContent("Duration of second");
		r.setSubject("Phisics in the mirror");
		
		Reporter rr = new Reporter();
		rr.setName("Tazz");
		rr.setSurname("Liv");	
		r.setReporter(rr);
		dper.createPerson(rr);
		
		News n = dp.findNews(idNews);
		log.info("Looking for latest news on: " + n.toString());
		
		r.setNews(n);
		dp.createTVProduction(n);
		dr.saveReportage(r);
		
		List<Reportage> lnews = dp.getLatestNews(n);
		log.info("Latest news are: " + lnews.toString());
		Assert.assertEquals(lnews.size(), 1);
		
	}
	
	@SuppressWarnings("deprecation")
	@Test 
	public void getMostPopularNewsTest(){
		Date d1 = Calendar.getInstance().getTime();
		d1.setMonth(1);
		Date d2 = Calendar.getInstance().getTime();
		d2.setMonth(4);
		Date d3 = Calendar.getInstance().getTime();
		d3.setMonth(10);
		Set<Date> dates1 = new HashSet<Date>();
		dates1.add(d1);
		dates1.add(d2);
		dates1.add(d3);
		
		
		News n1 = new News(50000);
		n1.setDate(dates1);
		dp.createTVProduction(n1);
		
		News n2 = new News(30000);
		dp.createTVProduction(n2);
		
		List<News> ln = dp.getMostPopularNews();
		log.info("Most popular News: \n" + ln.toString());
	}
	
	@SuppressWarnings("deprecation")
	@Test 
	public void getGreatestAverageNewsTest(){
		Date d1 = Calendar.getInstance().getTime();
		d1.setDate(14);
		Date d2 = Calendar.getInstance().getTime();
		d2.setMinutes(15);
		Date d3 = Calendar.getInstance().getTime();
		d3.setMonth(2);
		Set<Date> dates1 = new HashSet<Date>();
		Set<Date> dates2 = new HashSet<Date>();
		dates1.add(d1);
		dates1.add(d2);
		dates1.add(d3);
		dates2.add(d1);
		dates2.add(d2);
		
		News n1 = new News(50000);
		n1.setDate(dates1);
		dp.createTVProduction(n1);
		
		News n2 = new News(30000);
		n2.setDate(dates2);
		dp.createTVProduction(n2);
		
		News ln = dp.getGreatestAverageAudience();
		log.info("Gratest average audience among News: \n" + ln.toString());
	}
	
	@Test 
	public void getLongestTVSeriesByNoSesonsTest(){
		List<TVSeries> ltv = dp.getLongestTVSeriesByNoSesons();
		log.info(ltv.toString());		
	}
	
	@Test 
	public void getLongestTVSeriesByNoEpisodesTest(){
		List<TVSeries> ltv = dp.getLongestTVSeriesByNoEpisode();
		log.info(ltv.toString());		
	}
	
	
	@Test
	public void simpleDeleteTVSeriesTest(){
		TVSeries a1 = dp.findTVSeries(idTVSeries);
		dp.deleteTVProduction(a1);
		TVSeries a2 = dp.findTVSeries(idTVSeries);
		Assert.assertNull(a2);
	}
	
	@Test
	public void simpleDeleteNewsTest(){
		News a1 = dp.findNews(idNews);
		dp.deleteTVProduction(a1);		
		News a2 = dp.findNews(idNews);
		Assert.assertNull(a2);		
	}
}
