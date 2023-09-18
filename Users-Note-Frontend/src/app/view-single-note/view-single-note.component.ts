import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';



@Component({
  selector: 'app-view-single-note',
  templateUrl: './view-single-note.component.html',
  styleUrls: ['./view-single-note.component.css']
})
export class ViewSingleNoteComponent {
  content: string | null = null;
  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.content = params['myContent'];
    });
  }

  back() {
    this.router.navigate(['/viewNotes']);
  }

}
