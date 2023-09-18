import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  // user: any = {}; // You can define an object for user data
  email: string =""
  password: string=""
  isLoggedIn: boolean = true;
  constructor(private router: Router,private http: HttpClient) {}


onSubmit() {
  console.log(this.email);
  console.log(this.password);

  let bodyData = {
    "email": this.email,
    "password": this.password
  };

  this.http.post("http://localhost:3000/login", bodyData).subscribe((resultData: any) => {
    if (resultData.id) {
      
      // Check if 'id' exists in the response
      console.log("User ID:", resultData.id);
      this.router.navigate(['/viewNotes'], { queryParams: { userId: resultData.id } });
    } else {
      console.error("User ID not found in the response.");
    }
  }, error => {
    console.error('Login failed: ' + error);
    alert("Invalid EmailId or Password");
  });
}

}
