import { NgModule } from "@angular/core";
import {MatIconModule} from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';

@NgModule({
    exports: [
        MatToolbarModule,
        MatIconModule,
        MatMenuModule,
    ]
})
export class MaterialModule { }