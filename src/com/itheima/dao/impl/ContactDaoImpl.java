package com.itheima.dao.impl;

import com.itheima.dao.ContactDao;
import com.itheima.domain.ContactInfo;
import com.itheima.utils.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ContactDaoImpl implements ContactDao {

    //获取JdbcTemplate
    JdbcTemplate jt = DBUtil.getJdbcTemplate();

    /**
     * 查询所有的数据
     *
     * @return
     */
    @Override
    public List<ContactInfo> queryAll() {

        String sql = "select * from contact_info where del=0";
        List<ContactInfo> query = jt.query(sql, new BeanPropertyRowMapper<>(ContactInfo.class));

        return query;
    }

    /**
     * 删除某一个数据，采用逻辑删除，并非物理删除
     *
     * @param id 用户id
     * @return 返回删除的行数
     */
    @Override
    public int delContact(String id) {

//        String sql="delete from contact_info where id=? where ";
        String sql = "update contact_info set del=1 where id=?";

        return jt.update(sql, id);
    }


    /**
     * 修改跳转后的页面，将数据展示到那个页面
     *
     * @return 一个ContactInfo的list集合
     */
    @Override
    public List<ContactInfo> queryById(String id) {

        String sql = "select * FROM contact_info where id=?";

        return jt.query(sql, new BeanPropertyRowMapper<>(ContactInfo.class), id);
    }

    @Override
    public int update(ContactInfo contact) {

        String sql = "update contact_info set name= ?,gender= ?,birthday= ?, birthplace= ?, mobile= ?,email= ? where id= ?";
        return jt.update(sql,
                contact.getName(),
                contact.getGender(),
                contact.getBirthday(),
                contact.getBirthplace(),
                contact.getMobile(),
                contact.getEmail(),
                contact.getId()
        );

    }

    /**
     * 添加数据
     *
     * @param contact
     * @return
     */
    @Override
    public int addContact(ContactInfo contact) {
        String sql = "insert into contact_info (name,gender,birthday,birthplace,mobile,email) values (?,?,?,?,?,?)";
        return jt.update(sql, contact.getName(),contact.getGender(), contact.getBirthday(), contact.getBirthplace(), contact.getMobile(), contact.getEmail());

    }


    /**
     * 通过分页查询
     * @param offSet 传过来的页面的初始位置
     * @param pageSize 当前页的长度
     * @return 查询到的集合
     *
     * 统统换成int的
     */
    @Override
    public List<ContactInfo> queryByPage(int offSet, int pageSize) {



        String sql="select * from contact_info where del=0  limit ? , ?";
        return jt.query(sql,new BeanPropertyRowMapper<>(ContactInfo.class),offSet,pageSize);

    }

    @Override
    public int queryCounts() {
        String sql="select count(*) from contact_info where del=0";

        return jt.queryForObject(sql,Integer.class);

    }

    /**
     * 校验email是否存在
     * @param email
     * @return
     */
    @Override
    public int checkEmail(String email) {
        String sql="select count(*) from contact_info where email=?";
        return jt.queryForObject(sql,Integer.class,email);
    }


    /**
     * 查询数据返回到返回给service中的方法（将其加入到redis）
     * @param offSet limit的第一个关键字
     * @param pageSize limit的第二个关键字
     * @return 返回一段数据。可能不用，因为这样的话还是每次要从sql中查询没有意思。
     */

    @Override
    public List<ContactInfo> queryByPageRedis(int offSet, int pageSize) {
        String sql="select * from contact_info where del=0 limit ? , ?";
//        String sql="select * from contact_info where del=0";
        return jt.query(sql,new BeanPropertyRowMapper<>(ContactInfo.class),offSet,pageSize);

    }
}
