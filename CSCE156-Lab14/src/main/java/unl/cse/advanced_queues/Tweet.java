package unl.cse.advanced_queues;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Tweet {

	private String user;
	private String text;
	private String language;
	
	public Tweet(String json) {
		JsonParser jp = new JsonParser();
		JsonObject root = jp.parse(json).getAsJsonObject();
		JsonElement je = root.get("text");
		JsonElement jeUser = root.get("user");
		user = null;
		text = null;
		language = null;
		if(je != null) {
			this.text = je.getAsString();
			this.user = jeUser.getAsJsonObject().get("name").getAsString();
			this.language = jeUser.getAsJsonObject().get("lang").getAsString();
		}
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String getText() {
		return this.text;
	}
	
	public String getLanguage() {
		return this.language;
	}

	public String toString() {
		return "Twit " + user + " sez in the strange language of "+language+": " + text;
	}
}
