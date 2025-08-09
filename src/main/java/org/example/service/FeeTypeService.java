package org.example.service;

import org.example.entity.FeeType;
import java.util.List;

public interface FeeTypeService {
    FeeType getFeeTypeById(Integer id);
    List<FeeType> getAllFeeTypes();
    int addFeeType(FeeType feeType);
    int updateFeeType(FeeType feeType);
    int deleteFeeTypeById(Integer id);
}
