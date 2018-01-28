                                Java RMI Example

1. Unzip the folder and place the contents into your directory on Pegasus or Tesla. Open an instance of putty.

2. If you are using Tesla please make sure to change both the BankServer and BankClient to reflect this host.

3. If you wish to run the RMI Registry on a different port than the defaul of 1099 you need to specify this in 
   the code. You can do this by adding the port number to the string:
   (e.g. String name = "//pegasus.cs.iupui.edu:2016/BankServer";)

4. Compile all of the Java files - you can accomplish this through the following command: javac *.java

5. Run the RMI Registry in the background - this will run at the default port:
   % rmiregistry &
   
   If you wish to run on a different port than the default (1099) you will need to specify it:
   (e.g. %rmiregistry 2016&)
   
6. Run the BankServer by executing the following command:
   % java -Djava.security.policy=policy BankServer
   
7. Open a second instance of putty.
   
8. Run the BankClient by executing the following command:
   % java -Djava.security.policy=policy BankClient
   
9. Once you have completed this work please remember to clean up by terminating the RMI Registry. You 
   can bring this process to the foreground through the following command:
   % fg
   At which point you can kill the process.