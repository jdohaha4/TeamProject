package com.study.android.team_project;

public class MyPageCheckReservationDTO {
    private String b_visit_pet_sitter;      // 펫시터 이름
    private String b_visit_type;           // 방문 유형
    private String b_count;                // 방문 횟수
    private String b_reserve_day;          // 예약 요일
    private String b_use_time;             // 이용 시간
    private String b_visit_startdate;     // 방문 시작일
    private String b_visit_time;           // 방문 시간
    private String b_visit_endDate;       // 방문 종료일
    private String b_sysdate;             // 결제 일시
    private String b_totalcount;         // 결제 금액
    private String b_sitter_type;        // 산책 / 돌봄
    private String b_request;            // 요구사항
    private String c_id;                 // 예약자 id

    public MyPageCheckReservationDTO(String b_visit_pet_sitter, String b_visit_type, String b_count, String b_reserve_day, String b_use_time, String b_visit_startdate, String b_visit_time, String b_visit_endDate, String b_sysdate, String b_totalcount, String b_sitter_type, String b_request, String c_id) {
        this.b_visit_pet_sitter = b_visit_pet_sitter;
        this.b_visit_type = b_visit_type;
        this.b_count = b_count;
        this.b_reserve_day = b_reserve_day;
        this.b_use_time = b_use_time;
        this.b_visit_startdate = b_visit_startdate;
        this.b_visit_time = b_visit_time;
        this.b_visit_endDate = b_visit_endDate;
        this.b_sysdate = b_sysdate;
        this.b_totalcount = b_totalcount;
        this.b_sitter_type = b_sitter_type;
        this.b_request = b_request;
        this.c_id = c_id;
    }

    public String getB_visit_pet_sitter() {
        return b_visit_pet_sitter;
    }

    public void setB_visit_pet_sitter(String b_visit_pet_sitter) {
        this.b_visit_pet_sitter = b_visit_pet_sitter;
    }

    public String getB_visit_type() {
        return b_visit_type;
    }

    public void setB_visit_type(String b_visit_type) {
        this.b_visit_type = b_visit_type;
    }

    public String getB_count() {
        return b_count;
    }

    public void setB_count(String b_count) {
        this.b_count = b_count;
    }

    public String getB_reserve_day() {
        return b_reserve_day;
    }

    public void setB_reserve_day(String b_reserve_day) {
        this.b_reserve_day = b_reserve_day;
    }

    public String getB_use_time() {
        return b_use_time;
    }

    public void setB_use_time(String b_use_time) {
        this.b_use_time = b_use_time;
    }

    public String getB_visit_startdate() {
        return b_visit_startdate;
    }

    public void setB_visit_startdate(String b_visit_startdate) {
        this.b_visit_startdate = b_visit_startdate;
    }

    public String getB_visit_time() {
        return b_visit_time;
    }

    public void setB_visit_time(String b_visit_time) {
        this.b_visit_time = b_visit_time;
    }

    public String getB_visit_endDate() {
        return b_visit_endDate;
    }

    public void setB_visit_endDate(String b_visit_endDate) {
        this.b_visit_endDate = b_visit_endDate;
    }

    public String getB_sysdate() {
        return b_sysdate;
    }

    public void setB_sysdate(String b_sysdate) {
        this.b_sysdate = b_sysdate;
    }

    public String getB_totalcount() {
        return b_totalcount;
    }

    public void setB_totalcount(String b_totalcount) {
        this.b_totalcount = b_totalcount;
    }

    public String getB_sitter_type() {
        return b_sitter_type;
    }

    public void setB_sitter_type(String b_sitter_type) {
        this.b_sitter_type = b_sitter_type;
    }

    public String getB_request() {
        return b_request;
    }

    public void setB_request(String b_request) {
        this.b_request = b_request;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
}

