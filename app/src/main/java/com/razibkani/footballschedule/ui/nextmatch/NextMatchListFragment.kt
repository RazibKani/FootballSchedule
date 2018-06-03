package com.razibkani.footballschedule.ui.nextmatch


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
import kotlinx.android.synthetic.main.fragment_next_match_list.*
import kotlinx.android.synthetic.main.layout_loading_indicator.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class NextMatchListFragment : Fragment(), NextMatchMvpView {

    @Inject
    lateinit var presenter: NextMatchPresenter
    @Inject
    lateinit var nextMatchAdapter: NextMatchAdapter

    companion object {
        val TAG: String = NextMatchListFragment::class.java.simpleName

        fun newInstance(): NextMatchListFragment {
            return NextMatchListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_next_match_list, container, false)
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

    override fun updateData(data: List<Event>) {
        nextMatchAdapter.updateData(data)
    }

    private fun initAdapter() {
        nextMatchAdapter.onItemClickListener = object : OnItemClickListener<Event> {
            override fun onClick(item: Event) {
                MatchDetailActivity.start(context, item)
            }
        }
    }

    private fun initUI() {
        listNextMatch.layoutManager = LinearLayoutManager(context)
        listNextMatch.addItemDecoration(VerticalItemDecoration(resources.getDimensionPixelSize(R.dimen.divider)))
        listNextMatch.adapter = nextMatchAdapter
    }
}