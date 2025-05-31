package Kesehatan.Asklepios.controller.admin;

import Kesehatan.Asklepios.model.Transaction;
import Kesehatan.Asklepios.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/transactions")
public class TransactionManagementController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
        Transaction tx = transactionService.getById(id);
        return ResponseEntity.ok(tx);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Transaction> updateStatus(@PathVariable String id, @RequestParam Transaction.Status status) {
        Transaction tx = transactionService.updatePaymentStatus(id, status, LocalDateTime.now());
        return ResponseEntity.ok(tx);
    }
}
