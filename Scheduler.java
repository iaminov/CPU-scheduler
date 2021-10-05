import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Scheduler {
	protected ArrayList<Process> readyQueue;
	protected int currentTime;
	protected int timeSpentIdle;
	protected int totalNumProcess;
	protected int totalWaitingTime;
	protected int totalResponseTime;
	protected int totalTurnaroundTime;
	protected double averageCPUusage;
	protected double averageWaitingTime;
	protected double averageResponseTime;
	protected double averageTurnaroundTime;
	
	public Scheduler() {
		readyQueue = new ArrayList<Process>();
		currentTime = 0;
		timeSpentIdle = 0;
	}
	
	public void Add(Process p) {
		readyQueue.add(p);
	}
	
	public void Remove(Process p) {
		readyQueue.remove(readyQueue.indexOf(p));
	}
	
	public void Run() {
	}
	
	public void CompleteProcess(Process currentProcess) {
		System.out.println("<system time "+currentTime+"> process "+ currentProcess.getID() + " finished....");
		currentProcess.setTurnaroundTime(currentTime-currentProcess.getArrival());
		this.totalResponseTime+=currentProcess.getResponseTime();
		this.totalTurnaroundTime+=currentProcess.getTurnaroundTime();
		this.totalWaitingTime+=currentProcess.getWaitingTime();
		readyQueue.remove(currentProcess);
	}

	
	public void PrintStatistics() {			
		this.averageCPUusage=((currentTime-timeSpentIdle)/(double)currentTime)*100;
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		this.averageWaitingTime=(double)this.totalWaitingTime/this.totalNumProcess;
		this.averageResponseTime=(double)this.totalResponseTime/this.totalNumProcess;
		this.averageTurnaroundTime=(double)this.totalTurnaroundTime/this.totalNumProcess;
		
		System.out.println("============================================================\n"+
				"Average CPU usage: "+ df.format(this.averageCPUusage)+"%"+
				"\nAverage waiting time: "+df.format(this.averageWaitingTime)+
				"\nAverage response time: "+df.format(this.averageResponseTime)+
				"\nAverage turnaround time: "+df.format(this.averageTurnaroundTime)+
				"\n============================================================");
	}
	
	protected void updateWaitTimesForAllOtherProcesses(Process current) {
		for(Process p:readyQueue) {
			if(p.getArrival()<currentTime&&p!=current&&!p.isFirstTimeRunning()) {
				p.incrementWaitingTime();
			}
		}
	}
}
