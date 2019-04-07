import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule , routingComponents} from './app-routing.module';
//
import {AppComponent} from './app.component';
// import {NavbarComponent} from './forum';
// import {RegisterComponent} from './authentication';
// import {TopicsComponent} from './forum';

@NgModule({
  declarations: [
    AppComponent,
    routingComponents
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  exports: [
    AppComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
