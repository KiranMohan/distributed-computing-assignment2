#!/bin/bash

NAMESERVER_PORT=38693
NAMESERVER_CORBALOC=corbaloc::localhost:$NAMESERVER_PORT/NameService
MAIN_JAR=lamports-logical-clock/target/lamport-clock.jar

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
$JACORB_HOME/bin/ns -DOAPort=$NAMESERVER_PORT > /dev/null &
NS_PID=$!

#start jacorb nameserver manager
#$JACORB_HOME/bin/nmg -ORBInitRef "NameService=$NAMESERVER_CORBALOC" > /dev/null &
#NMG_PID=$!

#start event processors
$JACORB_HOME/bin/jaco -DORBInitRef.NameService=$NAMESERVER_CORBALOC -jar $MAIN_JAR EventProcessor1  &
EVT_PRO1_PID=$!
$JACORB_HOME/bin/jaco -DORBInitRef.NameService=$NAMESERVER_CORBALOC -jar $MAIN_JAR EventProcessor2  &
EVT_PRO2_PID=$!
$JACORB_HOME/bin/jaco -DORBInitRef.NameService=$NAMESERVER_CORBALOC -jar $MAIN_JAR EventProcessor3  &
EVT_PRO3_PID=$!


# let processes run for sometime 
sleep 60

# OK. Stop the demo.
#pkill -P $NMG_PID 
#kill $NMG_PID 

pkill -P $EVT_PRO1_PID 
kill $EVT_PRO1_PID 

pkill -P $EVT_PRO2_PID 
kill $EVT_PRO2_PID 

pkill -P $EVT_PRO3_PID 
kill $EVT_PRO3_PID 

pkill -P $NS_PID
kill $NS_PID

sleep 1
echo "======================"
echo "Stopping the demo.Bye."
echo "======================"
