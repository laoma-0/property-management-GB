package org.example.mapper;

import org.example.entity.PaymentBill;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PaymentBillMapper {
    PaymentBill selectById(Long billId);
    List<PaymentBill> selectAll();
    int insert(PaymentBill bill);
    int update(PaymentBill bill);
    int deleteById(Long billId);
}
