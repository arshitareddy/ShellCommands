
public class History implements Command {
	
	private String command_name;
	
	public History(){
	this.command_name = "history";
	}
	
	public String getCommandName(){
		return this.command_name;
	}

	CommandHistory command_history = CommandHistory.getInstance();
	@Override
	public void execute(){
	for(Command cmd : command_history.getHistory())
	System.out.println(cmd.getCommandName());
	}
}
