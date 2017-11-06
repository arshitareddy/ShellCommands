import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FileSystem {
	private static FileSystem instance = null;
	private Directory rootDir= new Directory("root", "Arshita", 0, null);
	private Directory current;
	//private ArrayList<FSElement> current_children ;
	private FileSystem(){		
	}
	
	public static FileSystem getFileSystem(){ 
		if(instance==null) instance = new FileSystem(); 
		return instance; 
		}
	
	public void setRootDIR(Directory rootname){
		this.rootDir = rootname;
	}
	
	public Directory getRootDIR(){
		return this.rootDir;
	}
	
	public void setCurrentDIR(Directory child){
		this.current = child;
	}
	
	public Directory getCurrentDIR(){
		return this.current;
	}
	
	public ArrayList<FSElement> sort(Directory dir, Comparator<FSElement> ac){
		Collections.sort(dir.getChildren(), ac);	
		return dir.getChildren();
	}
	
	public int getInsertionLocation(Directory dir, FSElement child){
		sort(dir, new AlphebeticalComparator());
		int location = 0;
		for(FSElement c : dir.getChildren()){
			AlphebeticalComparator ac = new AlphebeticalComparator();
			int compared_value = ac.compare(c, child);
			if (compared_value < 0){
				location++;
			}
			
		}
		return location;
	}
	
	public void addChild(Directory parent, FSElement child){
		int index = getInsertionLocation(parent,child);
		parent.addChild(child, index);
	}
	
	public ArrayList<FSElement> getCurrentChildren(Directory current){
		return this.current.getChildren();
	}
	
	public void showAllElement(){
		for(FSElement child: rootDir.getChildren())
		{
			System.out.println("Directory:"+child.getName());
			if(child instanceof Directory)
				{
				  ((Directory) child).showDirElements((Directory) child);				
				}
			if(child instanceof File){ 
					System.out.println("File Name:"+child.getName() +" Size: "+child.getSize());
				}
			if(child instanceof Link){
				System.out.println("Link Name:"+child.getName() +" Size: "+child.getSize());
			    }
			}
		}
	
			
	}
		
		
	
	


