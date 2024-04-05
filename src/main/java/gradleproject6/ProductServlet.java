/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package gradleproject6;

import dao.ProductDao;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author hoangquangthang
 */
@WebServlet(name="ProductServlet",urlPatterns = {"/product/list","/product/add","/product/edit"})
public class ProductServlet extends HttpServlet {
    private ProductDao productDao;
//    public List<Product> dataList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.productDao = new ProductDao(HibernateUtil.getSessionFactory());
    }
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        List<Product> products = productDao.getAllProducts(); // list nhaanj tu csdl via
        String uri = request.getRequestURI();
        request.setAttribute("products", products);
        if(uri.contains("list"))  {
             request.getRequestDispatcher("/views/index.jsp").forward(request, response);
        } else if(uri.contains("add")) {
             request.getRequestDispatcher("/views/add.jsp").forward(request, response);
        } else if (uri.contains("edit")) {
            int productID = Integer.parseInt(request.getParameter("id"));
             Product product =  productDao.getProductById(productID);
             request.setAttribute("product", product);
             request.getRequestDispatcher("/views/edit.jsp").forward(request, response);
        }
         
//        System.out.println("abc" + products);
       
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
        // nếu nhập trống hiển thị ra mời nhập vào giá 
        //trống tên hiển thị ra mời nhập vào tên 
        // Tạo 1 mảng lưu trữ lỗi 
      
        List<String> errors = new ArrayList<>();
        String uri = request.getRequestURI();  
   
        if(uri.contains("add")) {
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            //xử lý thêm trong này 
            if(name.isEmpty()) {
                errors.add("Tên sản phẩm khum được trống");
            }
            if (price.isEmpty()) {
                errors.add("Giá sản phẩm không được trống");
            }
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/views/add.jsp").forward(request, response);
            } // có lỗi
            else {
            
            Product product = new Product(name, Double.parseDouble(price));
            productDao.addProduct(product);
            response.sendRedirect("/gradleproject6/product/list");
            }
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
