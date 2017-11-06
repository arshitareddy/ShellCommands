import java.util.ArrayList;

public class Link extends FSElement{
	
	private FSElement linkedTo;
	private int size = 0;	
	private int temp;
	public Link(String name, String owner, int size, Directory parent, FSElement linkedTo) {
		super(name, owner, size, parent);
		this.linkedTo = linkedTo;
	}
	
	public ArrayList<FSElement> getChildren(){
		return ((Directory) this.linkedTo).getChildren();
	}
	
	public void appendChild(FSElement child){
		((Directory) linkedTo).appendChild(child);
	}
	
   @Override
   public int getSize(){
	   return this.size;
   }
   
   public int getTargetsize(){
	   temp = linkedTo.getSize();
	   return temp;
   }
   
   @Override
	public void accept(FSVisitor v){
		v.visit(this);
	}

   

}
