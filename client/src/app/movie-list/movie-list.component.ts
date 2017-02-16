import {Component} from '@angular/core';
import {MovieService} from "../movie-service/movie.service";
import {AuthenticationService} from "../auth-service/authenticate.service";

@Component({
    templateUrl: 'movie-list.component.html',
})
export class MovieListComponent{

    movieList: any = [];
    isAdmin:boolean = false;

    constructor(private movieService: MovieService) {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if(currentUser && currentUser.role=='admin'){
            this.isAdmin = true;
        }
        movieService.getMovies()
            .subscribe(
                movies => this.movieList = movies,
                error => console.log(error)
            );
    }

    showMovies(){
        this.movieService.getMoviesByType('movie')
            .subscribe(
                movies => this.movieList = movies,
                error => console.log(error)
            );
    }

    showSeries(){
        this.movieService.getMoviesByType('series')
            .subscribe(
                movies => this.movieList = movies,
                error => console.log(error)
            );
    }

    showTopMovies(){
        this.movieService.getTopMoviesByType('movie')
            .subscribe(
                movies => this.movieList = movies,
                error => console.log(error)
            );
    }

    showTopSeries(){
        this.movieService.getTopMoviesByType('series')
            .subscribe(
                movies => this.movieList = movies,
                error => console.log(error)
            );
    }

    searchByKeyword(val: string){
        this.movieService.searchMovieByKeyword(val)
            .subscribe(
                movies => this.movieList = movies,
                error => console.log(error)
            );
    }
}