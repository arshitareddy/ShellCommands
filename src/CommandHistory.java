
import java.util.ArrayList;

public class CommandHistory {
	private static CommandHistory instance = null;
	private ArrayList<Command> historyOfCommands = new ArrayList<>();
	
	private CommandHistory(){
		
	}
	
	public static CommandHistory getInstance(){
		
		if(instance==null) instance = new CommandHistory(); 
		return instance; 		
	}
	
	public void push(Command command){
		this.historyOfCommands.add(command);
	}
	
	public Command pop(){
		Command previousCommand = this.historyOfCommands.get(this.historyOfCommands.size()-1);
		return previousCommand;
	}

	public ArrayList<Command> getHistory() {
		return historyOfCommands;
	}

	
}