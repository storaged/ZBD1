package zbd1.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

import zbd1.Actor;
import zbd1.Episode;
import zbd1.News;
import zbd1.Person;
import zbd1.Rating;
import zbd1.Reportage;
import zbd1.Reporter;
import zbd1.TVSeries;
import zbd1.TVWorker;


public class Service {

	private static final Logger log = LoggerFactory.getLogger(Service.class);
	static long idActor1; 
	static long idActor2;
	static long idEpisode1;
	static long idEpisode2;
	static long idEpisode3;
	static long idTVSeries1;
	static long idReporter1;
	static long idReporter2;
	static long idReportage1;
	static long idReportage2;
	static long idReportage3;
	static long idNews1;
	static long idNews2;
	
	@SuppressWarnings("deprecation")
	@Test
	public void serviceTest() {
		
		DAOPerson dp = new DAOPerson();
		DAOEpisode de = new DAOEpisode();
		DAOTVProduction dtv = new DAOTVProduction();
		DAOReportage dr = new DAOReportage();
		
		Person person1 = new Person("Abratz", "Torhan");
		dp.createPerson(person1);
		
		Person person2 = new Person("Korps", "Josh");
		dp.createPerson(person2);
		
		TVWorker tvw1 = new TVWorker("Maxwell", "Tobiash", "RobbsTV");
		dp.createPerson(tvw1);
		
		TVWorker tvw2 = new TVWorker("Tangardian", "Olaf", "SkandyTV");
		dp.createPerson(tvw2);
		
		Actor a1 = new Actor("Doodle", "North", "SkandyTV", Rating.GOOD);
		dp.createPerson(a1);
		idActor1 = a1.getId();
		
		Actor a2 = new Actor("Fondle", "Josh", "BBC1", Rating.GOOD);
		dp.createPerson(a2);
		idActor2 = a2.getId();
		
		Actor a3 = new Actor("Skater", "Max", "BBC1", Rating.AVERAGE);
		dp.createPerson(a3);
		
		Actor a4 = new Actor("Orbison", "Jess", "4f", Rating.HORRIBLE);
		dp.createPerson(a4);
		
		Actor a5 = new Actor("Parkinson", "Todd", "BBC1", Rating.WEAK);
		dp.createPerson(a5);
		
		Reporter r1 = new Reporter("Jead", "Cleark", "BBC1", "Animals");
		dp.createPerson(r1);
		idReporter1 = r1.getId();
		
		Reporter r2 = new Reporter("Gabb", "Eva", "Newsweek", "Showbusiness");
		dp.createPerson(r2);
		idReporter2 = r2.getId();
		
		Reporter r3 = new Reporter("Mousen", "Lilly", "4f", "Science");
		dp.createPerson(r3);
		
		Episode e1 = new Episode(1, 1);
		Episode e2 = new Episode(1, 2);
		Episode e3 = new Episode(2, 1);
		Episode e4 = new Episode(2, 2);
		Episode e5 = new Episode(1, 1);
		Episode e6 = new Episode(1, 2);
		
		Set<Actor> cast1 = new HashSet<Actor>();
		cast1.add(a1);
		cast1.add(a2);
		cast1.add(a3);
		cast1.add(a4);
		
		Set<Actor> cast2 = new HashSet<Actor>();
		cast2.add(a2);
		cast2.add(a3);
		cast2.add(a4);
		
		Set<Actor> cast3 = new HashSet<Actor>();
		cast3.add(a1);
		cast3.add(a3);
		cast3.add(a5);
		
		Set<Actor> cast4 = new HashSet<Actor>();
		cast4.add(a1);
		cast4.add(a2);
		cast4.add(a3);
		cast4.add(a4);
		cast4.add(a5);
		
		Set<Actor> cast5 = new HashSet<Actor>();
		cast5.add(a2);
		cast5.add(a4);
		
		Set<Actor> cast6 = new HashSet<Actor>();
		cast6.add(a1);
		cast6.add(a2);
		cast6.add(a4);
		cast6.add(a5);
		
		e1.setCast(cast1);
		e2.setCast(cast2);
		e3.setCast(cast3);
		e4.setCast(cast4);
		e5.setCast(cast5);
		e6.setCast(cast6);
		
		de.createEpisode(e1);
		idEpisode1 = e1.getId();
		de.createEpisode(e2);
		idEpisode2 = e2.getId();
		de.createEpisode(e3);
		idEpisode3 = e3.getId();
		de.createEpisode(e4);
		de.createEpisode(e5);
		de.createEpisode(e6);
		
		TVSeries tvs1 = new TVSeries("Looniers");
		TVSeries tvs2 = new TVSeries("Graddiers of Night");
		
		Set<Episode> eps1 = new HashSet<Episode>();
		eps1.add(e1);
		eps1.add(e2);
		eps1.add(e3);
		eps1.add(e4);
		
		Set<Episode> eps2 = new HashSet<Episode>();
		eps1.add(e5);
		eps1.add(e6);
		
		tvs1.setEpisodes(eps1);
		tvs2.setEpisodes(eps2);
		
		Reportage rep1 = new Reportage("New dimensions in the future", "Quazi phisics");
		rep1.setReporter(r1);
		idReportage1 = r1.getId();
		
		Reportage rep2 = new Reportage("Parallel world", "String theory, big bang");
		rep2.setReporter(r2);
		idReportage2 = r2.getId();
		
		Reportage rep3 = new Reportage("Pandas habitat", "Asia as home of nature");
		rep3.setReporter(r2);
		
		Reportage rep4 = new Reportage("Stars are blind", "Hollywood today and tommorow");
		rep4.setReporter(r3);
		
		Reportage rep5 = new Reportage("God, better, the worst", "Maniac monday of yours");
		rep5.setReporter(r3);
		
		Reportage rep6 = new Reportage("xxyxxnsxxt sound", "Music of the future");
		rep6.setReporter(r3);
		
		Date d1 = Calendar.getInstance().getTime();;
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
		
		News n1 = new News(10000);
		n1.setDate(dates1);
		rep1.setNews(n1);
		rep2.setNews(n1);
		rep3.setNews(n1);
		News n2 = new News(30000);
		n2.setDate(dates2);
		rep4.setNews(n2);
		rep5.setNews(n2);
		News n3 = new News(25000);
		rep6.setNews(n3);
		
		Set<TVWorker> tvwset1 = new HashSet<TVWorker>();
		tvwset1.add(tvw1);
		tvwset1.add(tvw2);
		
		Set<TVWorker> tvwset2 = new HashSet<TVWorker>();
		tvwset2.add(a1);
		tvwset2.add(a2);
		tvwset2.add(r3);
		
		Set<TVWorker> tvwset3 = new HashSet<TVWorker>();
		tvwset3.add(tvw1);
		tvwset3.add(a3);
		tvwset3.add(r1);
		tvwset3.add(r3);
		
		Set<TVWorker> tvwset4 = new HashSet<TVWorker>();
		tvwset4.add(a1);
		tvwset4.add(a2);
		
		n1.setTvWorkers(tvwset1);
		n2.setTvWorkers(tvwset3);
		tvs1.setTvWorkers(tvwset2);
		tvs2.setTvWorkers(tvwset4);
		
		dtv.createTVProduction(n1);
		idNews1 = n1.getId();
		dtv.createTVProduction(n2);
		dtv.createTVProduction(n3);
		dtv.createTVProduction(tvs1);
		idTVSeries1 = tvs1.getId();
		dtv.createTVProduction(tvs2);
		
		dr.saveReportage(rep1);
		dr.saveReportage(rep2);
		dr.saveReportage(rep3);
		dr.saveReportage(rep4);
		dr.saveReportage(rep5);
		dr.saveReportage(rep6);
		
		
		List<Person> lp = dp.findAllPeople();
		Assert.assertTrue(lp.size()==12);
		
		List<TVWorker> ltvw = dp.findAllTVWorkers();
		Assert.assertTrue(ltvw.size()==10);
		
		List<Actor> la = dp.findAllActors();
		Assert.assertTrue(la.size()==5);
		
		List<Episode> le = de.findAllEpisodes();
		Assert.assertTrue(le.size()==6);
		
		List<TVSeries> ltvs = dtv.findAllTVSeries();
		Assert.assertTrue(ltvs.size()==2);
		
		List<Reporter> lr = dp.findAllReporters();
		Assert.assertTrue(lr.size()==3);
		
		List<Reportage> lrep = dr.findAllReportages();
		Assert.assertTrue(lrep.size()==6);
		
		List<News> ln = dtv.findAllNews();
		Assert.assertTrue(ln.size()==3);
		
		log.info("\n all objects create such an structures" +
				"\n People:\n" + lp.toString() + 
				"\n TVWorkers:\n" + ltvw.toString() +
				"\n Actors:\n" + la.toString() + 
				"\n Episodes:\n" + le.toString() + 
				"\n TVSeries:\n" + ltvs.toString() +
				"\n Reporters:\n" + lr.toString() +
				"\n Reportage:\n" + lrep.toString() +
				"\n News:\n" + ln.toString()
				);

		News nf1 = dtv.findNews(idNews1);
		log.info("\nNews with biggest average audience: \n" + dtv.getGreatestAverageAudience().toString());
		log.info("\nNews with biggest audience: \n" + dtv.getMostPopularNews().toString());
		log.info("\nLatest news: \n" + dtv.getLatestNews(nf1).toString());
		log.info("\nLongest tv series by no episodes: \n" + dtv.getLongestTVSeriesByNoEpisode().toString());
		log.info("\nLongest tv series by no seasons: \n" + dtv.getLongestTVSeriesByNoSesons().toString());
		
		}

}
