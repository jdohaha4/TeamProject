package com.study.android.team_project;

public class EBoardDTO {
    private String esitter;  // 펫시터 사진
    private String ename;   // 작성자 이름
    private String edate;   // 날짜
    private String estar;   // 평점
    private String econtent;    // 내용
    private String etitle; // 제목

    public EBoardDTO(String ename, String edate, String estar, String econtent, String esitter, String etitle) {
        this.esitter = esitter;
        this.ename = ename;
        this.edate = edate;
        this.estar = estar;
        this.econtent = econtent;
        this.etitle = etitle;
    }

    public String getEsitter() {
        return esitter;
    }

    public void setEsitter(String esitter) {
        this.esitter = esitter;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }


    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getEstar() {
        return estar;
    }

    public void setEstar(String estar) {
        this.estar = estar;
    }

    public String getEcontent() {
        return econtent;
    }

    public void setEcontent(String econtent) {
        this.econtent = econtent;
    }

    public String getEtitle() {
        return etitle;
    }

    public void setEtitle(String etitle) {
        this.etitle = etitle;
    }


}

