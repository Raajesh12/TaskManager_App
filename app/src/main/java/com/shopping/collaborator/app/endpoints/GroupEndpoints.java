package com.shopping.collaborator.app.endpoints;

import com.shopping.collaborator.app.requests.CreateGroupRequest;
import com.shopping.collaborator.app.requests.UpdateGroupRequest;
import com.shopping.collaborator.app.responses.GIDResponse;
import com.shopping.collaborator.app.responses.GroupListResponse;
import com.shopping.collaborator.app.responses.ItemsCompletedResponse;
import com.shopping.collaborator.app.responses.LastModifiedResponse;
import com.shopping.collaborator.app.responses.TotalPriceResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by raajesharunachalam on 8/11/17.
 */

public interface GroupEndpoints {
    @Headers("Token: 5c8ab94e-3c95-40f9-863d-e31ae49e5d8d")
    @GET("/groups")
    Call<GroupListResponse> getGroups(@Query("uid") long uid);

    @Headers("Token: 5c8ab94e-3c95-40f9-863d-e31ae49e5d8d")
    @GET("/group_last_modified")
    Call<LastModifiedResponse> getGroupLastModified(@Query("gid") long gid);

    @Headers("Token: 5c8ab94e-3c95-40f9-863d-e31ae49e5d8d")
    @POST("/groups/")
    Call<GIDResponse> createGroup(@Body CreateGroupRequest createGroupRequest);

    @Headers("Token: 5c8ab94e-3c95-40f9-863d-e31ae49e5d8d")
    @PUT("/groups/{gid}")
    Call<Void> updateGroup(@Path("gid") long gid, @Body UpdateGroupRequest updateGroupRequest);

    @Headers("Token: 5c8ab94e-3c95-40f9-863d-e31ae49e5d8d")
    @DELETE("/groups/{gid}")
    Call<Void> deleteGroup(@Path("gid") long gid, @Query("uid") long uid);

    @Headers("Token: 5c8ab94e-3c95-40f9-863d-e31ae49e5d8d")
    @GET("/add_total_price")
    Call<TotalPriceResponse> getGroupPriceTotal(@Query("gid") long gid);

    @Headers("Token: 5c8ab94e-3c95-40f9-863d-e31ae49e5d8d")
    @GET("/items_completed")
    Call<ItemsCompletedResponse> getItemsCompleted(@Query("gid") long gid);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.taskmanager.host")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    GroupEndpoints groupEndpoints = retrofit.create(GroupEndpoints.class);

}
