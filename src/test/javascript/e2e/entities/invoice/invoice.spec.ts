/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { InvoiceComponentsPage, InvoiceDeleteDialog, InvoiceUpdatePage } from './invoice.page-object';

const expect = chai.expect;

describe('Invoice e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let invoiceUpdatePage: InvoiceUpdatePage;
    let invoiceComponentsPage: InvoiceComponentsPage;
    let invoiceDeleteDialog: InvoiceDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Invoices', async () => {
        await navBarPage.goToEntity('invoice');
        invoiceComponentsPage = new InvoiceComponentsPage();
        await browser.wait(ec.visibilityOf(invoiceComponentsPage.title), 5000);
        expect(await invoiceComponentsPage.getTitle()).to.eq('Invoices');
    });

    it('should load create Invoice page', async () => {
        await invoiceComponentsPage.clickOnCreateButton();
        invoiceUpdatePage = new InvoiceUpdatePage();
        expect(await invoiceUpdatePage.getPageTitle()).to.eq('Create or edit a Invoice');
        await invoiceUpdatePage.cancel();
    });

    it('should create and save Invoices', async () => {
        const nbButtonsBeforeCreate = await invoiceComponentsPage.countDeleteButtons();

        await invoiceComponentsPage.clickOnCreateButton();
        await promise.all([
            invoiceUpdatePage.setMonthInput('5'),
            invoiceUpdatePage.setYearInput('5'),
            invoiceUpdatePage.setCreatedDateInput('2000-12-31'),
            invoiceUpdatePage.setUpdatedDateInput('2000-12-31'),
            invoiceUpdatePage.setDueDateInput('2000-12-31'),
            invoiceUpdatePage.setDisplayDateInput('2000-12-31'),
            invoiceUpdatePage.setEmailedDateInput('2000-12-31'),
            invoiceUpdatePage.stateSelectLastOption(),
            invoiceUpdatePage.setCreatedByInput('createdBy'),
            invoiceUpdatePage.setUpdatedByInput('updatedBy'),
            invoiceUpdatePage.setExternalNotesInput('externalNotes'),
            invoiceUpdatePage.setInternalNotesInput('internalNotes')
        ]);
        expect(await invoiceUpdatePage.getMonthInput()).to.eq('5');
        expect(await invoiceUpdatePage.getYearInput()).to.eq('5');
        expect(await invoiceUpdatePage.getCreatedDateInput()).to.eq('2000-12-31');
        expect(await invoiceUpdatePage.getUpdatedDateInput()).to.eq('2000-12-31');
        expect(await invoiceUpdatePage.getDueDateInput()).to.eq('2000-12-31');
        expect(await invoiceUpdatePage.getDisplayDateInput()).to.eq('2000-12-31');
        expect(await invoiceUpdatePage.getEmailedDateInput()).to.eq('2000-12-31');
        const selectedEmailedToCustomer = invoiceUpdatePage.getEmailedToCustomerInput();
        if (await selectedEmailedToCustomer.isSelected()) {
            await invoiceUpdatePage.getEmailedToCustomerInput().click();
            expect(await invoiceUpdatePage.getEmailedToCustomerInput().isSelected()).to.be.false;
        } else {
            await invoiceUpdatePage.getEmailedToCustomerInput().click();
            expect(await invoiceUpdatePage.getEmailedToCustomerInput().isSelected()).to.be.true;
        }
        expect(await invoiceUpdatePage.getCreatedByInput()).to.eq('createdBy');
        expect(await invoiceUpdatePage.getUpdatedByInput()).to.eq('updatedBy');
        expect(await invoiceUpdatePage.getExternalNotesInput()).to.eq('externalNotes');
        expect(await invoiceUpdatePage.getInternalNotesInput()).to.eq('internalNotes');
        await invoiceUpdatePage.save();
        expect(await invoiceUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await invoiceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Invoice', async () => {
        const nbButtonsBeforeDelete = await invoiceComponentsPage.countDeleteButtons();
        await invoiceComponentsPage.clickOnLastDeleteButton();

        invoiceDeleteDialog = new InvoiceDeleteDialog();
        expect(await invoiceDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Invoice?');
        await invoiceDeleteDialog.clickOnConfirmButton();

        expect(await invoiceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
