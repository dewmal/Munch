package io.egreen.feedreader.wooly.feedreader.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.egreen.feedreader.wooly.feedreader.R;
import io.egreen.feedreader.wooly.feedreader.archive.ArchivePresenter;
import io.egreen.feedreader.wooly.feedreader.archive.IArchiveView;
import io.egreen.feedreader.wooly.feedreader.models.FeedItem;
import io.egreen.feedreader.wooly.feedreader.ui.adapters.FeedsRecyclerViewAdapter;
import io.egreen.feedreader.wooly.feedreader.utils.FadeAnimationUtil;

/**
 * Created by Kartik_ch on 12/9/2015.
 */
public class ArchiveFragment extends Fragment implements IArchiveView {

    @BindView(R.id.linear_layout_empty_archive)
    LinearLayout linearLayoutEmptyArchive;
    @BindView(R.id.recycler_view_feeds)
    RecyclerView recyclerViewFeeds;

    private ArchivePresenter mArchivePresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FeedsRecyclerViewAdapter mFeedsRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archive, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mArchivePresenter == null) {
            mArchivePresenter = new ArchivePresenter(this);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mArchivePresenter.attemptArchiveRetrieval(getActivity());
            }
        }, 500);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerViewFeeds.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewFeeds.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onArchiveRetrieved(List<FeedItem> feedItems) {
        if (feedItems.size() == 0) {
            new FadeAnimationUtil(getActivity()).fadeInAlpha(linearLayoutEmptyArchive, 500);
        } else {
            linearLayoutEmptyArchive.setVisibility(View.INVISIBLE);
        }

        mFeedsRecyclerViewAdapter = new FeedsRecyclerViewAdapter(getActivity(), feedItems);
        recyclerViewFeeds.setAdapter(mFeedsRecyclerViewAdapter);
    }

    @Override
    public void onArchiveRetrievalFailed(String message) {
        Toast.makeText(getActivity(), "Error:\n" + message, Toast.LENGTH_SHORT).show();
    }
}
