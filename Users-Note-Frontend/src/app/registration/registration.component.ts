import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  email: string =""
  password: string=""
  confirmPassword: string= '';
  errorMessage = ''; // Variable for error message

  constructor(private http: HttpClient,private router: Router) {}

  onSubmit() {
    // Check if the password and confirm password match
    if (this.password !== this.confirmPassword) {
      this.errorMessage = 'Password and confirm password do not match'; // Set error message
      return;
    }

    // Clear the error message
    this.errorMessage = '';

    let bodyData ={
      "email": this.email,
      "password":this.password
    };

    this.http.post("http://localhost:3000/register",bodyData,{responseType:"text"}).subscribe((resultData:any)=>{
      console.log(resultData);
      // alert("sucess login");
      console.log("Successfull Login");
      this.router.navigate(['']);

    })

    // Send the registration request
    // this.http.post('/api/register', this.user).subscribe(
    //   (response) => {
    //     console.log('Registration successful:', response);
    //     this.router.navigate(['']);
    //   },
    //   (error) => {
    //     console.error('Registration failed:', error);
    //   }
    // );
  }
}
