import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Guest } from '../dto/guest';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  private BASE_URL:string = "http://localhost:8080/api/guest/";

  constructor(private http: HttpClient ) { }

  public get(endpoint:string): Observable<Guest[]> {
    return this.http.get<Guest[]>(this.BASE_URL + endpoint)
  }

  public add(endpoint:string, body: Guest): Observable<Boolean> {
    return this.http.post<Boolean>(this.BASE_URL + endpoint, body);
  }

  public put(endpoint:string, confirmedList: Guest[]): Observable<Boolean> {
   return this.http.put<Boolean>(this.BASE_URL + endpoint, confirmedList);
  }

  public delete(endpoint:string, id:Number): Observable<Boolean> {
    return this.http.delete<Boolean>(this.BASE_URL + endpoint.concat("/") + id);
  }
}
