package com.itheima.web;

import com.itheima.dao.ContactDao;
import com.itheima.service.ContactService;
import com.itheima.service.impl.ContactServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/query_by_pages_redis_json")
public class QueryByPagesRedisJsonServlet extends HttpServlet {
    ContactService service = new ContactServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        //1.接收数据
        //2.处理数据
        int currentPage = 1;
        int totalContactsCount = service.queryCounts();
        int pageSize = 5;

        String currentPage1 = request.getParameter("currentPage");
        String pageSize1 = request.getParameter("pageSize");
        if (currentPage1 != null) {
            currentPage = Integer.parseInt(currentPage1);
        }

        if (pageSize1 != null) {
            pageSize = Integer.parseInt(pageSize1);
        }


        int pages = (int) Math.ceil(totalContactsCount / (double) pageSize);

        /**
         * 这里的offSet即是在数据库中查询的limit的第一个关键字，也是在redis中查询的第一个关键字。只不过redis中的第二个关键字是
         * offSet加上pageSize
         */
        int offSet=(currentPage-1)*pageSize;
        String ContactsDataJson=service.queryByPageRedis(offSet,pageSize);
        System.out.println("ContactsDataJson = " + ContactsDataJson);

        //3.响应数据
//        request.setAttribute("currentPage", currentPage);
//        request.setAttribute("pageSize", pageSize);
//        request.setAttribute("pages", pages);


        response.setContentType("text/json; charset=utf-8");
        response.getWriter().write(ContactsDataJson);

//        request.getRequestDispatcher("list_pages_redis.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
