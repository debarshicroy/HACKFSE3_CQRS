import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  
  constructor(
    private http: HttpClient
  ) { }
  private editProductInfo = new BehaviorSubject<any>(null);
  currentApprovalStageMessage = this.editProductInfo.asObservable();
  updateApprovalMessage(message: string) {
    this.editProductInfo.next(message)
  }

  private userName = new BehaviorSubject<any>(null);
  infoMessage = this.userName.asObservable();
  userInfoDetails(message: string) {
    this.userName.next(message)
  }
  getStatesAndCities() {
    return this.http
      .get(
        `https://secure.geonames.org/childrenJSON?geonameId=1269750&lang=en-IN,hi,bn,te,mr,ta,ur,gu,kn,ml,or,pa,as,bh,sat,ks,ne,sd,kok,doi,mni,sit,sa,fr,lus,inc&username=ahii`
      )
      ;
  }

  assignUrl() {
    //let url = 'https://codehackfseverify.auth.us-east-1.amazoncognito.com/login?client_id=11msjde8bjnu12f4chriuidrju&response_type=code&scope=aws.cognito.signin.user.admin+email+openid+phone+profile&redirect_uri=http://localhost:4200/home';
    let url = 'https://eauction1.auth.us-east-1.amazoncognito.com/login?client_id=5o0a85us2mgotd14k3pmfbd6ns&response_type=code&scope=aws.cognito.signin.user.admin+email+openid+phone+profile&redirect_uri=https://d11363ooaqsnz3.cloudfront.net/';
    window.location.assign(url);
  }

  getExchangeCodeForTokens(authorizationcode: string){
    //const client_id ='11msjde8bjnu12f4chriuidrju';
    //const domain ='codehackfseverify.auth.us-east-1.amazoncognito.com'
    //const url = 'https://codehackfseverify.auth.us-east-1.amazoncognito.com/oauth2/token';
    const url = 'https://eauction1.auth.us-east-1.amazoncognito.com/oauth2/token';
    const param = new HttpParams({
      fromObject:{
        grant_type:'authorization_code',
        client_id: '1qr4pobuop3it7jht1m3fdji7e',
        code: authorizationcode,
        redirect_uri:'http://localhost:4200/home'
      },
    });
    const httpOptions ={
      headers: new HttpHeaders({
        'Content-Type':'application/x-www-form-urlencoded',
        Authorization:
          'Basic NW8wYTg1dXMybWdvdGQxNGszcG1mYmQ2bnM6MWlnN2RrYjZoaGo5aW5lN2xmZ29iZWUwNDRzdTRsNWc1aGRobGFidTZvbWtwNGg0bXMxYw=='
      }),
    };
    return this.http.post(url,param,httpOptions);
  }
  
}
