import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {NavbarComponent, TopicsComponent} from './forum';
import {RegisterComponent} from './authentication';


const appRoutes: Routes = [
  {path: '', component: TopicsComponent},
  {path: 'register', component: RegisterComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]})
export class AppRoutingModule {

}
export const routingComponents = [TopicsComponent, NavbarComponent, RegisterComponent]
