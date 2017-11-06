
public class SizeCountingVisitor implements FSVisitor {
	
	
	private int totalSize = 0;
	
	public void visit(Link link){
		this.totalSize = this.totalSize + link.getTargetsize();
	}
	public void visit(File file){
		this.totalSize = this.totalSize + file.getSize();
	}
	
	public void visit(Directory dir){
		
	}
	
	public int getTotalsizeNum(){
		return this.totalSize;
	}
	
}
