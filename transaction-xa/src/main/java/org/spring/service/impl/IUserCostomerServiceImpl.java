package org.spring.service.impl;

import org.spring.dao.customer.CustomerMapper;
import org.spring.dao.user.UsersMapper;
import org.spring.model.Customer;
import org.spring.model.Users;
import org.spring.service.IUserCostomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class IUserCostomerServiceImpl implements IUserCostomerService {

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private CustomerMapper customerMapper;

    @Transactional
    @Override
    public void add() {
        Users u =new Users();
        u.setUsername("sadsd");
        u.setPasswd("212121");
        usersMapper.insertSelective(u);
        Customer customer =new Customer();
        customer.setName("sasa");
        customerMapper.insertSelective(customer);
    }
}
