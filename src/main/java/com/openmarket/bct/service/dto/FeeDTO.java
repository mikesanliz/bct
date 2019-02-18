package com.openmarket.bct.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Fee entity.
 */
public class FeeDTO implements Serializable {

    private Long id;

    private BigDecimal unitPrice;

    private Long quantity;

    private BigDecimal subTotal;

    private BigDecimal taxTotal;

    private BigDecimal taxPercentage;

    private String description;

    private LocalDate createdDate;

    private LocalDate updatedDate;

    private String createdBy;

    private String updatedBy;

    private String internalNotes;

    private Integer appearanceOrder;

    private String costId;


    private Long invoiceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getInternalNotes() {
        return internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public Integer getAppearanceOrder() {
        return appearanceOrder;
    }

    public void setAppearanceOrder(Integer appearanceOrder) {
        this.appearanceOrder = appearanceOrder;
    }

    public String getCostId() {
        return costId;
    }

    public void setCostId(String costId) {
        this.costId = costId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeeDTO feeDTO = (FeeDTO) o;
        if (feeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeeDTO{" +
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
            ", invoice=" + getInvoiceId() +
            "}";
    }
}
