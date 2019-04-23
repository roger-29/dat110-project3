package no.roger.dat110.node.client.testclients;

import no.roger.dat110.rpc.ChordNodeContainer;

public class Process6 {

	public static void main(String[] args) throws Exception {
		new ChordNodeContainer("process6", 60000, true);
	}
}
