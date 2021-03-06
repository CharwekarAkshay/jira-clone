import { Component, Input, OnChanges, OnInit, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';
import { HeaderMenuItem } from 'src/app/types/HeaderMenuItem';

@Component({
  selector: 'app-side-drawer',
  templateUrl: './side-drawer.component.html',
  styleUrls: ['./side-drawer.component.scss'],
})
export class SideDrawerComponent implements OnInit{
  @Input() isSideNavOpen: boolean = false;
  @Input() menu: Array<HeaderMenuItem> = [];

  constructor() {}

  ngOnInit(): void {}

  checkDivider(checkWith: string): boolean {
    return checkWith === 'divider';
  }
}
