import java.io.FileInputStream;
import java.util.Scanner;

public class Assignment2 {

	public static void main(String[] args) {
		Scheduler sched=null;
		switch (args[0]) {
			case "FCFS":
				sched = new FCFSScheduler();
				break;
			case "SRTF":
				sched = new SRTFScheduler();
				break;
			case "RR":
				sched = new RRScheduler(Integer.parseInt(args[1]));
				break;
		}
		
		try {
			FileInputStream fstream = new FileInputStream("assignment2.txt");
		    Scanner scanner = new Scanner(fstream);

			while(scanner.hasNextInt()) {
				sched.Add(new Process(scanner.nextInt(),scanner.nextInt(),scanner.nextInt()));
			}
        	fstream.close();			
        	scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		sched.Run();
		

	}

}
