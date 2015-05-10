package com.kyleh.exquisite.utility;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kylehebert on 5/8/15.
 * Objects of this class allow the user to share a url to their
 * corpse on social media like Twitter.
 * Based on code examples from http://twitter4j.org/en/code-examples.html
 */
public class ShareCorpse {

    String twitterAuthURL;

    public void shareCorpseOnTwitter(ShareCorpseMessage shareCorpseMessage) {


        try {
            //TwitterFactory twitterFactory = new TwitterFactory(cb.build());
            Twitter twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer(getConsumerKey(), getConsumerSecret());
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
                System.out.println("Retrieved Token. Updating Twittter.");
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
            System.out.println("Failed to read the the consumer key/secret from file.");
        }
    }

    protected String getConsumerKey() {
        //used to read in the Twitter OAuth Consumer key from a file
        String consumerKey ="";

        //used to count lines, since I know precisely where the value I want is
        int lineCounter = 0;
        try {
            FileReader fileReader = new FileReader("twitterkeys.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (lineCounter <=0) {
                if (lineCounter == 0) {
                    consumerKey = bufferedReader.readLine();
                }
                lineCounter++;
            }
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException ioe) {
            System.out.println("Could not open or read twitterkeys.txt");
            System.out.println(ioe.toString());
        }
        return consumerKey;
    }

    protected String getConsumerSecret() {

        String consumerSecret = "";

        //used to read in the Twitter OAuth Consumer secret from a file
        try {
            FileReader fileReader = new FileReader("twitterkeys.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //this only works because I know the consumer secret is on line 1 of the file
            for (int i = 0; i < 1; i++) {
                bufferedReader.readLine();
            }

            consumerSecret = bufferedReader.readLine();

            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException ioe) {
            System.out.println("Could not open or read twitterkeys.txt");
            System.out.println(ioe.toString());
        }
        return consumerSecret;
    }


}
