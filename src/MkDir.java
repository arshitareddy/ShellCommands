

public class MkDir implements Command{
	
	private String parameter;
	private String command_name;
	
	public MkDir(String parameter){
		this.parameter = parameter;
		this.command_name = "mkdir "+parameter;
	}
	
	public String getCommandName(){
		return this.command_name;
	}

	@Override
	public void execute() {
		FileSystem fs =FileSystem.getFileSystem();
		Directory newdir = new Directory(parameter, fs.getCurrentDIR().getOwner(), 0, fs.getCurrentDIR());
		fs.addChild(fs.getCurrentDIR(), newdir);
		System.out.println("New Directory, "+this.parameter+", is created");
	}
	
}
