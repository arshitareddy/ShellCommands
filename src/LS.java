import java.util.ArrayList;

public class LS implements Command {
	
	private String command_name;
	
	public LS(){
		this.command_name = "ls";
	}
	
	public String getCommandName(){
		return this.command_name;
	}

	@Override
	public void execute() {
		FileSystem fs =FileSystem.getFileSystem();
		System.out.println("Current Directory is : "+fs.getCurrentDIR().getName());
	    ArrayList<FSElement> children = fs.getCurrentChildren(fs.getCurrentDIR());
	    for(FSElement child : children){
	    	System.out.println(child.getName());
	    }
		
	}
	

}
