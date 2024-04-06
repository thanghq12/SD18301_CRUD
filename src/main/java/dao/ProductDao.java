/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entity.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 *
 * @author hoangquangthang
 */
public class ProductDao {
    private final SessionFactory sessionFactoty;
    
    public ProductDao(SessionFactory sessionFactoty) {
        this.sessionFactoty = sessionFactoty;
    }
    // thuwcj hien lay data tu db ve
    public List<Product> getAllProducts() {
        try (Session session = sessionFactoty.openSession()) {
               return session.createQuery("FROM Product",Product.class).list();
        }
    }
    public void addProduct(Product product)   {
          Session session = null ;
          Transaction transaction = null;
          try {
              session = sessionFactoty.openSession();
              transaction = session.beginTransaction();
              session.save(product);
              transaction.commit();
          } catch(Exception e) {
              if (transaction != null) {
                transaction.rollback();
              }
              e.printStackTrace(); // biết lỗi khi mà thêm không thành công 
          } finally {
              if (session != null) {
                  session.close();
              }
          }
    }
     public Product getProductById(int productId)   {
          Session session = null ;
          Transaction transaction = null;
          Product product = null;
          try {
              session = sessionFactoty.openSession();
              transaction = session.beginTransaction();
               String sql = "FROM Product WHERE id = :productId";
               Query query = session.createQuery(sql);
               query.setParameter("productId",productId);
               // hứng data khi product trả về 
               product = (Product) query.getSingleResult();
              transaction.commit();
          } catch(Exception e) {
              if (transaction != null) {
                transaction.rollback();
              }
              e.printStackTrace(); // biết lỗi khi mà thêm không thành công 
          } finally {
              if (session != null) {
                  session.close();
              }
          }
          return product;
          
    }
     // phương thức cập nhập thông tin product 
       public void updateProduct(Product product)   {
          Session session = null ;
          Transaction transaction = null;
          try {
              session = sessionFactoty.openSession();
              transaction = session.beginTransaction();
              session.update(product);
              transaction.commit();
          } catch(Exception e) {
              if (transaction != null) {
                transaction.rollback();
              }
              e.printStackTrace(); // biết lỗi khi mà thêm không thành công 
          } finally {
              if (session != null) {
                  session.close();
              }
          }
    }
       //xóa sản phẩm
       public void deleteProductById(int productId)   {
          Session session = null ;
          Transaction transaction = null;
         
          try {
              session = sessionFactoty.openSession();
              transaction = session.beginTransaction();
              Product product = session.get(Product.class,productId );
              if(product != null) {
                  session.delete(product);
              }
              transaction.commit();
          } catch(Exception e) {
              if (transaction != null) {
                transaction.rollback();
              }
              e.printStackTrace(); // biết lỗi khi mà thêm không thành công 
          } finally {
              if (session != null) {
                  session.close();
              }
          }
      
          
    }
}
