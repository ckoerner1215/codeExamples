import { Component, OnInit } from '@angular/core';
import { Module } from 'src/app/models/module.model';
import { ModuleService } from 'src/app/services/module.service';

@Component({
  selector: 'app-add-module',
  templateUrl: './add-module.component.html',
  styleUrls: ['./add-module.component.css']
})
export class AddModuleComponent implements OnInit {
  module: Module = {
    title: '',
    description: '',
    published: false
  };
  submitted = false;

  constructor(private moduleService: ModuleService) { }

  ngOnInit(): void {
  }

  saveModule(): void {
    const data = {
      title: this.module.title,
      description: this.module.description
    };

    this.moduleService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  newModule(): void {
    this.submitted = false;
    this.module = {
      title: '',
      description: '',
      published: false
    };
  }

}
