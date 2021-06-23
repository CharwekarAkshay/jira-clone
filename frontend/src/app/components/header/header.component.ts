import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

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

  constructor() { }

  ngOnInit(): void {
  }

  checkDivider(checkWith: string): boolean {
      return checkWith === 'divider';
  }

}
