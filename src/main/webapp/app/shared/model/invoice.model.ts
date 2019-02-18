import { Moment } from 'moment';
import { IFee } from 'app/shared/model/fee.model';

export const enum InvoiceStatus {
    Draft = 'Draft',
    Final = 'Final',
    Closed = 'Closed',
    Void = 'Void',
    ACCRUAL = 'ACCRUAL'
}

export interface IInvoice {
    id?: number;
    month?: number;
    year?: number;
    createdDate?: Moment;
    updatedDate?: Moment;
    dueDate?: Moment;
    displayDate?: Moment;
    emailedDate?: Moment;
    emailedToCustomer?: boolean;
    state?: InvoiceStatus;
    createdBy?: string;
    updatedBy?: string;
    externalNotes?: string;
    internalNotes?: string;
    fees?: IFee[];
}

export class Invoice implements IInvoice {
    constructor(
        public id?: number,
        public month?: number,
        public year?: number,
        public createdDate?: Moment,
        public updatedDate?: Moment,
        public dueDate?: Moment,
        public displayDate?: Moment,
        public emailedDate?: Moment,
        public emailedToCustomer?: boolean,
        public state?: InvoiceStatus,
        public createdBy?: string,
        public updatedBy?: string,
        public externalNotes?: string,
        public internalNotes?: string,
        public fees?: IFee[]
    ) {
        this.emailedToCustomer = this.emailedToCustomer || false;
    }
}
