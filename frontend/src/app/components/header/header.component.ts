import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDrawer, MatDrawerContainer } from '@angular/material/sidenav';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  // Will come from API in future
  headerMenu = [
    {
      title: 'Dashboard',
      subMenu: [
        "Project Dashboard",
        "Release Dashboard"
      ]
    },
    {
      title: 'Project',
      subMenu: [
        "Front-end (Fruit App)",
        "Back-end (Reddit-Backend)",
        "divider",
        "Release Product (F.B.I)",
        "Indian Railways (I.R.C.T.C)",
        "divider",
        "Create Project ..."
      ]
    },
    {
      title: 'Issues',
      subMenu: [
        "Dashboard 4",
        "Dashboard 5"
      ]
    },
    {
      title: 'Capture',
      subMenu: [
        "Dashboard 4",
        "Dashboard 5"
      ]
    },
    {
      title: 'Boards',
      subMenu: [
        "Dashboard 4",
        "Dashboard 5"
      ]
    },
    {
      title: 'Plans',
      subMenu: [
        "Dashboard 4",
        "Dashboard 5"
      ]
    },
    {
      title: 'Git',
      subMenu: [
        "Dashboard 4",
        "Dashboard 5"
      ]
    }

  ];

  searchControl: FormControl = new FormControl();

  // In future will come from backend
  searchOptions = [
    {
      heading: 'Issues',
      content: ['Issues - 1: [Frontend] Alignment issue', 'Issue-3 [UI][UX] Responsiveness of dropdown', 'Issue-4 [Database] Flyway migrations script not working', 'Issue-5 [Backend] Data Inconsistency issue'],
    },
    {
      heading: 'Projects',
      content: [
        "Front-end (Fruit App)",
        "Back-end (Reddit-Backend)",
        "Release Product (F.B.I)",
        "Indian Railways (I.R.C.T.C)",
      ],
    }
  ];

  _sideNavBar:boolean = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  checkDivider(checkWith: string): boolean {
    return checkWith === 'divider';
  }

  toggleDrawer() {
    this._sideNavBar = !this._sideNavBar;
  }

  get sideNavBar(): boolean {
    return this._sideNavBar;
  }
}
