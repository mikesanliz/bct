import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BctSharedModule } from 'app/shared';
import {
    FeeComponent,
    FeeDetailComponent,
    FeeUpdateComponent,
    FeeDeletePopupComponent,
    FeeDeleteDialogComponent,
    feeRoute,
    feePopupRoute
} from './';

const ENTITY_STATES = [...feeRoute, ...feePopupRoute];

@NgModule({
    imports: [BctSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [FeeComponent, FeeDetailComponent, FeeUpdateComponent, FeeDeleteDialogComponent, FeeDeletePopupComponent],
    entryComponents: [FeeComponent, FeeUpdateComponent, FeeDeleteDialogComponent, FeeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BctFeeModule {}
