package io.egreen.feedreader.wooly.article;

import io.egreen.feedreader.wooly.models.FeedItem;

/**
 * Created by Kartik_ch on 12/2/2015.
 */
public interface IArticlePresenter {
    void attemptArticleLoading(String url);

    void archiveArticle(FeedItem feedItem, String article);

    void removeArticle(FeedItem feedItem);
}
