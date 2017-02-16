import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MovieService} from "../movie-service/movie.service";
import {UserReviewService} from "../user-review.service/user-review.service";
import {RatingModule} from 'primeng/primeng';

@Component({
    templateUrl: 'movie-create.component.html'
})
export class MovieCreateComponent {

    movie: any = {};

    constructor(private router: Router, private movieService: MovieService) {
        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if(currentUser && currentUser.role!='admin'){
            this.router.navigate(['movies']);
        }
    }

    createNewMovie(): void{
        this.movieService.createMovie(this.movie)
                            .subscribe(
                                movie => {
                                    this.movie = movie;
                                    this.router.navigate(['movies',this.movie.id])
                                },
                                error => console.log(error)
                            );
    }
}