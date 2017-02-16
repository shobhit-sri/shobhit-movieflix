import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';
import {RouterModule, Routes} from '@angular/router';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {DataTableModule,SharedModule} from 'primeng/primeng';
import {RatingModule} from 'primeng/primeng';

import {AppComponent}  from './app.component';
import {MovieListComponent} from './movie-list/movie-list.component';
import {MovieDetailComponent} from './movie-detail/movie-detail.component';
import {MovieEditComponent} from "./movie-edit/movie-edit.component";
import {MovieCreateComponent} from "./movie-create/movie-create.component";
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {UserRegisterComponent} from './user-register/user-register.component';
import {UserLoginComponent} from "./user-login/user-login.component";

import {MovieService} from './movie-service/movie.service';
import {UserService} from './user-service/user.service';
import {UserReviewService} from "./user-review.service/user-review.service";
import {RequestFilter} from "./request-filter/request-filter";
import {AuthenticationService} from "./auth-service/authenticate.service";

const appRoutes: Routes = [
    {path: 'movies', component: MovieListComponent, canActivate: [RequestFilter]},
    {path: 'movies/:id', component: MovieDetailComponent, canActivate: [RequestFilter]},
    {path: 'movies/edit/:id', component: MovieEditComponent, canActivate: [RequestFilter]},
    {path: 'create', component: MovieCreateComponent, canActivate: [RequestFilter]},
    {path: 'register', component: UserRegisterComponent},
    {path: 'login', component: UserLoginComponent},
    {path: '', component: MovieListComponent, canActivate: [RequestFilter]},
    {path: '**', component: NotFoundComponent}
];

@NgModule({
    imports: [BrowserModule, HttpModule, RouterModule.forRoot(appRoutes), FormsModule, DataTableModule, SharedModule, RatingModule],  /*modules needed by this module (and its components etc.)*/
    declarations: [AppComponent, MovieListComponent, MovieDetailComponent, MovieEditComponent, MovieCreateComponent, NotFoundComponent, HeaderComponent, FooterComponent, UserRegisterComponent, UserLoginComponent], /*List of components etc this module contains. All components et al must be defined here.*/
    providers: [MovieService, UserService, UserReviewService, AuthenticationService, RequestFilter],   /*Gets data from server. Re-usable. Since components only interact with templates and it knows nothing about data source.*/
    bootstrap: [AppComponent]   /*which module(s) to initialize when app starts defined by bootstrap*/
})
export class AppModule {

}

console.log('Running');
