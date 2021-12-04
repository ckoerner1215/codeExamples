import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddModuleComponent } from './components/add-module/add-module.component';
import { ModuleDetailsComponent } from './components/module-details/module-details.component';
import { ModulesListComponent } from './components/modules-list/modules-list.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RunModuleComponent } from './components/run-module/run-module.component';

@NgModule({
  declarations: [
    AppComponent,
    AddModuleComponent,
    ModuleDetailsComponent,
    ModulesListComponent,
    RunModuleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


