import { Component, OnInit } from '@angular/core';
import { Module } from 'src/app/models/module.model';
import { ModuleService } from 'src/app/services/module.service';

@Component({
  selector: 'app-modules-list',
  templateUrl: './modules-list.component.html',
  styleUrls: ['./modules-list.component.css']
})
export class ModulesListComponent implements OnInit {
  modules?: Module[];
  currentModule?: Module;
  currentIndex = -1;
  title = '';

  constructor(private moduleService: ModuleService) { }

  ngOnInit(): void {
    this.retrieveModules();
  }

  retrieveModules(): void {
    this.moduleService.getAll()
      .subscribe(
        data => {
          this.modules = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveModules();
    this.currentModule = undefined;
    this.currentIndex = -1;
  }

  setActiveModule(module: Module, index: number): void {
    this.currentModule = module;
    this.currentIndex = index;
  }

  removeAllModules(): void {
    this.moduleService.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.refreshList();
        },
        error => {
          console.log(error);
        });
  }

  searchTitle(): void {
    this.moduleService.findByTitle(this.title)
      .subscribe(
        data => {
          this.modules = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

}





