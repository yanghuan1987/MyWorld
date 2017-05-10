package com.spfood.pms.intf.domain;

import java.util.List;

import com.spfood.kernel.domain.DomainObject;

public class CompositeProduct implements DomainObject{

	private static final long serialVersionUID = 1L;

	private Product product;
	
	private List<ProductItem> productItems;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ProductItem> getProductItems() {
		return productItems;
	}

	public void setProductItems(List<ProductItem> productItems) {
		this.productItems = productItems;
	}	
}
