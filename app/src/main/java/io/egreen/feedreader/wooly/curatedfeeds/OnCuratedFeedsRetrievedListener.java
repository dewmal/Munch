package io.egreen.feedreader.wooly.curatedfeeds;

import io.egreen.feedreader.wooly.models.SourceItem;

import java.util.List;

/**
 * Created by Kartik_ch on 1/3/2016.
 */
public interface OnCuratedFeedsRetrievedListener {
    void onSuccess(List<SourceItem> sourceItems);

    void onFailure(String message);
}
