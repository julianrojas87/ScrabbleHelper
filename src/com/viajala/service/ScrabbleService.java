package com.viajala.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.viajala.scrabble.ScrabbleHelper;

@Path("/scrabble")
public class ScrabbleService {

	/**
	 * REST WS invocation method
	 * 
	 * @param list: List of letters from which words wanted to be found.
	 * @return JSON object.
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String findWords(@QueryParam("list") String list) {
		ScrabbleHelper helper = new ScrabbleHelper();
		JSONObject obj = new JSONObject();

		if (helper.validateInput(list)) {
			helper.findWords(list);

			for (int i = 2; i <= helper.getOriginals().length(); i++) {
				JSONArray array = new JSONArray();
				obj.put("Words with " + i + " letters", array);
			}

			for (String s : helper.getFoundWords()) {
				JSONArray array = (JSONArray) obj.get("Words with " + s.length() + " letters");
				obj.remove("Words with " + s.length() + " letters");
				array.add(s);
				obj.put("Words with " + s.length() + " letters", array);
			}

			obj.put("Dictionary source", "https://github.com/acbellini/juzzle/tree/master/TrieDictionary/resources");

			return obj.toJSONString();
		} else {
			obj.put("Error", "Please enter valid letters [a-z][A-Z]");
			return obj.toJSONString();
		}
	}
}
