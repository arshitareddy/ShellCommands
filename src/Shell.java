import java.util.Scanner;

public class Shell {

	public static void main(String[] args) {

		System.out.println("Enter the Command as suggested");
		System.out.println("******************************************************************");
		System.out.println("");
		System.out.println("Enter 'pwd' to know current working dir");
		System.out.println("Enter 'cd dest_dir_name' to chage directory");
		System.out.println("Enter 'cd' to go to root dir");
		System.out.println("Enter 'ls' to list all the elements in current dir");
		System.out.println("Enter 'dir' to print all the information of current dir & its all elements ");
		System.out.println("Enter 'dir dir/file name' to print all the information");
		System.out.println("Enter 'mkdir dir_name' to create a new dir");
		System.out.println("Enter 'rmdir dir_name' to remove dir");
		System.out.println("Enter 'ln target_name alias_name' to create a link to target");
		System.out.println("Enter 'mv source_dir/file_name destination_dir_name' to move file");
		System.out.println("Enter 'history' to know the set of commands executed");
		System.out.println("Enter 'redo' to to repeat executing last command");
		System.out.println("Enter 'cp source_dir/file_name destination_dir_name'");
		System.out.println(
				"Enter 'sort to sort in alphabetical order; 'sort reversealpha' to sort in reverse-alphabetical; ");
		System.out.println(
				"Enter 'sort timestamp' to sort in timestamping order; 'sort reversetimestamp' to sort in reverse-timestamping; ");
		System.out.println("Enter 'chown new_owner_name file/dir' to change owner of specified file/dir ");
		System.out.println("");
		System.out.println("******************************************************************");
		System.out.println("");

		CommandHistory command_history = CommandHistory.getInstance();
		FileSystem rootdir = FileSystem.getFileSystem();
		Directory root = rootdir.getRootDIR();
		Directory system = new Directory("system", "Arshita", 0, rootdir.getRootDIR());
		Directory home = new Directory("home", "Arshita", 0, rootdir.getRootDIR());
		Directory pictures = new Directory("pictures", "Arshita", 0, home);

		rootdir.setCurrentDIR(root);
		System.out.println("You are currently in root Directory");
		System.out.println("");

		File a = new File("a", "Arshita", 1, system);
		File b = new File("b", "Arshita", 2, system);
		File c = new File("c", "Arshita", 3, system);
		File d = new File("d", "Arshita", 4, home);
		File e = new File("e", "Arshita", 5, pictures);
		File f = new File("f", "Arshita", 6, pictures);

		Link x = new Link("x", "Arshita", 0, home, system);
		Link y = new Link("y", "Arshita", 0, pictures, e);

		root.appendChild(system);
		root.appendChild(home);
		home.appendChild(x);
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		home.appendChild(d);
		home.appendChild(pictures);
		pictures.appendChild(e);
		pictures.appendChild(f);
		pictures.appendChild(y);

		while (true) {
			System.out.print(">");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			String[] arg = input.split(" ");
			switch (arg[0]) {
			case "pwd":
				Command cmd1 = new PWD();
				cmd1.execute();
				command_history.push(cmd1);
				break;
			case "cd":
				if (arg.length == 2) {
					if (arg[1].contains("/")) {
						String[] path_parts = arg[1].split("/");
						Command cd = new CD(path_parts[0], path_parts[1]);
						cd.execute();
						command_history.push(cd);

					} else {
						Command cd = new CD(arg[1]);
						cd.execute();
						command_history.push(cd);
					}
				} else {
					Command cd = new CD();
					cd.execute();
					command_history.push(cd);
				}
				break;
			case "dir":
				if (arg.length == 2) {

					if (arg[1].contains("/")) {
						String[] path_parts = arg[1].split("/");
						Command dir = new Dir(path_parts[0], path_parts[1]);
						dir.execute();
						command_history.push(dir);

					} else {
						Command dir = new Dir(arg[1]);
						dir.execute();
						command_history.push(dir);
					}
				} else {
					Command dir = new Dir();
					dir.execute();
					command_history.push(dir);
				}
				break;
			case "ls":
				LS ls = new LS();
				ls.execute();
				command_history.push(ls);
				break;
			case "mkdir":
				if (arg.length == 2) {
					MkDir mkdir = new MkDir(arg[1]);
					mkdir.execute();
					command_history.push(mkdir);
				} else {
					System.out.println("Invalid Argument");
				}
				break;
			case "rmdir":
				if (arg.length == 2) {
					RmDir rmdir = new RmDir(arg[1]);
					rmdir.execute();
					command_history.push(rmdir);
				} else {
					System.out.println("Invalid Argument");
				}
				break;
			case "chown":
				if (arg.length == 3) {
					Command chown = new Chown(arg[1], arg[2]);
					chown.execute();
					command_history.push(chown);
				} else {
					System.out.println("Invalid Argument");
				}
				break;
			case "sort":
				if (arg.length == 2) {
					Command sort = new Sort(arg[1]);
					sort.execute();
					command_history.push(sort);
				} else {
					Command sort = new Sort();
					sort.execute();
					command_history.push(sort);
				}
				break;
			case "history":
				Command history = new History();
				history.execute();
				command_history.push(history);
				break;
			case "ln":
				if (arg.length == 3) {
					Command ln = new Ln(arg[1], arg[2]);
						ln.execute();
						command_history.push(ln);
					}
				 else {
					System.out.println("Invalid number of argument");
				}
				break;
			case "cp":
				if (arg.length == 3) {
					Command cp = new Cp(arg[1], arg[2]);
						cp.execute();
						command_history.push(cp);
					}
				 else {
					System.out.println("Invalid number of argument");
				}
				break;
			case "mv":
				if (arg.length == 3) {
					Command mv = new Mv(arg[1], arg[2]);
						mv.execute();
						command_history.push(mv);
					}
				 else {
					System.out.println("Invalid number of argument");
				}
				break;

			case "exit":
				return;
			default:
				System.out.println("Please enter a valid command");

			}

		}

	}

}
