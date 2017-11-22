/**
 *@author huangdongxu
 *@Date Nov 21, 2017
 *some one can add the blog into his favourite list
*/

package org.davingci.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="favourite")
public class Favourite {
	
	@Id @GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="blogId")
	private int blogId;
	
	@Column(name="authorId")
	private int authorId;
	
	@Column(name="favouriteUserId")
	private int favouriteUserId;
	
	@Column(name="createAt")
	private Date createAt;

	

	public Favourite() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Favourite authorId(int authorId) {
		this.authorId = authorId;
		return this;
	}
	
	public Favourite blogId(int blogId) {
		this.blogId = blogId;
		return this;
	}
	
	public Favourite favouriteUserId(int favouriteUserId) {
		this.favouriteUserId = favouriteUserId;
		return this;
	}
	
	public Favourite createAt(Date createAt) {
		this.createAt = createAt;
		return this;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getBlogId() {
		return blogId;
	}


	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}


	public int getAuthorId() {
		return authorId;
	}


	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}


	public int getFavouriteUserId() {
		return favouriteUserId;
	}


	public void setFavouriteUserId(int favouriteUserId) {
		this.favouriteUserId = favouriteUserId;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	

	
	
	

}
