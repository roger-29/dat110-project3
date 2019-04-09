package no.roger.dat110.node.client;

/**
 * exercise/demo purpose in dat110
 * @author tdoy
 *
 */

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.List;

import no.roger.dat110.file.FileManager;
import no.roger.dat110.node.Message;
import no.roger.dat110.rpc.StaticTracker;
import no.roger.dat110.rpc.interfaces.ChordNodeInterface;
import no.roger.dat110.util.Hash;
import no.roger.dat110.util.Util;

public class NodeClientReader extends Thread {

	private boolean succeed = false;
	
	private String filename;
	
	public NodeClientReader(String filename) {
		this.filename = filename;
	}
	
	public void run() {
		sendRequest();
	}
	
	private void sendRequest() {
		
		// Lookup(key) - Use this class as a client that is requesting for a new file and needs the identifier and IP of the node where the file is located
		// assume you have a list of nodes in the tracker class and select one randomly. We can use the Tracker class for this purpose
	
		// connect to an active chord node - can use the process defined in StaticTracker 
	
		// Compute the hash of the node's IP address
		Registry registry = Util.tryIPs();

		// use the hash to retrieve the ChordNodeInterface remote object from the registry
		String hashedIP = Hash.hashOf(Util.activeIP).toString();

		try {
			ChordNodeInterface entryNode = (ChordNodeInterface) registry.lookup(hashedIP);
			FileManager fm = new FileManager(entryNode, StaticTracker.N);
			this.succeed = fm.requestToReadFileFromAnyActiveNode(filename);
		} catch (RemoteException | NotBoundException ex) {
			ex.printStackTrace();
		}

		// do: FileManager fm = new FileManager(ChordNodeInterface, StaticTracker.N);
		
		// do: boolean succeed = fm.requestToReadFileFromAnyActiveNode(filename);
	}
	
	public boolean isSucceed() {
		return succeed;
	}
}
