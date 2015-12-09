package com.span.psrp.reportingsystem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity(name="ACCOUNT_INFO")
@NamedQuery(name = "accountRead", query = "select acct from ACCOUNT_INFO acct where acct.customerId = :customerId")
public class AccountInfo implements Serializable {

	private static final long serialVersionUID = -3679559260483352707L;
	@Id
    @Column(name="ACCOUNT_ID")
	private  Integer accountId;
	@Column(name="ACCOUNT_NO")
	private  String accountNo;
	@Column(name="ACCOUNT_TYPE")
	private  String accountType;
	@Column(name="FK_ACCOUNT_CUST_ID")
	private  String customerId;
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
