import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

@Injectable()
export class UserReviewService {
    readonly requestUrl: string;
    constructor(private http: Http) {
        this.requestUrl = 'http://localhost:8080/movieflix/api/reviews';
    }

    getUserReviewsByMovie(movieId:string): Observable<any[]> {
        return this.http.get(`${this.requestUrl}/movie/${movieId}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    getAverageUserRating(id: string): Observable<any[]> {
        return this.http.get(`${this.requestUrl}/rating/${id}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    getUserByEmail(email: string): Observable<any[]> {
        return this.http.get(`${this.requestUrl}/email/${email}`)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }

    createReview(review: any): Observable<any[]> {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        return this.http.post(this.requestUrl, review, options)
            .map(response => response.json())
            .catch(error => Observable.throw(error.statusText));
    }
}

