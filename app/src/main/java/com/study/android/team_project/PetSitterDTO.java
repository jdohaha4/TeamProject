package com.study.android.team_project;

import android.os.Parcel;
import android.os.Parcelable;

public class PetSitterDTO implements Parcelable {
    private String name; // 펫시터이름
    private int hit;   // 추천수
    private String contents; // 펫시터 정보
    private String sex;  //펫시터 성별
    private String img; // 펫시터 사진
//    private int reId;

    public PetSitterDTO(String name, int hit, String contents, String sex, String img) {
        this.name = name;
        this.hit = hit;
        this.contents = contents;
        this.sex = sex;
        this.img = img;

    }

    protected PetSitterDTO(Parcel in) {

        this.name = in.readString();
        this.hit = in.readInt();
        this.contents = in.readString();
        this.sex = in.readString();
        this.img = in.readString();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString (this.name);
        dest.writeInt (this.hit);
        dest.writeString (this.contents);
        dest.writeString (this.sex);
        dest.writeString (this.img);
    }

    public static final Creator<PetSitterDTO> CREATOR = new Creator<PetSitterDTO> () {
        @Override
        public PetSitterDTO createFromParcel(Parcel in) {
            return new PetSitterDTO (in) ;
        }
        @Override
        public PetSitterDTO[] newArray(int size) {
            return new PetSitterDTO[size];
        }
    };
}


