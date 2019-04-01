package no.roger.dat110;

import no.roger.dat110.rpc.ChordNodeContainer;

public class Process1 {

	public static void main(String[] args) throws Exception {
		new ChordNodeContainer("process1", 50000, true);
	}
}
