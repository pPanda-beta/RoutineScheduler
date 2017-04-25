package routine.holder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import routine.model.DayTimeSlot;
import routine.model.Room;
import routine.model.Subject;

/**
 * Created by Palash_Das on 24-04-2017.
 */

public class RoomHolder {
	
	private final List<Room> rooms = new ArrayList<>();
	
	public RoomHolder(Collection<String> roomNos) {
		roomNos.stream()
				.map(Room::new)
				.forEach(rooms::add);
	}
	
	public Room getFreeRoomDuring(Subject subject, DayTimeSlot slot) {
		return rooms.stream()
				.filter(room -> room.isFreeDuring(slot))
				.filter(room -> room.canSupport(subject))
				.findFirst()
				.orElse(null);
	}
}
