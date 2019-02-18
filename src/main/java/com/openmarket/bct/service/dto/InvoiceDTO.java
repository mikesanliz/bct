package com.openmarket.bct.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import com.openmarket.bct.domain.enumeration.InvoiceStatus;

/**
 * A DTO for the Invoice entity.
 */
public class InvoiceDTO implements Serializable {

    private Long id;

    private Integer month;

    private Integer year;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private LocalDate dueDate;

    private LocalDate displayDate;

    private LocalDate emailedDate;

    private Boolean emailedToCustomer;

    private InvoiceStatus state;

    private String createdBy;

    private String updatedBy;

    private String externalNotes;

    private String internalNotes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(LocalDate displayDate) {
        this.displayDate = displayDate;
    }

    public LocalDate getEmailedDate() {
        return emailedDate;
    }

    public void setEmailedDate(LocalDate emailedDate) {
        this.emailedDate = emailedDate;
    }

    public Boolean isEmailedToCustomer() {
        return emailedToCustomer;
    }

    public void setEmailedToCustomer(Boolean emailedToCustomer) {
        this.emailedToCustomer = emailedToCustomer;
    }

    public InvoiceStatus getState() {
        return state;
    }

    public void setState(InvoiceStatus state) {
        this.state = state;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getExternalNotes() {
        return externalNotes;
    }

    public void setExternalNotes(String externalNotes) {
        this.externalNotes = externalNotes;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvoiceDTO invoiceDTO = (InvoiceDTO) o;
        if (invoiceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoiceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvoiceDTO{" +
            "id=" + getId() +
            ", month=" + getMonth() +
            ", year=" + getYear() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", displayDate='" + getDisplayDate() + "'" +
            ", emailedDate='" + getEmailedDate() + "'" +
            ", emailedToCustomer='" + isEmailedToCustomer() + "'" +
            ", state='" + getState() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", externalNotes='" + getExternalNotes() + "'" +
            ", internalNotes='" + getInternalNotes() + "'" +
            "}";
    }
}
