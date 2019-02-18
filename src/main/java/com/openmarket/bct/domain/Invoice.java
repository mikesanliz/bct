package com.openmarket.bct.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.openmarket.bct.domain.enumeration.InvoiceStatus;

/**
 * A Invoice.
 */
@Entity
@Table(name = "t_invoice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month")
    private Integer month;

    @Column(name = "jhi_year")
    private Integer year;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "display_date")
    private LocalDate displayDate;

    @Column(name = "emailed_date")
    private LocalDate emailedDate;

    @Column(name = "emailed_to_customer")
    private Boolean emailedToCustomer;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private InvoiceStatus state;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "external_notes")
    private String externalNotes;

    @Column(name = "internal_notes")
    private String internalNotes;

    @OneToMany(mappedBy = "invoice")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Fee> fees = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public Invoice month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public Invoice year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Invoice createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public Invoice updatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Invoice dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDisplayDate() {
        return displayDate;
    }

    public Invoice displayDate(LocalDate displayDate) {
        this.displayDate = displayDate;
        return this;
    }

    public void setDisplayDate(LocalDate displayDate) {
        this.displayDate = displayDate;
    }

    public LocalDate getEmailedDate() {
        return emailedDate;
    }

    public Invoice emailedDate(LocalDate emailedDate) {
        this.emailedDate = emailedDate;
        return this;
    }

    public void setEmailedDate(LocalDate emailedDate) {
        this.emailedDate = emailedDate;
    }

    public Boolean isEmailedToCustomer() {
        return emailedToCustomer;
    }

    public Invoice emailedToCustomer(Boolean emailedToCustomer) {
        this.emailedToCustomer = emailedToCustomer;
        return this;
    }

    public void setEmailedToCustomer(Boolean emailedToCustomer) {
        this.emailedToCustomer = emailedToCustomer;
    }

    public InvoiceStatus getState() {
        return state;
    }

    public Invoice state(InvoiceStatus state) {
        this.state = state;
        return this;
    }

    public void setState(InvoiceStatus state) {
        this.state = state;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Invoice createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Invoice updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getExternalNotes() {
        return externalNotes;
    }

    public Invoice externalNotes(String externalNotes) {
        this.externalNotes = externalNotes;
        return this;
    }

    public void setExternalNotes(String externalNotes) {
        this.externalNotes = externalNotes;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public Invoice internalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
        return this;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public Set<Fee> getFees() {
        return fees;
    }

    public Invoice fees(Set<Fee> fees) {
        this.fees = fees;
        return this;
    }

    public Invoice addFees(Fee fee) {
        this.fees.add(fee);
        fee.setInvoice(this);
        return this;
    }

    public Invoice removeFees(Fee fee) {
        this.fees.remove(fee);
        fee.setInvoice(null);
        return this;
    }

    public void setFees(Set<Fee> fees) {
        this.fees = fees;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        if (invoice.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), invoice.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Invoice{" +
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
