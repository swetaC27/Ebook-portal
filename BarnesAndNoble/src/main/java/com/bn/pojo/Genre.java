package com.bn.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "GENRE")
public class Genre {
	
	public Genre() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GENRE_ID")
	private long genreId;
	
	@Column(name = "GENRE_NAME")
	private String genreName;
	
	@OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
	private Set<Book> books = new HashSet<Book>();
	
	public Genre(String genreName) {
		this.genreName = genreName;	
		this.books = new HashSet<Book>();
	}
	
	public long getGenreId() {
		return genreId;
	}

	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	 public void addBook(Book book) {
         getBooks().add(book);
     }

	
}
