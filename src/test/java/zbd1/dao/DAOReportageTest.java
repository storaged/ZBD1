package zbd1.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zbd1.News;
import zbd1.Reportage;
import zbd1.Reporter;

public class DAOReportageTest {

	private static final Logger log = LoggerFactory.getLogger(DAOReportageTest.class);
	DAOReportage dr;
	
	static long idReportage;
	static int noTest=2;
	
	@Before
	public void before(){
		log.info("\n[START-OF-TEST] " + this.getClass().getDeclaredMethods()[noTest].toString());
		dr = new DAOReportage();
	}
	
	@After
	public void after(){
		log.info("\n[END-OF-TEST] " + this.getClass().getDeclaredMethods()[noTest].toString());
		noTest++;
	}
	
	@Test
	public void simpleCreateReportageTest() {
		DAOPerson dp = new DAOPerson();
		DAOTVProduction dtv = new DAOTVProduction();
		
		log.info("1. Preparing reportage");
		Reportage reportage = new Reportage();
		reportage.setContent("some stupid f**kin' content");
		reportage.setSubject("No lads");
		
		log.info("2. Preparing reporter for reportage");
		Reporter reporter = new Reporter();
		reporter.setName("Fred");
		reporter.setSurname("Hax");
		reportage.setReporter(reporter);
		dp.createPerson(reporter);
		
		log.info("4. Preparing news for reportage");
		News news = new News();
		news.setAudience(2000000);
		reportage.setNews(news);
		dtv.createTVProduction(news);
		
		log.info("saving reportage");
		dr.saveReportage(reportage);
				
		Assert.assertNotNull(reportage.getId());
		idReportage = reportage.getId();
		log.info("Reportage's id after save is: " + idReportage);
		
	}
	
	@Test
	public void simpleUpdateReportageTest(){
		log.info("ID --- " + idReportage);
		Reportage e1 = dr.findReportage(idReportage);
		Assert.assertNotNull(e1);
		int oldVer = e1.getVersion();
		log.info("\nNews from db is: " + e1.toString());
		
		e1.setContent("new stuff");
		log.info("\nNews updated before update is: " + e1.toString());
		dr.saveReportage(e1);
		
		Reportage e2 = dr.findReportage(e1.getId());
		Assert.assertTrue(oldVer + 1 == e2.getVersion());
		log.info("\nNews after update is: " + e2.toString());
		
	}
	
	@Test
	public void findReportageTest(){
		Reportage e1 = dr.findReportage(idReportage);
		Assert.assertNotNull(e1);
		log.info("\nReportage found: " + e1.toString());
		
	}

	@Test
	public void findAllReportagesTest(){
		DAOPerson dp = new DAOPerson();
		DAOTVProduction dtv = new DAOTVProduction();
		
		Reportage reportage = new Reportage();
		reportage.setSubject("Universe");
		
		Reporter reporter = new Reporter("Exploration");
		reporter.setName("Fred");
		reporter.setSurname("Hax");
		reporter.setTvStation("NonAme");
		reportage.setReporter(reporter);
		
		dp.createPerson(reporter);	
		
		News news = new News(200000);
		reportage.setNews(news);
		
		dtv.createTVProduction(news);
		
		dr.saveReportage(reportage);
		idReportage = reportage.getId();
		List<Reportage> list = dr.findAllReportages();
		Assert.assertEquals(3, list.toArray().length);
		log.info("\nAll Reportages found: " + list.toString());
		
	}

	@Test
   public void deleteReportageTest() {
		
		DAOPerson dp = new DAOPerson();
		DAOTVProduction dtv = new DAOTVProduction();
		
        Reportage r1 = dr.findReportage(idReportage);
        log.info("\nReportage to be deleted:" + r1.toString());
        Reporter r = r1.getReporter();
        News n = r1.getNews();
        log.info("before delete: \n" + r.toString());
        log.info("\n" + n.toString());
        
        dr.deleteReportage(r1);
        Reportage r2 = dr.findReportage(idReportage);
        Assert.assertNull(r2);
        
        Reporter rr = dp.findReporter(r.getId());
        News nn = dtv.findNews(n.getId());
        log.info("After delete: \n" + rr.toString());
        log.info("\n" + nn.toString());
     
   }
}
