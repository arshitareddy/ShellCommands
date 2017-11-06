import java.util.Comparator;

public class AlphebeticalComparator implements Comparator<FSElement> {

	@Override
	public int compare(FSElement arg0, FSElement arg1) {
		return arg0.getName().compareTo(arg1.getName());
		
	}

	
}
