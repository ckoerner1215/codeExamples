import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Module } from '../models/module.model';
import {environment} from '../../environments/environment'
import {ports} from '../../environments/environment'

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

  run(key: any): Observable<string> {
    console.log("key: " + key);
    var url = environment.baseURL + ports.get(key) + "/" + key + "/run";
    console.log("url: " + url);
    return this.http.get(url, {responseType: 'text'});
  }

  runWithInputs(data: any): Observable<any> {
    console.log("runWithInputs    "+data);
    var key = data.name;
    console.log("key: " + key);
    var url = environment.baseURL + ports.get(key) + "/" + key + "/run";
    console.log("url: " + url);
    console.log("data: " + data);
    return this.http.post<String>(url, data);
  }

  help(name: any): Observable<any> {
    console.log("in help in ModuleService");
    console.log(name);
    var key = name;
    var url = environment.baseURL + ports.get(key) + "/" + key + "/help";
    console.log("url:" + url);
    console.log("**************************");
    return this.http.get(url);
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



