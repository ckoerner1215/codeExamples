import { Component, OnInit } from '@angular/core';
import { ModuleService } from 'src/app/services/module.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Module } from 'src/app/models/module.model';

@Component({
  selector: 'app-module-details',
  templateUrl: './module-details.component.html',
  styleUrls: ['./module-details.component.css']
})
export class ModuleDetailsComponent implements OnInit {
  currentModule: Module = {
    title: '',
    description: '',
    published: false
  };
  message = '';

  constructor(
    private moduleService: ModuleService,
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
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updatePublished(status: boolean): void {
    const data = {
      title: this.currentModule.title,
      description: this.currentModule.description,
      published: status
    };

    this.moduleService.update(this.currentModule.id, data)
      .subscribe(
        response => {
          this.currentModule.published = status;
          console.log(response);
          this.message = response.message;
        },
        error => {
          console.log(error);
        });
  }

  updateModule(): void {
    this.moduleService.update(this.currentModule.id, this.currentModule)
      .subscribe(
        response => {
          console.log(response);
          this.message = response.message;
        },
        error => {
          console.log(error);
        });
  }

  runModule(): void {
    this.moduleService.run(this.currentModule.id, this.currentModule)
      .subscribe(
        response => {
          console.log(response);
          this.message = response;
//          this.message = response.message;
//          this.message = 'ck001 module has run...';
        },
        error => {
          console.log(error);
        });
  }

  deleteModule(): void {
    this.moduleService.delete(this.currentModule.id)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/modules']);
        },
        error => {
          console.log(error);
        });
  }
}




