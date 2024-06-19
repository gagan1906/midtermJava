package com.example.exam1;


public class Diagnosis {
    private String patientName;
    private String diagnosis;
    private String date;

    public Diagnosis(String patientName, String diagnosis, String date) {
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
