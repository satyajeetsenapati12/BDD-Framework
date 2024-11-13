package com.constant;

public class ApiConst {
	
	public String access_token;
	String instance_url;
	String id;
	String token_type;
	String issued_at;
	String signature;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getInstance_url() {
		return instance_url;
	}
	public void setInstance_url(String instance_url) {
		this.instance_url = instance_url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getIssued_at() {
		return issued_at;
	}
	public void setIssued_at(String issued_at) {
		this.issued_at = issued_at;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "BearerToken [access_token=" + access_token + ", instance_url=" + instance_url + ", id=" + id
				+ ", token_type=" + token_type + ", issued_at=" + issued_at + ", signature=" + signature + "]";
	}
	

}
