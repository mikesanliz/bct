import { element, by, ElementFinder } from 'protractor';

export class InvoiceComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-invoice div table .btn-danger'));
    title = element.all(by.css('jhi-invoice div h2#page-heading span')).first();

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

export class InvoiceUpdatePage {
    pageTitle = element(by.id('jhi-invoice-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    monthInput = element(by.id('field_month'));
    yearInput = element(by.id('field_year'));
    createdDateInput = element(by.id('field_createdDate'));
    updatedDateInput = element(by.id('field_updatedDate'));
    dueDateInput = element(by.id('field_dueDate'));
    displayDateInput = element(by.id('field_displayDate'));
    emailedDateInput = element(by.id('field_emailedDate'));
    emailedToCustomerInput = element(by.id('field_emailedToCustomer'));
    stateSelect = element(by.id('field_state'));
    createdByInput = element(by.id('field_createdBy'));
    updatedByInput = element(by.id('field_updatedBy'));
    externalNotesInput = element(by.id('field_externalNotes'));
    internalNotesInput = element(by.id('field_internalNotes'));

    async getPageTitle() {
        return this.pageTitle.getText();
    }

    async setMonthInput(month) {
        await this.monthInput.sendKeys(month);
    }

    async getMonthInput() {
        return this.monthInput.getAttribute('value');
    }

    async setYearInput(year) {
        await this.yearInput.sendKeys(year);
    }

    async getYearInput() {
        return this.yearInput.getAttribute('value');
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

    async setDueDateInput(dueDate) {
        await this.dueDateInput.sendKeys(dueDate);
    }

    async getDueDateInput() {
        return this.dueDateInput.getAttribute('value');
    }

    async setDisplayDateInput(displayDate) {
        await this.displayDateInput.sendKeys(displayDate);
    }

    async getDisplayDateInput() {
        return this.displayDateInput.getAttribute('value');
    }

    async setEmailedDateInput(emailedDate) {
        await this.emailedDateInput.sendKeys(emailedDate);
    }

    async getEmailedDateInput() {
        return this.emailedDateInput.getAttribute('value');
    }

    getEmailedToCustomerInput() {
        return this.emailedToCustomerInput;
    }
    async setStateSelect(state) {
        await this.stateSelect.sendKeys(state);
    }

    async getStateSelect() {
        return this.stateSelect.element(by.css('option:checked')).getText();
    }

    async stateSelectLastOption() {
        await this.stateSelect
            .all(by.tagName('option'))
            .last()
            .click();
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

    async setExternalNotesInput(externalNotes) {
        await this.externalNotesInput.sendKeys(externalNotes);
    }

    async getExternalNotesInput() {
        return this.externalNotesInput.getAttribute('value');
    }

    async setInternalNotesInput(internalNotes) {
        await this.internalNotesInput.sendKeys(internalNotes);
    }

    async getInternalNotesInput() {
        return this.internalNotesInput.getAttribute('value');
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

export class InvoiceDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-invoice-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-invoice'));

    async getDialogTitle() {
        return this.dialogTitle.getText();
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
