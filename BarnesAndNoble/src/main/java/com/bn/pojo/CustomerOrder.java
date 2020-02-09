package com.bn.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrder {

	public CustomerOrder() {

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private long orderId;

	@Column(name = "NUMBER_OF_ITEMS")
	private int noOfItems;

	@Column(name = "ORDER_DATE")
	private String orderDate;
	
	@ManyToOne()
	@JoinColumn(name = "USER_ID")
	private Customer customer;
	
	@OneToMany(mappedBy = "orderId")
	private Set<CustomerOrderDetails> orderDetails = new HashSet<CustomerOrderDetails>();

	public CustomerOrder(String orderDate, int noOfItems) {
		this.noOfItems = noOfItems;
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<CustomerOrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<CustomerOrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


} 



