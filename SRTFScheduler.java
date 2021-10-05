
public class SRTFScheduler extends Scheduler {
	public void Run() {
		Process currentProcess=readyQueue.get(0);
		this.totalNumProcess=readyQueue.size();
		System.out.println("Scheduling algorithm: Shortest Remaining Time First\n============================================================");
		while(!readyQueue.isEmpty()) {
			while(currentProcess.getArrival()>currentTime) {
				System.out.println("<system time "+currentTime+"> idle");
				timeSpentIdle++;
				currentTime++;
			}
			if(currentProcess.isFirstTimeRunning()) {
				currentProcess.setWaitingTime(currentTime-currentProcess.getArrival());
				currentProcess.setResponseTime(currentTime-currentProcess.getArrival());
				currentProcess.setFirstTimeRunning(false);
			}
			while(currentProcess.getBurstLength()!=0){
				System.out.println("<system time "+currentTime+"> process "+ currentProcess.getID() + " is running");
				currentTime++;
				currentProcess.decrementBurstLength();
				this.updateWaitTimesForAllOtherProcesses(currentProcess);
				for(int i=0;i<this.readyQueue.size();i++) {
					if(this.readyQueue.get(i).getArrival()<=currentTime&&this.readyQueue.get(i).getBurstLength()<currentProcess.getBurstLength()) {
						currentProcess=this.readyQueue.get(i);
						break;
					}
				}
			}
			if(currentProcess.getBurstLength()==0) {
				CompleteProcess(currentProcess);
				if(!readyQueue.isEmpty()) {
					currentProcess=readyQueue.get(0);
				}
			}					
		}
		System.out.println("<system time "+currentTime+"> All processes finished......");
		this.PrintStatistics();
	}	
}
