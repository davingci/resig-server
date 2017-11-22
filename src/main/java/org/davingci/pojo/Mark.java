/**
 *@author huangdongxu
 *@Date Nov 21, 2017
 *someone can mark the blog
*/

package org.davingci.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mark")
public class Mark {

	@Id @GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="blogId")
	private int blogId;
	
	@Column(name="authorId")
	private int authorId;
	
	@Column(name="markUserId")
	private int markUserId;
	
	@Column(name="createAt")
	private Date createAt;

	public Mark() {
		super();
		
	}
	
	public Mark authorId(int authorId) {
		this.authorId = authorId;
		return this;
	}
	
	public Mark blogId(int blogId) {
		this.blogId = blogId;
		return this;
	}
	
	public Mark markUserId(int markUserId) {
		this.markUserId = markUserId;
		return this;
	}
	
	public Mark createAt(Date createAt) {
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

	public int getMarkUserId() {
		return markUserId;
	}

	

	public void setMarkUserId(int markUserId) {
		this.markUserId = markUserId;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
}
