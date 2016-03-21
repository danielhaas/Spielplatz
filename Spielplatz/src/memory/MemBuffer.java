package memory;

import java.util.concurrent.locks.ReentrantLock;

public class MemBuffer<T> {

	private final int[] ref_counter;
	private final T[] objects;
	private int index;
	private final ReentrantLock lock = new ReentrantLock();
	
	public MemBuffer(ArrayProvider<T> anArrayProvider)
	{
		this(anArrayProvider.createArray());
	}
	
	public MemBuffer(T[] anArray)
	{
		final int size = anArray.length;
		ref_counter = new int[size];
		objects = anArray;
	}

	interface ArrayProvider<T>
	{
		T[] createArray();
	}

	// TODO check speed reentrantlock vs. synchronized
	T getObject()
	{
		try
		{
			lock.lock();
			final int myIndex = getFreeIndex();
			if (myIndex==-1) return null;
			ref_counter[myIndex] = 1;
			return (T) objects[myIndex];
		}
		finally
		{
			lock.unlock();
		}
	}
	
	T getObjectBlocking(int timeout)
	{
		final T myReturn = getObject();
		if (myReturn==null)
		{
			
			// TODO needs to be implemented
		}
		return myReturn;
	}
	
	void releaseObject(T anObject)
	{
		int runner = index;
		for (int i = 0; i < objects.length; i++) {
			runner--;
			if (runner<0) runner = objects.length-1;
			if (objects[runner]==anObject) 
			{
				ref_counter[runner]--;
				return;
			}
		}
	}
	
	private int getFreeIndex()
	{
		int runner = index;
		for (int i = 0; i < objects.length; i++) {
			if (runner==objects.length) runner = 0;			
			if (ref_counter[runner]==0) return runner;
			runner++;
		}
		return -1;
	}
}
