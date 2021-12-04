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

   runInfo: any = {};
   moveInfo: any = {};

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
          this.listOfFiles = data;
          this.kshFile = data[0];
          console.log("kshFile: ",this.kshFile);
          this.getLastIndexOf("/",this.kshFile);
          this.kshSaveAsName = this.kshFile.substr(this.index+1,this.kshFile.length);
          console.log("kshSaveAsName: ",this.kshSaveAsName);
          this.locationOfKsh = this.kshFile.substr(0,this.index);
          console.log("locationOfKsh: ",this.locationOfKsh);
          this.runInfo['name']=title;
          this.runInfo['kshFile']=data[0];
          this.runInfo['scriptLocation']=this.locationOfKsh;
          this.showFile();

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
          if(response.success){
             mess = response.output;
             this.textAreaText = mess;

          this.getLastIndexOf("/",this.kshFile);
          this.kshSaveAsName = this.kshFile.substr(this.index+1,this.kshFile.length);

          } else {
             mess = 'There was a problem getting the file. <br>';
             mess = mess + '   Error code: ' + response.errorCode;
             mess = mess + '  OUTPUT: ' + response.output;
             this.textAreaText = mess;
          }
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

    this.moveInfo['filename']=this.fileToUpload.name;
    this.moveInfo['destination']=this.runInfo['scriptLocation'];
    console.log("moveInfo:  ", this.moveInfo);

    this.moduleService.uploadFile(this.fileToUpload)
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
     this.getFileList(this.currentModule.title);
 

  }


  runModule(): void {
    var mess = '  ';

    this.moduleService.runWithInputs(this.runInfo)
      .subscribe(
        response => {
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
