import java.util.ArrayList;

public class Chown implements Command{
	
	private String owner_name;
	private FSElement file_dir1;
	
	private String argument;
	
	private String command_name;
	private boolean filefound;
	
	
	public Chown(String owner, String file_dir){
		this.owner_name = owner;
		this.argument = file_dir;
		
	}
	

	public void changeToFSElement() {
		FileSystem fs = FileSystem.getFileSystem();
		ArrayList<FSElement> check_children = fs.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (this.argument.equals(child.getName())) {
				this.file_dir1 = child;
				this.filefound = true;
				
			
				}
			}
		}

	
	@Override
	public void execute() {
			this.changeToFSElement();
			
			if(this.filefound){
				this.file_dir1.setOwner(this.owner_name);
				System.out.println("Changed owner name");
			}
			else{
				System.out.println("File/Directory not found");
			}
			
	}

	@Override
	public String getCommandName() {
		return command_name;
	}

}
