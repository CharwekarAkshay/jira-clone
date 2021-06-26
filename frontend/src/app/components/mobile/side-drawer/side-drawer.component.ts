import { Component, Input, OnChanges, OnInit, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';

@Component({
  selector: 'app-side-drawer',
  templateUrl: './side-drawer.component.html',
  styleUrls: ['./side-drawer.component.scss'],
})
export class SideDrawerComponent implements OnInit, OnChanges {
  @Input() isSideNavOpen: boolean = false;
  @Input() menu: Array<any> = [];
  @ViewChild('drawer') sideDrawer?: MatDrawer;

  constructor() {}

  ngOnInit(): void {}

  ngOnChanges(): void {
    if (this.isSideNavOpen) {
      this.sideDrawer?.open();
    } else {
      this.sideDrawer?.close();
    }
  }

  checkDivider(checkWith: string): boolean {
    return checkWith === 'divider';
  }
}
