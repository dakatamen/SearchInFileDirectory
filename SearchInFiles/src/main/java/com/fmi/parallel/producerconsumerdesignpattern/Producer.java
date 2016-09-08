package com.fmi.parallel.producerconsumerdesignpattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import com.fmi.parallel.exceptions.ReadFromFileException;
import com.fmi.parallel.exceptions.WaitingToProduceException;

public class Producer implements Runnable {

	List<File> currentFiles;
	private Queue<String> queue;
	private int maxSize;
	AtomicInteger finishFlag;

	public Producer(List<File> currentFiles, Queue<String> queue, int maxSize, AtomicInteger finishFlag) {
		this.currentFiles = currentFiles;
		this.queue = queue;
		this.maxSize = maxSize;
		this.finishFlag = finishFlag;
	}

	@Override
	public void run() {
		for (File file : currentFiles) {
			try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
				String line;
				int lineNumber = 0;
				while (true) {
					line = bf.readLine();
					synchronized (queue) {
						if (file == currentFiles.get(currentFiles.size() - 1)) {
							if (line == null) {
								finishFlag.decrementAndGet();
								queue.notifyAll();
							}
						}
						if (line == null)
							break;
						while (queue.size() == maxSize) {
							try {
//								System.out.println("!!!Queue is full, " + "Producer thread waiting for "
//										+ "consumer to take something from queue");
								queue.wait();
							} catch (InterruptedException ex) {
								throw new WaitingToProduceException("An error occurs while waiting to produce from the queue.");
							}
						}
						lineNumber++;
//						System.out.println("ADD :" + file.getName() + "- " + lineNumber + ": " + line + " by: "
//								+ Thread.currentThread().getName());
						queue.add(file.getName() + "- " + lineNumber + ": " + line);
						queue.notifyAll();
					Thread.yield();
					}
				}
			} catch (IOException e) {
				throw new ReadFromFileException("An error occurs while trying to read from a file.");
			}
		}
	}
}
