/**
 *@author huangdongxu
 *@Date Nov 21, 2017
*/

package org.davingci.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="view")
public class View {
	
	@Id @GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="blogId")
	private int blogId;
	
	@Column(name="authorId")
	private int authorId;
	
	@Column(name="viewUserId")
	private int viewUserId;
	
	@Column(name="createAt")
	private Date createAt;
	
	

	public View() {
		super();
		
	}
	
	
	public View authorId(int authorId) {
		this.authorId = authorId;
		return this;
	}
	
	public View blogId(int blogId) {
		this.blogId = blogId;
		return this;
	}
	
	public View viewUserId(int viewUserId) {
		this.viewUserId = viewUserId;
		return this;
	}
	
	public View createAt(Date createAt) {
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

	public int getViewUserId() {
		return viewUserId;
	}

	public void setViewUserId(int viewUserId) {
		this.viewUserId = viewUserId;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	

}
