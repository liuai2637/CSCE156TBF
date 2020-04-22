package unl.cse.advanced_queues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PCDemo {

	public static void main(String args[]) {
		BlockingQueue<String> q = new LinkedBlockingQueue<String>();
		Producer p1 = new Producer(q, "producer");
		Consumer c1 = new Consumer(q, "consumer 01");
		Consumer c2 = new Consumer(q, "consumer 02");
		Consumer c3 = new Consumer(q, "consumer 03");
		new Thread(p1).start();
		new Thread(c1).start();
		new Thread(c2).start();
		new Thread(c3).start();
	}
}
