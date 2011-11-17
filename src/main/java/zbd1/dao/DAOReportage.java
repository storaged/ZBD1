package zbd1.dao;

import java.util.List;

import org.hibernate.Query;

import zbd1.Reportage;

public class DAOReportage extends DAOClass{
	
	/**
	 * creating new DAOReportage object.
	 * Witch allows user to work with Reportage objects 
	 */
	public DAOReportage() {
        super();
    }

    /**
     * Insert a new Reportage into the database
     * or update one already existing. 
     * Can be used when only when Reportage do not need reporter nor news
     * @param reportage
     */
    // public void createReportage(Reportage reportage) {
    //    super.save(reportage);
    // }

	/**
     * Insert a new Reportage into the database
     * or update one already existing. 
     * @exception hibernateForeignKeyViolation reportage needs an existing (not-null) reporter and news to be set, 
     * otherwise the error will ocure
     * @param reportage (where reporter and news are not null)
     */
    @SuppressWarnings("unchecked")
   	public void saveReportage(Reportage r){
       	beginSession();
       	Query query = session.createQuery("select max(r.version) from Reportage r where r.subject = '" + r.getSubject() + "' and r.reporter = " + r.getReporter().getId() + " and r.news = " + r.getNews().getId() );
       	List<Integer> lr = (List<Integer>) query.list();
       	   	
       	if(lr.get(0) == null){
       		r.setVersion(1);
       	} else {
       		Integer r2 = lr.get(0);
           	r.setVersion(r2+1);
       	}
           
       	session.save(r);  
       	endSession();
       }
       
       public List<Reportage> getLatestNews(){
        return null;
    }

    /**
     * Delete a detached Reportage from the database.
     * @param reportage
     */
    public void deleteReportage(Reportage reportage) {
        super.delete(reportage);
    }

    /**
     * Find a Reportage by its primary key (id).
     * @param id
     * @return reportage
     */
    public Reportage findReportage(Long id) {
        return (Reportage) super.find(Reportage.class, id);
    }
    
    /**
     * Finds all Reportages in the database.
     * @return list of Reportages
     */
    @SuppressWarnings("unchecked")
	public List<Reportage> findAllReportages(){
        return (List<Reportage>) super.findAll(Reportage.class);
    }

}
