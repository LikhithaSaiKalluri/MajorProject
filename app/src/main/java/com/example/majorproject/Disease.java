package com.example.majorproject;

public class Disease {
    private String Diseasename;
    private String Symptoms;
    private String Prevention;
    private String Areaofspread;
    private String Fatality;

    public Disease() {
    }

    public String getDiseasename() {
        return Diseasename;
    }

    public void setDiseasename(String diseasename) {
        Diseasename = diseasename;
    }

    public String getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(String symptoms) {
        Symptoms = symptoms;
    }

    public String getPrevention() {
        return Prevention;
    }

    public void setPrevention(String prevention) {
        Prevention = prevention;
    }

    public String getAreaofspread() {
        return Areaofspread;
    }

    public void setAreaofspread(String areaofspread) {
        Areaofspread = areaofspread;
    }

    public String getFatality() {
        return Fatality;
    }

    public void setFatality(String fatality) {
        Fatality = fatality;
    }
}
