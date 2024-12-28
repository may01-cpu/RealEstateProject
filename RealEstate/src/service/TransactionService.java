package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Transaction;
import model.TransactionType;
import model.Property;
import model.Client;

public class TransactionService {
    private List<Transaction> transactions;

    public TransactionService() {
        this.transactions = new ArrayList<>();
    }

    public void creerTransaction(TransactionType type, Property property, Client client, double montant, String idTransaction, String initiatorId, String recipientId, Date dateDebut, Date dateFin) {
        if (property == null || client == null) {
            System.out.println("Erreur");
            return;
        }
        if (montant <= 0) {
            System.out.println("Erreur");
            return;
        }
        Transaction transaction = new Transaction(type, idTransaction, initiatorId, recipientId, montant, Transaction.Status.PENDING, new Date(), dateDebut, dateFin, 0);
        transactions.add(transaction);
        System.out.println("Transaction créée : " + transaction);
    }

    public List<Transaction> recupererTransactions() {
        return transactions;
    }

    public void afficherTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("Aucune transaction.");
            return;
        }
        for (Transaction transaction : transactions) {
            System.out.println(transaction); 
        }
    }

    public List<Transaction> rechercherTransactionsParType(TransactionType type) {
        List<Transaction> resultats = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == type) {
                resultats.add(transaction);
            }
        }
        return resultats;
    }

    public List<Transaction> rechercherTransactionsParClient(Client client) {
        List<Transaction> resultats = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getInitiatorId().equals(client.getId()) || transaction.getRecipientId().equals(client.getId())) {
                resultats.add(transaction);
            }
        }
        return resultats;
    }

    public void genererContrat(Transaction transaction) {
        String contrat = "Contrat de " + transaction.getTransactionType() + " entre " + transaction.getInitiatorId() + " et " + transaction.getRecipientId() +
                         " pour un montant de " + transaction.getPrice() + " à partir du " + transaction.getDateDebut() + " jusqu'au " + transaction.getDateFin();
        System.out.println("Contrat généré : " + contrat);
    }

    public void enregistrerPaiement(Transaction transaction, double montant) {
        if (montant <= 0) {
            System.out.println("Erreur");
            return;
        }
        transaction.setMontantPaiement(transaction.getMontantPaiement() + montant); 
        System.out.println("Paiement de " + montant + " enregistré pour la transaction " + transaction.getIdTransaction());
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}