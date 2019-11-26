package com.itheima.dao;

import com.itheima.domain.ContactInfo;

import java.util.List;

public interface ContactDao {
    List<ContactInfo> queryAll();

    int delContact(String id);

    List<ContactInfo> queryById(String id);

    int update(ContactInfo contact);

    int addContact(ContactInfo contact);

    List<ContactInfo> queryByPage(int offSet, int pageSize);

    int queryCounts();

    int checkEmail(String email);

    List<ContactInfo> queryByPageRedis(int offSet, int pageSize);
}
