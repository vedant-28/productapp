package productapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import productapp.dao.ProductDao;
import productapp.entities.Product;

@Controller
public class MainController {
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping("/")
	public String home(Model model) {
		List<Product> products = productDao.getProducts();
		model.addAttribute("products", products);
		
		return "index";
	}
	
	@RequestMapping("/add-product")
	public String addProduct(Model model) {
		model.addAttribute("title", "Add Product");
		return "addProductForm";
	}
	
	@RequestMapping(value = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleAddProduct(@ModelAttribute Product product,
										 HttpServletRequest request) {
		System.out.println(product);
		productDao.createProduct(product);
		RedirectView view = new RedirectView();
		view.setUrl(request.getContextPath() + "/");
		
		return view;
	}
	
	@RequestMapping("/delete-product/{productId}")
	public RedirectView handleDeleteProduct(@PathVariable("productId") int productId, 
																	   HttpServletRequest request) {
		productDao.deleteProduct(productId);
		
		RedirectView view = new RedirectView();
		view.setUrl(request.getContextPath() + "/");
		
		return view;
	}
	
	@RequestMapping("/update-product/{productId}")
	public String handleUpdateProduct(@PathVariable("productId") int productId, Model model) {
		Product product = productDao.getProduct(productId);
		model.addAttribute("product" ,product);
		
		return "updateProductForm";
	}
	
}
