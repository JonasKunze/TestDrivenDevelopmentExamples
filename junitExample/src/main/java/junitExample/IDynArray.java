package junitExample;

public interface IDynArray {
	public int get(int index) throws ArrayIndexOutOfBoundsException;
	
	public void set(int index, int value) throws ArrayIndexOutOfBoundsException;

	public void add(int value);

	public boolean remove(int index) throws ArrayIndexOutOfBoundsException;

	public void resize(int newSize);

	public String toString();

	public int size();

	public void clear();
}
