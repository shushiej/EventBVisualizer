/**'
 * This class gets the events from the txt file, and checks 
 * the guards by using a parser. 
 * @author shushruth
 *
 */

public class Event {
	private String name;
	private Guard guard;
	private Assignment assignment;
	
	public Event(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Guard getGuard() {
		return guard;
	}

	public void setGuard(Guard guard) {
		this.guard = guard;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
}
