package org.example.controller;

import org.example.entity.FeeType;
import org.example.service.FeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fee-types")
public class FeeTypeController {
    @Autowired
    private FeeTypeService feeTypeService;

    @GetMapping("/{id}")
    public FeeType getFeeTypeById(@PathVariable Integer id) {
        return feeTypeService.getFeeTypeById(id);
    }

    @GetMapping
    public List<FeeType> getAllFeeTypes() {
        return feeTypeService.getAllFeeTypes();
    }

    @PostMapping
    public int addFeeType(@RequestBody FeeType feeType) {
        return feeTypeService.addFeeType(feeType);
    }

    @PutMapping
    public int updateFeeType(@RequestBody FeeType feeType) {
        return feeTypeService.updateFeeType(feeType);
    }

    @DeleteMapping("/{id}")
    public int deleteFeeType(@PathVariable Integer id) {
        return feeTypeService.deleteFeeTypeById(id);
    }
}
