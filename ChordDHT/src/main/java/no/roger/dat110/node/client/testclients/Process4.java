package no.roger.dat110.node.client.testclients;

import no.roger.dat110.rpc.ChordNodeContainer;

public class Process4 {

	public static void main(String[] args) throws Exception {
		new ChordNodeContainer("process4", 60000, true);
	}
}
