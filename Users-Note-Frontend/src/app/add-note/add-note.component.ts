import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-note',
  templateUrl: './add-note.component.html',
  styleUrls: ['./add-note.component.css']
})
export class AddNoteComponent implements OnInit{
  
  errorMessage: string = "";
  content: string =""
  userId: string | null = null;
  constructor(private route: ActivatedRoute,private http: HttpClient,private router: Router) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.userId = params['userId'];
      console.log(this.userId);
    });
  }

  submit() {
    // Check if content is null or empty
    if (this.content === null || this.content.trim() === '') {
      console.error('Content is required.');
      return; 
    }

  // Define the regex pattern to allow @, ;, &, *, +, -, alphabet, and digits
  const regexPattern = /^[@;&*+\-\w\d\s]+$/;

  // Check if the content matches the pattern
  if (!this.content.match(regexPattern)) {
    this.errorMessage = 'Invalid characters in content.'; // Set error message
    return;
  }

  // If content is valid, clear any previous error message
  this.errorMessage = '';
  
    // If content is not null or empty, proceed with form submission
    let bodyData = {
      "content": this.content,
      "userId": this.userId
    };
  
    this.http.post("http://localhost:3000/addNote", bodyData, { responseType: "text" }).subscribe((resultData: any) => {
      console.log(resultData);
      console.log("Successfully Added");
      this.router.navigate(['viewNotes'], { queryParams: { userId: this.userId } });
    });
  }
  
  
}
