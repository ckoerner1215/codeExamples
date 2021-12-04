package com.bana.ck100.controller;

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
import com.bana.ck100.model.Input;
import com.bana.ck100.model.ModuleInfo;
import com.bana.ck100.model.RunInfo;
import com.bana.ck100.model.RunStatus;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Ck100Controller {

        @RequestMapping("/ck100/run")
        public String run() {

                System.out.println("Starting run() in Ck100Controller");
		ProcessBuilder processBuilder = new ProcessBuilder();

		processBuilder.command("/bin/sh", "-c", "cd src/main/resources/static ; ./ck100.ksh . OUTPUT 3 \"1 2 3\" ");
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

        @RequestMapping("/ck100/help")
        public ModuleInfo help() {
                System.out.println("Returning the information for ck100.");
                ModuleInfo moduleInfo = new ModuleInfo();
                return moduleInfo;
        }

        @PostMapping(path = "/ck100/run")
        public RunStatus runModule(@RequestBody RunInfo module) {
                System.out.println("This is runModule POST ");
                System.out.println("Inputs:  " + module);
                RunStatus rs = new RunStatus(module.getName(), module.getDescription(), 999, false);
                ProcessBuilder processBuilder = new ProcessBuilder();               

                String command= module.getScriptLocation() + "/ck100.ksh ";
		command = command + module.getExecutableLocation() + " " + module.getOutputDirectory() 
                      + " " + module.getNumberOfPoints() + " " + "\"" + module.getPoints() + "\"" +  " ;";

                processBuilder.command("/bin/sh", "-c", command);
                String outputline="<br>";

                outputline = outputline + module.getScriptLocation() + "<br>" + 
                      module.getExecutableLocation() + "<br> " + module.getNumberOfPoints() + "<br>" + module.getPoints()  
                      + " <br>" + module.getOutputDirectory() + "<br> ";
                try {

                    Process process = processBuilder.start();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        outputline=outputline+line+" <br>";
                    }
		    //outputline=outputline + "  " + module.getDescription() +"<br />";
		    outputline=outputline + " <br>" + module.getDescription() +" <br> ";

                    int exitCode = process.waitFor();
                    rs.setErrorCode(exitCode);
                    rs.setDescription(outputline);
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

