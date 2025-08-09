package org.example.mapper;

import org.example.entity.FeeType;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface FeeTypeMapper {
    FeeType selectById(Integer feeId);
    List<FeeType> selectAll();
    int insert(FeeType feeType);
    int update(FeeType feeType);
    int deleteById(Integer feeId);
}
