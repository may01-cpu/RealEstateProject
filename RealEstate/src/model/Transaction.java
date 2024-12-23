package model;

import java.util.Date;

public class Transaction {
    public enum Status {
        PENDING,
        COMPLETED,
        CANCELLED;
    }

    private TransactionType transactionType; 
    private String idTransaction;
    private String initiatorId; 
    private String recipientId; 
    private double price; 
    private Status status;
    private Date date;
    private Date dateDebut;
    private Date dateFin;
    private double montantPaiement;

    
    public Transaction(TransactionType transactionType, String idTransaction, String initiatorId, String recipientId, double price, Status status, Date date, Date dateDebut, Date dateFin, double montantPaiement) {
        this.transactionType = transactionType;
        this.idTransaction = idTransaction;
        this.initiatorId = initiatorId;
        this.recipientId = recipientId;
        this.price = price;
        this.status = status;
        this.date = date;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montantPaiement = montantPaiement;
    }

    
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getMontantPaiement() {
        return montantPaiement;
    }

    public void setMontantPaiement(double montantPaiement) {
        this.montantPaiement = montantPaiement;
    }
}