package com.itheima.web;

import com.itheima.domain.ContactInfo;
import com.itheima.service.ContactService;
import com.itheima.service.impl.ContactServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/change_page_size")
public class ChangePageSizeServlet extends HttpServlet {
    ContactService service = new ContactServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        //1.接受数据

        //2.处理数据

        int queryRecordCounts = service.queryCounts();
        int pageSize = 5;
        int currentPage = 1;


        String currentPage1 = request.getParameter("currentPage");
        if (currentPage1 != null) {
            currentPage = Integer.parseInt(currentPage1);
        }


        String pageSize1 = request.getParameter("pageSize");
        if (pageSize1 != null) {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }

        //这边不能加一？？
        int offSet = (currentPage - 1) * pageSize;

        //通过前端数据动态获取页数，然后在后端进行操作
        int pages = (int) Math.ceil(queryRecordCounts / (double) pageSize);

//
        /**
         * 传一个offSet limit的第一个关键字。传一个pageSize第二个关键字。晚上去把底下的dao，service文件夹中的与分页有关的方法通通换为int
         * 在把页面间的跳转撸清楚
         *老式方法，从数据库中抽取文件
         */
        List<ContactInfo> contacts = service.queryByPage(offSet, pageSize);
        //3.响应数据
        request.setAttribute("contacts", contacts);
        request.setAttribute("pages", pages);
        /**
         *新式方法，使用redis
         */


        request.setAttribute("currentPage", currentPage);

        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
