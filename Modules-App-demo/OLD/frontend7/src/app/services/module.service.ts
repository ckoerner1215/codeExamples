import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Module } from '../models/module.model';
import {environment} from '../../environments/environment'

// Change/Add urls and port mappings in src/environments/environment.ts file.

@Injectable({
  providedIn: 'root'
})
export class ModuleService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Module[]> {
    return this.http.get<Module[]>(environment.backendURL);
  }

  get(id: any): Observable<Module> {
    return this.http.get(`${environment.backendURL}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(environment.backendURL, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${environment.backendURL}/${id}`, data);
  }

//  run(key: any): Observable<string> {
//    console.log("key: " + key);
//    var url = environment.microserviceURL + "/" +  key;
//    console.log("url: " + url);
//    return this.http.get(url, {responseType: 'text'});
//  }

  runWithInputs(data: any): Observable<any> {
//    var key = data.name;
    console.log("data: ",data);
    var url = environment.microserviceURL;
    return this.http.post<String>(url, data);
  }

//  help(name: any): Observable<any> {
//    var key = name.trim();
//    var url = environment.baseURL + "/help/" + key;
//    return this.http.get(url);
//  }

//  getListOfDirectories(module: any): Observable<any> {
//    var key = module.trim();
//    var directory = environment.repoRootDirectory + key;
//    var url = environment.fileAccessURL + "/listdirectories";
//    return this.http.post<any>(url,directory);
//   
//  }

  showFile(file: any): Observable<any> {
    var fileName = file.trim();
    var url = environment.fileAccessURL + "/showfile";
    return this.http.post<any>(url,fileName);
  }

  showFileContents(file: any): Observable<any> {
    var fileNam = file.trim();
    var url = "http://gitlab.gms.bana.com/api/v4/projects/3/repository/files/ck001%2Fck001_default.ksh/raw?ref=master"
    return this.http.get<any>(url);
  }

  moveFile(moveinfo: any): Observable<any> {
    console.log(moveinfo.filename); 
    console.log(moveinfo.destination);
    var url = environment.fileAccessURL + "/moveToModuleDirectory";
    return this.http.post<any>(url,moveinfo);
  }

  uploadFile(file: File): Observable<any> {
    var url = environment.fileAccessURL + "/uploadFile";
    var data: FormData = new FormData();
    data.append('file',file);
    return this.http.post<any>(url,data);
  }

  downloadFile(file: any): Observable<any> {
    console.log("File: " + file);
    var fileName = file.trim();
    var url = environment.fileAccessURL + "/downloadfile";
    return this.http.post<any>(url,fileName);
  }

  getListOfFiles(module: any): Observable<any> {
    var key = module.trim();
    var directory = environment.repoRootDirectory + key;
    var url = environment.fileAccessURL + "/listfiles";
    return this.http.post<any>(url,directory);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${environment.backendURL}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(environment.backendURL);
  }

  findByTitle(title: any): Observable<Module[]> {
    return this.http.get<Module[]>(`${environment.backendURL}?title=${title}`);
  }
}



