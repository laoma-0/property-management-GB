package org.example.mapper;

import org.example.entity.PaymentBill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PaymentBillMapper {
    PaymentBill selectById(Long billId);
    List<PaymentBill> selectAll();
    List<PaymentBill> selectByCondition(@Param("roomId") Long roomId, @Param("feeId") Integer feeId, @Param("status") Integer status);
    int insert(PaymentBill bill);
    int update(PaymentBill bill);
    int deleteById(Long billId);
}