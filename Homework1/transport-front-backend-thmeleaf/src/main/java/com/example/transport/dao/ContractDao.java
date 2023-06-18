package com.example.transport.dao;

import com.example.transport.entity.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ContractDao {
    @Select("SELECT * FROM contract")
    List<Contract> findAll();
}
