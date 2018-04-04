**************************************************************************************************
					Execution using Makefiles
**************************************************************************************************
1	-Open a session of putty after uploading all java source files and makefiles to Tesla.
2	-Server used for this assignement is on //10.234.136.55:5432/ machine
3	-For SQL Connection, jar file is placed in this path: /home/yashkuru/OOAD/4Assignment/mysql-connector-java-5.1.46.jar
4	-Run the RMI registry either normally or in background on the above server 55. To do this in background use:	
		$rmiregistry 5432&
5	-Run the makefileS.sh to run the server side files using(on server 10.234.136.55)
		$sh makefileS.sh
6	-Open another 5 sessions of putty , then run the makefileC.sh to run the client side files using
	Client addresses are for each session of putty:
	• in-csci-rrpc02.cs.iupui.edu - 10.234.136.56
	• in-csci-rrpc03.cs.iupui.edu - 10.234.136.57
	• in-csci-rrpc04.cs.iupui.edu - 10.234.136.58
	• in-csci-rrpc05.cs.iupui.edu - 10.234.136.59
	• in-csci-rrpc06.cs.iupui.edu - 10.234.136.60
		$sh makefileC.sh
7	-Login details for each client session. Use any:
		->Login details for admin	-> id: admin, password: test
		->Login details for customer	-> id: customer, password: test
8	-Perform add items, browse items and purchase items as per instructions on screen
9	-When both these are run, kill the background running process by pulling this to foreground using:
		$fg
10	-After pulling this to foreground, use Ctrl+C to kill the process.
		
**************************************************************************************************
				Execution without using Makefiles(In case makefiles fail)
**************************************************************************************************

1	-Download all the java source files, policy file and upload these to Tesla server.
2	-Open a new instance of Putty and login to the server.
3	-To implement RMI, port 5432 is used for RMI Registry instead of the default port. 
		Execution rides using this port number for these java files.
4	-For me source files are placed in home directory of my folder. Change path or port values as required.
		//10.234.136.55:5432/OnlineMarketServer
5	-Now compile all these source files using 	
		$javac -classpath "/home/yashkuru/OOAD/4Assignment/mysql-connector-java-5.1.46.jar" *.java
6	-Run the RMI registry either normally or in background. To do this in background use:	
		$rmiregistry 5432&
			Remove & to run in foreground.
7	-After RMI starts running in the background, use this command to run the server side controller file(in this case OnlineMarketController.java).
		$java -Djava.security.policy=policy OnlineMarketController
8	-Now open another instance of Putty to run the client side controller file(in this case MarketViewController.java)
		$java -classpath ".:/home/yashkuru/OOAD/4Assignment/mysql-connector-java-5.1.46.jar" -Djava.security.policy=policy OnlineMarketController
	-Follow instructions on the interface and enter either admin or customer
	-Login details for admin	-> id: admin, password: test
	-Login details for customer	-> id: customer, password: test
9	-When both these are run, kill the background running process by pulling this to foreground using:
		$fg
10	-After pulling this to foreground, use Ctrl+C to kill the process.