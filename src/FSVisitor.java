
public interface FSVisitor {
	
	public void visit(Link link);
	public void visit(File file);
	public void visit(Directory dir);
	

}
