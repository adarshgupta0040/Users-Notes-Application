// view-notes.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-view-notes',
  templateUrl: './view-notes.component.html',
  styleUrls: ['./view-notes.component.css']
})
export class ViewNotesComponent implements OnInit {
  userId: string | null = null;
  notes: any[] = [];

  constructor(private route: ActivatedRoute,private http: HttpClient,private router: Router) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.userId = params['userId'];
      this.fetchRecentNotes();
    });
  }

  fetchRecentNotes() {
    this.http.get(`http://localhost:3000/recent?userId=${this.userId}`).subscribe(
      (data: any) => {
        this.notes = data;
        console.log(this.notes);
      },
      error => {
        console.error('Error fetching recent notes: ' + error);
      }
    );
  }

  delete(id:any,userId:any){

    let bodyData = {
      "userId": userId,
      "id": id
    };
    this.http.post("http://localhost:3000/delete",bodyData,{responseType:"text"}).subscribe((resultData:any)=>{
      console.log(resultData);
      // alert("sucess login");
      console.log("Deleted");
      this.fetchRecentNotes();

    })
  }

  addNote(){
    this.router.navigate(['/addNote'], { queryParams: { userId: this.userId } });
  }

  viewNote(content :any){
    this.router.navigate(['/viewSingleNote'], { queryParams: { myContent: content } });
  }

logout(){
  this.router.navigate(['']);
}

  
}
