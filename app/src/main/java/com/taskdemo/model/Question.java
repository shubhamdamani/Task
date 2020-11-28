package com.taskdemo.model;

public class Question {

    private String qtitle,qcontent,qtopic,qcompany,qinterview,qjob,qcollege;
    private Integer qup,qid;

    public Question(String qtitle, String qcontent, String qtopic, String qcompany, String qinterview, String qjob, String qcollege, Integer qup, Integer qid) {
        this.qtitle = qtitle;
        this.qcontent = qcontent;
        this.qtopic = qtopic;
        this.qcompany = qcompany;
        this.qinterview = qinterview;
        this.qjob = qjob;
        this.qcollege = qcollege;
        this.qup = qup;
        this.qid = qid;
    }

    public String getQtitle() {
        return qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }

    public String getQcontent() {
        return qcontent;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent;
    }

    public String getQtopic() {
        return qtopic;
    }

    public void setQtopic(String qtopic) {
        this.qtopic = qtopic;
    }

    public String getQcompany() {
        return qcompany;
    }

    public void setQcompany(String qcompany) {
        this.qcompany = qcompany;
    }

    public String getQinterview() {
        return qinterview;
    }

    public void setQinterview(String qinterview) {
        this.qinterview = qinterview;
    }

    public String getQjob() {
        return qjob;
    }

    public void setQjob(String qjob) {
        this.qjob = qjob;
    }

    public String getQcollege() {
        return qcollege;
    }

    public void setQcollege(String qcollege) {
        this.qcollege = qcollege;
    }

    public Integer getQup() {
        return qup;
    }

    public void setQup(Integer qup) {
        this.qup = qup;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }
}
