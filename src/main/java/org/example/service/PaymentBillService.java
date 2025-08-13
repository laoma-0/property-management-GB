package org.example.service;

import com.github.pagehelper.PageInfo;
import org.example.entity.PaymentBill;
import java.util.List;

public interface PaymentBillService {
    PaymentBill getPaymentBillById(Long id);
    List<PaymentBill> getAllPaymentBills();
    PageInfo<PaymentBill> getPaymentBillsByPage(int pageNum, int pageSize);
    PageInfo<PaymentBill> getPaymentBillsByPageAndCondition(Long roomId, Integer feeId, Integer status, int pageNum, int pageSize);
    int addPaymentBill(PaymentBill bill);
    int updatePaymentBill(PaymentBill bill);
    int deletePaymentBillById(Long id);
}