import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { ViewNotesComponent } from './view-notes/view-notes.component';
import { AddNoteComponent } from './add-note/add-note.component';
import { ViewSingleNoteComponent } from './view-single-note/view-single-note.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'viewNotes', component: ViewNotesComponent},
  { path: 'addNote', component:AddNoteComponent },
  { path: 'viewSingleNote', component:ViewSingleNoteComponent },
  { path: '**', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
