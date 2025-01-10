package service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Transaction;
import model.TransactionType;
import model.Property;
import model.Client;

public class TransactionService {
    private static final String FILE_NAME = "recources/transactions.CVS";

    public TransactionService() {
        try {
            new File(FILE_NAME).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creerTransaction(TransactionType type, Property property, Client client, double montant, String idTransaction, String initiatorId, String recipientId, Date dateDebut, Date dateFin) {
        if (property == null || client == null) {
            System.out.println("Erreur : La propriété ou le client est nul.");
            return;
        }
        if (montant <= 0) {
            System.out.println("Erreur : Le montant doit être supérieur à zéro.");
            return;
        }

        if (viewTransaction(idTransaction)) {
            System.out.println("Erreur : L'ID de la transaction existe déjà.");
            return;
        }

        Transaction transaction = new Transaction(type, idTransaction, initiatorId, recipientId, montant, Transaction.Status.PENDING, new Date(), dateDebut, dateFin, 0, property);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(transaction.toFileFormat());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Transaction créée : " + transaction);
    }

    public List<Transaction> recupererTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transaction transaction = Transaction.fromString(line);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void afficherTransactions() {
        List<Transaction> transactions = recupererTransactions();
        if (transactions.isEmpty()) {
            System.out.println("Aucune transaction.");
            return;
        }
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public boolean viewTransaction(String idTransaction) {
        List<Transaction> transactions = recupererTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getIdTransaction().equals(idTransaction)) {
                System.out.println("Détails de la transaction : " + transaction);
                return true;
            }
        }
        System.out.println("Transaction avec ID " + idTransaction + " non trouvée.");
        return false;
    }

    public List<Transaction> rechercherTransactionsParType(TransactionType type) {
        List<Transaction> resultats = new ArrayList<>();
        List<Transaction> transactions = recupererTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == type) {
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
            System.out.println("Erreur : Le montant doit être supérieur à zéro.");
            return;
        }
        transaction.setMontantPaiement(transaction.getMontantPaiement() + montant);
        System.out.println("Paiement de " + montant + " enregistré pour la transaction " + transaction.getIdTransaction());
    }
}