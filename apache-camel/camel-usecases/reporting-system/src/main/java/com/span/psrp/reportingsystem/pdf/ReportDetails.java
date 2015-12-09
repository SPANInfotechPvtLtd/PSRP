
package com.span.psrp.reportingsystem.pdf;

import com.span.psrp.reportingsystem.model.AccountInfo;
import com.span.psrp.reportingsystem.model.CustomerInfo;

/**
 * @author mohanarao_sv
 *
 */
public class ReportDetails {

    private CustomerInfo customerInfo;

    private AccountInfo  accountInfo;

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

    /**
     * @return the accountInfo
     */
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    /**
     * @param accountInfo the accountInfo to set
     */
    public void setAccountInfo(final AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }
}
