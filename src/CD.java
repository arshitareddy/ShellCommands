import java.util.ArrayList;

public class CD implements Command {

	private String parameter;
	private boolean found = false;
	private String command_name;
	private boolean pathfound;

	public CD() {
		this.command_name = "cd";
	}

	public CD(String parameter) {
		this.parameter = parameter;
		this.command_name = "cd " + parameter;
	}

	public CD(String arg1, String arg2) {
		FileSystem fs = FileSystem.getFileSystem();
		ArrayList<FSElement> check_children = fs.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (arg1.equals(child.getName())) {
				if (child instanceof Directory) {
					fs.setCurrentDIR((Directory) child);
					this.parameter = arg2;
					this.command_name = "cd " + arg1+"/ "+arg2;
					this.pathfound = true;
				}
			}
		}
		if(!this.pathfound){
			System.out.println("Invalid Arguments");
		}
	}

	public String getCommandName() {
		return this.command_name;
	}

	@Override
	public void execute() {

		if (this.parameter == null) {
			FileSystem fs = FileSystem.getFileSystem();
			fs.setCurrentDIR(fs.getRootDIR());
			System.out.println("Now the Current Directory is : " + fs.getCurrentDIR().getName());
		} else if (this.parameter.equals("..")) {
			FileSystem fs = FileSystem.getFileSystem();
			fs.setCurrentDIR(fs.getCurrentDIR().getParent());
			System.out.println("Now the Current Directory is : " + fs.getCurrentDIR().getName());
		} else {
			FileSystem fs = FileSystem.getFileSystem();
			ArrayList<FSElement> check_children = fs.getCurrentDIR().getChildren();
			for (FSElement child : check_children) {
				if (this.parameter.equals(child.getName())) {
					if (child instanceof Directory) {
						fs.setCurrentDIR((Directory) child);
						System.out.println("Now the Current Directory is : " + fs.getCurrentDIR().getName());
						this.found = true;
					} else {
						System.out.println("Invalid Directory Name");
					}
				}

			}
			if (this.found == false) {
				System.out.println("Invalid Directory Name");
			}

		}

	}
}
