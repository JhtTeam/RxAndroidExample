package com.rxandroidsample.data.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by anhndt on 5/25/16.
 */
public class Profile{
    public Name name;
    public String id;
    public String email;
    public String avatar;
    public Date dateOfBirth;
    public String hexColor;
    public String bio;
    public boolean isActive;

    public Profile() {

    }

    public Name getName() {
        return name;
    }
    public String getNameText() {
        return (name != null) ? name.toString() : "";
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getHexColor() {
        return hexColor;
    }

    public String getBio() {
        return bio;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (name != null ? !name.equals(profile.name) : profile.name != null) return false;
        if (email != null ? !email.equals(profile.email) : profile.email != null) return false;
        if (hexColor != null ? !hexColor.equals(profile.hexColor) : profile.hexColor != null)
            return false;
        if (avatar != null ? !avatar.equals(profile.avatar) : profile.avatar != null)
            return false;
        if (dateOfBirth != null
                ? !dateOfBirth.equals(profile.dateOfBirth) : profile.dateOfBirth != null)
            return false;
        return !(bio != null ? !bio.equals(profile.bio) : profile.bio != null);

    }
}
