
public class Process {
	private int ID;
	private int arrival;
	private int burstLength;
	private int turnaroundTime;
	private int waitingTime;
	private int responseTime;
	private boolean firstTimeRunning;
	
	
	public boolean isFirstTimeRunning() {
		return firstTimeRunning;
	}

	public void setFirstTimeRunning(boolean firstTimeRunning) {
		this.firstTimeRunning = firstTimeRunning;
	}


	public int getTurnaroundTime() {
		return turnaroundTime;
	}

	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}

	public Process(int ID, int arrival, int burstLength ) {
		this.ID=ID;
		this.arrival=arrival;
		this.burstLength=burstLength;
		this.firstTimeRunning=true;
	}

	public int getID() {
		return ID;
	}

	public int getArrival() {
		return arrival;
	}

	public int getBurstLength() {
		return burstLength;
	}
	
	public void decrementBurstLength() {
		burstLength--;
	}
	
	public void incrementWaitingTime() {
		waitingTime++;
	}
}
