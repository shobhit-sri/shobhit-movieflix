import {Component} from '@angular/core';
import {UserService} from '../user-service/user.service';
import {Router} from "@angular/router";

@Component({
    selector: 'register-app',
    templateUrl: 'user-register.component.html'
})
export class UserRegisterComponent {
    user = {};
    constructor(private userService: UserService, private router: Router){}

    createUser(){
        this.userService.createUser(this.user).subscribe(error => console.log(error));
        this.router.navigateByUrl('movies');
    }
}

