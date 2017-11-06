import java.util.Comparator;

public class ReverseTimestampComparator implements Comparator<FSElement>{

	@Override
	public int compare(FSElement arg0, FSElement arg1) {
		return arg1.getCreatedOn().compareTo(arg0.getCreatedOn());
	}

}
