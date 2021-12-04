import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModulesListComponent } from './components/modules-list/modules-list.component';
import { ModuleDetailsComponent } from './components/module-details/module-details.component';
import { AddModuleComponent } from './components/add-module/add-module.component';
import { RunModuleComponent } from './components/run-module/run-module.component';

const routes: Routes = [
  { path: '', redirectTo: 'modules', pathMatch: 'full' },
  { path: 'modules', component: ModulesListComponent },
  { path: 'modules/:id', component: ModuleDetailsComponent },
  { path: 'add', component: AddModuleComponent },
  { path: 'run/:id', component: RunModuleComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
