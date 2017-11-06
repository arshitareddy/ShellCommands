import java.util.Comparator;

public class ReverseAlphebeticalComparator implements Comparator<FSElement> {

	@Override
	public int compare(FSElement arg0, FSElement arg1) {
		return arg1.getName().compareTo(arg0.getName());
		
	}

}
