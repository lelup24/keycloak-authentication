import {Component, Input} from "@angular/core";
import {MatCardModule} from "@angular/material/card";
import {RouterLink} from "@angular/router";
import {NgIf} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";

export class Kachel {
  titel: string = '';
  route: string | any[] = [];
  iconName: string = '';
}

@Component({
  standalone: true,
  imports: [
    MatCardModule,
    MatIconModule,
    RouterLink,
    NgIf
  ],
  selector: 'app-kachel',
  templateUrl: 'kachel.component.html',
  styleUrls: ['kachel.component.scss']
})
export class KachelComponent {

  @Input() kachel: Kachel = new Kachel();
  @Input() asButton: boolean = false;

}
