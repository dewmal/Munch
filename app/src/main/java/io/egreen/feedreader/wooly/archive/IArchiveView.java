package io.egreen.feedreader.wooly.archive;

import io.egreen.feedreader.wooly.models.FeedItem;

import java.util.List;

/**
 * Created by Kartik_ch on 12/9/2015.
 */
public interface IArchiveView {
    void onArchiveRetrieved(List<FeedItem> feedItems);

    void onArchiveRetrievalFailed(String message);
}
