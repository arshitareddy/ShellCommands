
public class File extends FSElement{

	public File(String name, String owner, int size, Directory parent) {
		super(name, owner, size, parent);
		
	}
	
	@Override
	public void accept(FSVisitor v){
		v.visit(this);
	}
	

}
