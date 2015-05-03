package org.jmusixmatch.entity.snippet;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kyledhebert on 4/30/15.
 * Objects of this clas represent a lyric snippet from the
 * MusixMatch API.
 */
public class Snippet {

    @SerializedName("snippet_language")
    private int snippetLanguage;

    @SerializedName("restricted")
    private int restricted;

    @SerializedName("instrumental")
    private int instrumental;

    @SerializedName("snippet_body")
    private String snippetBody;

    @SerializedName("script_tracking_url")
    private String scriptTrackingURL;

    @SerializedName("pixel_tracking_url")
    private String pixelTrackingURL;

    @SerializedName("html_tracking_url")
    private String htmlTrackingURL;

    @SerializedName("updated_time")
    private String updatedTime;

    public int getSnippetLanguage() {
        return snippetLanguage;
    }

    public void setSnippetLanguage(int snippetLanguage) {
        this.snippetLanguage = snippetLanguage;
    }

    public int getRestricted() {
        return restricted;
    }

    public void setRestricted(int restricted) {
        this.restricted = restricted;
    }

    public int getInstrumental() {
        return instrumental;
    }

    public void setInstrumental(int instrumental) {
        this.instrumental = instrumental;
    }

    public String getSnippetBody() {
        return snippetBody;
    }

    public void setSnippetBody(String snippetBody) {
        this.snippetBody = snippetBody;
    }

    public String getPixelTrackingURL() {
        return pixelTrackingURL;
    }

    public void setPixelTrackingURL(String pixelTrackingURL) {
        this.pixelTrackingURL = pixelTrackingURL;
    }

    public String getScriptTrackingURL() {
        return scriptTrackingURL;
    }

    public void setScriptTrackingURL(String scriptTrackingURL) {
        this.scriptTrackingURL = scriptTrackingURL;
    }

    public String getHtmlTrackingURL() {
        return htmlTrackingURL;
    }

    public void setHtmlTrackingURL(String htmlTrackingURL) {
        this.htmlTrackingURL = htmlTrackingURL;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
