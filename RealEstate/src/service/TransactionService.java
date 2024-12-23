package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Transaction;
import model.TransactionType;
import model.Property;
import model.Client;


public class TransactionService {
    private List<Transaction>transactions;
    
    public TransactionService(){
        this.transactions = new ArrayList<>();  
    }


public void creerTransaction(TransactionType type, Property property, Client client, double montant) {
    if (property == null || client == null) {
        System.out.println("Erreur");
        return;
    }
    if (montant <= 0) {
        System.out.println("Erreur");
        return;
    }
    Transaction transaction = new Transaction(type, property, client, new Date(), montant);
    transactions.add(transaction);
    System.out.println("Transaction créée : " + transaction);
}
public List<Transaction> recupererTransactions(){
    return transactions;
}
    public void afficherTransactions(){
        if (transactions.isEmpty()){
            System.out.println("aucune transaction.");
            return;
        }
        for (Transaction transaction: transactions){
            transaction.afficherDetails();
            System.out.println("Date de la transaction : "+ transaction.getDate());
        }
    }

    public List<Transaction> rechercherTransactionsParType(TransactionType type){
       List<Transaction>resultats = new ArrayList<>();
       for (Transaction transaction : transactions){
        if (transaction.getType() == type){
            resultats.add(transaction);
        }
       } 
       return resultats;
    }
    public List<Transaction> rechercherTransactionsParClient(Client client) {
        List<Transaction> resultats = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getClient().equals(client)) {
                resultats.add(transaction);
            }
        }
        return resultats;
}

}


