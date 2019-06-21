package com.study.android.team_project;

import com.google.firebase.database.PropertyName;

public class PetDTO {

    @PropertyName( "Name" )
    private String name;
    @PropertyName( "Kind" )
    private String kind;
    @PropertyName( "Sex" )
    private String sex;
    @PropertyName( "Birth" )
    private String birth;
    @PropertyName( "Weight" )
    private String weight;

    public PetDTO(){}

    public PetDTO(String name, String kind, String sex, String birth, String weight){
        this.name = name;
        this.kind = kind;
        this.sex = sex;
        this.birth = birth;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


}
