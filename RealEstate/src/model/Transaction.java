package model;

import java.text.SimpleDateFormat;
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

    public String toFileFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return transactionType + "," + idTransaction + "," + initiatorId + "," + recipientId + "," + price + "," + status + "," + sdf.format(date) + "," + sdf.format(dateDebut) + "," + sdf.format(dateFin) + "," + montantPaiement;
    }

    public static Transaction fromFileFormat(String line) {
        String[] parts = line.split(",");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            TransactionType transactionType = TransactionType.valueOf(parts[0]);
            String idTransaction = parts[1];
            String initiatorId = parts[2];
            String recipientId = parts[3];
            double price = Double.parseDouble(parts[4]);
            Status status = Status.valueOf(parts[5]);
            Date date = sdf.parse(parts[6]);
            Date dateDebut = sdf.parse(parts[7]);
            Date dateFin = sdf.parse(parts[8]);
            double montantPaiement = Double.parseDouble(parts[9]);
            return new Transaction(transactionType, idTransaction, initiatorId, recipientId, price, status, date, dateDebut, dateFin, montantPaiement);
        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion de la ligne en transaction : " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Transaction{" +
                "transactionType=" + transactionType +
                ", idTransaction='" + idTransaction + '\'' +
                ", initiatorId='" + initiatorId + '\'' +
                ", recipientId='" + recipientId + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", date=" + sdf.format(date) +
                ", dateDebut=" + sdf.format(dateDebut) +
                ", dateFin=" + sdf.format(dateFin) +
                ", montantPaiement=" + montantPaiement +
                '}';
    }}