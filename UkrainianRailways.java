package epam.com.transport;

import java.util.List;

public class UkrainianRailways {
	
	private List <Train> trains;
	private List <Ticket> tickets;
	
	public void addTrain(Train train) {
		trains.add(train);
	}
	
	public void removeTrain(Train train) {
		trains.remove(train);
	}
	
}
