package vn.com.hiringviet.service;

import vn.com.hiringviet.model.Account;

public interface AccountService {

	public Account checkLogin(String email, String password);
	
	public boolean isExistedAccount(String email);
}