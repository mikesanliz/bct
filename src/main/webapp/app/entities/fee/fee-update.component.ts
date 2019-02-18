import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IFee } from 'app/shared/model/fee.model';
import { FeeService } from './fee.service';
import { IInvoice } from 'app/shared/model/invoice.model';
import { InvoiceService } from 'app/entities/invoice';

@Component({
    selector: 'jhi-fee-update',
    templateUrl: './fee-update.component.html'
})
export class FeeUpdateComponent implements OnInit {
    fee: IFee;
    isSaving: boolean;

    invoices: IInvoice[];
    createdDateDp: any;
    updatedDateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected feeService: FeeService,
        protected invoiceService: InvoiceService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fee }) => {
            this.fee = fee;
        });
        this.invoiceService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IInvoice[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInvoice[]>) => response.body)
            )
            .subscribe((res: IInvoice[]) => (this.invoices = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.fee.id !== undefined) {
            this.subscribeToSaveResponse(this.feeService.update(this.fee));
        } else {
            this.subscribeToSaveResponse(this.feeService.create(this.fee));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFee>>) {
        result.subscribe((res: HttpResponse<IFee>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackInvoiceById(index: number, item: IInvoice) {
        return item.id;
    }
}
