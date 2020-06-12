package com.afnanulcoder.piplans;

public class ProjectDetails
{
    String uTitle, uCategory, uShort, uLong, uProgrammingLanguage, uWebLanguage, uFramework, uDatabase, uploaderName, uploaderID;

    public ProjectDetails() {
    }


    public ProjectDetails(String uTitle, String uCategory, String uShort, String uLong, String uProgrammingLanguage, String uWebLanguage, String uFramework, String uDatabase, String uploaderName, String uploaderID) {
        this.uTitle = uTitle;
        this.uCategory = uCategory;
        this.uShort = uShort;
        this.uLong = uLong;
        this.uProgrammingLanguage = uProgrammingLanguage;
        this.uWebLanguage = uWebLanguage;
        this.uFramework = uFramework;
        this.uDatabase = uDatabase;
        this.uploaderName = uploaderName;
        this.uploaderID = uploaderID;
    }


    public String getuTitle() {
        return uTitle;
    }

    public void setuTitle(String uTitle) {
        this.uTitle = uTitle;
    }

    public String getuCategory() {
        return uCategory;
    }

    public void setuCategory(String uCategory) {
        this.uCategory = uCategory;
    }

    public String getuShort() {
        return uShort;
    }

    public void setuShort(String uShort) {
        this.uShort = uShort;
    }

    public String getuLong() {
        return uLong;
    }

    public void setuLong(String uLong) {
        this.uLong = uLong;
    }

    public String getuProgrammingLanguage() {
        return uProgrammingLanguage;
    }

    public void setuProgrammingLanguage(String uProgrammingLanguage) {
        this.uProgrammingLanguage = uProgrammingLanguage;
    }

    public String getuWebLanguage() {
        return uWebLanguage;
    }

    public void setuWebLanguage(String uWebLanguage) {
        this.uWebLanguage = uWebLanguage;
    }

    public String getuFramework() {
        return uFramework;
    }

    public void setuFramework(String uFramework) {
        this.uFramework = uFramework;
    }

    public String getuDatabase() {
        return uDatabase;
    }

    public void setuDatabase(String uDatabase) {
        this.uDatabase = uDatabase;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    public String getUploaderID() {
        return uploaderID;
    }

    public void setUploaderID(String uploaderID) {
        this.uploaderID = uploaderID;
    }
}
