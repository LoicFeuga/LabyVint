package jeu;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The Class Parser.
 */
public final class Parser {

	/**
	 * Json encoding.
	 *
	 * @param data the data
	 * @return the string
	 */
	public static String jsonEncoding(HashMap<String, Object> data) {
		JSONObject json = new JSONObject(data);
		StringWriter out = new StringWriter();
		try {
			json.writeJSONString(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	/**
	 * Json decoding.
	 *
	 * @param data the data
	 * @return the hash map
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> jsonDecoding(String data) {

		JSONParser parser = new JSONParser();
		try {
			return (HashMap<String, Object>) parser.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

}
