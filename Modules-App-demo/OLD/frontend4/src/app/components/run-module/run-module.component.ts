import { Component, OnInit } from '@angular/core';
import { ModuleService } from 'src/app/services/module.service';
import { Module } from 'src/app/models/module.model';
import { ActivatedRoute, Router } from '@angular/router';

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
   currentModuleFields: any = {};
   keys: Array<string> = [];
   values: Array<string> = [];
   labels:  Array<string> = [];
   labelValues:  Array<string> = [];
   labelDescriptions: Array<string> = [];
   moduleDescription = '';

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
          console.log("Should have data now.....");
          console.log(data);
          this.getFields(this.currentModule.title);
        },
        error => {
          console.log(error);
        });
  }

  getFields(title: any): void {
    this.moduleService.help(title)
      .subscribe(
        data => {
          this.currentModuleFields = data;
          this.keys = Object.keys(data);
          for (var key of this.keys){
               if(key.indexOf('name') >= 0){
                 this.labels.push('Name: ');
                 this.labelValues.push(data[key]);
                 this.labelDescriptions.push(data[key]); 
                 this.values.push(data[key]);
               }
               else if (key.indexOf('description') >= 0){
                 this.moduleDescription = data[key];
                 this.values.push(data[key]);
               }
               else if( key.indexOf('Description') >= 0){
                 this.labelDescriptions.push(data[key]);
                 this.values.push(data[key]);                
               }
               else {
                  this.values.push(data[key]);
                  this.labelValues.push(data[key]);
                  this.labels.push(key);
               }
          }
        },
        error => {
          console.log(error);
        });
  }

  runModule(): void {
    var rundata: any = {};
    var i = 0;
    for (var key of this.keys){
     console.log(key + '  ' + this.values[i]);
     rundata[key] = this.values[i];
     i=i+1;
    }
    var mess = '  ';

    this.moduleService.runWithInputs(rundata)
      .subscribe(
        response => {
          console.log("response:  " + response);
          console.log("success:  " + response.success);
          console.log("error:   " + response.errorCode);
          if(response.success){
             mess = response.description;
             mess = mess + 'The module has run successfully.';
             this.message = mess;
          } else {
             mess = 'There was a problem running the module.';
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
