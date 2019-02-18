import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFee } from 'app/shared/model/fee.model';

type EntityResponseType = HttpResponse<IFee>;
type EntityArrayResponseType = HttpResponse<IFee[]>;

@Injectable({ providedIn: 'root' })
export class FeeService {
    public resourceUrl = SERVER_API_URL + 'api/fees';

    constructor(protected http: HttpClient) {}

    create(fee: IFee): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fee);
        return this.http
            .post<IFee>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(fee: IFee): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fee);
        return this.http
            .put<IFee>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFee>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFee[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(fee: IFee): IFee {
        const copy: IFee = Object.assign({}, fee, {
            createdDate: fee.createdDate != null && fee.createdDate.isValid() ? fee.createdDate.format(DATE_FORMAT) : null,
            updatedDate: fee.updatedDate != null && fee.updatedDate.isValid() ? fee.updatedDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
            res.body.updatedDate = res.body.updatedDate != null ? moment(res.body.updatedDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((fee: IFee) => {
                fee.createdDate = fee.createdDate != null ? moment(fee.createdDate) : null;
                fee.updatedDate = fee.updatedDate != null ? moment(fee.updatedDate) : null;
            });
        }
        return res;
    }
}
