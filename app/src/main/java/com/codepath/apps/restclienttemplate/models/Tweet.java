package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public User user;
    public String media_url;

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {

        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));

        //Access entities object and then acccess media and the access media url
        JSONObject entities = jsonObject.getJSONObject("entities");
        if(entities.has("media")) {
            //if media is defined then there must be a url
            //grab index 0 because that is the first picture
            tweet.media_url = entities.getJSONArray("media").getJSONObject(0).getString("media_url_https");
        } else {
            tweet.media_url = "";
        }

        return tweet;
    }

    public Tweet() {}

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {

        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i <jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }

        return tweets;
    }

    //Check if tweet has media_url
    //If it does, set the tweet media_url == to MEDIA_URL
    //If it does, use Glide to load it
}
