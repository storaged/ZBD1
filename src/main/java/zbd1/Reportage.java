package zbd1;

public class Reportage {

	Long id;
	
	private String subject;
	private String content;
	private int version;

	private Reporter reporter;
	private News news;

	public Reportage() {
		
	}
	
	public Reportage(String subject, String content, int version) {
		super();
		this.subject = subject;
		this.content = content;
		this.version = version;
	}
	
	public Reportage(String subject, String content){
		super();
		this.subject = subject;
		this.content = content;
	}

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Reporter getReporter() {
		return reporter;
	}

	public void setReporter(Reporter reporters) {
		this.reporter = reporters;
	}
	
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
	
	public String toString(){
		return "\nReportage:\n  Title '" + this.getSubject() + "',\n  ver." 
				+ this.getVersion() + ",\n  contains: " + this.getContent();
	}
	
}