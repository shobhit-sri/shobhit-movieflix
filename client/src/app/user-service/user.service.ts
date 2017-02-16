import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

@Injectable()
export class UserService {
    readonly requestUrl: string;
    constructor(private http: Http) {
        this.requestUrl = 'http://localhost:8080/movieflix/api/users';
    }

    getUsers(): Observable<any[]> {
        return this.http.get(this.requestUrl)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    getUserById(id: string): Observable<any[]> {
        return this.http.get(`${this.requestUrl}/${id}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    getUserByEmail(email: string): Observable<any[]> {
        return this.http.get(`${this.requestUrl}/email/${email}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    createUser(user: any): Observable<any[]> {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        return this.http.post(`${this.requestUrl}/create`, user, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }
}

