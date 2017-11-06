import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FileSystemTest {

	CommandHistory command_history = CommandHistory.getInstance();
	FileSystem rootdir = FileSystem.getFileSystem();
	Directory root = rootdir.getRootDIR();
	Directory system = new Directory("system", "Arshita", 0, rootdir.getRootDIR());
	Directory home = new Directory("home", "Arshita", 0, rootdir.getRootDIR());
	Directory pictures = new Directory("pictures", "Arshita", 0, home);

	File a = new File("a.txt", "Arshita", 1, system);
	File b = new File("b", "Arshita", 2, system);
	File c = new File("c", "Arshita", 3, system);
	File d = new File("d", "Arshita", 4, home);
	File e = new File("e", "Arshita", 5, pictures);
	File f = new File("f.txt", "Arshita", 6, pictures);
	File test = new File("test", "Arshita", 0, home);

	Link x = new Link("x", "Arshita", 0, home, system);
	Link y = new Link("y", "Arshita", 0, pictures, e);
	
	public void appendingfiles(){
		
		home.appendChild(x);
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		home.appendChild(d);
		home.appendChild(test);
		home.appendChild(pictures);
		pictures.appendChild(e);
		pictures.appendChild(f);
		pictures.appendChild(y);
	}

	@Test
	public void testFileStructure() {
		rootdir.showAllElement();
	}
	
	@Test
	public void testVisitorSize() {
		root.appendChild(system);
		root.appendChild(home);
		appendingfiles();
		SizeCountingVisitor visitorS = new SizeCountingVisitor();
		root.accept(visitorS);
		int expected = 32;
		int actual = visitorS.getTotalsizeNum();
		assertThat(actual, is(expected));

	}

	@Test
	public void testSizeOfRootDir() {
		
		int expected1 = 21;
		int actual1 = root.getSize();
		assertThat(actual1, is(expected1));
		
	}

	@Test
	public void testSizeOfX_link() {
		root.appendChild(system);
		root.appendChild(home);
		appendingfiles();
		int expected1 = 0;
		int actual1 = x.getSize();
		assertThat(actual1, is(expected1));
	}

	@Test
	public void testSizeOfY_link() {
		root.appendChild(system);
		root.appendChild(home);
		appendingfiles();
		int expected1 = 0;
		int actual1 = y.getSize();
		assertThat(actual1, is(expected1));
	}

	@Test
	public void testTargetSizeOfX_link() {
		system.appendChild(a);
		system.appendChild(b);
		system.appendChild(c);
		int expected1 = 6;
		int actual1 = x.getTargetsize();
		assertThat(actual1, is(expected1));
	}

	@Test
	public void testTargetSizeOfY_link() {
		int expected1 = 5;
		int actual1 = y.getTargetsize();
		assertThat(actual1, is(expected1));
	}

	

	@Test
	public void testVisitorCounter() {
		CountingVisitor visitor = new CountingVisitor();
		root.accept(visitor);
		int expected1 = 4;
		int expected2 = 8;
		int expected3 = 2;
		int actual1 = visitor.getDirNum();
		int actual2 = visitor.getFileNum();
		int actual3 = visitor.getLinkNum();
		assertThat(actual1, is(expected1));
		assertThat(actual2, is(expected2));
		assertThat(actual3, is(expected3));

	}

	@Test
	public void testVisitorSearch() {
		FileSearchVisitor visitorF = new FileSearchVisitor(".txt");
		root.accept(visitorF);
		ArrayList<File> files = visitorF.getFoundFiles();
		ArrayList<String> expected = new ArrayList<String>();
		ArrayList<String> actual = new ArrayList<String>();
		expected.add("a.txt");
		expected.add("f.txt");
		for (File file : files) {
			actual.add(file.getName());
		}

		assertThat(actual, is(expected));
	}
	
	@Test
	public void testPWDCommand(){
		this.rootdir.setCurrentDIR(root);
		Command cmd1 = new PWD();
		cmd1.execute();
		this.command_history.push(cmd1);
	}
	
	@Test
	public void testHistoryCommand(){
		Command history = new History();
		System.out.println("Here is history");
		history.execute();
		
	}
	
	@Test
	public void testDirCommand(){
		this.rootdir.setCurrentDIR(home);
		appendingfiles();
		String arg;
		
		Command dir = new Dir();
		dir.execute();
		this.command_history.push(dir);
		System.out.println("Displaying output for dir");
		System.out.println("");
		
		arg = "..";
		Command dir1 = new Dir(arg);
		dir1.execute();
		this.command_history.push(dir1);
		System.out.println("Displaying output for 'dir ..'");
		System.out.println("");
		
		arg = "d";
		Command dir2 = new Dir(arg);
		dir2.execute();
		this.command_history.push(dir2);
		System.out.println("Displaying output for 'dir file or directory'");
		System.out.println("");
		
		arg = "pictures/y";
		String[] path_parts = arg.split("/");
		Command dir3 = new Dir(path_parts[0], path_parts[1]);
		dir3.execute();
		this.command_history.push(dir3);
		System.out.println("Displaying output for 'dir directory/file'");
		System.out.println("");
		
	}
	
	@Test
	public void testCdCommand(){
		this.rootdir.setCurrentDIR(home);
		appendingfiles();
		String arg = "pictures";
		Command cd = new CD(arg);
		cd.execute();
		command_history.push(cd);
		Directory expected2 = pictures;
		Directory actual2 = rootdir.getCurrentDIR();
		
		arg = "..";
		Command cd2 = new CD(arg);
		cd2.execute();
		command_history.push(cd2);
		Directory expected3 = home;
		Directory actual3 = rootdir.getCurrentDIR();
		
		
		Command cd1 = new CD();
		cd1.execute();
		command_history.push(cd1);
		Directory expected1 = root;
		Directory actual1 = rootdir.getCurrentDIR();
		
		arg = "home/pictures";
		String[] path_parts = arg.split("/");
		Command cd3 = new CD(path_parts[0], path_parts[1]);
		cd3.execute();
		command_history.push(cd3);
		String expected4 = "pictures";
		String actual4 = rootdir.getCurrentDIR().getName();
		
		assertThat(actual1, is(expected1));
		assertThat(actual2, is(expected2));
		assertThat(actual3, is(expected3));
		assertThat(actual4, is(expected4));
	}
	
	@Test
	public void testMkDirCommand(){
		this.rootdir.setCurrentDIR(home);
		appendingfiles();
		String arg = "New_Dir";
		String actual = null;
		Command mkdir = new MkDir(arg);
		mkdir.execute();
		this.command_history.push(mkdir);
		ArrayList<FSElement> check_children = rootdir.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (arg.equals(child.getName())) {
				actual = child.getName();
			}
		}
		
		assertThat(actual, is(arg));
	}
	
	@Test
	public void testChownCommand(){
		this.rootdir.setCurrentDIR(home);
		appendingfiles();
		String file_name = "d";
		String owner_name = "Test_owner";
		String actual = null;
		Command chown = new Chown(owner_name, file_name);
		chown.execute();
		command_history.push(chown);
		ArrayList<FSElement> check_children = rootdir.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (file_name.equals(child.getName())) {
				actual = child.getOwner();
			}
		}
		assertThat(actual, is(owner_name));
	}
	
	
	
	@Test
	public void testlsCommand(){
		this.rootdir.setCurrentDIR(home);
		appendingfiles();
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("x");
		expected.add("d");
		expected.add("test");
		expected.add("pictures");
		ArrayList<String> actual = new ArrayList<String>();
		LS ls = new LS();
		ls.execute();
		command_history.push(ls);
		System.out.println("Here is list of files");
		ArrayList<FSElement> check_children = rootdir.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
		 actual.add(child.getName());
	}
		assertThat(actual, is(expected));
	}
	
	@Test
	public void testCPCommand(){
		this.rootdir.setCurrentDIR(home);
		appendingfiles();
		String arg1 = "test";
		String arg2 = "pictures";
		String expected = arg1;
		String actual = null;
		Command cp = new Cp(arg1, arg2);
		cp.execute();
		command_history.push(cp);
		ArrayList<FSElement> check_children = rootdir.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (arg1.equals(child.getName())) {
				actual = child.getName();
			}
		}
				
		assertThat(actual, is(expected));
	
	}
	
	@Test
	public void testMvCommand(){
		this.rootdir.setCurrentDIR(home);
		appendingfiles();
		String arg1 = "test";
		String arg2 = "pictures";
		String expected = arg1;
		String actual = null;
		Command mv = new Mv(arg1, arg2);
		mv.execute();
		command_history.push(mv);
		ArrayList<FSElement> check_children = rootdir.getCurrentDIR().getChildren();
		for (FSElement child : check_children) {
			if (arg1.equals(child.getName())) {
				actual = child.getName();
			}
		}
				
		assertThat(actual, is(expected));
	
	}
	
}
