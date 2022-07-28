import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css'],
})
export class TopBarComponent implements OnInit {
  userName:string='';
  constructor(private router:Router,private appService:AppService) {}
  enable: boolean = false;
  ngOnInit() {
    this.enable = false;
    this.appService.infoMessage.subscribe(data => {
      console.log('User Name --->'+data);
      this.userName =data;
    })
    console.log('Inside the top bar service --->'+this.userName);
  }
  logout(){
    sessionStorage.removeItem('jwtToken');
    this.router.navigateByUrl("/login");
  }
}

/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/
