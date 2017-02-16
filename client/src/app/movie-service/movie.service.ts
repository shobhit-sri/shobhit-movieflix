import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import {AuthenticationService} from "../auth-service/authenticate.service";

@Injectable()
export class MovieService {
    readonly requestUrl: string;
    constructor(private http: Http, private authenticationService: AuthenticationService) {
        this.requestUrl = 'http://localhost:8080/movieflix/api/movies';
    }

    getMovies(): Observable<any[]> {
        let headers = new Headers();
        headers.append('Authorization', 'Basic '+this.authenticationService.token);
        let options = new RequestOptions({ headers: headers });
        return this.http.get(this.requestUrl, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    getMoviesByType(type: string): Observable<any[]> {
        let headers = new Headers({ 'Authorization': 'Bearer ' + this.authenticationService.token });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(`${this.requestUrl}/type/${type}`, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    getTopMoviesByType(type: string): Observable<any[]> {
        let headers = new Headers({ 'Authorization': 'Bearer ' + this.authenticationService.token });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(`${this.requestUrl}/top/${type}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    searchMovieByKeyword(keyword: string): Observable<any[]> {
        let headers = new Headers({ 'Authorization': 'Bearer ' + this.authenticationService.token });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(`${this.requestUrl}/search/${keyword}`, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    getMovieById(id: string): Observable<any[]> {
        let headers = new Headers({ 'Authorization': 'Bearer ' + this.authenticationService.token });
        let options = new RequestOptions({ headers: headers });
        return this.http.get(`${this.requestUrl}/${id}`, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    createMovie(movie: any): Observable<any[]> {
        let headers = new Headers({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + this.authenticationService.token});
        let options = new RequestOptions({headers: headers});
        return this.http.post(this.requestUrl, movie, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    updateMovie(id: string, movie: any): Observable<any[]> {
        let headers = new Headers({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + this.authenticationService.token});
        let options = new RequestOptions({headers: headers});
        return this.http.put(`${this.requestUrl}/${id}`, movie, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    deleteMovie(id: string, movie: any): Observable<any[]> {
        let headers = new Headers({'Content-Type': 'application/json', 'Authorization': 'Bearer ' + this.authenticationService.token});
        let options = new RequestOptions({headers: headers});
        return this.http.delete(`${this.requestUrl}/${id}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }
}

