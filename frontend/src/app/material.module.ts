import { NgModule } from '@angular/core';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { DomSanitizer } from '@angular/platform-browser';

@NgModule({
  exports: [
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatDividerModule,
    MatButtonModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatSidenavModule,
  ],
})
export class MaterialModule {
  customIcons: Array<[string, string]> = [
    ['app_switcher', 'assets/icons/app_switcher.svg'],
    ['export', 'assets/icons/export.svg'],
  ];

  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    this.customIcons.forEach(([iconName, iconPath]) => {
      iconRegistry.addSvgIcon(
        iconName,
        sanitizer.bypassSecurityTrustResourceUrl(iconPath)
      );
    });
  }
}
