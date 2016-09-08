package com.fmi.parallel.producerconsumerdesignpattern;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import com.fmi.parallel.exceptions.WaitingToConsumeException;

class Consumer implements Runnable {
	private Queue<String> queue;
	AtomicInteger finishFlag;
	private String searchWord;

	public Consumer(Queue<String> queue, int maxSize, AtomicInteger finishFlag, String searchWord) {
		this.queue = queue;
		this.finishFlag = finishFlag;
		this.searchWord=searchWord;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (queue) {
				while (queue.isEmpty()) {
//					System.out.println("!!!Queue is empty," + "Consumer thread is waiting"
//							+ " for producer thread to put something in queue");
					try {
						if ((int) finishFlag.get() == 0) {
							return;
						}
						queue.wait();
					} catch (InterruptedException ex) {
						throw new WaitingToConsumeException("An error occurs while waiting to consume from the queue.");
					}
				}
				if (queue.peek().contains(searchWord))
					System.out.println("Find value : " + queue.remove() + " by: " + Thread.currentThread().getName());
				else {
					queue.remove();
//					System.out
//							.println("CONSUME value : " + queue.remove() + " by: " + Thread.currentThread().getName());
				}
				queue.notifyAll();
				Thread.yield();
			}
		}
	}
}