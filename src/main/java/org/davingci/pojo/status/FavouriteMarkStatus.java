/**
 *@author huangdongxu
 *@Date Nov 22, 2017
*/

package org.davingci.pojo.status;

public enum FavouriteMarkStatus {
	
	FavoritedMarked(3,"Favourited and Marked"),
	UnFavouriteMarked(1,"UnFavourite but Marked"),
	FavouritedUnMark(2,"Favourited but UnMark"),
	UnFavouriteUnMark(0,"UnFavourite and UnMark");
	
	public String content;
	public int id;
	
	private FavouriteMarkStatus(int id, String content) {
		this.id = id;
		this.content = content;
	}
	

}
