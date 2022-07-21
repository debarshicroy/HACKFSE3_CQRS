import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService{
    constructor(
        private http: HttpClient
      ) {}
      private editProductInfo = new BehaviorSubject<any>(null);
      currentApprovalStageMessage = this.editProductInfo.asObservable();
      updateApprovalMessage(message: string) {
        this.editProductInfo.next(message)
        }
      getStatesAndCities() {
        return this.http
          .get(
            `https://secure.geonames.org/childrenJSON?geonameId=1269750&lang=en-IN,hi,bn,te,mr,ta,ur,gu,kn,ml,or,pa,as,bh,sat,ks,ne,sd,kok,doi,mni,sit,sa,fr,lus,inc&username=ahii`
          )
          ;
      }

      

}
