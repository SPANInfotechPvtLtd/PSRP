package com.span.psrp.apache.camel.topics.recipientlist;

public class Employee {

    private boolean      isResigning;
    private boolean      isNew;
    private boolean      isOnLeave;
    private boolean      isPromoted;
    private String       msg;
    private final String name;

    public Employee(final String name) {
        this.name = name;
    }

    public void setResigning(final boolean isResigning) {
        this.isResigning = isResigning;
    }

    public void setNew(final boolean isNew) {
        this.isNew = isNew;
    }

    public void setOnLeave(final boolean isOnLeave) {
        this.isOnLeave = isOnLeave;
    }

    public void setPromoted(final boolean isPromoted) {
        this.isPromoted = isPromoted;
    }

    public boolean isResigning() {
        return isResigning;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isOnLeave() {
        return isOnLeave;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setMessage(final String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Employee " + name + " " + msg;
    }
}