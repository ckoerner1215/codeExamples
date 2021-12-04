package com.bana.generic.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.bana.generic.model.RunInfo;
import com.bana.generic.model.RunStatus;
import java.io.PrintWriter;
import java.io.File;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class GenericController {

        private String repoDirectory = "/app/prod/";

//don't really use this endpoint.......
        @RequestMapping("/run/{module}")
        public String run(@PathVariable("module") String module) {

                System.out.println("Starting run() in GenericController");
		ProcessBuilder processBuilder = new ProcessBuilder();

		processBuilder.command("/bin/sh", "-c", "cd " + this.repoDirectory + module.trim() + " ; ./" + module.trim()+".ksh");
                String outputline="<br />";

		try {

		    Process process = processBuilder.start();

		    BufferedReader reader =
			    new BufferedReader(new InputStreamReader(process.getInputStream()));

		    String line;
		    while ((line = reader.readLine()) != null) {
			System.out.println(line);
			outputline=outputline+line+"<br />";
		    }

		    int exitCode = process.waitFor();
		    System.out.println("Exited with error code : " + exitCode);

		} catch (IOException e) {
		    e.printStackTrace();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		return String.format(outputline);
        }

        @PostMapping(path = "/run")
        public RunStatus runModule(@RequestBody RunInfo runInfo) {
                String kshFile = runInfo.getKshFile().trim();
                String kshFileName = runInfo.getKshFileName().trim();
                System.out.println("Running the module: " + kshFile);
                System.out.println("KSH Filename: " + kshFileName);
                System.out.println("Inputs:  " + runInfo);
                String moduleName = runInfo.getName();
                String kshFileContents = runInfo.getKshFileContents();
                System.out.println("Name: " + moduleName);
                RunStatus rs = new RunStatus(runInfo.getName(), 999, false, "Did not run");
                ProcessBuilder processBuilder = new ProcessBuilder();               
               
                System.out.println(" "); 
                System.out.println("  ");
                System.out.println(this.repoDirectory + kshFile);
                System.out.println("  ");
               //try{
               //    File newKshFile = new File(this.repoDirectory + kshFile);
               //    newKshFile.getParentFile().mkdirs();
               //    PrintWriter printWriter = new PrintWriter(newKshFile);
               //    printWriter.println(kshFileContents);
               //    printWriter.close();
               // } catch (IOException e) {
               //     e.printStackTrace();
               // } 

               String command = "cd " + this.repoDirectory  + runInfo.getScriptLocation().trim() + "; ";

               command = command + "git fetch; git checkout -m origin/master " + kshFileName + "; ";



                //String command = "";
                //if(kshFile.substring(0,1).equals("/")){
                //   command = command + "cd " + runInfo.getScriptLocation().trim() + "; " + kshFile;
                //}
                //else{
                   //System.out.println("in else.........");
                   command = command + " ./" + kshFileName;
                //}

                System.out.println("command: " + command);

                processBuilder.command("/bin/sh", "-c", command);
                String outputline="<br>";

                outputline = outputline + "Running " + kshFile + "<br> ";
                try {

                    Process process = processBuilder.start();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        outputline=outputline+line+" <br>";
                    }
		    outputline=outputline + " <br> ";

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

