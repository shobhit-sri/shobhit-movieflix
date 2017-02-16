import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MovieService} from "../movie-service/movie.service";
import {UserReviewService} from "../user-review.service/user-review.service";
import {RatingModule} from 'primeng/primeng';

@Component({
    templateUrl: 'movie-detail.component.html'
})
export class MovieDetailComponent {

    movie: any = {};
    reviews: any[] = [];
    avgUserRating: any;
    newReview:any = {};
    hasReviews:boolean=false;
    isAdmin:boolean = false;

    constructor(private route: ActivatedRoute, private router: Router, private movieService: MovieService, private userReviewService: UserReviewService) {

        var currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if(currentUser && currentUser.role=='admin'){
            this.isAdmin = true;
        }

        this.route.params.subscribe(
            params => {
                movieService.getMovieById(params['id'])
                    .subscribe(
                        movie => this.movie = movie,
                        error => console.log(error)
                    );
            });

        this.route.params.subscribe(
            params => {
                userReviewService.getUserReviewsByMovie(params['id'])
                    .subscribe(
                        reviews => {
                            this.reviews = reviews;
                            this.hasReviews = this.reviews.length > 0;
                        },
                        error => console.log(error)
                    );
            });

        this.route.params.subscribe(
            params => {
                userReviewService.getAverageUserRating(params['id'])
                    .subscribe(
                        rating => this.avgUserRating = rating,
                        error => {if(error) console.log(error)}
                    );
            });
    }

    addReview(): void{
        if(this.newReview.userComment.trim().length > 0) {
            this.newReview.movie = this.movie;
            this.newReview.user = JSON.parse(localStorage.getItem('currentUser'));
            this.userReviewService.createReview(this.newReview)
                .subscribe(
                    response => this.router.navigate(['movies', this.movie.id]),
                    error => console.log(error)
                );
            location.reload();  //this.router.navigate(['movies', this.movie.id]); /*not working*/
        }

    }

    deleteMovie(): void {
        this.movieService.deleteMovie(this.movie.id, this.movie)
                            .subscribe(
                                data => this.router.navigate(['']),
                                error => console.log(error)
                            );

    }
}