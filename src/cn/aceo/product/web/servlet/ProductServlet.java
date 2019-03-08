package cn.aceo.product.web.servlet;

import cn.aceo.product.dao.impl.ProductDaoImpl;
import cn.aceo.product.dao.impl.ProductDirDaoImpl;
import cn.aceo.product.domain.Product;
import cn.aceo.product.domain.ProductDir;
import cn.aceo.product.query.ProductQueryObject;
import cn.aceo.product.template.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

//导包 Servlet-api
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private ProductDaoImpl productDao;
    private ProductDirDaoImpl productDirDao;

    @Override
    public void init() throws ServletException {
        productDao=new ProductDaoImpl();
        productDirDao=new ProductDirDaoImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Object userSession = req.getSession().getAttribute("SESSION_ON_USER");
        //判断session是否为空
        if(userSession==null){
            req.setAttribute("errorMsg","请先登录");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        //req.setAttribute("user",user);
        String cmd=req.getParameter("cmd");
        if("save".equals(cmd)){
            this.updateOrSave(req,resp);
        }else if("delete".equals(cmd)){
            this.delete(req,resp);
        }else if("edit".equals(cmd)){
            this.edit(req,resp);
        }else {
            this.list(req,resp);
        }
    }

    /**
     * 显示列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收请求参数
        //2.调用业务方法
        ProductQueryObject queryObject=new ProductQueryObject();
        this.request2QueryObj(req,queryObject);
        req.setAttribute("queryObject",queryObject);

        List<Product> list=productDao.query(queryObject);
//        productDao.listAll();
        req.setAttribute("products",list);

        List<ProductDir> productDirs = productDirDao.listAll();
        req.setAttribute("Dirs",productDirs);
        //3.控制劫面跳转
        req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req,resp);

    }

    private void request2QueryObj(HttpServletRequest req, ProductQueryObject queryObject) {
        String name = req.getParameter("name");
        String minSalePrice = req.getParameter("minSalePrice");
        String maxSalePrice = req.getParameter("maxSalePrice");
        String dir_id=req.getParameter("dir_id");

        if (hasLength(name)){queryObject.setName(name);}
        if (hasLength(minSalePrice)){queryObject.setMinSalePrice(new BigDecimal(minSalePrice));}
        if(hasLength(maxSalePrice)){queryObject.setMaxSalePrice(new BigDecimal(maxSalePrice));}
        if(hasLength(dir_id)){queryObject.setDir_id(Long.valueOf(dir_id));}

    }


    /**
     * 更新和保存
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateOrSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收请求参数，封装成对象
        String id=req.getParameter("id");
        Product product=new Product();
        this.request2Obj(req,product);
        //2.调用业务逻辑
        if(hasLength(id)){
            productDao.update(Long.valueOf(id),product);
        }else{
            productDao.save(product);
        }
        //3.控制界面跳转
        //req.getRequestDispatcher("/product").forward(req,resp);XXXXXX错误的
        resp.sendRedirect("/product");
    }

    /**
     * 删除
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收
        String id=req.getParameter("id");
        //2.处理
        if (hasLength(id)){
            productDao.delete(Long.valueOf(id));
        }
        //3.跳转
        resp.sendRedirect("/product");

    }

    /**
     * 帮助跳转
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收请求参数
        String id=req.getParameter("id");
//        req.setAttribute("id",id);
        //2.调用业务方法
        List<ProductDir> productDirs=productDirDao.listAll();
        req.setAttribute("dir_list",productDirs);
        //************这里显示***********
        if(hasLength(id)){
            Product product=new Product();
            product=productDao.get(Long.valueOf(id));
            req.setAttribute("products",product);
        }
        //3.控制界面跳转

        req.getRequestDispatcher("/WEB-INF/views/product/edit.jsp").forward(req,resp);
    }
    //判断字符是否为空
    private Boolean hasLength(String str){
        return str!=null&&!"".equals(str.trim());
    }
    //封装
    private void request2Obj(HttpServletRequest req,Product product){
        String productName= req.getParameter("productName");
        String brand= req.getParameter("brand");
        String supplier= req.getParameter("supplier");
        String salePrice= req.getParameter("salePrice");
        String costPrice= req.getParameter("costPrice");
        String cutoff= req.getParameter("cutoff");
        String dir_id= req.getParameter("dir_id");
        //应该判断是否为空

        product.setProductName(productName);
        product.setBrand(brand);
        product.setSupplier(supplier);
        product.setSalePrice(new BigDecimal(salePrice));
        product.setCostPrice(new BigDecimal(costPrice));
        product.setCutoff(Double.valueOf(cutoff));
        product.setDir_id(Long.valueOf(dir_id));

    }
}
