package com.fmi.parallel.filesandirectories;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class FindFiles {
	private File[] startDirectoryFiles;
	private List<File> allFiles;

	public FindFiles(String directory) {
		startDirectoryFiles = new File(directory).listFiles();
		allFiles = new ArrayList<>();
	}

	private void showFiles(File[] files, List<File> allFiles) {
		for (File file : files) {
			if (file.isDirectory()) {
				showFiles(file.listFiles(), allFiles);
			} else {
				if (FilenameUtils.getExtension(file.getAbsolutePath()).equals("txt")) {
					allFiles.add(file);
				}
			}
		}
	}

	public List<File> getAllFiles() {
		showFiles(startDirectoryFiles, allFiles);
		return allFiles;

	}

}
