package org.jmusixmatch.entity.snippet.get;

import com.google.gson.annotations.SerializedName;


public class SnippetGetMessage {

    @SerializedName("message")
    private SnippetGetContainer container;

    public SnippetGetContainer getContainer() {
        return container;
    }

    public void setContainer(SnippetGetContainer container) {
        this.container = container;
    }
}
