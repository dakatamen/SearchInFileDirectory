package com.fmi.parallel.execution;

import com.fmi.parallel.producerconsumerdesignpattern.Storage;

public class Demo {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Storage sp = new Storage(4, 4, "D:/JavaWorkspace", "name");
		sp.searchInFiles();
		System.out.println("Run in: " + (System.currentTimeMillis() - start) + " millis");
	}
}
