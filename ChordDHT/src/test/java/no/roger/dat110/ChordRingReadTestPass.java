package no.roger.dat110;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import no.roger.dat110.node.client.NodeClientReader;

/*
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
*/

import no.roger.dat110.rpc.ChordNodeContainer;
import no.roger.dat110.rpc.StaticTracker;
import no.roger.dat110.rpc.interfaces.ChordNodeInterface;
import no.roger.dat110.util.Hash;

class ChordRingReadTestPass {
	
	ChordNodeInterface p1;
	ChordNodeInterface p2;
	ChordNodeInterface p3;
	ChordNodeInterface p4;
	ChordNodeInterface p5;
	ChordNodeInterface p6;
	ChordNodeInterface p7;
	ChordNodeInterface p8;
	ChordNodeInterface p9;
	ChordNodeInterface p10;

	@Before
	void setUp() throws Exception {
		
		String node1 = "process1";
		String node2 = "process2";
		String node3 = "process3";
		String node4 = "process4";
		String node5 = "process5";
		String node6 = "process6";
		String node7 = "process7";
		String node8 = "process8";
		String node9 = "process9";
		String node10 = "process10";
		
		// To test the correctness - we will manually manipulate the processes
		// Get the registry  - running on local machine's IP
		Registry registry = LocateRegistry.getRegistry(StaticTracker.PORT);

		// Get the hash value of the node's name
		BigInteger node1id = Hash.hashOf(node1);					
		BigInteger node2id = Hash.hashOf(node2);					
		BigInteger node3id = Hash.hashOf(node3);
		BigInteger node4id = Hash.hashOf(node4);
		BigInteger node5id = Hash.hashOf(node5);
		BigInteger node6id = Hash.hashOf(node6);
		BigInteger node7id = Hash.hashOf(node7);
		BigInteger node8id = Hash.hashOf(node8);
		BigInteger node9id = Hash.hashOf(node9);
		BigInteger node10id = Hash.hashOf(node10);
		
		// Look up the registry for each remote object
		p1 = (ChordNodeInterface) registry.lookup(node1id.toString());	
		p2 = (ChordNodeInterface) registry.lookup(node2id.toString());
		p3 = (ChordNodeInterface) registry.lookup(node3id.toString());
		p4 = (ChordNodeInterface) registry.lookup(node4id.toString());
		p5 = (ChordNodeInterface) registry.lookup(node5id.toString());
		p6 = (ChordNodeInterface) registry.lookup(node6id.toString());
		p7 = (ChordNodeInterface) registry.lookup(node7id.toString());
		p8 = (ChordNodeInterface) registry.lookup(node8id.toString());
		p9 = (ChordNodeInterface) registry.lookup(node9id.toString());
		p10 = (ChordNodeInterface) registry.lookup(node10id.toString());
	}

	@Test
	public void test() throws RemoteException, InterruptedException {
		// test quorum-based consistency protocol - must have enough votes

		NodeClientReader r = new NodeClientReader("process1");
		r.start();
		r.join();
		assertTrue(r.isSucceed());
		// test must pass as this should return true
	}
}
