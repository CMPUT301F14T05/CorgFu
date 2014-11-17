/*
 * Retrieved from Victor Guana's github 
 * (https://github.com/guana/elasticsearch) on November 10th, 2014
 */
package ca.ualberta.cs.corgFu;
/**This class SearchHit is used to determine whether or not a Question has been successfully found in a search.
 * 
 * @author Alex Makepeace
 * 
 * @version 2.0 Nov.15/2014
 */

public class SearchHit<T> {


	private String _index;
	private String _type;
	private String _id;
	private String _version;
	private boolean found;
	private T _source;

	public SearchHit() {

	}

	public String get_index() {
		return _index;
	}

	public void set_index(String _index) {
		this._index = _index;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_version() {
		return _version;
	}

	public void set_version(String _version) {
		this._version = _version;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}
	
	/**Finds out the source question that the search hit has found
	 * 
	 * @return the Question that has been attached to the SearchHit
	 */

	public T getSource() {
		return _source;
	}

	public void setSource(T source) {
		this._source = source;
	}

	@Override
	public String toString() {
		return "SimpleElasticSearchResponse [_index=" + _index + ", _type="
				+ _type + ", _id=" + _id + ", _version=" + _version
				+ ", found=" + found + ", _source=" + _source + "]";

	}

}
