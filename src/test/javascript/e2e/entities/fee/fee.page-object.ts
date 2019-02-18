import { element, by, ElementFinder } from 'protractor';

export class FeeComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-fee div table .btn-danger'));
    title = element.all(by.css('jhi-fee div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getText();
    }
}

export class FeeUpdatePage {
    pageTitle = element(by.id('jhi-fee-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    unitPriceInput = element(by.id('field_unitPrice'));
    quantityInput = element(by.id('field_quantity'));
    subTotalInput = element(by.id('field_subTotal'));
    taxTotalInput = element(by.id('field_taxTotal'));
    taxPercentageInput = element(by.id('field_taxPercentage'));
    descriptionInput = element(by.id('field_description'));
    createdDateInput = element(by.id('field_createdDate'));
    updatedDateInput = element(by.id('field_updatedDate'));
    createdByInput = element(by.id('field_createdBy'));
    updatedByInput = element(by.id('field_updatedBy'));
    internalNotesInput = element(by.id('field_internalNotes'));
    appearanceOrderInput = element(by.id('field_appearanceOrder'));
    costIdInput = element(by.id('field_costId'));
    invoiceSelect = element(by.id('field_invoice'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setUnitPriceInput(unitPrice) {
        await this.unitPriceInput.sendKeys(unitPrice);
    }

    async getUnitPriceInput() {
        return this.unitPriceInput.getAttribute('value');
    }

    async setQuantityInput(quantity) {
        await this.quantityInput.sendKeys(quantity);
    }

    async getQuantityInput() {
        return this.quantityInput.getAttribute('value');
    }

    async setSubTotalInput(subTotal) {
        await this.subTotalInput.sendKeys(subTotal);
    }

    async getSubTotalInput() {
        return this.subTotalInput.getAttribute('value');
    }

    async setTaxTotalInput(taxTotal) {
        await this.taxTotalInput.sendKeys(taxTotal);
    }

    async getTaxTotalInput() {
        return this.taxTotalInput.getAttribute('value');
    }

    async setTaxPercentageInput(taxPercentage) {
        await this.taxPercentageInput.sendKeys(taxPercentage);
    }

    async getTaxPercentageInput() {
        return this.taxPercentageInput.getAttribute('value');
    }

    async setDescriptionInput(description) {
        await this.descriptionInput.sendKeys(description);
    }

    async getDescriptionInput() {
        return this.descriptionInput.getAttribute('value');
    }

    async setCreatedDateInput(createdDate) {
        await this.createdDateInput.sendKeys(createdDate);
    }

    async getCreatedDateInput() {
        return this.createdDateInput.getAttribute('value');
    }

    async setUpdatedDateInput(updatedDate) {
        await this.updatedDateInput.sendKeys(updatedDate);
    }

    async getUpdatedDateInput() {
        return this.updatedDateInput.getAttribute('value');
    }

    async setCreatedByInput(createdBy) {
        await this.createdByInput.sendKeys(createdBy);
    }

    async getCreatedByInput() {
        return this.createdByInput.getAttribute('value');
    }

    async setUpdatedByInput(updatedBy) {
        await this.updatedByInput.sendKeys(updatedBy);
    }

    async getUpdatedByInput() {
        return this.updatedByInput.getAttribute('value');
    }

    async setInternalNotesInput(internalNotes) {
        await this.internalNotesInput.sendKeys(internalNotes);
    }

    async getInternalNotesInput() {
        return this.internalNotesInput.getAttribute('value');
    }

    async setAppearanceOrderInput(appearanceOrder) {
        await this.appearanceOrderInput.sendKeys(appearanceOrder);
    }

    async getAppearanceOrderInput() {
        return this.appearanceOrderInput.getAttribute('value');
    }

    async setCostIdInput(costId) {
        await this.costIdInput.sendKeys(costId);
    }

    async getCostIdInput() {
        return this.costIdInput.getAttribute('value');
    }

    async invoiceSelectLastOption() {
        await this.invoiceSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async invoiceSelectOption(option) {
        await this.invoiceSelect.sendKeys(option);
    }

    getInvoiceSelect(): ElementFinder {
        return this.invoiceSelect;
    }

    async getInvoiceSelectedOption() {
        return this.invoiceSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class FeeDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-fee-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-fee'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
