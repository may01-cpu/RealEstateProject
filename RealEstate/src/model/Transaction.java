package model;

public class Transaction {
    enum Status{
        PENDING,
        COMPLETED,
        CANCELLED;
    }
    private TransactionType TransactionType;
    private String ID_Transaction;
    private String initiatorId; // ID of the initiator (Seller or Landlord)
    private String recipientId; // ID of the recipient (Buyer or Tenant)
    private double Price;
    private Status status; // Current status (PENDING, COMPLETED, CANCELLED)


}
