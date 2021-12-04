import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Module } from '../models/module.model';
import {environment} from '../../environments/environment'

// Change/Add urls in src/environments/environment.ts file:
//export const environment = {
//  production: false,
//  backendURL: 'http://localhost:8081/api/modules',
//  ck001URL: 'http://localhost:8888/ck001/run'
//};

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

  run(id: any, data: any): Observable<string> {
    return this.http.get(`${environment.ck001URL}/run`, {responseType: 'text'});
  }

  runWithInputs(data: any): Observable<any> {
    return this.http.post<String>(`${environment.ck001URL}/runpost`, data);
  }

  help(id: any, data: any): Observable<any> {
    return this.http.get(`${environment.ck001URL}/help`);
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



