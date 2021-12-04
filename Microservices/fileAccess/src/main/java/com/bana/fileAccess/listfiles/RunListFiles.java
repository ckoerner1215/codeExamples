package com.bana.fileAccess.listfiles;

import java.io.IOException;
import java.util.Set;
import com.bana.fileAccess.listfiles.ListFiles;


public class RunListFiles {

	public static void main(String[] args) {
		ListFiles listfiles = new ListFiles();
        String directory = "/apps/carolyn/prod/ck100";
        Set<String> myListOfFiles1 = listfiles.listFilesUsingJavaIO(directory);
        System.out.println("List of Files:");
        System.out.println(myListOfFiles1);
        
        directory = "/apps/carolyn/prod/ck100";
        Set<String> myListOfFiles2;
		try {
			myListOfFiles2 = listfiles.listDirectoriesUsingFileWalk(directory,5);
	        System.out.println("List of Directories:");
	        System.out.println(myListOfFiles2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
