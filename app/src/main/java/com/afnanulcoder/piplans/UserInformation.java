package com.afnanulcoder.piplans;

public class UserInformation
{
    String fullName, organisation, gender, age, eduDegree, country, skype, gitHub, email, numberOfProjects, numberOfProblems;
    boolean isMsgReceived;

    public UserInformation() {
    }

    public UserInformation(String fullName, String organisation, String gender, String age, String eduDegree, String country, String skype, String gitHub, String email, String numberOfProjects, String numberOfProblems, boolean isMsgReceived) {
        this.fullName = fullName;
        this.organisation = organisation;
        this.gender = gender;
        this.age = age;
        this.eduDegree = eduDegree;
        this.country = country;
        this.skype = skype;
        this.gitHub = gitHub;
        this.email = email;
        this.numberOfProjects = numberOfProjects;
        this.numberOfProblems = numberOfProblems;
        this.isMsgReceived = isMsgReceived;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEduDegree() {
        return eduDegree;
    }

    public void setEduDegree(String eduDegree) {
        this.eduDegree = eduDegree;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberOfProjects() {
        return numberOfProjects;
    }

    public void setNumberOfProjects(String numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }

    public String getNumberOfProblems() {
        return numberOfProblems;
    }

    public void setNumberOfProblems(String numberOfProblems) {
        this.numberOfProblems = numberOfProblems;
    }

    public boolean isMsgReceived() {
        return isMsgReceived;
    }

    public void setMsgReceived(boolean msgReceived) {
        isMsgReceived = msgReceived;
    }
}
