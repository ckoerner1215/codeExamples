package com.bana.fileAccess.listfiles.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.bana.fileaccess.listfiles.model.RunStatus;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bana.fileAccess.listfiles.ListFiles;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ListFilesController {

	@PostMapping("/listfiles")
    public Set<String> listFiles(@RequestBody String directory) {
		ListFiles listfiles = new ListFiles();
		Set<String> myListOfFiles = new HashSet<String>();

		try {
			//myListOfFiles = listfiles.listFilesUsingFileWalk(directory, 2);
			 myListOfFiles = listfiles.listFilesUsingFileWalkAndVisitor(directory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myListOfFiles;
		
	}
	@PostMapping("/listdirectories")
    public Set<String> listDirectories(@RequestBody String directory) {
		ListFiles listfiles = new ListFiles();
		Set<String> myListOfDirs = new HashSet<String>();
                int depth = 6;


		try {
			//myListOfFiles = listfiles.listFilesUsingFileWalk(directory, 2);
			 myListOfDirs = listfiles.listDirectoriesUsingFileWalk(directory,depth);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myListOfDirs;
		
	}

        @PostMapping(path = "/showfile")
        public RunStatus showFile(@RequestBody String file) {
                System.out.println("File:  " + file);
                RunStatus rs = new RunStatus(file, 999, false, "Did not display file");
                ProcessBuilder processBuilder = new ProcessBuilder();

                String command = "cat " + file + ";";
                System.out.println("command: " + command);

                processBuilder.command("/bin/sh", "-c", command);
                String outputline="";

                //outputline = outputline + "File: " + file + "\n";
                try {

                    Process process = processBuilder.start();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        outputline=outputline+line+"\n";
                    }
                    outputline=outputline + "\n";

                    int exitCode = process.waitFor();
                    rs.setErrorCode(exitCode);
                    rs.setOutput(outputline);
                    if (exitCode == 0) {
                       rs.setSuccess(true);
                    } else {
                       rs.setSuccess(false);
                    }
                    System.out.println("Exited with error code ------- : " + exitCode);

                } catch (IOException e) {
                    e.printStackTrace();
                    rs.setSuccess(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    rs.setSuccess(false);
                }
                return rs;
        }
	

        @PostMapping(path = "/downloadfile")
        public RunStatus downloadFile(@RequestBody String file) {
                System.out.println("File:  " + file);
                RunStatus rs = new RunStatus(file, 999, false, "Did not display file");
                ProcessBuilder processBuilder = new ProcessBuilder();

                String command = "cat " + file + ";";
                System.out.println("command: " + command);

                processBuilder.command("/bin/sh", "-c", command);
                String outputline="\n";

                try {

                    Process process = processBuilder.start();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        outputline=outputline+line+"\n";
                    }
                    outputline=outputline + "\n";

                    int exitCode = process.waitFor();
                    rs.setErrorCode(exitCode);
                    rs.setOutput(outputline);
                    if (exitCode == 0) {
                       rs.setSuccess(true);
                    } else {
                       rs.setSuccess(false);
                    }
                    System.out.println("Exited with error code ------- : " + exitCode);

                } catch (IOException e) {
                    e.printStackTrace();
                    rs.setSuccess(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    rs.setSuccess(false);
                }
                return rs;
        }
	


}
