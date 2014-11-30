/*
 * Retrieved from Victor Guana's github 
 * (https://github.com/guana/elasticsearch) on November 10th, 2014
 */
package ca.ualberta.cs.corgFuES;

import java.util.List;
/**
 * A utility class used for parsing elasticsearch hits
 * @author Victor Guana
 *
 * @param <T> the objects that are being held in elastic search
 */
public class Hits<T> {
	private int total;
	private float max_score;
	private List<SearchHit<T>> hits;

	public Hits() {}
	/**
	 * Returns the number of search hits recieved
	 * @return The number of hits
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * Sets the number of search hits
	 * @param total The number of search Hits
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * Gets the max score for the search hit
	 * @return the max score of the search hit
	 */
	public float getMax_score() {
		return max_score;
	}
	/**
	 * Sets the max score of the search hit
	 * @param max_score the max score to be set for the search hit
	 */
	public void setMax_score(float max_score) {
		this.max_score = max_score;
	}
	/**
	 * Gets the search hits list
	 * @return list of search hits
	 */
	public List<SearchHit<T>> getHits() {
		return hits;
	}
	/**
	 * sets the search hits list
	 * @param hits the list to be set as the search hits list
	 */
	public void setHits(List<SearchHit<T>> hits) {
		this.hits = hits;
	}

	@Override
	/**
	 * returns the string representation of the search hit
	 */
	public String toString() {
		return "Hits [total=" + total + ", max_score=" + max_score + ", hits="
				+ hits + "]";
	}
}

