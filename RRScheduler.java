
public class RRScheduler extends Scheduler {
	private int timeQuantum;
	
	public RRScheduler(int timeQuantum) {
		super();
		this.timeQuantum=timeQuantum;
	}
	
	public void Run() {
		Process currentProcess=readyQueue.get(0);
		this.totalNumProcess=readyQueue.size();
		System.out.println("Scheduling algorithm: Round Robin\n============================================================");
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
			
			for(int i=0;(i<timeQuantum)&&(currentProcess.getBurstLength()!=0);i++){
				System.out.println("<system time "+currentTime+"> process "+ currentProcess.getID() + " is running");
				currentTime++;
				currentProcess.decrementBurstLength();
				this.updateWaitTimesForAllOtherProcesses(currentProcess);
			}
			if(currentProcess.getBurstLength()==0) {
				CompleteProcess(currentProcess);
				if(!readyQueue.isEmpty())
					currentProcess=readyQueue.get(0);
			}
			else {				
				Rotate(currentProcess);
				for(int i=0;i<this.readyQueue.size();i++) {
					if(readyQueue.get(i).getArrival()<=currentTime) {
						currentProcess=readyQueue.get(i);
						break;
					}
				}
			}			
		}
		System.out.println("<system time "+currentTime+"> All processes finished......");
		this.PrintStatistics();
	}	
	
	private void Rotate(Process currentProcess) {
		Process temp = currentProcess;
		this.Remove(currentProcess);
		this.Add(temp);
	}
	
}
