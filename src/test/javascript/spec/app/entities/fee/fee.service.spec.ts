/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { FeeService } from 'app/entities/fee/fee.service';
import { IFee, Fee } from 'app/shared/model/fee.model';

describe('Service Tests', () => {
    describe('Fee Service', () => {
        let injector: TestBed;
        let service: FeeService;
        let httpMock: HttpTestingController;
        let elemDefault: IFee;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(FeeService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Fee(0, 0, 0, 0, 0, 0, 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        createdDate: currentDate.format(DATE_FORMAT),
                        updatedDate: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Fee', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        createdDate: currentDate.format(DATE_FORMAT),
                        updatedDate: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        createdDate: currentDate,
                        updatedDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Fee(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Fee', async () => {
                const returnedFromService = Object.assign(
                    {
                        unitPrice: 1,
                        quantity: 1,
                        subTotal: 1,
                        taxTotal: 1,
                        taxPercentage: 1,
                        description: 'BBBBBB',
                        createdDate: currentDate.format(DATE_FORMAT),
                        updatedDate: currentDate.format(DATE_FORMAT),
                        createdBy: 'BBBBBB',
                        updatedBy: 'BBBBBB',
                        internalNotes: 'BBBBBB',
                        appearanceOrder: 1,
                        costId: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        createdDate: currentDate,
                        updatedDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Fee', async () => {
                const returnedFromService = Object.assign(
                    {
                        unitPrice: 1,
                        quantity: 1,
                        subTotal: 1,
                        taxTotal: 1,
                        taxPercentage: 1,
                        description: 'BBBBBB',
                        createdDate: currentDate.format(DATE_FORMAT),
                        updatedDate: currentDate.format(DATE_FORMAT),
                        createdBy: 'BBBBBB',
                        updatedBy: 'BBBBBB',
                        internalNotes: 'BBBBBB',
                        appearanceOrder: 1,
                        costId: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        createdDate: currentDate,
                        updatedDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Fee', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
