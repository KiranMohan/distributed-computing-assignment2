package org.kiranmohan.lamport.clock;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.kiranmohan.lamport.clock.message.IMessage;
import org.kiranmohan.lamport.clock.message.IMessageHelper;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class EventProcessorMain {

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		
		String processName = args[0];
		
		EventProcessorMain processor = new EventProcessorMain(processName);
		processor.start(args);
	}

	/**
	 * Start the Event Processor.
	 * 
	 * Initialize ORB and listen for events.
	 * Find all external Event Handlers.
	 * Start the random event generator
	 * @param orbArgs
	 */
	public void start(String[] orbArgs) {
		try {
			orb = ORB.init(orbArgs, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			myEventHandler = new EventHandler(this.processName, myClock);

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(myEventHandler);
			myEventHandlerORBRef = IMessageHelper.narrow(ref);
			eventHandlers.add(myEventHandlerORBRef);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			ncRef = NamingContextExtHelper.narrow(objRef);

			// bind the Object Reference in Naming
			NameComponent path[] = ncRef.to_name(processName);
			ncRef.rebind(path, myEventHandlerORBRef);

		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
			System.exit(1);
		}

		// start event generator, event handler in their own threads
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(this::runEventHandler);
		findEventHandlers();
		executor.execute(this::runEventGenerator);
		executor.shutdown();
	}

	private void runEventHandler() {
		// start the ORB to listen for events
		try {
			System.out.println(processName + " is ready and waiting ...");
			// wait for invocations from clients
			orb.run();
		}

		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		System.out.println(processName + " Exiting ...");
		System.exit(1);
	}

	private void runEventGenerator() {
		Random random = new Random(System.currentTimeMillis());
		while (true) {
			try {
				// sleep upto 3000 msec
				TimeUnit.MILLISECONDS.sleep(1000l + random.nextInt(2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// randomly pick an event Handler
			IMessage eventHandler = nextEventHandler(random);

			synchronized (myClock) {

				// update clock value for the new event
				int currentClockValue = myClock.incrementValue();
				if (myEventHandlerORBRef == eventHandler) {
					System.out.println(processName + " : " + currentClockValue + " : local");
				} else {
					System.out.println(processName + " : " + currentClockValue + " : send");
				}

				// send the event to the selected eventHandler.
				String otherProcessName = eventHandler.message(processName, currentClockValue);

				// System.out.println(processName + " send message to " + otherProcessName);
			} // synchronized 
		}

	}

	private IMessage nextEventHandler(Random random) {
		// pick an event handler randomly
		int index = random.nextInt(eventHandlers.size());
		IMessage eventHandler = eventHandlers.get(index);
		return eventHandler;
	}

	private void findEventHandlers() {

		// sleep some time for all event handlers to be up.
		try {
			TimeUnit.SECONDS.sleep(1l); // 1 sec
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// a little hardcoding :-)
		// TODO: remove the hardcoding
		String[] handlerNames = { "EventProcessor1", "EventProcessor2", "EventProcessor3" };

		for (String name : handlerNames) {
			if (name == this.processName) {
				continue;
			}
			try {
				IMessage eventHandler = IMessageHelper.narrow(ncRef.resolve_str(name));
				eventHandlers.add(eventHandler);
			} catch (NotFound | CannotProceed | InvalidName e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public EventProcessorMain(String processName) {
		super();
		this.processName = processName;
	}

	private final String processName;
	private final LamportClock myClock = new LamportClock();
	private static final String NS_GROUP_ID = "org/kiranmohan/lamportclock";
	private EventHandler myEventHandler;
	private NamingContextExt ncRef;
	private List<IMessage> eventHandlers = new ArrayList<>();
	private ORB orb;
	private IMessage myEventHandlerORBRef;

}
