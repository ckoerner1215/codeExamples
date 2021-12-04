import { Component, OnInit } from '@angular/core';
import { ModuleService } from 'src/app/services/module.service';
import { Module } from 'src/app/models/module.model';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-run-module',
  templateUrl: './run-module.component.html',
  styleUrls: ['./run-module.component.css']
})
export class RunModuleComponent implements OnInit {


  currentModule: Module = {
    title: '',
    description: '',
    published: false
  };
   message = '';
   labels:  Array<string> = [];
   labelValues:  Array<string> = [];
   labelDescriptions: Array<string> = [];
   validModule = true;
   fileToUpload: File = new File(["foo"], "foo.txt", {type: "text/plain"});
   kshFile = '';
   kshSaveAsName = '';
   textAreaText = '  ';
   index = 0;

   loadFile: File = new File(["foo"], "foo.txt", {type: "text/plain"});

   locationOfKsh = '';
   listOfFiles: Array<string> = [];
   listOfDirectories: Array<string> = [];
   listOfNames: Array<string> = [];
   listOfBlobs: Array<any> = [];
   listOfIds: Array<string> = [];

   runInfo: any = {};
   moveInfo: any = {};
   currentBlob: any = {};

  constructor(private moduleService: ModuleService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    this.getModule(this.route.snapshot.params.id);
  }

  getModule(id: string): void {
    this.moduleService.get(id)
      .subscribe(
        data => {
          this.currentModule = data;
          this.getFileList(this.currentModule.title);
        },
        error => {
          console.log(error);
        });
  }

  getFileList(title: any): void {
      this.moduleService.getListOfFiles(title)
        .subscribe(
        data => {

          for( var subdir of data) {
               var id = subdir.id;
               var name = subdir.name; 
               var type = subdir.type;
               var path = subdir.path;
               var mode = subdir.mode;

               if(type === "tree"){
                 this.listOfDirectories.push(path);
               }
               if(type === "blob"){
                 if(path.includes("ksh")){
                     this.listOfFiles.push(path);
                     this.listOfNames.push(name);
                     this.listOfBlobs.push(subdir);
                     this.listOfIds.push(id);
                 }
               }
          }


          if(this.listOfFiles.length > 0){
		  this.kshFile = this.listOfFiles[0];
		  this.kshSaveAsName = this.listOfNames[0];
		  this.currentBlob = this.listOfBlobs[0];
		  this.getLastIndexOf("/",this.kshFile);
		  //this.kshSaveAsName = this.kshFile.substr(this.index+1,this.kshFile.length);
		  this.locationOfKsh = this.kshFile.substr(0,this.index);
		  this.runInfo['name']=title;
		  this.runInfo['kshFile']=this.kshFile;
		  this.runInfo['kshFileName']=this.kshSaveAsName;
		  this.runInfo['scriptLocation']=this.locationOfKsh;

		  this.showFile();
          }

        },
        error => {
          console.log(error);
        });

      this.moduleService.getListOfFilesInSubdirectories(title)
        .subscribe(
        data => {

          for( var subdir of data) {
               var id = subdir.id;
               var name = subdir.name;
               var type = subdir.type;
               var path = subdir.path;
               var mode = subdir.mode;

               if(type === "tree"){
                 this.listOfDirectories.push(path);
               }
               if(type === "blob"){
                 if(path.includes("ksh")){
                     var index = this.listOfFiles.indexOf(path);
                     if(index < 0){
                        this.listOfFiles.push(path);
                        this.listOfNames.push(name);
                        this.listOfBlobs.push(subdir);
                        this.listOfIds.push(id);
                     }
                 }
               }
          }

          if (this.kshFile.trim() === ''){

             this.kshFile = this.listOfFiles[0];
             this.kshSaveAsName = this.listOfNames[0];
             this.currentBlob = this.listOfBlobs[0];
             this.getLastIndexOf("/",this.kshFile);
             //this.kshSaveAsName = this.kshFile.substr(this.index+1,this.kshFile.length);
             this.locationOfKsh = this.kshFile.substr(0,this.index);
             console.log("locationOfKsh: ",this.locationOfKsh);
             this.runInfo['name']=title;
             this.runInfo['kshFile']=this.kshFile;
             this.runInfo['kshFileName']=this.kshSaveAsName;
             this.runInfo['scriptLocation']=this.locationOfKsh;

             this.showFile();

          }

        },
        error => {
          console.log(error);
        });




  }

//  downloadFile(): void {
//    var mess = '  ';
//    var index, i;  
//
//    for (i=0; i < this.runInfo['kshFile'].length; i++){
//           if (this.runInfo['kshFile'].substring(i, i+1) == '/'){
//		   index = i+1;
//	   }
//    }
//    var filename = this.runInfo['kshFile'].substring(index);
//    this.filename = filename;
//
//    this.moduleService.downloadFile(this.runInfo['kshFile'])
//      .subscribe(
//        response => {
//          if(response.success){
//             mess = response.output;
//             var blob = new Blob([mess], {type: "text/plain;charset=utf-8", endings:"native"});
//             saveAs(blob, filename);
//          } else {
//             mess = 'There was a problem getting the file. <br>';
//             mess = mess + '   Error code: ' + response.errorCode;
//             mess = mess + '  OUTPUT: ' + response.output;
//             this.message = mess;
//          }
//        },
//        error => {
//         console.log(error);
//          this.message = "Unfortunately, this did not seem to work :(";
//        });
//  
//
//  }


