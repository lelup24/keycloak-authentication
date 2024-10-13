import {Component, inject, OnInit} from '@angular/core';
import {DomSanitizer} from "@angular/platform-browser";
import {AuthService} from "../authentication/auth.service";
import {MatIconRegistry} from "@angular/material/icon";
import {Kachel} from "../common/kachel/kachel.component";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  http = inject(HttpClient);

  kachel: Kachel = new Kachel();

  constructor(private authService: AuthService,
              private sanitizer: DomSanitizer,
              private registry: MatIconRegistry) {
    this.kachel.iconName = 'login-variant';
    this.registry.addSvgIcon('login-variant', sanitizer.bypassSecurityTrustResourceUrl('./assets/icons/login-variant.svg'))
  }

  login(): void {
    this.authService.login();
  }

  ngOnInit(): void {
    this.http.get<string>('/api/secured').subscribe(console.log)
  }

}
