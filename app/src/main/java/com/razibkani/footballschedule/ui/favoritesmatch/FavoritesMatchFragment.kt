package com.razibkani.footballschedule.ui.favoritesmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.razibkani.footballschedule.R
import com.razibkani.footballschedule.data.model.Event
import com.razibkani.footballschedule.data.model.FavoriteEvent
import com.razibkani.footballschedule.ui.base.BaseActivity
import com.razibkani.footballschedule.ui.detailmatch.MatchDetailActivity
import com.razibkani.footballschedule.utils.OnItemClickListener
import com.razibkani.footballschedule.utils.VerticalItemDecoration
import com.razibkani.footballschedule.utils.hide
import com.razibkani.footballschedule.utils.visible
import kotlinx.android.synthetic.main.fragment_favorites_match.*
import kotlinx.android.synthetic.main.layout_loading_indicator.*
import javax.inject.Inject

class FavoritesMatchFragment : Fragment(), FavoritesMatchMvpView {

    @Inject
    lateinit var presenter: FavoritesMatchPresenter
    @Inject
    lateinit var favMatchAdapter: FavoritesMatchAdapter

    companion object {
        val TAG: String = FavoritesMatchFragment::class.java.simpleName

        fun newInstance(): FavoritesMatchFragment {
            return FavoritesMatchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).activityComponent()?.inject(this)
        presenter.attachView(this)

        initAdapter()
        initUI()
    }

    override fun onStart() {
        super.onStart()
        presenter.getFavoriteEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        loadingIndicator.visible()
    }

    override fun hideLoading() {
        loadingIndicator.hide()
    }

    override fun updateData(favoriteEvents: List<FavoriteEvent>) {
        favMatchAdapter.updateData(favoriteEvents)
    }

    private fun initAdapter() {
        favMatchAdapter.onItemClickListener = object : OnItemClickListener<FavoriteEvent> {
            override fun onClick(item: FavoriteEvent) {
                val event = Event(item.idEvent,
                        item.homeTeam,
                        item.awayTeam,
                        item.homeScore.toString(),
                        item.awayScore.toString(),
                        item.dateEvent)
                MatchDetailActivity.start(context, event)
            }
        }
    }

    private fun initUI() {
        listFavMatch.layoutManager = LinearLayoutManager(context)
        listFavMatch.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listFavMatch.adapter = favMatchAdapter
    }
}