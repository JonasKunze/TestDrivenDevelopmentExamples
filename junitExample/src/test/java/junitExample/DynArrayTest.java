package junitExample;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DynArrayTest {
	private DynArray array;

	@Before
	public void setUp() throws Exception {
		array = new DynArray(new int[] { 1, 2, 3, 4 });
	}

	@Test
	public void testToString() {
		assertEquals(array.toString(), "[1, 2, 3, 4]");
	}
	
	public void testSet() {
		array.set(1, 123);
		assertEquals(array.toString(), "[1, 123, 3, 4]");
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testInvalidSet() {
		array.set(4, 123);
	}

	@Test
	public void testDefaultConstructor() {
		DynArray empty = new DynArray();
		assertEquals(0, empty.size());
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testInvalidAccess() {
		array.get(5);
	}

	@Test
	public void testToStringEmptyArray() {
		array.clear();
		assertEquals("[]", array.toString());
		DynArray empty = new DynArray();
		assertEquals("[]", empty.toString());
	}

	@Test
	public void testAdd() {
		assertEquals("[1, 2, 3, 4]", array.toString());
		assertEquals(4, array.size());
		array.add(5);
		assertEquals("[1, 2, 3, 4, 5]", array.toString());
		assertEquals(5, array.get(4));
		assertEquals(5, array.size());
	}

	@Test
	public void testAt() {
		assertEquals(1, array.get(0));
		assertEquals(4, array.get(3));
	}

	@Test
	public void testRemove() {
		array.add(3);
		assertEquals("[1, 2, 3, 4, 3]", array.toString());
		array.remove(2);
		assertEquals("[1, 2, 4, 3]", array.toString());
		assertEquals(4, array.get(2));
		assertEquals(4, array.size());
	}
	

	interface PerfTestOperation {
		void operation(int step);
	}

	@Test
	public void testPerformance() {
		heatUp();
		System.out.println("Testing performance gain of DynArray vs. ArrayList:");
		List<Integer> list = new ArrayList<Integer>();
		DynArray dynArray = new DynArray();
		
		Histogram addPerf = measureAddPerformanceRatio(dynArray, list);
		System.out.println("Average add performance gain: " + addPerf.getAverage());
		System.out.println(addPerf);
		
		Histogram removePerf = measureRemovePerformanceRatio(dynArray, list);
		System.out.println("Average remove performance gain: " + removePerf.getAverage());
		System.out.println(removePerf);
	}
	
	private Histogram measureAddPerformanceRatio(DynArray dynArray, List<Integer> arrayList) {
		Histogram gains = new Histogram(0, 10, 100);
		for (int i = 0; i < 30; i++) {
			long dynArrayTime = measurePerformance((int index) -> dynArray.add(index), 30000);
			long listTime = measurePerformance((int index) -> arrayList.add(index), 30000);
			float gain = listTime / (float) dynArrayTime;
			gains.insert(gain); 
		}
		return gains;
	}
	
	private Histogram measureRemovePerformanceRatio(DynArray dynArray, List<Integer> arrayList) {
		Histogram gains = new Histogram(0.5f, 1.5f, 100);
		for (int i = 0; i < 30; i++) {
			long dynArrayTime = measurePerformance((int index) -> dynArray.remove(0), 100);
			long listTime = measurePerformance((int index) -> dynArray.remove(0), 100);
			float gain = listTime / (float) dynArrayTime;
			gains.insert(gain); 
		}
		return gains;
	}

	private void heatUp() {
		DynArray dynArray = new DynArray();
		List<Integer> arrayList = new ArrayList<Integer>();

		for (int i = 0; i != 10000; i++) {
			dynArray.add(i);
			arrayList.add(i);
		}
	}

	private long measurePerformance(PerfTestOperation op, int steps) {
		long start = System.nanoTime();
		for (int i = 0; i != steps; i++) {
			op.operation(i);
		}
		return (System.nanoTime() - start) / 1000;
	}
}
