package com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce;

import com.google.gson.annotations.SerializedName;

public class AdListResponsenew {

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@SerializedName("data")
	public String data;

	@SerializedName("success")
	private int success;


	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}
}