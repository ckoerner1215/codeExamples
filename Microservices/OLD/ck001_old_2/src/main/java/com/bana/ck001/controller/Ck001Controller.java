package com.bana.ck001.controller;

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
import com.bana.ck001.model.Module;
import com.bana.ck001.model.RunStatus;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class Ck001Controller {

        @RequestMapping("/ck001/run")
        public String run() {

                System.out.println("Starting run() in Ck001Controller");
		ProcessBuilder processBuilder = new ProcessBuilder();

		processBuilder.command("/bin/sh", "-c", "cd src/main/resources/static ; ./ck001.ksh");
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

        @RequestMapping("/ck001/help")
        public Module help() {
                System.out.println("Returning the fields in Module class.");
                Module module = new Module();
                module.setName("ck001");
                module.setDescription("This is the description for ck001");
                module.setInfile1("/app/prod/ck001/input.dat");
                module.setInfile2("/app/prod/ck001/llh.bin");
                module.setOutputDirectory("/app/prod/ck001/OUTPUT");
                module.setScriptLocation("/app/prod/ck001");
                module.setExecutableLocation("/app/prod/ck001");
                return module;
        }

        @PostMapping(path = "/ck001/run")
        public RunStatus runModule(@RequestBody Module module) {
                System.out.println("This is runModule POST ");
                RunStatus rs = new RunStatus(module.getName(), module.getDescription(), 999, false);
                ProcessBuilder processBuilder = new ProcessBuilder();               

                String command= module.getScriptLocation() + "/ck001.ksh ";
		command = command + module.getExecutableLocation() + " " + module.getInfile1() 
                      + " " + module.getInfile2() + " " + module.getOutputDirectory() + " ;";

                processBuilder.command("/bin/sh", "-c", command);
                String outputline="<br>";

                outputline = outputline + module.getScriptLocation() + "<br>" + module.getExecutableLocation() + "<br> " + module.getInfile1() 
                               + "<br> " + module.getInfile2() + " <br>" + module.getOutputDirectory() + "<br> ";
                try {

                    Process process = processBuilder.start();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        outputline=outputline+line+" <br>";
                    }
		    outputline=outputline + " <br>" + module.getDescription() +" <br> ";

                    int exitCode = process.waitFor();
                    rs.setErrorCode(exitCode);
                    rs.setDescription(outputline);
                    if (exitCode == 0) {
                       rs.setSuccess(true);
                    } else {
                       rs.setSuccess(false);
                    }
                    System.out.println("<br>Exited with error code ------- : " + exitCode);

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

