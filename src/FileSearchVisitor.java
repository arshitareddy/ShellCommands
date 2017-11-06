import java.util.ArrayList;

public class FileSearchVisitor implements FSVisitor{
	
	private String extention;
	private ArrayList<File> foundFiles = new ArrayList<File>();
	
	public FileSearchVisitor(String ext){
		this.extention = ext;
	}
	
	public void visit(Link link){
		return;
	}
	
	public void visit(Directory dir){
		return;
	}
	
	public void visit(File file){
		if(file.getName().contains(this.extention)){
			this.foundFiles.add(file);
		}
	}
	
	public ArrayList<File> getFoundFiles(){
		return foundFiles;
	}
		

}
