package junitExample;

public class DynArray implements IDynArray {
	private int[] data;
	private int firstFreeElement = 0;

	public DynArray() {
		this(1);
	}

	public DynArray(int initialCapacity) {
		data = new int[initialCapacity];
	}

	public DynArray(int[] initData) {
		data = initData.clone();
		firstFreeElement = initData.length;
	}
	
	@Override
	public void set(int index, int value) throws ArrayIndexOutOfBoundsException {
		if (index >= firstFreeElement) {
			throw new ArrayIndexOutOfBoundsException();
		}
		data[index] = value;
	}

	@Override
	public int get(int index) throws ArrayIndexOutOfBoundsException {
		if (index >= firstFreeElement) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return data[index];
	}

	@Override
	public void add(int value) {
		checkCapacity(firstFreeElement + 1);
		data[firstFreeElement++] = value;
	}

	@Override
	public boolean remove(int index) throws ArrayIndexOutOfBoundsException {
		if (index >= firstFreeElement) {
			return false;
		}

		final int delta = firstFreeElement-- - index - 1;
		if (delta > 0)
			System.arraycopy(data, index + 1, data, index, delta);

		return true;
	}

	@Override
	public void resize(int newSize) {
		checkCapacity(newSize);
		firstFreeElement = newSize;
	}

	private void checkCapacity(int requiredSize) {
		if (data.length < requiredSize) {
			expand(requiredSize);
		}
	}
	
	private void expand(final int requiredSize) {
		int[] tmp = new int[(int) (requiredSize * 2 + 1)];
		System.arraycopy(data, 0, tmp, 0, data.length);
		data = tmp;
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder("[");

		for (int i = 0; i != firstFreeElement; i++) {
			strBuilder.append(get(i));
			if (i != firstFreeElement - 1) {
				strBuilder.append(", ");
			}
		}

		strBuilder.append(']');
		return strBuilder.toString();
	}

	@Override
	public int size() {
		return firstFreeElement;
	}

	@Override
	public void clear() {
		firstFreeElement = 0;
	}
}
