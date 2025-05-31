package Kesehatan.Asklepios.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Kesehatan.Asklepios.model.Transaction;
import Kesehatan.Asklepios.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/transactions")
public class TransactionManagementController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public String listTransactions(Model model) {
        List<Transaction> transactions = transactionRepository.findAll();
        model.addAttribute("transactions", transactions);
        return "admin/transactions/list";
    }

    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable String id, Model model) {
        Transaction tx = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction ID"));
        model.addAttribute("transaction", tx);
        return "admin/transactions/detail";
    }

    @PostMapping("/update/{id}")
    public String updateTransaction(
            @PathVariable String id,
            @RequestParam("paymentMethod") Transaction.PaymentMethod paymentMethod,
            @RequestParam("status") Transaction.Status status,
            @RequestParam(value = "paymentDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime paymentDate
    ) {
        Transaction tx = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transaction ID"));

        tx.setPaymentMethod(paymentMethod);
        tx.setStatus(status);
        tx.setPaymentDate(paymentDate);

        transactionRepository.save(tx);
        return "redirect:/admin/transactions/detail/" + id;
    }
}