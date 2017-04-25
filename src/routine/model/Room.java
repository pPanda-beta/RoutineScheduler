package routine.model;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public class Room extends Reservable {
	
	private final String number;
	private final boolean supportsLab;
	
	public Room(String number) {
		this.number = number;
		this.supportsLab = number.toUpperCase().contains("LAB");
	}
	
	public boolean canSupport(Subject subject) { //TODO : Hard to understand, '! |' can also be used
		return subject.isLabSubject() == supportsLab;
	}
	
	@Override
	public String toString() {
		return number;
	}
}
