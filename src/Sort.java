import java.util.ArrayList;

public class Sort implements Command {
	private String sort_name;
	private String command_name;
	public Sort(){
		this.sort_name = "";
		this.command_name = "sort";
		}
	public Sort(String parameter){
		this.sort_name = parameter;
		this.command_name = "sort "+parameter;
	}
	
	public String getCommandName(){
		return this.command_name;
	}
	
	ArrayList<FSElement> sorted_childrens = new ArrayList<FSElement>();

	@Override
	public void execute() {
		FileSystem fs =FileSystem.getFileSystem();
		if(this.sort_name.contentEquals("")){
			this.sorted_childrens = fs.sort(fs.getCurrentDIR(), new AlphebeticalComparator());
			for(FSElement c : this.sorted_childrens){
				System.out.println(c.getName());
			}
		}
		
		else if(this.sort_name.contains("reversealpha")){
			this.sorted_childrens = fs.sort(fs.getCurrentDIR(), new ReverseAlphebeticalComparator());
			for(FSElement c : this.sorted_childrens){
				System.out.println(c.getName());
			}
		}
		
		else if(this.sort_name.contains("timestamp")){
			this.sorted_childrens = fs.sort(fs.getCurrentDIR(), new TimestampComaprator());
			for(FSElement c : this.sorted_childrens){
				System.out.println(c.getName());
			}
		}
		
		else if(this.sort_name.contains("reversetimestamp")){
			this.sorted_childrens = fs.sort(fs.getCurrentDIR(), new ReverseTimestampComparator());
			for(FSElement c : this.sorted_childrens){
				System.out.println(c.getName());
			}
		}
		
		else{
			System.out.println("Wrong entry");
		}
		
	}

}
