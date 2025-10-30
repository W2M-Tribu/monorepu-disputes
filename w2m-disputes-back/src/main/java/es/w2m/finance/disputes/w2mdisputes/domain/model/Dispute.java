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
}
