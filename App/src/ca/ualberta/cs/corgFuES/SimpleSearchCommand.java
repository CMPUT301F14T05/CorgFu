/*
 * Retrieved from Victor Guana's github 
 * (https://github.com/guana/elasticsearch) on November 10th, 2014
 */
package ca.ualberta.cs.corgFuES;
/**
 * A class used for creating an Elasticsearch search
 * command in json format
 * @author Victor Guana
 *
 */
public class SimpleSearchCommand {

	private String query;
	private String[] fields;
	/**
	 * sets the query to the specified query and fields
	 * to null if it isn't provided.
	 * @param query The search query to be searched for
	 */
	public SimpleSearchCommand(String query) {
		this(query, null);
	}
	/**
	 * Sets the query and fields specified.
	 * @param query The search query to be searched for
	 * @param fields The field to search for the query in.
	 */
	public SimpleSearchCommand(String query, String[] fields) {
		this.query = query;
		this.fields = fields;
	}
	/**
	 * Creates the search request in a json format to be 
	 * sent to elastic search
	 * @return The json command for searching the query in the 
	 * specified fields.
	 */
	public String getJsonCommand() {
		StringBuffer command = new StringBuffer(
				"{\"query\" : {\"query_string\" : {\"query\" : \"" + query
						+ "\"");

		if (fields != null) {
			command.append(", \"fields\":  [");

			for (int i = 0; i < fields.length; i++) {
				command.append("\"" + fields[i] + "\", ");
			}
			command.delete(command.length() - 2, command.length());

			command.append("]");
		}

		command.append("}}}");

		return command.toString();
	}
}

