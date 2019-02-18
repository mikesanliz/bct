import { Moment } from 'moment';

export interface IFee {
    id?: number;
    unitPrice?: number;
    quantity?: number;
    subTotal?: number;
    taxTotal?: number;
    taxPercentage?: number;
    description?: string;
    createdDate?: Moment;
    updatedDate?: Moment;
    createdBy?: string;
    updatedBy?: string;
    internalNotes?: string;
    appearanceOrder?: number;
    costId?: string;
    invoiceId?: number;
}

export class Fee implements IFee {
    constructor(
        public id?: number,
        public unitPrice?: number,
        public quantity?: number,
        public subTotal?: number,
        public taxTotal?: number,
        public taxPercentage?: number,
        public description?: string,
        public createdDate?: Moment,
        public updatedDate?: Moment,
        public createdBy?: string,
        public updatedBy?: string,
        public internalNotes?: string,
        public appearanceOrder?: number,
        public costId?: string,
        public invoiceId?: number
    ) {}
}
