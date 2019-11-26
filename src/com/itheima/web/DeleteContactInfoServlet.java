package com.itheima.web;

import com.itheima.service.ContactService;
import com.itheima.service.impl.ContactServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_contact_info")
public class DeleteContactInfoServlet extends HttpServlet {
    ContactService service=new ContactServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码数据
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
        //1.接受数据
        String id = request.getParameter("id");
        //2.处理数据

        boolean flag=service.delContact(id);
        //3.响应数据
        if (flag){
            response.sendRedirect("change_page_size");
        }else {
            request.setAttribute("errorMsg","删除出错拉");
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
