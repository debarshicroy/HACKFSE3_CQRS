import { Injectable } from "@angular/core";
import { MaterialModule } from "../materialmodule";
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { MatDialog } from "@angular/material/dialog";
import { AlertDialogComponent } from "../alert-dialog/alert-dialog.component";


@Injectable({
    providedIn:'root'
})
export class AuthenticationGuard implements CanActivate{
    constructor(public dialog: MatDialog,private router:Router){}
    authenticated:boolean = false;
    canActivate(){
    if(sessionStorage.getItem('jwtToken') ===undefined || sessionStorage.getItem('jwtToken') === null){
        this.alertDialog('Your are not authenticated to visit this page');
        this.router.navigateByUrl("/login");
    }
    else{
        this.authenticated= true;
    }
    return this.authenticated;
    }

    alertDialog(info: any) {
        this.dialog.open(AlertDialogComponent, {
          data: {
            message: info
          }
        });
      }
}