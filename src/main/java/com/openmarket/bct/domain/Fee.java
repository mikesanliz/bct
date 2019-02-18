package com.openmarket.bct.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Fee.
 */
@Entity
@Table(name = "t_fee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Fee implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "sub_total", precision = 10, scale = 2)
    private BigDecimal subTotal;

    @Column(name = "tax_total", precision = 10, scale = 2)
    private BigDecimal taxTotal;

    @Column(name = "tax_percentage", precision = 10, scale = 2)
    private BigDecimal taxPercentage;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "internal_notes")
    private String internalNotes;

    @Column(name = "appearance_order")
    private Integer appearanceOrder;

    @Column(name = "cost_id")
    private String costId;

    @ManyToOne
    @JsonIgnoreProperties("fees")
    private Invoice invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Fee unitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Fee quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public Fee subTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
        return this;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public Fee taxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
        return this;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public Fee taxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
        return this;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public String getDescription() {
        return description;
    }

    public Fee description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Fee createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public Fee updatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Fee createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Fee updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getInternalNotes() {
        return internalNotes;
    }

    public Fee internalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
        return this;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public Integer getAppearanceOrder() {
        return appearanceOrder;
    }

    public Fee appearanceOrder(Integer appearanceOrder) {
        this.appearanceOrder = appearanceOrder;
        return this;
    }

    public void setAppearanceOrder(Integer appearanceOrder) {
        this.appearanceOrder = appearanceOrder;
    }

    public String getCostId() {
        return costId;
    }

    public Fee costId(String costId) {
        this.costId = costId;
        return this;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Fee invoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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
        Fee fee = (Fee) o;
        if (fee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fee{" +
            "id=" + getId() +
            ", unitPrice=" + getUnitPrice() +
            ", quantity=" + getQuantity() +
            ", subTotal=" + getSubTotal() +
            ", taxTotal=" + getTaxTotal() +
            ", taxPercentage=" + getTaxPercentage() +
            ", description='" + getDescription() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", internalNotes='" + getInternalNotes() + "'" +
            ", appearanceOrder=" + getAppearanceOrder() +
            ", costId='" + getCostId() + "'" +
            "}";
    }
}
