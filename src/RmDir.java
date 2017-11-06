import java.util.ArrayList;

public class RmDir implements Command {

	private Directory parameter;
	private String argument;
	private String command_name;
	
	public RmDir(String dir) {
		this.argument = dir;
		this.command_name = "rmdir "+dir;
	}
	
	public String getCommandName(){
		return this.command_name;
	}

	public void changeToFSElement() {
		FileSystem fs = FileSystem.getFileSystem();
		ArrayList<FSElement> check_children = fs.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (this.argument.equals(child.getName())) {
				if (child instanceof Directory) {
					this.parameter = (Directory) child;
				}
			}
		}

	}

	@Override
	public void execute() {
		this.changeToFSElement();
		if (this.parameter == null) {
			System.out.println("no such directory");

		} else {
			FileSystem fs = FileSystem.getFileSystem();
			fs.getCurrentDIR().removeChild(this.parameter);
			System.out.println("Directory, " + this.parameter + ", is removed from Current Directory");

		}
	}

}
