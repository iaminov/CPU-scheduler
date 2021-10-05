public class FCFSScheduler extends Scheduler{
	public void Run() {
		Process currentProcess=readyQueue.get(0);
		this.totalNumProcess=readyQueue.size();
		System.out.println("Scheduling algorithm: First Come First Serve\n============================================================");
		while(!readyQueue.isEmpty()) {
			while(currentProcess.getArrival()>currentTime) {
				System.out.println("<system time "+currentTime+"> idle");
				timeSpentIdle++;
				currentTime++;
			}
			
			//currentProcess.setStartTime(currentTime);
			currentProcess.setWaitingTime(currentTime-currentProcess.getArrival());
			currentProcess.setResponseTime(currentTime-currentProcess.getArrival());
			while(currentProcess.getBurstLength()!=0) {
				System.out.println("<system time "+currentTime+"> process "+ currentProcess.getID() + " is running");
				currentTime++;
				currentProcess.decrementBurstLength();
			}
			CompleteProcess(currentProcess);
			if(!readyQueue.isEmpty())
				currentProcess=readyQueue.get(0);
		}
		System.out.println("<system time "+currentTime+"> All processes finished......");
		this.PrintStatistics();
	}	
}
