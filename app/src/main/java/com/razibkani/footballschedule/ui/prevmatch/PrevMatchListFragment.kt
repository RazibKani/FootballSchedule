package com.razibkani.footballschedule.ui.prevmatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.razibkani.footballschedule.R
import com.razibkani.footballschedule.data.model.Event
import com.razibkani.footballschedule.ui.base.BaseActivity
import com.razibkani.footballschedule.ui.detailmatch.MatchDetailActivity
import com.razibkani.footballschedule.utils.OnItemClickListener
import com.razibkani.footballschedule.utils.VerticalItemDecoration
import com.razibkani.footballschedule.utils.hide
import com.razibkani.footballschedule.utils.visible
import kotlinx.android.synthetic.main.fragment_prev_match_list.*
import kotlinx.android.synthetic.main.layout_loading_indicator.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class PrevMatchListFragment : Fragment(), PrevMatchListMvpView {

    @Inject
    lateinit var presenter: PrevMatchListPresenter
    @Inject
    lateinit var prevMatchAdapter: PrevMatchAdapter

    companion object {
        val TAG: String = PrevMatchListFragment::class.java.simpleName

        fun newInstance(): PrevMatchListFragment {
            return PrevMatchListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_prev_match_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).activityComponent()?.inject(this)
        presenter.attachView(this)

        initAdapter()
        initUI()

        presenter.getEvents()
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

    override fun showErrorMessage(message: String) {
        toast(message)
    }

    override fun updateData(eventList: List<Event>) {
        prevMatchAdapter.updateData(eventList)
    }

    private fun initAdapter() {
        prevMatchAdapter.onItemClickListener = object : OnItemClickListener<Event> {
            override fun onClick(item: Event) {
                MatchDetailActivity.start(context, item)
            }
        }
    }

    private fun initUI() {
        listPrevMatch.layoutManager = LinearLayoutManager(context)
        listPrevMatch.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listPrevMatch.adapter = prevMatchAdapter
    }
}