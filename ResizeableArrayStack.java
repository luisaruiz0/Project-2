import java.util.*;

public class ResizeableArrayStack<T> implements StackInterface<T> {
	private T[] stack;
	private int topIndex;
	private final static int DEFAULT_CAPACITY = 50;
	private final static int MAX_CAPACITY = 10000;
	private boolean integrityOK = false;

	//Constructors
	public ResizeableArrayStack() {
		this(DEFAULT_CAPACITY);
	}
	public ResizeableArrayStack(int initialCapacity) {
		integrityOK = false;
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
	     T[] tempStack = (T[])new Object[initialCapacity];
	       stack = tempStack;
	       topIndex = -1;
	       integrityOK = true;
	}
	
	
	public void checkIntegrity() {
		if (integrityOK == false) {
			throw new SecurityException("Stack is corrupt");
		}
	}
	
	public void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a bag whose " +
			"capacity exeeds allowed maximum of " + MAX_CAPACITY);
		}
	}
	
	//doubleCapacity
	public void ensureCapacity() {
		if (topIndex >= stack.length-1) {
			int newLength = 2*stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		}
	}
	
	public void push(T newEntry) {
		checkIntegrity();
		ensureCapacity();
		stack[topIndex+1] = newEntry;
		topIndex++;
	}
	
	public T pop() {
		checkIntegrity();
		if (!isEmpty()) {
			T topEntry = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return topEntry;
		} else {
			throw new EmptyStackException();
		}
	}

	public T peek() {
		checkIntegrity();
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return stack[topIndex];
		}
	}

	public boolean isEmpty() {
		if (topIndex<0) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		checkIntegrity();
		while (topIndex > -1) {
			stack[topIndex] = null;
			topIndex--;
		}
	}
	
}
