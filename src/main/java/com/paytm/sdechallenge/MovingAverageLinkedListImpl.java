package com.paytm.sdechallenge;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * The <code>MovingAverageArrayListImpl</code> class is slower
 * than <code>MovingAverageArrayListImpl</code> and thus not formally part of this submission.
 * It is provided for illustration purposes.
 * 
 * @author Steve Kotsopoulos
 *
 */
public class MovingAverageLinkedListImpl implements MovingAverage {
	
	private Long sampleSize;
	private List<BigDecimal> elements;
	
	MovingAverageLinkedListImpl(Long sampleSize) {
		this.sampleSize = sampleSize;
		this.elements = new LinkedList<BigDecimal>();
	}

	@Override
	public void add(BigDecimal value) {
		elements.add(value);
	}
	
	@Override
	public void add(Long value) {
		elements.add(BigDecimal.valueOf(value));
	}

	@Override
	public BigDecimal getMovingAverage() {
		Iterator<BigDecimal> iterator = elements.iterator();
		if (elements.size() > sampleSize) {
			long counter = elements.size() - sampleSize;
			while (counter-- > 0) {
				iterator.next();
			}
		}
		long counter = 1;
		BigDecimal sum = BigDecimal.ZERO;
		while (iterator.hasNext() && counter <= sampleSize) {
			BigDecimal value = (BigDecimal) iterator.next();
			sum = sum.add(value);
			counter++;
		}
		BigDecimal result = sum.divide(BigDecimal.valueOf(sampleSize), MathContext.DECIMAL128);
		// System.out.println("returning " + sum + " divided by " + sampleSize + " = " + result);
		return result;
	}

	@Override
	public BigDecimal get(int index) {
		return elements.get(index);
	}
	
	@Override
	public int size() {
		return elements.size();
	}
}
