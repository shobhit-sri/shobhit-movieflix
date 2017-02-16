import {Component} from '@angular/core';
import {AuthenticationService} from "../auth-service/authenticate.service";
import {Router} from "@angular/router";

@Component({
    selector: 'header-app',
    templateUrl: 'header.component.html'
})
export class HeaderComponent {
    //isSigned:boolean = false;
    constructor(private router: Router, private authenticationService: AuthenticationService){
        //this.isSigned = this.authenticationService.token != null;
    }

    signOut(): void {
        this.authenticationService.logout();
        this.router.navigate(['login']);
    }
}