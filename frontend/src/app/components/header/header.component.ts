import { Component, OnInit } from "@angular/core";
import { FormControl } from "@angular/forms";
import { HeaderMenuItem } from "src/app/types/HeaderMenuItem";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  headerMenu: Array<HeaderMenuItem> = [
    {
      title: 'Dashboard',
      subMenu: [
        { name: 'Project Dashboard', route: '' },
        { name: 'Release Dashboard', route: '' },
      ],
    },
    {
      title: 'Project',
      subMenu: [
        { name: 'Front-end (Fruit App)', route: '' },
        { name: 'Back-end (Reddit-Backend)', route: '' },
        { name: 'divider'},
        { name: 'Release Product (F.B.I)', route: '' },
        { name: 'Indian Railways (I.R.C.T.C)', route: '' },
        { name: 'divider' },
        {name: 'Create Project ...', route: 'create-project'}
    ],
    },
    {
      title: 'Issues',
      subMenu: [
        {name: 'Dashboard', route: ''},
        {name: 'Dashboard', route: ''},
      ]
    },
    {
      title: 'Capture',
      subMenu: [
        {name: 'Dashboard', route: ''},
        {name: 'Dashboard', route: ''},
      ]
    },
    {
      title: 'Boards',
      subMenu: [
        {name: 'Dashboard', route: ''},
        {name: 'Dashboard', route: ''},
      ]
    },
    {
      title: 'Plans',
      subMenu: [
        {name: 'Dashboard', route: ''},
        {name: 'Dashboard', route: ''},
      ]
    },
    {
      title: 'Git',
      subMenu: [
        {name: 'Dashboard', route: ''},
        {name: 'Dashboard', route: ''},
      ]
    }

  ];
  searchControl: FormControl = new FormControl();

  //  In future will come from backend
  searchOptions = [
    {
      heading: 'Issues',
      content: [
        'Issues - 1: [Frontend] Alignment issue',
        'Issue-3 [UI][UX] Responsiveness of dropdown',
        'Issue-4 [Database] Flyway migrations script not working',
        'Issue-5 [Backend] Data Inconsistency issue',
      ],
    },
    {
      heading: 'Projects',
      content: [
        'Front-end (Fruit App)',
        'Back-end (Reddit-Backend)',
        'Release Product (F.B.I)',
        'Indian Railways (I.R.C.T.C)',
      ],
    },
  ];

  _sideNavBar: boolean = false;

  constructor() {}

  ngOnInit(): void {}

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
