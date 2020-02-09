package com.bn.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CUSTOMER_ORDER_DETAILS")
public class CustomerOrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderDetailsId;

	@ManyToOne()
	@JoinColumn(name = "ORDER_ID")
	private CustomerOrder orderId;
	
	@Column(name = "BOOK_ID")
	private long bookId;
	
	@Column(name = "CUSTOMER_ID")
	private long customerId;
	
	@Column(name = "BOOK_NAME")
	private String bookName;
	
	@Column(name = "AUTHOR")
	private String bookAuthor;
	
	@Column(name = "PRICE")
	private float price;

	public long getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(long orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public CustomerOrder getOrderId() {
		return orderId;
	}

	public void setOrderId(CustomerOrder orderId) {
		this.orderId = orderId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
	

}
