*******************************Contents*******************************
Source files	->	OnlineMarket.java
			OnlineMarketView.java
			OnlineMarketModel.java
			OnlineMarketController.java
Project Report
Makefiles

*************************************************
	Execution without using Makefiles
*************************************************

1	-Download all the java source files, policy file and upload these to Tesla server.
2	-Open a new instance of Putty and login to the server.
3	-To implement RMI, port 5432 is used for RMI Registry instead of the default port. 
		Execution rides using this port number for these java files.
4	-For me source files are placed in home directory of my folder. Change path or port values as required.
		//tesla.cs.iupui.edu:5432/onlineMarketServer
5	-Now compile all these source files using 	
		$javac *.java
6	-Run the RMI registry either normally or in background. To do this in background use:	
		$rmiregistry 5432&
			Remove & to run in foreground.
7	-After RMI starts running in the background, use this command to run the server file(in this case OnlineMarketModel.java).
		$java -Djava.security.policy=policy OnlineMarketModel
8	-Now open another instance of Putty to run the client file(in this case OnlineMarketView.java)
		$java -Djava.security.policy=policy OnlineMarketView
9	-When both these are run, kill the background running process by pulling this to foreground using:
		$fg
10	-After pulling this to foreground, use Ctrl+C to kill the process.
11	-I have implemented registerCustomer() method, but as of now anyone can register to OnlineMarket Place

*************************************************
	Execution using Makefiles
*************************************************
1	-Open a session of putty after uploading all java source files and makefiles to Tesla.
2	-Run the RMI registry either normally or in background. To do this in background use:	
		$rmiregistry 5432&
3	-Run the makefileS.sh to run the server side files using
		$sh makefileS.sh
4	-Open another session of putty, then run the makefileC.sh to run the client side files using
		$sh makefileC.sh