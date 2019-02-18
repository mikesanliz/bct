/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FeeComponentsPage, FeeDeleteDialog, FeeUpdatePage } from './fee.page-object';

const expect = chai.expect;

describe('Fee e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let feeUpdatePage: FeeUpdatePage;
    let feeComponentsPage: FeeComponentsPage;
    let feeDeleteDialog: FeeDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Fees', async () => {
        await navBarPage.goToEntity('fee');
        feeComponentsPage = new FeeComponentsPage();
        await browser.wait(ec.visibilityOf(feeComponentsPage.title), 5000);
        expect(await feeComponentsPage.getTitle()).to.eq('Fees');
    });

    it('should load create Fee page', async () => {
        await feeComponentsPage.clickOnCreateButton();
        feeUpdatePage = new FeeUpdatePage();
        expect(await feeUpdatePage.getPageTitle()).to.eq('Create or edit a Fee');
        await feeUpdatePage.cancel();
    });

    it('should create and save Fees', async () => {
        const nbButtonsBeforeCreate = await feeComponentsPage.countDeleteButtons();

        await feeComponentsPage.clickOnCreateButton();
        await promise.all([
            feeUpdatePage.setUnitPriceInput('5'),
            feeUpdatePage.setQuantityInput('5'),
            feeUpdatePage.setSubTotalInput('5'),
            feeUpdatePage.setTaxTotalInput('5'),
            feeUpdatePage.setTaxPercentageInput('5'),
            feeUpdatePage.setDescriptionInput('description'),
            feeUpdatePage.setCreatedDateInput('2000-12-31'),
            feeUpdatePage.setUpdatedDateInput('2000-12-31'),
            feeUpdatePage.setCreatedByInput('createdBy'),
            feeUpdatePage.setUpdatedByInput('updatedBy'),
            feeUpdatePage.setInternalNotesInput('internalNotes'),
            feeUpdatePage.setAppearanceOrderInput('5'),
            feeUpdatePage.setCostIdInput('costId'),
            feeUpdatePage.invoiceSelectLastOption()
        ]);
        expect(await feeUpdatePage.getUnitPriceInput()).to.eq('5');
        expect(await feeUpdatePage.getQuantityInput()).to.eq('5');
        expect(await feeUpdatePage.getSubTotalInput()).to.eq('5');
        expect(await feeUpdatePage.getTaxTotalInput()).to.eq('5');
        expect(await feeUpdatePage.getTaxPercentageInput()).to.eq('5');
        expect(await feeUpdatePage.getDescriptionInput()).to.eq('description');
        expect(await feeUpdatePage.getCreatedDateInput()).to.eq('2000-12-31');
        expect(await feeUpdatePage.getUpdatedDateInput()).to.eq('2000-12-31');
        expect(await feeUpdatePage.getCreatedByInput()).to.eq('createdBy');
        expect(await feeUpdatePage.getUpdatedByInput()).to.eq('updatedBy');
        expect(await feeUpdatePage.getInternalNotesInput()).to.eq('internalNotes');
        expect(await feeUpdatePage.getAppearanceOrderInput()).to.eq('5');
        expect(await feeUpdatePage.getCostIdInput()).to.eq('costId');
        await feeUpdatePage.save();
        expect(await feeUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await feeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Fee', async () => {
        const nbButtonsBeforeDelete = await feeComponentsPage.countDeleteButtons();
        await feeComponentsPage.clickOnLastDeleteButton();

        feeDeleteDialog = new FeeDeleteDialog();
        expect(await feeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Fee?');
        await feeDeleteDialog.clickOnConfirmButton();

        expect(await feeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
