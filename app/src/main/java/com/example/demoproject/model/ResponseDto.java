package com.example.demoproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDto {

@SerializedName("users")
@Expose
private List<User> users = null;

public List<User> getUsers() {
return users;
}

public void setUsers(List<User> users) {
this.users = users;
}

}
