package io.egreen.feedreader.wooly.feedreader.archive;

import io.egreen.feedreader.wooly.feedreader.models.FeedItem;

import java.util.List;

/**
 * Created by Kartik_ch on 12/9/2015.
 */
public interface OnArticleRetrievedListener {
    void onSuccess(List<FeedItem> feedItems);

    void onFailure(String message);
}
