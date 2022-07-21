import { Component, OnInit } from '@angular/core';
import { VERSION } from '@angular/material/core';
//import { MatSnackBar } from '@angular/material';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{ 
  version = VERSION;
  constructor(private dialog: MatDialog,
    private snackBar: MatSnackBar,private router:Router){

  }
  ngOnInit(){
    let response = window.location.toString();
    response =response.substr(response.indexOf('?')+1);
    const params = response.split('&');
    let authCodeparam = params.find((p)=>p.indexOf('code')>-1);
    console.log("authCodeparam-------------->"+authCodeparam);
    
    if(authCodeparam == null ||authCodeparam ===undefined){
    this.assignUrl();
    }
    else{
      let codefromcog =authCodeparam.replace('code=','');
      console.log("response---------->"+codefromcog);
      this.router.navigateByUrl('/home');
    }
  }
  assignUrl(){
    let url='https://eauction.auth.us-east-1.amazoncognito.com/login?client_id=60jci95dmqj5ruaql4qdp9lb3r&response_type=code&scope=aws.cognito.signin.user.admin&redirect_uri=http://localhost:8083/home';
    window.location.assign(url);
  }
  
}



/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/