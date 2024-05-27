package com.azz;

import jakarta.persistence.*;

import java.lang.reflect.Constructor;
import java.sql.Date;
import java.util.Objects;

@Entity

public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_Id_sequence",
            sequenceName = "transaction_id_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_id_sequence"
    )

    private Integer idTransaction;
    private Date transactionDate;
    private Integer idMember;
    private Integer idBook;
    private Integer status;

    public Transaction() {}

    public Transaction(int idTransaction, Date transactionDate, int idMember, int idBook, int status) {
        this.idTransaction = idTransaction;
        this.transactionDate = transactionDate;
        this.idMember = idMember;
        this.idBook = idBook;
        this.status = status;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return idTransaction == that.idTransaction && idMember == that.idMember && idBook == that.idBook && status == that.status && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaction, transactionDate, idMember, idBook, status);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + idTransaction +
                ", transactionDate=" + transactionDate +
                ", idMember=" + idMember +
                ", idBook=" + idBook +
                ", status=" + status +
                '}';
    }
}

