package pl.pb.r.sm_sqlite.api_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Radosław Naruszewicz on 2016-12-12.
 */

public class JsonModel {
    @SerializedName("results")
    @Expose
    public List<Result> results;
    @SerializedName("info")
    @Expose
    public Info info;

    public class Id {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("value")
        @Expose
        public Object value;

    }
    public class Info {

        @SerializedName("seed")
        @Expose
        public String seed;
        @SerializedName("results")
        @Expose
        public Integer results;
        @SerializedName("page")
        @Expose
        public Integer page;
        @SerializedName("version")
        @Expose
        public String version;

    }
    public class Location {

        @SerializedName("street")
        @Expose
        public String street;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("state")
        @Expose
        public String state;
        @SerializedName("postcode")
        @Expose
        public Integer postcode;

    }
    public class Login {

        @SerializedName("username")
        @Expose
        public String username;
        @SerializedName("password")
        @Expose
        public String password;
        @SerializedName("salt")
        @Expose
        public String salt;
        @SerializedName("md5")
        @Expose
        public String md5;
        @SerializedName("sha1")
        @Expose
        public String sha1;
        @SerializedName("sha256")
        @Expose
        public String sha256;

    }
    public class Name {

        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("first")
        @Expose
        public String first;
        @SerializedName("last")
        @Expose
        public String last;

    }
    public class Picture {

        @SerializedName("large")
        @Expose
        public String large;
        @SerializedName("medium")
        @Expose
        public String medium;
        @SerializedName("thumbnail")
        @Expose
        public String thumbnail;

    }

    public class Result {

        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("name")
        @Expose
        public Name name;
        @SerializedName("location")
        @Expose
        public Location location;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("login")
        @Expose
        public Login login;
        @SerializedName("dob")
        @Expose
        public String dob;
        @SerializedName("registered")
        @Expose
        public String registered;
        @SerializedName("phone")
        @Expose
        public String phone;
        @SerializedName("cell")
        @Expose
        public String cell;
        @SerializedName("id")
        @Expose
        public Id id;
        @SerializedName("picture")
        @Expose
        public Picture picture;
        @SerializedName("nat")
        @Expose
        public String nat;

    }
}
