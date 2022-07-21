import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(private formBuilder: FormBuilder, private router: Router) {
    // redirect to home if already logged in
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string = '';
  password: string = '';
  userName: string = '';
  ngOnInit() {
    // get return url from route parameters or default to '/'
    //this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }
  get f() {
    return this.loginForm.controls;
  }
  onSubmit() {
    this.submitted = true;

    // reset alerts on submit
    //this.alertService.clear();

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    } else {
      this.loading = true;
      if (this.password === 'buyer' && this.userName === 'buyer') {
        this.router.navigateByUrl('/home');
      }
    }
  }
}
