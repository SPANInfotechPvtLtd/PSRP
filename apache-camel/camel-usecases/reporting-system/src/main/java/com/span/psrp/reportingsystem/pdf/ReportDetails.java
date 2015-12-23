
package com.span.psrp.reportingsystem.pdf;

import java.util.ArrayList;
import java.util.List;

import com.span.psrp.reportingsystem.model.AccountInfo;
import com.span.psrp.reportingsystem.model.CustomerInfo;

/**
 * @author mohanarao_sv
 *
 */
public class ReportDetails {

    private CustomerInfo      customerInfo;

    private List<AccountInfo> accountInfo;

    /**
     * @return the accountInfo
     */
    public List<AccountInfo> getAccountInfo() {
        if (accountInfo == null) {
            accountInfo = new ArrayList<AccountInfo>();
        }
        return accountInfo;
    }

    /**
     * @param accountInfo the accountInfo to set
     */
    public void setAccountInfo(final List<AccountInfo> accountInfo) {
        this.accountInfo = accountInfo;
    }

    /**
     * @return the customerInfo
     */
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    /**
     * @param customerInfo the customerInfo to set
     */
    public void setCustomerInfo(final CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

}
