
public class PWD implements Command{
	private String command_name;
	
	public PWD(){
		this.command_name = "pwd";
		}

	public String getCommandName(){
		return this.command_name;
	}
	
	@Override
	public void execute() {
		FileSystem fs =FileSystem.getFileSystem();
		System.out.println("The Current Working Directory is: "+fs.getCurrentDIR().getName());
		
	}

}
