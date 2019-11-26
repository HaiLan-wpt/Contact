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

@WebServlet("/update_query")
public class UpdateQueryServlet extends HttpServlet {
    /**
     * 这是设置其更新页面有数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    ContactService service = new ContactServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        //1.接受数据
        String id = request.getParameter("id");
        ContactInfo contact = service.queryById(id);
        //2.处理数据
        if (contact != null) {
            request.setAttribute("contact", contact);
            request.getRequestDispatcher("/update.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMsg", "更新页面出错拉");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        //3.响应数据
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
