#!/bin/bash

BASEDIR=`dirname $0`
BASEDIR=`readlink -e $BASEDIR`

if [ -z $JACORB_HOME ]; then
	JACORB_HOME=$BASEDIR/jacorb-3.7
fi

if [ ! -d $JACORB_HOME ]; then
	echo "ERROR: JACORB_HOME is not set or doesn't exist";
	exit 0;
fi

export PATH=$JACORB_HOME/bin:$PATH

#start jacorb nameserver
$JACORB_HOME/bin/ns -DOAPort=38693 > /dev/null &
NS_PID=$!

#start jacorb nameserver manager
$JACORB_HOME/bin/nmg -ORBInitRef "NameService=corbaloc::localhost:38693/NameService" > /dev/null &
NMG_PID=$!

#start event processors
$JACORB_HOME/bin/jaco -DORBInitRef.NameService=corbaloc::localhost:38693/NameService -jar lamports-logical-clock/target/lamport-clock.jar EventProcessor1 > /dev/null &
EVT_PRO1_PID=$!


# let processes run for sometime 
sleep 10

# OK. Stop the demo.
pkill -P $NMG_PID 
kill $NMG_PID 
pkill -P $EVT_PRO1_PID 
kill $EVT_PRO1_PID 
pkill -P $NS_PID
kill $NS_PID

sleep 1
echo "Stopping the demo.Bye."
