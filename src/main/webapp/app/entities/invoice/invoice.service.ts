import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInvoice } from 'app/shared/model/invoice.model';

type EntityResponseType = HttpResponse<IInvoice>;
type EntityArrayResponseType = HttpResponse<IInvoice[]>;

@Injectable({ providedIn: 'root' })
export class InvoiceService {
    public resourceUrl = SERVER_API_URL + 'api/invoices';

    constructor(protected http: HttpClient) {}

    create(invoice: IInvoice): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(invoice);
        return this.http
            .post<IInvoice>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(invoice: IInvoice): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(invoice);
        return this.http
            .put<IInvoice>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IInvoice>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInvoice[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(invoice: IInvoice): IInvoice {
        const copy: IInvoice = Object.assign({}, invoice, {
            createdDate: invoice.createdDate != null && invoice.createdDate.isValid() ? invoice.createdDate.format(DATE_FORMAT) : null,
            updatedDate: invoice.updatedDate != null && invoice.updatedDate.isValid() ? invoice.updatedDate.format(DATE_FORMAT) : null,
            dueDate: invoice.dueDate != null && invoice.dueDate.isValid() ? invoice.dueDate.format(DATE_FORMAT) : null,
            displayDate: invoice.displayDate != null && invoice.displayDate.isValid() ? invoice.displayDate.format(DATE_FORMAT) : null,
            emailedDate: invoice.emailedDate != null && invoice.emailedDate.isValid() ? invoice.emailedDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
            res.body.updatedDate = res.body.updatedDate != null ? moment(res.body.updatedDate) : null;
            res.body.dueDate = res.body.dueDate != null ? moment(res.body.dueDate) : null;
            res.body.displayDate = res.body.displayDate != null ? moment(res.body.displayDate) : null;
            res.body.emailedDate = res.body.emailedDate != null ? moment(res.body.emailedDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((invoice: IInvoice) => {
                invoice.createdDate = invoice.createdDate != null ? moment(invoice.createdDate) : null;
                invoice.updatedDate = invoice.updatedDate != null ? moment(invoice.updatedDate) : null;
                invoice.dueDate = invoice.dueDate != null ? moment(invoice.dueDate) : null;
                invoice.displayDate = invoice.displayDate != null ? moment(invoice.displayDate) : null;
                invoice.emailedDate = invoice.emailedDate != null ? moment(invoice.emailedDate) : null;
            });
        }
        return res;
    }
}
