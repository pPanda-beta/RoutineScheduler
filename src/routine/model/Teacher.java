package routine.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Palash_Das on 23-04-2017.
 */

public class Teacher extends Reservable {
	
	private final String name;
	private final List<Subject> subjects;
	
	public Teacher(String name, Collection<Subject> subjects) {
		super();
		this.name = name;
		this.subjects = new ArrayList<>(subjects);
	}
	
	public boolean canTeach(Subject subject) {
		return subjects.contains(subject);
	}
	
	public int teachesMoreSubjectsThan(Teacher other){
		return this.subjects.size() - other.subjects.size();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Teacher)) return false;
		
		Teacher teacher = (Teacher) o;
		
		return name != null ? name.equals(teacher.name) : teacher.name == null;
		
	}
	
	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
