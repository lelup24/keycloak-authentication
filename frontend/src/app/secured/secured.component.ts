import {Component, inject, OnInit} from '@angular/core';
import {AuthService} from "../authentication/auth.service";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-secured',
  templateUrl: './secured.component.html',
  styleUrls: ['./secured.component.scss']
})
export class SecuredComponent implements OnInit {
  authService = inject(AuthService);
  http = inject(HttpClient);

  logout() {
    this.authService.logout()
  }

  get username(): string {
    return this.authService.getUsername();
  }

  ngOnInit(): void {
    this.http.get<string>('/api/secured').subscribe(console.log)
  }
}
