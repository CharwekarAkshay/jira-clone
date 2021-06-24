import { NgModule } from "@angular/core";
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { DomSanitizer } from "@angular/platform-browser";

@NgModule({
    exports: [
        MatToolbarModule,
        MatIconModule,
        MatMenuModule,
        MatDividerModule,
    ]
})
export class MaterialModule {
    customIcons: Array<[string, string]> = [
        ["app_switcher", "assets/icons/app_switcher.svg"]
    ];

    constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
        this.customIcons.forEach(([iconName, iconPath]) => {
            iconRegistry.addSvgIcon(
                iconName,
                sanitizer.bypassSecurityTrustResourceUrl(iconPath),
            );
        });
    }

}