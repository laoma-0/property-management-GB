package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.entity.PaymentBill;
import org.example.mapper.PaymentBillMapper;
import org.example.service.PaymentBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentBillServiceImpl implements PaymentBillService {
    @Autowired
    private PaymentBillMapper paymentBillMapper;

    @Override
    public PaymentBill getPaymentBillById(Long id) {
        return paymentBillMapper.selectById(id);
    }

    @Override
    public List<PaymentBill> getAllPaymentBills() {
        return paymentBillMapper.selectAll();
    }

    @Override
    public PageInfo<PaymentBill> getPaymentBillsByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PaymentBill> paymentBills = paymentBillMapper.selectAll();
        return new PageInfo<>(paymentBills);
    }

    @Override
    public PageInfo<PaymentBill> getPaymentBillsByPageAndCondition(Long roomId, Integer feeId, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PaymentBill> paymentBills = paymentBillMapper.selectByCondition(roomId, feeId, status);
        return new PageInfo<>(paymentBills);
    }

    @Override
    public int addPaymentBill(PaymentBill bill) {
        return paymentBillMapper.insert(bill);
    }

    @Override
    public int updatePaymentBill(PaymentBill bill) {
        return paymentBillMapper.update(bill);
    }

    @Override
    public int deletePaymentBillById(Long id) {
        return paymentBillMapper.deleteById(id);
    }
}