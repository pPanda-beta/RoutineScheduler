/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routine.model;

import java.time.LocalTime;

/**
 * @author Palash_Das
 */
public class Allotment {
	
	private final String teacher;
	private final String subject;
	private final String roomNo;
	private final LocalTime startTime;
	private final int length;
	
	public Allotment(String teacher, String subject, String roomNo, LocalTime startTime, int length) {
		this.teacher = teacher;
		this.subject = subject;
		this.roomNo = roomNo;
		this.startTime = startTime;
		this.length = length;
	}
	
	public boolean isOverlappingWith(Allotment other) {
		if (!roomNo.equals(other.roomNo)) {
			return false;
		}
		TimeSlot mySlot = new TimeSlot(startTime, endTime());
		TimeSlot othersSlot = new TimeSlot(other.startTime, other.endTime());
		return mySlot.isOverlappingWith(othersSlot);
//		LocalTime myEndTime = endTime();
//		LocalTime othersEndTime = other.endTime();
//		return (myEndTime.isAfter(other.startTime) && myEndTime.isBefore(othersEndTime))
//				|| (othersEndTime.isAfter(startTime) && othersEndTime.isBefore(myEndTime));
	}
	
	private LocalTime endTime() {
		return startTime.plusMinutes(length * 50);
	}
	
	@Override
	public String toString() {
		return " <" + teacher + " - "
				+ subject + " - "
				+ roomNo +
				" ( " + startTime + " -> " + endTime() + ") > ";
	}
}

