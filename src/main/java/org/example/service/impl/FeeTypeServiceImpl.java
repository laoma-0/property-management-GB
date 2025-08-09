package org.example.service.impl;

import org.example.entity.FeeType;
import org.example.mapper.FeeTypeMapper;
import org.example.service.FeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeeTypeServiceImpl implements FeeTypeService {
    @Autowired
    private FeeTypeMapper feeTypeMapper;

    @Override
    public FeeType getFeeTypeById(Integer id) {
        return feeTypeMapper.selectById(id);
    }

    @Override
    public List<FeeType> getAllFeeTypes() {
        return feeTypeMapper.selectAll();
    }

    @Override
    public int addFeeType(FeeType feeType) {
        return feeTypeMapper.insert(feeType);
    }

    @Override
    public int updateFeeType(FeeType feeType) {
        return feeTypeMapper.update(feeType);
    }

    @Override
    public int deleteFeeTypeById(Integer id) {
        return feeTypeMapper.deleteById(id);
    }
}
