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

  newFilename = '';

  header = new HttpHeaders().set(
      "PRIVATE-TOKEN",
       "vm5VKf9wPVzLXsqU6mgx"
    );
  options = {
        headers: this.header
    }



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
    console.log("in runWithInputs......");
    console.log("data: ",data);
    var url = environment.microserviceURL;
    return this.http.post<String>(url, data);
  }


  showFile(file: any): Observable<any> {
    console.log("in showFileTest");
    console.log(file);
    var fileName = file.trim();
    this.replaceSlashesWith(fileName);
    var url = "http://gitlab.gms.bana.com/api/v4/projects/3/repository/files/" + this.newFilename + "?ref=master";
    return this.http.get<any>(url, this.options);
  }

  replaceSlashesWith(filename: any){
      var replaceRegEx = new RegExp(/\//g);
      var newString = "%2F";
      var newFilename = filename.replace(replaceRegEx,newString);
      this.newFilename = newFilename;
  }

  moveFile(moveinfo: any): Observable<any> {
    console.log(moveinfo.filename); 
    console.log(moveinfo.destination);
    var url = environment.fileAccessURL + "/moveToModuleDirectory";
    return this.http.post<any>(url,moveinfo);
  }

  uploadFile(blob: any, filename: string): Observable<any> {
    blob.lastModifiedDate = new Date();
    blob.name = filename;
    var file = <File>blob;
    var url = environment.fileAccessURL + "/uploadFile";
    var data: FormData = new FormData();
    data.append('file',file);
    return this.http.post<any>(url,data);
  }


  updateFile(filename: any, data: any){
    this.replaceSlashesWith(filename);
    var url = environment.repoFileUrl+"/"+this.newFilename;
    return this.http.put<any>(url,data, this.options);
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
    var url = environment.repoDirectoriesURL + "&path=" + key;
    return this.http.get<any>(url,this.options);
  }

  getListOfFilesInSubdirectories(module: any): Observable<any> {
    var key = module.trim();
    var directory = environment.repoRootDirectory + key;
    var url = environment.repoDirectoriesURL + "&recursive=true&path=" + key;
    return this.http.get<any>(url,this.options);
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



