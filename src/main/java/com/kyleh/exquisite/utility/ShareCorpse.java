package com.kyleh.exquisite.utility;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kylehebert on 5/8/15.
 * Objects of this class allow the user to share a url to their
 * corpse on Twitter.
 * Based on code examples from http://twitter4j.org/en/code-examples.html
 */
public class ShareCorpse {


    public void shareCorpseOnTwitter(ShareCorpseMessage shareCorpseMessage) {


        try {
            //TwitterFactory twitterFactory = new TwitterFactory(cb.build());
            Twitter twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer("SyzNRghgjTpbbVig7oxmdcvb1", "lwUJTLZGBpeFobEk645feP79kLipCMjnywC6QqC6tw7dmonphL");
            try {
                //get request token
                RequestToken requestToken = twitter.getOAuthRequestToken();
                AccessToken accessToken = null;

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                while (accessToken == null) {
                    System.out.println("Open this to authorize");
                    System.out.println(requestToken.getAuthorizationURL());
                    System.out.println("Enter the PIN if available, otherwise hit enter. PIN:");
                    String pin = bufferedReader.readLine();
                    try {
                        if (pin.length() > 0) {
                            accessToken = twitter.getOAuthAccessToken(requestToken,pin);
                        }
                        else {
                            accessToken = twitter.getOAuthAccessToken(requestToken);
                        }
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            System.out.println("Unable to get the access token.");
                        }
                        else {
                            te.printStackTrace();
                        }
                    }
                }
                System.out.println("Got token");
            }
            catch (IllegalStateException ie) {
                //token is already available, or consumer key/secret is not set
                if (!twitter.getAuthorization().isEnabled()) {
                    System.out.println("Oauth consumer key/secret not set");
                }
            }
            Status status = twitter.updateStatus(shareCorpseMessage.getMessage());
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline:" + te.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to read the system input.");
        }
    }
}
