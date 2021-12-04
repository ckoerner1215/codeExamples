import { Component, OnInit } from '@angular/core';
import { Runinfo } from 'src/app/models/runinfo.model';
import { ModuleService } from 'src/app/services/module.service';
import { Module } from 'src/app/models/module.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-run-module',
  templateUrl: './run-module.component.html',
  styleUrls: ['./run-module.component.css']
})
export class RunModuleComponent implements OnInit {
   runinfo: Runinfo = {
      inputfile1: '/app/prod/ck001/INPUT/input.dat',
      inputfile2: '/app/prod/ck001/INPUT/llh.bin',
      outputdirectory: '/app/prod/OUTPUT_FROM_CK001',
      scriptlocation: '/app/prod/ck001',
      executablelocation: '/app/prod/ck001'
   };
  currentModule: Module = {
    title: '',
    description: '',
    published: false
  };
   message = '';

  constructor(private moduleService: ModuleService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    console.log(this.route.snapshot.params.id);
    this.getModule(this.route.snapshot.params.id);
  }

  getModule(id: string): void {
    this.moduleService.get(id)
      .subscribe(
        data => {
          this.currentModule = data;
        },
        error => {
          console.log(error);
        });
  }

  runModule(): void {
    var rundata = {
         name: this.currentModule.title,
         description: this.currentModule.description,
         infile1: this.runinfo.inputfile1,
         infile2: this.runinfo.inputfile2,
         outputDirectory: this.runinfo.outputdirectory,
         scriptLocation: this.runinfo.scriptlocation,
         executableLocation: this.runinfo.executablelocation
    };
    var mess = '  ';

    this.moduleService.runWithInputs(rundata)
      .subscribe(
        response => {
          console.log(response);
          console.log(response.success);
          console.log(response.errorCode);
          if(response.success){
             mess = response.description;
             mess = mess + '     ck001 module has run successfully.';
             this.message = mess;
          } else {
             mess = 'There was a problem running ck001 module.';
             mess = mess + '   Error code: ' + response.errorCode;
             mess = mess + '  OUTPUT: ' + response.description;
             this.message = mess; 
          }    
        },
        error => {
          console.log(error);
          this.message = "Unfortunately, this did not seem to work :(";
        });
  }




}
