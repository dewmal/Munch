package io.egreen.feedreader.wooly.feedreader.importopml;

import io.egreen.feedreader.wooly.feedreader.models.SourceItem;

import java.util.List;

/**
 * Created by Kartik_ch on 1/9/2016.
 */
public interface OnOpmlImportListener {
    void onSuccess(List<SourceItem> sourceItems);

    void onFailure(String message);
}
