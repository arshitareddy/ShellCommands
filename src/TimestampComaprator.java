import java.util.Comparator;

public class TimestampComaprator implements Comparator<FSElement> {

	@Override
	public int compare(FSElement arg0, FSElement arg1) {
		return arg0.getCreatedOn().compareTo(arg1.getCreatedOn());
	}

}
