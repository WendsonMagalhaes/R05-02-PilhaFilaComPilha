package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}
		try {
			this.stack1.push(element);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		T element = null;
		this.unStack();
		try {
			element = this.stack2.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		this.stack();
		return element;
	}

	@Override
	public T head() {
		T element = null;
		if (!this.isEmpty()) {
			this.unStack();
			element = this.stack2.top();
		}
		this.stack();
		return element;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

	private void unStack() {
		if (!this.isEmpty()) {
			if (this.stack2.isEmpty()) {
				while (!this.stack1.isEmpty()) {
					try {
						this.stack2.push(this.stack1.pop());
					} catch (StackUnderflowException | StackOverflowException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void stack() {
		while (!this.stack2.isEmpty()) {
			try {
				this.stack1.push(this.stack2.pop());
			} catch (StackUnderflowException | StackOverflowException e) {
				e.printStackTrace();
			}
		}
	}

}
