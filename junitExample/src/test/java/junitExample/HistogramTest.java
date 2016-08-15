package junitExample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HistogramTest {
	private Histogram histo;
	@Before
	public void setUp() throws Exception {
		histo = new Histogram(1, 10, 10);
	}

	@Test
	public void testBinWidth() {
		assertEquals(1, histo.getBinWidth(), 1/1000.);
		histo = new Histogram(0.1f, 0.2f, 2);
		assertEquals(0.1, histo.getBinWidth(), 1/1000.);
	}
	
	@Test
	public void testGetIntegral() {
		assertEquals(0, histo.getIntegral());
		histo.insert(1);
		assertEquals(1, histo.getIntegral());
		histo.insert(2);
		assertEquals(2, histo.getIntegral());
	}
	
	@Test
	public void testInsert() {
		assertEquals(1, histo.getBinWidth(), 1/1000.);
		histo.insert(0.4999f);
		assertEquals(0, histo.getIntegral());
		histo.insert(0.5f);
		assertEquals(1, histo.getIntegral());
		histo.insert(10.49999f);
		assertEquals(2, histo.getIntegral());
		histo.insert(10.5f);
		assertEquals(2, histo.getIntegral());
	}
	
	@Test
	public void testGetLargestBinHeight() {
		assertEquals(0, histo.getIntegral());
		histo.insert(1);
		assertEquals(1, histo.getLargestBinHeight());
		histo.insert(1);
		assertEquals(2, histo.getLargestBinHeight());
		histo.insert(2);
		assertEquals(2, histo.getLargestBinHeight());
	}
	
	@Test
	public void testAverage() {
		assertTrue(Float.isNaN(histo.getAverage()));
		histo.insert(0.5f);
		assertEquals(1/1f, histo.getAverage(), 0.01);
		histo.insert(1);
		assertEquals((1+1)/2f, histo.getAverage(), 0.01);
		histo.insert(2);
		assertEquals((1+1+2)/3f, histo.getAverage(), 0.01);
	}	
	@Test
	public void testToString() {
		histo.insert(1);
		histo.insert(2);
		histo.insert(2);
		histo.insert(3);
		histo.insert(4);

		System.out.println(histo.toString());
	}
}
