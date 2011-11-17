package zbd1.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import zbd1.News;
import zbd1.Reportage;
import zbd1.TVProduction;
import zbd1.TVSeries;

public class DAOTVProduction extends DAOClass{

	/**
	 * creating new DAOTVProduction object.
	 * Witch allows user to work with News/TVSeries objects 
	 */
	public DAOTVProduction() {
        super();
    }

    /**
     * Insert a new News/TVSeries into the database.
     * @param tvProduction
     */
    public void createTVProduction(TVProduction tvProduction) {
        super.save(tvProduction);
    }


    /**
     * Delete a detached News/TVSeries from the database.
     * @param tvProduction
     */
    public void deleteTVProduction(TVProduction tvProduction) {
        super.delete(tvProduction);
    }

    /**
     * Find a TVProduction by its primary key (id).
     * @param id
     * @return tvProduction
     */
    public TVProduction findTVProduction(Long id) {
        return (TVProduction) super.find(TVProduction.class, id);
    }
    
    /**
     * Find a News by its primary key (id).
     * @param id
     * @return news
     */
    public News findNews(Long id) {
        return (News) super.find(News.class, id);
    }
    
    /**
     * Find a TVSeries by its primary key (id).
     * @param id
     * @return tvSeries
     */
    public TVSeries findTVSeries(Long id) {
        return (TVSeries) super.find(TVSeries.class, id);
    }

    /**
     * Finds all TVProductions in the database.
     * @return list of TVProductions
     */
    @SuppressWarnings("unchecked")
	public List<TVProduction> findAllTVProductions(){
        return (List<TVProduction>) super.findAll(TVProduction.class);
    }
    
    /**
     * Finds all TVSeries in the database.
     * @return list of TVSeries
     */
    @SuppressWarnings("unchecked")
	public List<TVSeries> findAllTVSeries(){
        return (List<TVSeries>) super.findAll(TVSeries.class);
    }
    
    /**
     * Finds all News in the database.
     * @return list of News
     */
    @SuppressWarnings("unchecked")
	public List<News> findAllNews(){
        return (List<News>) super.findAll(News.class);
    }
    
    /**
     * Function looks for reportages in their highest version number 
     * included in specified news 
     * @param n News in which we're looking for latest news/reportages
     * @return list of reportages, each in the latest version.
     */
    @SuppressWarnings("unchecked")
	public List<Reportage> getLatestNews(News n){
    	
    	List<Reportage> ans = null;
    	
    	try {
            beginSession();
            Query query = session.createQuery("select r from Reportage r " +
        			"where r.news = :tv and r.version = " +
                    "(select max(rr.version) from Reportage rr where " +
                    "rr.news = r.news and rr.subject = r.subject and r.reporter = rr.reporter)");
        	query.setParameter("tv", n);
        	ans =(List<Reportage>) query.list();
        	endSession();
        } catch (HibernateException e) {
        	log.warn("error while executing query :\n" + e.getMessage());
         	try {
         		session.close();
         	} catch (HibernateException e2) {
         		log.warn("error while closing session\n" + e2.getMessage());
         	}
        }      
    	return ans;
    }
    
    /**
     * function returns list of news with the biggest audience
     * @return list of news
     */
    @SuppressWarnings("unchecked")
	public List<News> getMostPopularNews(){
    	
    	List<News> ans = null;
    	
    	try {
            beginSession();
            Query query = session.createQuery("select n from News n " +
            		"where n.audience = " +
            		"(select max(nn.audience) from News nn)");
            ans =(List<News>) query.list();
        	endSession();
        } catch (HibernateException e) {
        	log.warn("error while executing query :\n" + e.getMessage());
         	try {
         		session.close();
         	} catch (HibernateException e2) {
         		log.warn("error while closing session\n" + e2.getMessage());
         	}
        }      
    	return ans;
    	
    }
    
    /**
     * function returns list of news with the biggest audience
     * @return list of news
     */
    @SuppressWarnings("unchecked")
	public News getGreatestAverageAudience(){
    	
    	List<News> ans = null;
    	News res = null;
    	
    	try {
            beginSession();
            Query query = session.createQuery("select n from News n" );
            		
            ans = (List<News>) query.list();
            double[] aver = new double[ans.size()];
            int indx = -1;
            double max = 0;
        	for(int i=0; i < ans.size(); i++){
        		if(ans.get(i).getDate().size()!=0){
	        		aver[i] = ans.get(i).getAudience()/ans.get(i).getDate().size();
	        		log.info(">> " + aver[i] + " " + ans.get(i).getDate().toString());
	        		if(aver[i] > max){
	        			indx = i;
	        			max = aver[i];
	        		}
        		}
        	}
        	if(max!=0)
        		res = ans.get(indx);
        	endSession();
        } catch (HibernateException e) {
        	log.warn("error while executing query :\n" + e.getMessage());
         	try {
         		session.close();
         	} catch (HibernateException e2) {
         		log.warn("error while closing session\n" + e2.getMessage());
         	}
        }      
    	return res;
    	
    }
    
    /**
     * returns list of tvserieses with the greatest amount of seasons included
     * @return list of tvseries
     */
    @SuppressWarnings("unchecked")
	public List<TVSeries> getLongestTVSeriesByNoSesons(){
    	
    	List<TVSeries> ans = null;
    	
    	try {
            beginSession();
            Query query = session.createQuery("select distinct s from TVSeries s, Episode e " +
        			"where e.tvSeries = s.id and e.season=" +
                    "(select max(e2.season) from Episode e2)");
            ans =(List<TVSeries>) query.list();
        	endSession();
        } catch (HibernateException e) {
        	log.warn("error while executing query :\n" + e.getMessage());
         	try {
         		session.close();
         	} catch (HibernateException e2) {
         		log.warn("error while closing session\n" + e2.getMessage());
         	}
        }      
    	return ans;
    
    }
    
    /**
     * returns list of tvserieses with the biggest amount of episodes included
     * @return list of tvseries
     */ 
	public List<TVSeries> getLongestTVSeriesByNoEpisode(){
    	
    	List<TVSeries> ans = null;
    	
    	try {
            beginSession();
            Query query = session.createQuery("select tv," +
                    "count(*) as noSeasons "  +
                    "from TVSeries tv, Episode e " +
                    "where e.tvSeries = tv.id " +
                    "group by tv.id, tv.title " +
                    "order by noSeasons");
        	Object[] al = query.list().toArray();
        	ans = new ArrayList<TVSeries>();
        	for(int i=0; i <al.length; i++){
        		ans.add((TVSeries) ((Object[]) al[i])[0]);
        	}
        	endSession();
        } catch (HibernateException e) {
        	log.warn("error while executing query :\n" + e.getMessage());
         	try {
         		session.close();
         	} catch (HibernateException e2) {
         		log.warn("error while closing session\n" + e2.getMessage());
         	}
        }      
    	return ans;
    	
    }
    
    
}
