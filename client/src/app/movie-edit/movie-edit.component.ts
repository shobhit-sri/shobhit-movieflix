import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MovieService} from "../movie-service/movie.service";
import {UserReviewService} from "../user-review.service/user-review.service";
import {RatingModule} from 'primeng/primeng';

@Component({
    templateUrl: 'movie-edit.component.html'
})
export class MovieEditComponent {

    movie: any = {};

    constructor(private route: ActivatedRoute, private router: Router, private movieService: MovieService) {

        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        console.log(currentUser);
        if(currentUser && currentUser.role!='admin'){
            this.router.navigate(['movies']);
        }

        this.route.params.subscribe(
            params => {
                movieService.getMovieById(params['id'])
                    .subscribe(
                        movie => this.movie = movie,
                        error => console.log(error)
                    );
            });
    }

    updateMovie(): void{
        this.movieService.updateMovie(this.movie.id, this.movie)
                            .subscribe(
                                response => this.router.navigate(['movies',this.movie.id]),
                                error => console.log(error)
                            );
    }
}