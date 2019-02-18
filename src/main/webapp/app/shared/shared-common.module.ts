import { NgModule } from '@angular/core';

import { BctSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [BctSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [BctSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class BctSharedCommonModule {}
