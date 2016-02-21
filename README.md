## Distributed-Computing-Assignment2

**How to run**

- Requirements: Linux, Java 8, JacORB 3.7  
- Set JACORB_HOME to JacORB path.
- run the script lamport-clock.sh.   
   - The script will launch three event handler processors,  
   - After about 1 mins, it will shut all processes when the demo is over. 

**Assignment Details**

BITS Pilani  
Work Integrated Learning Programme Division  
Distributed Computing (SS ZG526)  
Assignment 1 Q2  
Deadline of submission: 21.2.2016  
Date assigned: 8.2.2016  
  

**Problem description**
  
Write a distributed application which implements Lamport‟s Logical Clocks. You have to
write rules for updating the logical clock at each process when an event occurs. An event could
be one of two: (1) Local event, i.e. event local to the process (2) Send/Receive events involving
2 processes across the distributed system.  

Each process maintains a logical clock in the form of an integer variable, which gets incremented by 1 each time an event occurs. You have to initialize this integer variable to 0 (zero). When a process sends a message to another, the integer variable at the sending process get incremented by 1 and this logical time is sent along with the message. At the receiving process, a comparison of the current logical clock value and the received logical clock value is carried out, and maximum of the two is considered. Then the receiving process increments this value by 1 and set the new value as its current logical clock value.

Simulate the below scenario of a distributed system comprising 3 processes (or threads). At each event (local or send/receive), the logical clock value of the process involving in the events should be printed on the console along with the process name. The format for the same is:

	<process name> : <logical clock value> : <event type>

Where <event type> is one of the event types (local, send or receive).

Send/Receive events can be implemented by inter-thread communication (eg: via sockets). You are free to make any assumptions if you feel it is needed.

