package com.afnanulcoder.piplans;

public class ProblemDetails {

    String sTitle, sCategory, sShort, sLong, sHireOrBuy, submitterName, submitterID;

    public ProblemDetails() {
    }

    public ProblemDetails(String sTitle, String sCategory, String sShort, String sLong, String sHireOrBuy, String submitterName, String submitterID) {
        this.sTitle = sTitle;
        this.sCategory = sCategory;
        this.sShort = sShort;
        this.sLong = sLong;
        this.sHireOrBuy = sHireOrBuy;
        this.submitterName = submitterName;
        this.submitterID = submitterID;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsCategory() {
        return sCategory;
    }

    public void setsCategory(String sCategory) {
        this.sCategory = sCategory;
    }

    public String getsShort() {
        return sShort;
    }

    public void setsShort(String sShort) {
        this.sShort = sShort;
    }

    public String getsLong() {
        return sLong;
    }

    public void setsLong(String sLong) {
        this.sLong = sLong;
    }

    public String getsHireOrBuy() {
        return sHireOrBuy;
    }

    public void setsHireOrBuy(String sHireOrBuy) {
        this.sHireOrBuy = sHireOrBuy;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public String getSubmitterID() {
        return submitterID;
    }

    public void setSubmitterID(String submitterID) {
        this.submitterID = submitterID;
    }
}
