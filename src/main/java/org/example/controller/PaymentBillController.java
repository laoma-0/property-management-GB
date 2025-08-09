package org.example.controller;

import org.example.entity.PaymentBill;
import org.example.service.PaymentBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payment-bills")
public class PaymentBillController {
    @Autowired
    private PaymentBillService paymentBillService;

    @GetMapping("/{id}")
    public PaymentBill getPaymentBillById(@PathVariable Long id) {
        return paymentBillService.getPaymentBillById(id);
    }

    @GetMapping
    public List<PaymentBill> getAllPaymentBills() {
        return paymentBillService.getAllPaymentBills();
    }

    @PostMapping
    public int addPaymentBill(@RequestBody PaymentBill bill) {
        return paymentBillService.addPaymentBill(bill);
    }

    @PutMapping
    public int updatePaymentBill(@RequestBody PaymentBill bill) {
        return paymentBillService.updatePaymentBill(bill);
    }

    @DeleteMapping("/{id}")
    public int deletePaymentBill(@PathVariable Long id) {
        return paymentBillService.deletePaymentBillById(id);
    }
}
