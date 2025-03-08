package productapp.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import productapp.entities.Product;

@Component
@Transactional
@EnableTransactionManagement
public class ProductDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
	     this.sessionFactory = sessionFactory;
	}
	
	// Create-Update
	public void createProduct(Product product) {
		if(Objects.isNull(sessionFactory.getCurrentSession().find(Product.class, product.getProductId()))) {
			this.sessionFactory.getCurrentSession().persist(product);
		} else {
			this.sessionFactory.getCurrentSession().merge(product);
		}
	}
	
	// Get single product
	public Product getProduct(int productId) {
		Product product = this.sessionFactory.getCurrentSession().get(Product.class, productId);
		return product;
	}
	
	// Get multiple products
	public List<Product> getProducts() {
		List<Product> productList = 
				this.sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
		return productList;
	}
	
	// Delete Product by Id
	public void deleteProduct(int productId) {
		Product deleteProduct = this.sessionFactory.getCurrentSession().get(Product.class, productId);
		this.sessionFactory.getCurrentSession().remove(deleteProduct);
	}
}
