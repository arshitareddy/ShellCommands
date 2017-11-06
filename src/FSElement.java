import java.util.Date;

public class FSElement {
	private String name;
	private String owner;
	private Date createdOn;
	private Date lastModified;
	private int size;
	private Directory parent;
	
	public FSElement(String name, String owner, int size, Directory parent ){
		this.name = name;
		this.owner = owner;
		this.size = size;
		this.parent = parent;
		this.createdOn = new Date();
		this.lastModified = new Date();
		
	}
	
	public void rename(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getOwner(){
		return this.owner;
	}
	
	public void setOwner(String name){
		this.owner = name;
	}
	
	public Date getCreatedOn(){
		return this.createdOn;
		
	}
	public Date getLastModifiedOn(){
		return this.lastModified;
	}
	
	public void updateLastModified(){
		this.lastModified = new Date();
	}
	
	public Directory getParent(){
		return this.parent;
	}
	
	 public boolean isFile(){
		  return true;
	 }
	 
	public int getSize(){
		return this.size;
		
	}
	public void accept(FSVisitor v){
		
	}
	
	
}
