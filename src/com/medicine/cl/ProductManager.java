package com.medicine.cl;

import java.util.List;

import com.medicine.dao.ProductDAO;
import com.medicine.dao.SetProductDAO;
import com.medicine.entities.Product;
import com.medicine.entities.SetProduct;
import com.medicine.util.MedicineRuntimeException;

public class ProductManager extends DAOManager {
	private ProductDAO productDAO = new ProductDAO();
	private SetProductDAO setProductDAO = new SetProductDAO();

	public List<Product> findAllProducts(String productName, String herbsType) {
		List<Product> products = null;
		products = productDAO.findAllProducts(productName,herbsType);
		for (Product product : products) {
			List<SetProduct> setProducts = setProductDAO.findAllSetProducts(String.valueOf(product.getProductCode()));
			product.setSetProducts(setProducts);
		}
		return products;
	}
	
	public Product findProductByCodeOrName(String productCodeOrName) {
		Product product = null;
		product = productDAO.findProductByCodeOrName(productCodeOrName);
		return product;
	}
	
	public int addProduct(Product product) {
		int count = 0;
		try {
			this.beginTransaction();
			count = productDAO.addProduct(product);
			if (product.getHerbsType().equals("成套")) {
				for (SetProduct setProduct : product.getSetProducts() ) {
					setProduct.setSetProductCode(String.valueOf(product.getProductCode()));
					setProductDAO.addSetProduct(setProduct);
				}
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public int updateProduct(Product product) {
		int count = 0;
		try {
			this.beginTransaction();
			count = productDAO.updateProduct(product);
			int num = setProductDAO.deleteSetProduct(String.valueOf(product.getProductCode()));
			if (num != 0) {
				for (SetProduct setProduct : product.getSetProducts()) {
					setProduct.setSetProductCode(String.valueOf(product.getProductCode()));
					setProductDAO.addSetProduct(setProduct);
				}
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
	public int deleteProduct(int[] ids) {
		int count = 0;
		try {
			this.beginTransaction();
			for (int id : ids) {
				count = productDAO.deleteProduct(id);
				setProductDAO.deleteSetProductByProductId(id);
				count++;
			}
			this.commitTransaction();
		} catch (Exception e) {
			this.rollbackTransaction();
			throw new MedicineRuntimeException(e.getMessage());
		}
		return count;
	}
	
}
