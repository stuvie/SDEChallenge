package com.paytm.sdechallenge;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The <code>MovingAverageArrayListImpl</code> class is for tracking the moving average of the last N elements added.
 * Elements are stored as BigDecimal to support high-precision arithmetic.
 * It supports adding elements to the dataset and provides read-access to the elements.
 * 
 * @author Steve Kotsopoulos
 *
 */
public class MovingAverageArrayListImpl implements MovingAverage {
	
	private Long sampleSize;
	private List<BigDecimal> elements;
	private BigDecimal movingAverage;
	
	/**
     * Constructor
     * 
     * @param sampleSize number of elements to use in calculating the moving average
     */
	MovingAverageArrayListImpl(Long sampleSize) {
		this.sampleSize = sampleSize;
		this.elements = new ArrayList<BigDecimal>();
		this.movingAverage = BigDecimal.ZERO;
	}

	/**
     * Appends the specified element to the end of the list
     * 
     * @param value element to be appended to the list
     */
	@Override
	public void add(BigDecimal value) {
		elements.add(value);
		this.movingAverage = BigDecimal.ZERO;
	}
	
	/**
     * Appends the specified element to the end of the list
     * 
     * @param value element to be appended to the list
     */
	@Override
	public void add(Long value) {
		elements.add(BigDecimal.valueOf(value));
		this.movingAverage = BigDecimal.ZERO;
	}

	/**
     * Calculate and return the moving average
     * 
     * @return moving average
     */
	@Override
	public BigDecimal getMovingAverage() {
		if (!this.movingAverage.equals(BigDecimal.ZERO)) {
			// return previously cached value
			return this.movingAverage;
		}
		
		int index = 0;
		if (elements.size() > sampleSize) {
			index = (int) (elements.size() - sampleSize);
		}
		
		long counter = 1;
		BigDecimal sum = BigDecimal.ZERO;
		while (index < elements.size() && counter <= sampleSize) {
			BigDecimal value = (BigDecimal) elements.get(index);
			sum = sum.add(value);
			counter++;
			index++;
		}
		BigDecimal result = sum.divide(BigDecimal.valueOf(sampleSize), MathContext.DECIMAL128);
		this.movingAverage = result;
		return result;
	}

	/**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public BigDecimal get(int index) {
		return elements.get(index);
	}
	
	/**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
	@Override
	public int size() {
		return elements.size();
	}
}
