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
@Table(name="user")
public class User {
	
	@Id @GeneratedValue
	@Column(name="userId")
	private int userId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="avatarUrl")
	private String avatarUrl;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobilePhone")
	private String mobilePhone;
	
	@Column(name="createAt")
	private Date createAt;
	
	@Column(name="followerCount")
	//关注的数量
	private int followerCount;
	
	@Column(name="followeeCount") 
	//粉丝的数量
	private int followeeCount;

	public User() {
		super();
		
	}
	
	
	
	

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(UserBuilder b) {
		this.avatarUrl = b.avatarUrl;
		this.createAt = b.createAt;
		this.email = b.email;
		this.mobilePhone = b.mobilePhone;
		this.password = b.password;
		this.username = b.username;
	}


	public static class UserBuilder {
		private String username;
		private String password;
		private String avatarUrl;
		private Date createAt;
		private String email;
		private String mobilePhone;
		
		public UserBuilder() {
			
		}
		
		public UserBuilder username(String username) {
			this.username = username;
			return this;
		}
		
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder avatarUrl(String avatarUrl) {
			this.avatarUrl = avatarUrl;
			return this;
		}
		
		public UserBuilder createAt(Date createAt) {
			this.createAt = createAt;
			return this;
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public UserBuilder mobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}

	
	public void addFollower() {
		this.followerCount++;
	}
	
	public void addFollowee() {
		this.followeeCount++;
	}
	
	public void deleteFollower() {
		this.followerCount--;
	}
	
	public void deleteFollowee() {
		this.followeeCount--;
	}
	

	public int getFollowerCount() {
		return followerCount;
	}





	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}





	public int getFolloweeCount() {
		return followeeCount;
	}





	public void setFolloweeCount(int followeeCount) {
		this.followeeCount = followeeCount;
	}





	public int getUserId() {
		return userId;
	}





	public void setUserId(int userId) {
		this.userId = userId;
	}





	public String getAvatarUrl() {
		return avatarUrl;
	}





	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getMobilePhone() {
		return mobilePhone;
	}





	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}





	public Date getCreateAt() {
		return createAt;
	}





	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}





	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
