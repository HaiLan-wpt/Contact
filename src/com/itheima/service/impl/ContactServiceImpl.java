package com.itheima.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.dao.ContactDao;
import com.itheima.dao.impl.ContactDaoImpl;
import com.itheima.domain.ContactInfo;
import com.itheima.service.ContactService;
import com.itheima.utils.JredisUtils;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class ContactServiceImpl implements ContactService {
    ContactDao dao=new ContactDaoImpl();

    @Override
    public List<ContactInfo> queryAll() {
        List<ContactInfo> contacts=dao.queryAll();
        return contacts;
    }


    @Override
    public boolean delContact(String id) {
        int result=dao.delContact(id);
        return result==1;
    }

    @Override
    public ContactInfo queryById(String id) {

        List<ContactInfo> list=dao.queryById(id);
        if (list.size()==1){
            return  list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public boolean updateContact(Map<String, String[]> parameterMap) {
        ContactInfo contac=new ContactInfo();
        try {
            BeanUtils.populate(contac,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

            int updateResult=dao.update(contac);
            return updateResult==1;



    }

    @Override
    public boolean addContact(Map<String, String[]> parameterMap) {
        ContactInfo contact=new ContactInfo();
        try {
            BeanUtils.populate(contact,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("contact_addContact = " + contact);
        int addResult=dao.addContact(contact);
        return addResult==1;
    }

    @Override
    public List<ContactInfo> queryByPage(int offSet, int pageSize) {


        List<ContactInfo> list= dao.queryByPage( offSet,pageSize);
        if (list.size()!=0){
            return list;
        }else {
            return null;
        }

    }

    @Override
    public int queryCounts() {
        return dao.queryCounts();

    }

    @Override
    public boolean checkEmail(String email) {
        int result_Email=dao.checkEmail(email);
        return result_Email==0;
    }


    /**
     * 不分页使用redis和mysql结合
     * @param offSet 数据库查询中limit第一个关键字 无用
     * @param pageSize 数据库查询中 limit 第二个关键字 无用
     * @return 返回一个JSON文件
     */
    @Override
    public String queryByPageRedis(int offSet, int pageSize) {
//        List<ContactInfo> list_QueryByPageRedis= dao.queryByPageRedis(offSet,pageSize);
        /**
         * 直接把所有的加入到redis中
         */
        List<ContactInfo> list_QueryByPageRedis = dao.queryAll();
        Jedis jedis = JredisUtils.getJedis();



        String day06contactInfosData = jedis.get("day06contactInfosData");
//        jedis.rpush("",)

        if (day06contactInfosData==null) {

            ObjectMapper mapper = new ObjectMapper();
            try {
                String s = mapper.writeValueAsString(list_QueryByPageRedis);
                jedis.set("day06contactInfosData",s);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return jedis.get("day06contactInfosData");
    }
}
