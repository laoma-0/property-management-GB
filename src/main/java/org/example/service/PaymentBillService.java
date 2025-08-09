package org.example.service;

import org.example.entity.PaymentBill;
import java.util.List;

public interface PaymentBillService {
    PaymentBill getPaymentBillById(Long id);
    List<PaymentBill> getAllPaymentBills();
    int addPaymentBill(PaymentBill bill);
    int updatePaymentBill(PaymentBill bill);
    int deletePaymentBillById(Long id);
}
