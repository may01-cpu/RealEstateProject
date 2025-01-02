package model;

import java.util.Date;
import model.Client;
import model.Property;

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
    private Property property;


    public Transaction(TransactionType transactionType, String idTransaction, String initiatorId, String recipientId, double price, Status status, Date date, Date dateDebut, Date dateFin, double montantPaiement, Property property) {
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
        this.property = property;

    }

    public String toFileFormat() {
        return transactionType + "|" + idTransaction + "|" + initiatorId + "|" + recipientId + "|" +
                price + "|" + status + "|" + date.getTime() + "|" +
                dateDebut.getTime() + "|" + dateFin.getTime() + "|" + montantPaiement + "|" + property.toString();
    }

    public static Transaction fromString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 10) {
            throw new IllegalArgumentException("Invalid transaction format");
        }

        TransactionType transactionType = TransactionType.valueOf(parts[0]);
        String idTransaction = parts[1];
        String initiatorId = parts[2];
        String recipientId = parts[3];
        double price = Double.parseDouble(parts[4]);
        Status status = Status.valueOf(parts[5]);
        Date date = new Date(Long.parseLong(parts[6]));
        Date dateDebut = new Date(Long.parseLong(parts[7]));
        Date dateFin = new Date(Long.parseLong(parts[8]));
        double montantPaiement = Double.parseDouble(parts[9]);
        Property property = Property.fromFileFormat(parts[10]);


        return new Transaction(transactionType, idTransaction, initiatorId, recipientId, price, status, date, dateDebut, dateFin, montantPaiement, property);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionType=" + transactionType +
                ", idTransaction='" + idTransaction + '\'' +
                ", initiatorId='" + initiatorId + '\'' +
                ", recipientId='" + recipientId + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", date=" + date +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", montantPaiement=" + montantPaiement +
                ", property=" + property +
                '}';
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
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

    public void setDateFin(Date date) {
        this.dateFin = dateFin;
    }

    public double getMontantPaiement() {
        return montantPaiement;
    }

    public void setMontantPaiement(double montantPaiement) {
        this.montantPaiement = montantPaiement;
    }
}