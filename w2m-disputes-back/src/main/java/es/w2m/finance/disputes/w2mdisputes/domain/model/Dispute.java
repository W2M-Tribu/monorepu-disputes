package es.w2m.finance.disputes.w2mdisputes.domain.model;

import lombok.AllArgsConstructor;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dispute {

    private Long id;

    private String manager;
    private LocalDate openedDate;
    private String jira;
    private String bp;

    private String clientName;
    private String loc;
    private String disputeStatus;

    private String invoiceNumber;
    private LocalDate invoiceDate;
    private String agencyReference;

    private String delegation;

    private Double invoiceAmount;
    private String currency;

    private Double clientAmount;
    private Double disputedAmount;
    private Double finalSale;
    private String finalCurrency;

    private String clientComment;
    private String approvalStatus;
    private String internalComment;

    private String category;

    private LocalDate jiraHandledDate;
    private LocalDate jiraClosedDate;

    private String handledBy;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getManager() { return manager; }
    public void setManager(String manager) { this.manager = manager; }

    public LocalDate getOpenedDate() { return openedDate; }
    public void setOpenedDate(LocalDate openedDate) { this.openedDate = openedDate; }

    public String getJira() { return jira; }
    public void setJira(String jira) { this.jira = jira; }

    public String getBp() { return bp; }
    public void setBp(String bp) { this.bp = bp; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getLoc() { return loc; }
    public void setLoc(String loc) { this.loc = loc; }

    public String getDisputeStatus() { return disputeStatus; }
    public void setDisputeStatus(String disputeStatus) { this.disputeStatus = disputeStatus; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public LocalDate getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDate invoiceDate) { this.invoiceDate = invoiceDate; }

    public String getAgencyReference() { return agencyReference; }
    public void setAgencyReference(String agencyReference) { this.agencyReference = agencyReference; }

    public String getDelegation() { return delegation; }
    public void setDelegation(String delegation) { this.delegation = delegation; }

    public Double getInvoiceAmount() { return invoiceAmount; }
    public void setInvoiceAmount(Double invoiceAmount) { this.invoiceAmount = invoiceAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public Double getClientAmount() { return clientAmount; }
    public void setClientAmount(Double clientAmount) { this.clientAmount = clientAmount; }

    public Double getDisputedAmount() { return disputedAmount; }
    public void setDisputedAmount(Double disputedAmount) { this.disputedAmount = disputedAmount; }

    public Double getFinalSale() { return finalSale; }
    public void setFinalSale(Double finalSale) { this.finalSale = finalSale; }

    public String getFinalCurrency() { return finalCurrency; }
    public void setFinalCurrency(String finalCurrency) { this.finalCurrency = finalCurrency; }

    public String getClientComment() { return clientComment; }
    public void setClientComment(String clientComment) { this.clientComment = clientComment; }

    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }

    public String getInternalComment() { return internalComment; }
    public void setInternalComment(String internalComment) { this.internalComment = internalComment; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDate getJiraHandledDate() { return jiraHandledDate; }
    public void setJiraHandledDate(LocalDate jiraHandledDate) { this.jiraHandledDate = jiraHandledDate; }

    public LocalDate getJiraClosedDate() { return jiraClosedDate; }
    public void setJiraClosedDate(LocalDate jiraClosedDate) { this.jiraClosedDate = jiraClosedDate; }

    public String getHandledBy() { return handledBy; }
    public void setHandledBy(String handledBy) { this.handledBy = handledBy; }
}
