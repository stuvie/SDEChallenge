package com.paytm.sdechallenge;

import java.math.BigDecimal;

/**
 * 
 * The <code>MovingAverage</code> interface is for tracking the moving average of the last N elements added.
 * It supports adding elements to the dataset and provides read-access to the elements.
 * 
 * @author Steve Kotsopoulos
 *
 */
public interface MovingAverage {
	/**
     * Appends the specified element to the end of the list
     * @param value element to be appended to the list
     */
	public void add(BigDecimal value);
	
	/**
     * Appends the specified element to the end of the list
     * @param value element to be appended to the list
     */
	public void add(Long value);
	
	/**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	public BigDecimal get(int index);
	
	/**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
	public int size();
	
	/**
     * Calculate and return the moving average
     * @return moving average
     */
	public BigDecimal getMovingAverage();
}
