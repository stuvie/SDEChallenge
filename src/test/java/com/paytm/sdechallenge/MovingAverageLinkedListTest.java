package com.paytm.sdechallenge;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class MovingAverageLinkedListTest {
	private static final long SCALABILITY_TEST_SAMPLE_SIZE =  6000000;
	private static final long SCALABILITY_TEST_ELEMENT_SIZE = 9000000;
	
	@Test
	public void testEmptyMovingAverage() {
		MovingAverage ma = new MovingAverageLinkedListImpl(2L);
		Assert.assertEquals(0, ma.getMovingAverage().longValue());
	}

	@Test
	public void testMovingAverage() {
		MovingAverage ma = new MovingAverageLinkedListImpl(2L);
		ma.add(2L);
		ma.add(4L);
		Assert.assertEquals(3, ma.getMovingAverage().longValue());
		ma.add(8L);
		Assert.assertEquals(6, ma.getMovingAverage().longValue());
	}
	
	@Test
	public void testMovingAverageDoubles() {
		MovingAverage ma = new MovingAverageLinkedListImpl(2L);
		ma.add(BigDecimal.valueOf(3.14));
		ma.add(BigDecimal.valueOf(9.14));
		ma.add(BigDecimal.valueOf(656.3));
		Assert.assertEquals(332.72, ma.getMovingAverage().doubleValue(), 0.001);
	}
	
	@Test
	public void testMovingAverageScalability() {
		MovingAverage ma = new MovingAverageLinkedListImpl(SCALABILITY_TEST_SAMPLE_SIZE);
		double value = 3.14;
		long startTime = System.currentTimeMillis();
		for (int i=1; i < SCALABILITY_TEST_ELEMENT_SIZE; i++) {
			ma.add(BigDecimal.valueOf(i * value));
		}
		Assert.assertEquals(18839998.43, ma.getMovingAverage().doubleValue(), 0.001);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("scalability test took " + duration + " ms");
		Assert.assertEquals(18839998.43, ma.getMovingAverage().doubleValue(), 0.001);
		long repeatTime = System.currentTimeMillis();
		long repeatDuration = repeatTime - endTime;
		System.out.println("repeat test took " + repeatDuration + " ms without caching");
	}
}
