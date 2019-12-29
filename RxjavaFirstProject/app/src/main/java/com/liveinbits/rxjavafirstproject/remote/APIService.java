package com.liveinbits.rxjavafirstproject.remote;

import com.liveinbits.rxjavafirstproject.User;
import com.liveinbits.rxjavafirstproject.UserList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("posts")
    Observable<List<User>> getAllUser();
}
