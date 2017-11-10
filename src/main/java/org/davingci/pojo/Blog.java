/**
 *@author huangdongxu
 *@Date Nov 10, 2017
*/

package org.davingci.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blog")
public class Blog {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name="blogId")
	private int blogId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="createTime")
	private Date createTime;
	
	@Column(name="lastModifyTime")
	private Date lastModifyTime;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="username")
	private String username;
	
	

	public Blog() {
		super();

	}
	
	private Blog(BlogBuilder b) {
		this.content = b.content;
		this.createTime = b.createTime;
		this.lastModifyTime = b.lastModifyTime;
		this.title = b.title;
		this.userId = b.userId;
		this.username = b.username;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public static class BlogBuilder {
		
		private int blogId;
		private String title;
		private String content;
		private Date createTime;
		private Date lastModifyTime;
		
		private int userId;
		private String username;
		
		public BlogBuilder() {
			
		}
		
		public BlogBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public BlogBuilder content(String content) {
			this.content = content;
			return this;
		}
		
		public BlogBuilder createTime(Date createTime) {
			this.createTime = createTime;
			return this;
		}
		
		public BlogBuilder lastModifyTime(Date lastModifyTime) {
			this.lastModifyTime = lastModifyTime;
			return this;
		}
		
		public BlogBuilder userId(int userId) {
			this.userId = userId;
			return this;
		}
		
		public BlogBuilder username(String username) {
			this.username = username;
			return this;
		}
		
		public Blog build() {
			return new Blog(this);
		}
		
		
	}

	

}
