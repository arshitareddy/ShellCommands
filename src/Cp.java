import java.util.ArrayList;

public class Cp implements Command {

	private FSElement src;
	private Directory dest;
	private String src_name;
	private String dest_name;
	private String command_name;
	private Directory tempdir;
	
	public Cp(String src, String dest) {
		this.src_name = src;
		this.dest_name = dest;

	}

	public void changeToFSElement() {
		FileSystem fs = FileSystem.getFileSystem();
		ArrayList<FSElement> check_children = fs.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (this.src_name.equals(child.getName())) {
				this.src = child;
			}
		}
	}

	public void searchDir(Directory dest_dir) {

		//FileSystem fs = FileSystem.getFileSystem();
		ArrayList<FSElement> check_children = dest_dir.getChildren();
		for (FSElement child : check_children) {
			if (child instanceof Directory) {
				if (this.dest_name.equals(child.getName())) {
					this.dest = (Directory) child;
					break;
				} else {
					searchDir((Directory) child);
				}
			}
		}
	}

	@Override
	public void execute() {
		FileSystem fs = FileSystem.getFileSystem();
		tempdir = fs.getCurrentDIR();
		fs.setCurrentDIR(fs.getRootDIR());
		searchDir(fs.getCurrentDIR());
		fs.setCurrentDIR(tempdir);
		changeToFSElement();
		if(this.dest != null && this.src != null){
		this.command_name = "cp" + this.src_name + " " + this.dest_name;
		fs.addChild(this.dest, this.src);
		System.out.println("Copied file");
		}else{
			System.out.println("Not valid src or dest");
		}

	}

	@Override
	public String getCommandName() {
		
		return command_name;
	}

}
