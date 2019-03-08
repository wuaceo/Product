package cn.aceo.product.web.servlet;

import cn.aceo.product.dao.impl.UserDaoImpl;
import cn.aceo.product.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDaoImpl userDaoImpl;
    @Override
    public void init() throws ServletException {
        userDaoImpl=new UserDaoImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //1.接收请求参数
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        //2.调用业务处理方法
        User user=new User();
        user= userDaoImpl.getUserByusername(userName);
        //判断用户名是否为null
        if(user==null){
            req.setAttribute("errorMsg","账户名或者密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        //判断密码是否正确
        if(!user.getPassword().equals(password)){
            req.setAttribute("errorMsg","账户名或者密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        //3.控制页面跳转
        //session作用域为本次会话，此处用重定向
        req.getSession().setAttribute("SESSION_ON_USER",user);
       // req.getRequestDispatcher("/product").forward(req,resp);
        resp.sendRedirect("/product");
    }
}
