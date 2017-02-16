import {Component, OnInit} from '@angular/core';
import {UserService} from '../user-service/user.service';
import {Router, ActivatedRoute} from "@angular/router";
import {AuthenticationService} from "../auth-service/authenticate.service";

@Component({
    selector: 'login-app',
    templateUrl: 'user-login.component.html'
    })
export class UserLoginComponent  implements OnInit {
    loginUser = {email:'',password:''};
    errorMessage:string=null;
    constructor(private userService: UserService, private authenticationService: AuthenticationService, private router: Router){

    }
    ngOnInit() {
        // reset login status
        this.authenticationService.logout();
    }
    tryLoginUser(){

        //this.loading = true;
        this.authenticationService.login(this.loginUser)
                            .subscribe(
                                result => {
                                    if (result === true)
                                        this.router.navigate(['movies']);
                                },
                                error => {
                                    this.errorMessage = 'Username or password is incorrect';
                                    console.log(error);
                                });
        }
}

