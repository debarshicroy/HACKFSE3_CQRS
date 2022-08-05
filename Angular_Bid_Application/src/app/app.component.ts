import { Component, OnInit } from '@angular/core';
import { VERSION } from '@angular/material/core';
//import { MatSnackBar } from '@angular/material';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Route, Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { AppService } from './app.service';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{ 
  version = VERSION;
  constructor(private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private appService: AppService,
    private router:Router){

  }
  tokens: any ={
    "id_token": "",
    "refresh_token": "",
    "access_token":"",
    "expires_in": 0,
    "token_type": "Bearer"
  };
  cognitoResponseObj:any='';
  userName:string='';
  ngOnInit(){
    let response = window.location.toString();
    response =response.substr(response.indexOf('?')+1);
    const params = response.split('&');
    let authCodeparam = params.find((p)=>p.indexOf('code')>-1);
    console.log("authCodeparam-------------->"+authCodeparam);

    if(authCodeparam == null ||authCodeparam ===undefined){
    this.appService.assignUrl();
    }
    else{
      let codefromcog =authCodeparam.replace('code=','');
      console.log("response---------->"+codefromcog);
      sessionStorage.setItem('jwtToken',codefromcog);
      this.appService.getExchangeCodeForTokens(codefromcog).subscribe(data =>{
        this.tokens =data;
        console.log('access token -->'+this.tokens['access_token']);
        sessionStorage.setItem('accessToken',this.tokens['access_token']);
        this.cognitoResponseObj = jwtDecode(this.tokens['access_token']);
        this.userName = this.cognitoResponseObj['username'];
        this.appService.userInfoDetails(this.userName);
        sessionStorage.setItem('jwtexpiryTime',this.tokens['exp']);

        if(this.cognitoResponseObj['cognito:groups']){
          sessionStorage.setItem('userrole',this.cognitoResponseObj['cognito:groups'][0]);
          if(this.cognitoResponseObj['cognito:groups'][0] === 'buyer'){
            this.router.navigateByUrl("/buyer");
          }else{
            this.router.navigateByUrl("/seller");
          }
        }
      });
      this.router.navigateByUrl("/home");
      console.log("jwtToken in app component---------->"+sessionStorage.getItem('jwtToken'));
    }
  }
  
  
}



/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/