
public class Redo implements Command {
	
	private String command_name;
	
	public Redo(){
		this.command_name = "redo";
	}
	
	
	
	@Override
	public void execute() {
		CommandHistory command_history = CommandHistory.getInstance();
		Command last_cmd = command_history.pop();
		last_cmd.execute();
		
	}

	@Override
	public String getCommandName() {
		
		return command_name;
	}

}
