package com.itheima.service;

import com.itheima.domain.ContactInfo;

import java.util.List;
import java.util.Map;

public interface ContactService {

    List<ContactInfo> queryAll();

    boolean delContact(String id);

    ContactInfo queryById(String id);

    boolean updateContact(Map<String, String[]> parameterMap);

    boolean addContact(Map<String, String[]> parameterMap);

    List<ContactInfo> queryByPage(int offSet, int pageSize);

    int queryCounts();

    boolean checkEmail(String email);

    String queryByPageRedis(int offSet, int pageSize);
}
