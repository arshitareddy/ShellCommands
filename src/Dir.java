import java.util.ArrayList;

public class Dir implements Command {

	private FSElement parameter;
	private boolean noParameters;
	private boolean foundFSE;
	private boolean nofoundFSE;
	private boolean founddots;
	private String command_name;
	private boolean pathfound;

	public Dir() {
		this.noParameters = true;
		this.command_name = "dir";
	}

	public Dir(String arg) {
		if (arg.equals("..")) {
			this.founddots = true;
			this.command_name = "dir " + arg;
		} else {
			FileSystem fs = FileSystem.getFileSystem();
			ArrayList<FSElement> check_children = fs.getCurrentDIR().getChildren();
			for (FSElement child : check_children) {
				if (arg.equals(child.getName())) {
					this.parameter = child;
					this.foundFSE = true;
					this.command_name = "dir " + parameter;
				}
			}
			if (this.parameter == null) {
				this.nofoundFSE = true;
			}
		}
	}

	public Dir(String arg1, String arg2) {
		FileSystem fs = FileSystem.getFileSystem();
		Directory tempdir = fs.getCurrentDIR();
		ArrayList<FSElement> check_children = fs.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (arg1.equals(child.getName())) {
				if (child instanceof Directory) {
					fs.setCurrentDIR((Directory) child);
					this.pathfound = true;
				}
			}
		}
		if (this.pathfound) {
			//FileSystem fs1 = FileSystem.getFileSystem();
			ArrayList<FSElement> check_children1 = fs.getCurrentDIR().getChildren();
			for (FSElement child : check_children1) {
				if (arg2.equals(child.getName())) {
					this.parameter = child;
					this.foundFSE = true;
					this.command_name = "dir " +arg1+" "+arg2;
				}
			}
		} else {
			System.out.println("File or Directory not found");
		}
		fs.setCurrentDIR(tempdir);
	}

	public String getCommandName() {
		return this.command_name;
	}

	@Override
	public void execute() {
		if (this.noParameters) {
			FileSystem fs = FileSystem.getFileSystem();
			ArrayList<FSElement> children = fs.getCurrentChildren(fs.getCurrentDIR());
			for (FSElement c : children) {
				if (c instanceof Directory) {
					System.out.println("Name: " + c.getName() + " : Type is Dir, Size: 0" + c.getSize() + " Owner: "
							+ c.getOwner());
				} else if (c instanceof Link) {
					System.out.println("Name: " + c.getName() + " : Type is Link, Size: 0" + c.getSize() + " Owner: "
							+ c.getOwner());
				} else if (c instanceof File) {
					System.out.println("Name: " + c.getName() + " : Type is File, Size: 0" + c.getSize() + " Owner: "
							+ c.getOwner());
				}
			}
		}
		if (this.founddots) {
			FileSystem fs = FileSystem.getFileSystem();
			Directory tempDir = fs.getCurrentDIR();
			fs.setCurrentDIR(fs.getCurrentDIR().getParent());
			System.out.println(fs.getCurrentDIR().getName());
			ArrayList<FSElement> children = fs.getCurrentChildren(fs.getCurrentDIR());
			for (FSElement c : children) {
				if (c instanceof Directory) {
					System.out.println("Name: " + c.getName() + " : Type is Dir, Size: 0" + c.getSize() + " Owner: "
							+ c.getOwner());
				} else if (c instanceof Link) {
					System.out.println("Name: " + c.getName() + " : Type is Link, Size: 0" + c.getSize() + " Owner: "
							+ c.getOwner());
				} else if (c instanceof File) {
					System.out.println("Name: " + c.getName() + " : Type is File, Size: 0" + c.getSize() + " Owner: "
							+ c.getOwner());
				}

			}
			fs.setCurrentDIR(tempDir);
		}
		if (this.foundFSE) {
			//1FileSystem fs = FileSystem.getFileSystem();
			if (this.parameter instanceof Directory) {
				System.out.println("Name: " + this.parameter.getName() + " : Type is Dir, Size: 0"
						+ this.parameter.getSize() + " Owner: " + this.parameter.getOwner());
			} else if (this.parameter instanceof Link) {
				System.out.println("Name: " + this.parameter.getName() + " : Type is Link, Size: 0"
						+ this.parameter.getSize() + " Owner: " + this.parameter.getOwner());
			} else if (this.parameter instanceof File) {
				System.out.println("Name: " + this.parameter.getName() + " : Type is File, Size: 0"
						+ this.parameter.getSize() + " Owner: " + this.parameter.getOwner());
			} else {
				System.out.println("Invalid Type");
			}
		}
		if (this.nofoundFSE) {
			System.out.println("File or Directory not found");
		}
	}

}
