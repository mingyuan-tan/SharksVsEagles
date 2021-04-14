package model;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

	private static CommandHistory instance;

	// Singleton Pattern to ensure only one instance of CommandHistory object is created 
	public static CommandHistory getInstance() {
		if (instance == null) {
			instance = new CommandHistory();
		}
		return instance;
	}

	private List<Snapshot> history = new ArrayList<Snapshot>();

	public void push(Snapshot state) {
		history.add(state);
		var lastIndex = history.size() - 1;
		var lastState = history.get(lastIndex);
	}

	public Snapshot pop() {
		if (history.size() > 0) {
			var lastIndex = history.size() - 1;
			var lastState = history.get(lastIndex);
			history.remove(lastState);

			return lastState;
		}

		return null;
	}
}
