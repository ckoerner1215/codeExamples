package com.bana.fileAccess.fileuploaddownload.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.bana.fileAccess.fileuploaddownload.payload.Response;
import com.bana.fileAccess.fileuploaddownload.service.FileStorageService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.file.Paths;
import com.bana.fileAccess.fileuploaddownload.model.RunStatus;

@RestController
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        System.out.println("fileName: " + fileName);
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        System.out.println(file);


        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(fileName)
            .toUriString();


        return new Response(fileName, fileDownloadUri,
            file.getContentType(), file.getSize());
    }

    @PostMapping(path="/moveToModuleDirectory")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public RunStatus moveToModuleDirectory(@RequestBody MoveInfo moveInfo){
        System.out.println("in moveToMovduleDirectory");
	System.out.println("   ");
        RunStatus rs = new RunStatus(moveInfo.getFilename(), 999, false, "Did not move file");

        String uploaddir = fileStorageService.getUploadDir().toAbsolutePath().toString();
        System.out.println("upload dir: "+uploaddir);

        System.out.println("destination: "+moveInfo.getDestination());

	ProcessBuilder processBuilder = new ProcessBuilder();

        String uploadedFile = uploaddir + "/" + moveInfo.getFilename();
        String tempfile = uploaddir + "/" + "temp.file";

        // Have to convert between Windows format and Linux format for files:
        String command = "tr -d '\15\32' < " + uploadedFile + " > " + tempfile + "  ; ";
        command = command + "mv " + tempfile + "  " + uploadedFile + " ;";  
	command = command + "cp  " + uploadedFile + "  " 
               + moveInfo.getDestination() + "; chmod 777 " + moveInfo.getDestination() + "/" + moveInfo.getFilename() +";";
	System.out.println("command: " + command);

	processBuilder.command("/bin/sh", "-c", command);
        System.out.println("after processBuilder.command");
        System.out.println("  ");
	String outputline="";

	try {

	    Process process = processBuilder.start();
            System.out.println("after processBuilder.start");
            System.out.println("  ");

	    BufferedReader reader =
		    new BufferedReader(new InputStreamReader(process.getInputStream()));

	    String line;
	    while ((line = reader.readLine()) != null) {
		//System.out.println(line);
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
            outputline = outputline + String.valueOf(exitCode);
	    if (exitCode == 0) {
	       outputline = outputline + "  Success!!!";
	    } else {
	       outputline = outputline + "  Failure......";
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

    @PostMapping("/uploadMultipleFiles")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List < Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }
}
