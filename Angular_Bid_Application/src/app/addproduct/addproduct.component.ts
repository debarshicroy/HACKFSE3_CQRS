import { formatDate } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { biddercloudALBurl_8081 } from 'src/environments/environment.prod';
import { AlertDialogComponent } from '../alert-dialog/alert-dialog.component';
import { AppService } from '../app.service';


@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})

export class AddproductComponent implements OnInit {
  addProductForm: FormGroup;
  minDate: any;
  maxDate: any;
  showPopUp:boolean = false;
  
  constructor(private formBuilder: FormBuilder,private router:Router,private appService:AppService,private http:HttpClient,public dialog: MatDialog) { 
    const currentYear = new Date().getFullYear();
    //const minDate = new Date().toISOString().slice(0,10);
    this.addProductForm = this.formBuilder.group({
      productCategory:['', [Validators.required,Validators.maxLength(30)]],
      productName: ['', [Validators.required,Validators.minLength(5) ,Validators.maxLength(30)]],
      shortproductdesc: ['', [Validators.required,Validators.maxLength(50)]],
      detailedproductdesc: ['', [Validators.required,Validators.maxLength(500)]],
      price: ['', [Validators.required,Validators.pattern("^[0-9.]*$")]],
      pincode: ['', [Validators.required,Validators.pattern("^[0-9]*$"),Validators.maxLength(6)]],
      bidEndDate: ['', [Validators.required]],
      stateName:['', [Validators.required]],
      firstName: ['', [Validators.required,Validators.minLength(5) ,Validators.maxLength(30)]],
      lastName: ['', [Validators.required,Validators.minLength(3) ,Validators.maxLength(25)]],
      mobileNo:['',[Validators.required,Validators.pattern("^[0-9]*$"),Validators.maxLength(10)]],
      email: ['', [Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]
      ]
  });
  }
  ngOnInit(): void {
    //throw new Error('Method not implemented.');
    this.minDate = new Date().toISOString().slice(0,10);
    console.log("Date heres --->"+this.minDate);
     this.getStates();
     this.appService.currentApprovalStageMessage.subscribe(data =>{
       if(data !== undefined && data !=='' && data !== null){
        console.log(data['bidEndDate']);
       this.addProductForm.controls['productName'].setValue(data['name']);
       this.addProductForm.controls['shortproductdesc'].setValue(data['short_desc']);
       this.addProductForm.controls['detailedproductdesc'].setValue(data['detailed_desc']);
       this.addProductForm.controls['productCategory'].setValue(data['category']);
       this.addProductForm.controls['price'].setValue(data['price']);
       this.addProductForm.controls['bidEndDate'].setValue(formatDate(data['bidEndDate'],'yyyy-MM-dd','en'));
       }
     });
  }

  

  powers = ['Painting', 'Sculptor',
            'Ornament', 'Miscellanous Product'];
  geoResponseObj: any;
  submitted = false;
  reqBody: any ={"name":"","short_desc":"","detailed_desc":"","category":"","price":0,"bidEndDate":new Date()};
  onSubmit() { 
    //this.showPopUp = true;
    let url: any =biddercloudALBurl_8081+'add-product';
    this.reqBody['name']=this.addProductForm.controls['productName'].value;
    this.reqBody['short_desc']=this.addProductForm.controls['shortproductdesc'].value;
    this.reqBody['detailed_desc']=this.addProductForm.controls['detailedproductdesc'].value;
    this.reqBody['category']=this.addProductForm.controls['productCategory'].value;
    this.reqBody['price']=this.addProductForm.controls['price'].value;
    this.reqBody['bidEndDate']= this.addProductForm.controls['bidEndDate'].value;
    this.http.post(url,this.reqBody,{ responseType:'text'}).subscribe(data =>{
      console.log("data"+data);
      this.alertDialog("Product Added successfully");
    },(error)=>{
      console.log("error occured here");
      this.alertDialog("Failed to add the product. Please try after some time");
    });
  }

  keyPress(event: any) {
    const pattern = /[0-9\+\-\ ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
    
  }


  

  

  
  showFormControls(form: any) {
    return form && form.controls['name'] &&
    form.controls['name'].value; // Dr. IQ
  }

  onDateChange(newDate: Date) {
    console.log(newDate);
  }
  get f() { return this.addProductForm.controls; }
  geoNameObj:any;
  statesObj: any[]=[];
    async getStates(){
    this.appService.getStatesAndCities().subscribe(data =>{
      this.geoNameObj =data;
      this.statesObj = this.geoNameObj['geonames'];
      //var self = this;
    });
    console.log("this states "+this.statesObj);
  }

  alertDialog(info: any) {
    this.dialog.open(AlertDialogComponent, {
      data: {
        message: info
      }
    });
  }
  
}
