package org.spring.dao.user;

import org.apache.ibatis.annotations.Param;
import org.spring.model.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    Users findByUsernameAndPasswd(@Param("username") String username, @Param("passwd") String passwd);
}