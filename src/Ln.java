import java.util.ArrayList;

public class Ln implements Command {
	
	private FSElement real_target;
	private String link_name;
	private String command_name;
	
	
	public Ln(String real_target, String link_name){
	
		this.link_name = link_name;
		FileSystem fs = FileSystem.getFileSystem();
		ArrayList<FSElement> check_children = fs.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (real_target.equals(child.getName())) {
				this.real_target = child;
				this.command_name = "ln" +real_target+" "+link_name;
		}}
		if(this.real_target == null){
			System.out.println("Invalid target name");
		}
	}
		
	
	
	@Override
	public void execute() {
		FileSystem fs =FileSystem.getFileSystem();
		Link alias = new Link(link_name, "Arshita", 0, fs.getCurrentDIR(), real_target );
		fs.addChild(fs.getCurrentDIR(), alias);
		System.out.println("Link Created");
	}

	@Override
	public String getCommandName() {
		return command_name;
	}

}