  showFile(): void {
    var mess = '  ';
  
    this.moduleService.showFile(this.kshFile)
      .subscribe(
        response => {
          mess = atob(response.content);
          this.textAreaText = mess;
          this.kshSaveAsName = this.currentBlob.name;
          this.runInfo['kshFileName']=this.kshSaveAsName;
          this.runInfo['kshFileContents']=this.textAreaText;
        },
        error => {
          console.log(error);
          this.message = "Unfortunately, this did not seem to work :(";
        });
  

  }


  selectUploadFile(event: any) {
    this.fileToUpload = event.target.files[0];
  }

  moveFile(): void {
   var mess = '';
   this.moduleService.moveFile(this.moveInfo)
     .subscribe(
        response => {
             mess =  this.moveInfo['filename'] + " was successfully uploaded to " + this.runInfo['scriptLocation'];
             console.log("response: ", response);
             console.log("SUCCESS moving file");
             this.message = mess;
        },
        error => {
          console.log(error);
          this.message = "Unfortunately, this did not seem to work :(";
        });
     
  }

  uploadFile(): void {
    var mess = '  ';

    var blob = new Blob([this.textAreaText], {type: "text/plain;charset=utf-8", endings:"native"});

    this.moveInfo['filename']=this.kshSaveAsName;
    this.moveInfo['destination']=this.runInfo['scriptLocation'];
    console.log("moveInfo:  ", this.moveInfo);

    this.moduleService.uploadFile(blob, this.kshSaveAsName)
      .subscribe(
        response => {
          if(response.size > 0){
             this.moveFile();
          } else {
             mess = 'There was a problem uploading the file to the upload directory';
             console.log(mess);
          }
        },
        error => {
          console.log(error);
          this.message = "Unfortunately, this did not seem to work :(";
        });
    // this.getFileList(this.currentModule.title);
 

  }


  runModule(): void {
    var mess = '  ';

    //this.uploadFile();

    this.moduleService.runWithInputs(this.runInfo)
      .subscribe(
        response => {
          console.log("888888888888888");
          console.log("runwithINputs");
          if(response.success){
             mess = response.output;
             mess = mess + '<br>' + 'The module has run successfully.';
             this.message = mess;
          } else {
             mess = 'There was a problem running the module. <br>';
             mess = mess + '   Error code: ' + response.errorCode;
             mess = mess + '  OUTPUT: ' + response.output;
             this.message = mess; 
          }    
        },
        error => {
          console.log(error);
          this.message = "Unfortunately, this did not seem to work :(";
        });
  }

  changeKshFile(e: any) {
    var index = this.listOfFiles.indexOf(e.target.value);
    this.currentBlob = this.listOfBlobs[index];
    this.runInfo['kshFile']=e.target.value;
    this.kshFile = e.target.value;
    this.getLastIndexOf("/",this.kshFile);
    this.locationOfKsh = this.kshFile.substr(0,this.index);
    console.log("location of ksh: ",this.locationOfKsh);
    this.runInfo['scriptLocation']=this.locationOfKsh;
    this.showFile();
  }

  selectFileToLoad(e: any){
      this.loadFile = e.target.files[0];
  }

  getLastIndexOf(searchStr: string, str: string) {
      var startIndex = 0, index1;
      while ((index1 = str.indexOf(searchStr, startIndex)) > -1) {
        startIndex = index1 + 1;
      }
      this.index=startIndex-1;
  }


saveTextAsFile()
{
     var blob = new Blob([this.textAreaText], {type: "text/plain;charset=utf-8", endings:"native"});
     saveAs(blob, this.kshSaveAsName);

}

updateFile()
{
    console.log("Updating file...........");
    console.log(this.kshFile);
    
    var data: any = {};
    data['branch']="master";
    data['content']=this.textAreaText;
    data['commit_message']="Changed some stuff";
    console.log("data: ");
    console.log(data);


    this.moduleService.updateFile(this.kshFile,data)
      .subscribe(
        result => {
          var filepath = result.file_path;
          var branch = result.branch;
          console.log("filepath: "+filepath);
          console.log("branch: "+branch);
          this.kshSaveAsName = this.currentBlob.name;
          this.runInfo['kshFileName']=this.kshSaveAsName;
          this.runInfo['kshFileContents']=this.textAreaText;
        },
        error => {
          console.log(error);
        });
}
 
loadFileAsText()
{
    var fileToLoad = this.loadFile;
    var fileReader = new FileReader();
    fileReader.readAsText(fileToLoad, "UTF-8");
    fileReader.onload = (e: any) => {
        this.textAreaText = e.target.result;
    };
}
 

}
