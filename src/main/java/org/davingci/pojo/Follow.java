/**
 *@author huangdongxu
 *@Date Nov 21, 2017
 *someone could follow another one
 * 
*/

package org.davingci.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="follow")
public class Follow {
	
	@Id @GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="followeeId")
	private int followeeId;
	
	@Column(name="followerId")
	private int followerId;
	
	@Column(name="createAt")
	private Date createAt;
	
	

	public Follow() {
		super();
		// TODO Auto-generated constructor stub
	}



	
	
	public Follow followeeId(int followeeId) {
		this.followeeId = followeeId;
		return this;
	}
	
	public Follow followerId(int followerId) {
		this.followerId = followerId;
		return this;
	}

	public Follow createAt(Date createAt) {
		this.createAt = createAt;
		return this;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}





	public int getFolloweeId() {
		return followeeId;
	}





	public void setFolloweeId(int followeeId) {
		this.followeeId = followeeId;
	}





	public int getFollowerId() {
		return followerId;
	}





	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}





	public Date getCreateAt() {
		return createAt;
	}





	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}



	
	

}
