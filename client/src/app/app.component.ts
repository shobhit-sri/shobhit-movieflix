import {Component} from '@angular/core';

@Component({
    selector: 'plunker-app',
    template: `<header-app></header-app>
   <router-outlet></router-outlet>
   <footer-app></footer-app>
  `
})
export class AppComponent {
}
