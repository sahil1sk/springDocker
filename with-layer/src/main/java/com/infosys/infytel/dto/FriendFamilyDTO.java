package com.infosys.infytel.dto;

import com.infosys.infytel.entity.FriendFamily;

public class FriendFamilyDTO {
	long phoneNo;
	long friendAndFamily;

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public long getFriendAndFamily() {
		return friendAndFamily;
	}

	public void setFriendAndFamily(long friendAndFamily) {
		this.friendAndFamily = friendAndFamily;
	}

	public FriendFamily createFriend() {
		FriendFamily friend = new FriendFamily();
		friend.setPhoneNo(this.getPhoneNo());
		friend.setFriendAndFamily(this.getFriendAndFamily());
		return friend;
	}
}