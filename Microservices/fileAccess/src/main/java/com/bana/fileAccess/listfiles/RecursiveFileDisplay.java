package com.bana.fileAccess.listfiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecursiveFileDisplay {
	
	public List<String> listOfFiles = new ArrayList<>();
	
	public List<String> getListing(String topdir) {
		File currentDir = new File(topdir); 
		displayDirectoryContents(currentDir);
		for(String file : listOfFiles) {
			System.out.println(file);
		}
		return listOfFiles;
	}

	public void displayDirectoryContents(File dir) {
		String name;
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				name=file.getCanonicalPath();
				if (file.isDirectory()) {
					//System.out.println("directory:" + name);
					listOfFiles.add("directory: " + name);
					displayDirectoryContents(file);
				} else {
					//System.out.println("     file:" + file.getCanonicalPath());
					listOfFiles.add("    file: " + name);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
