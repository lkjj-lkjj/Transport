package com.example.transport.service.impl;

import com.example.transport.dao.ContractDao;
import com.example.transport.entity.Contract;
import com.example.transport.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;
    @Override
    public List<Contract> findAllContracts() {
        return contractDao.findAll();
    }
}
