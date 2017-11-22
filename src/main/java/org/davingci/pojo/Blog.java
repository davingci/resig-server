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
	
	
	@Column(name="createAt")
	private Date createAt;
	
	@Column(name="lastModifyAt")
	private Date lastModifyAt;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="html")
	private String html;
	
	@Column(name="markdown")
	private String markdown;
	
	@Column(name="blogEditor")
	private String blogEditor;
	
	@Column(name="viewCount")
	private int viewCount;
	
	@Column(name="favouriteCount")
	private int favouriteCount;
	
	@Column(name="markCount")
	private int markCount;

	public Blog() {
		super();

	}
	
	private Blog(BlogBuilder b) {
		
		this.title = b.title;
		this.html = b.html;
		this.markdown = b.markdown;
		this.blogEditor = b.blogEditor;
		
		this.createAt = b.createAt;
		this.lastModifyAt = b.lastModifyAt;
		
		
		
		this.userId = b.userId;
		this.username = b.username;
	}

	
	public void addFavourite() {
		this.favouriteCount++;
	}
	
	public void cancleFavourite() {
		this.favouriteCount--;
	}
	
	public void addMark() {
		this.markCount++;
	}
	
	public void cancleMark() {
		this.markCount--;
	}
	
	public int getFavouriteCount() {
		return favouriteCount;
	}

	public void setFavouriteCount(int favouriteCount) {
		this.favouriteCount = favouriteCount;
	}

	public int getMarkCount() {
		return markCount;
	}

	public void setMarkCount(int markCount) {
		this.markCount = markCount;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getMarkdown() {
		return markdown;
	}

	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getLastModifyAt() {
		return lastModifyAt;
	}

	public void setLastModifyAt(Date lastModifyAt) {
		this.lastModifyAt = lastModifyAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBlogEditor() {
		return blogEditor;
	}

	public void setBlogEditor(String blogEditor) {
		this.blogEditor = blogEditor;
	}
	

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}











	public static class BlogBuilder {
		

		private String title;
		private String content;
		private String html;
		private String markdown;
		private String blogEditor;
		
		private Date createAt;
		private Date lastModifyAt;
		
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
		
		public BlogBuilder html(String html) {
			this.html = html;
			return this;
		}
		
		public BlogBuilder markdown(String markdown) {
			this.markdown = markdown;
			return this;
		}
		
		public BlogBuilder blogEditor(String blogEditor) {
			this.blogEditor = blogEditor;
			return this;
		}
		
		
		public BlogBuilder createAt(Date createAt) {
			this.createAt = createAt;
			return this;
		}
		
		public BlogBuilder lastModifyAt(Date lastModifyAt) {
			this.lastModifyAt= lastModifyAt;
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
