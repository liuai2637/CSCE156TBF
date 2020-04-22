package unl.cse.advanced_queues;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Consumer implements Runnable {
	
	private static final LinkedList<String> MASTER_LIST = new LinkedList<String>();

	private final String name;
	private final BlockingQueue<String> queue;
	
	Consumer(BlockingQueue<String> q, String name) {
		this.name = name;
		this.queue = q;
	}
	public void run() {
		try {
			while(true) {
				consume(queue.take());
			}
		} catch (InterruptedException  e) {
			System.out.println("Death of consumerism, yay!");
		}
	}
	
	private void consume(String s) {

		JsonParser jp = new JsonParser();
		JsonObject root = jp.parse(s).getAsJsonObject();
		JsonElement je = root.get("text");
		JsonElement jeUser = root.get("user");
		String user = null;
		String text = null;
		String lang = null;
		if(je != null) {
			text = je.getAsString();
			user = jeUser.getAsJsonObject().get("name").getAsString();
			lang = jeUser.getAsJsonObject().get("lang").getAsString();
		}
		if(text != null) {
			System.out.println("["+this.name + "] Twitter " + user + " sez: " + text);
		}
	}
	
	private void consumeSearch(String s) {
		
		JsonParser jp = new JsonParser();
		JsonObject root = jp.parse(s).getAsJsonObject();
		JsonArray results = root.getAsJsonArray("results");
		for(JsonElement je : results) {
			String user = je.getAsJsonObject().get("from_user").getAsString();
			String text = je.getAsJsonObject().get("text").getAsString();
			String msg = user + " says: "+text;
			if(!MASTER_LIST.contains(msg)) {
				MASTER_LIST.addLast(msg);
				if(MASTER_LIST.size() > 1000) {
					MASTER_LIST.removeFirst();
				}
				System.out.println(msg);
			}
		}
	}
}
