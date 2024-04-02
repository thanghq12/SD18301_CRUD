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
}
