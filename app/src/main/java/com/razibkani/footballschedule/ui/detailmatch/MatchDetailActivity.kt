package com.razibkani.footballschedule.ui.detailmatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.razibkani.footballschedule.R
import com.razibkani.footballschedule.data.model.DetailEvent
import com.razibkani.footballschedule.data.model.Event
import com.razibkani.footballschedule.data.model.Team
import com.razibkani.footballschedule.ui.base.BaseActivity
import com.razibkani.footballschedule.utils.hide
import com.razibkani.footballschedule.utils.loadUrl
import com.razibkani.footballschedule.utils.setFormattedDate
import com.razibkani.footballschedule.utils.visible
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.layout_loading_indicator.*
import kotlinx.android.synthetic.main.layout_section_away_team.*
import kotlinx.android.synthetic.main.layout_section_defense.*
import kotlinx.android.synthetic.main.layout_section_forward.*
import kotlinx.android.synthetic.main.layout_section_goalkeeper.*
import kotlinx.android.synthetic.main.layout_section_goals.*
import kotlinx.android.synthetic.main.layout_section_header.*
import kotlinx.android.synthetic.main.layout_section_home_team.*
import kotlinx.android.synthetic.main.layout_section_midfield.*
import kotlinx.android.synthetic.main.layout_section_shoots.*
import kotlinx.android.synthetic.main.layout_section_subtitutes.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class MatchDetailActivity : BaseActivity(), MatchDetailMvpView {

    @Inject
    lateinit var presenter: MatchDetailPresenter
    private lateinit var event: Event

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    companion object {
        const val ARG_EVENT = "arg_event"

        fun start(context: Context?, event: Event) {
            val intent = Intent(context, MatchDetailActivity::class.java)
            intent.putExtra(ARG_EVENT, event)

            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        activityComponent()?.inject(this)
        presenter.attachView(this)

        setupToolbar()

        event = intent.getParcelableExtra(ARG_EVENT)
        event.idEvent?.let {
            presenter.getMatchDetail(it)
        }
        event.idEvent?.let {
            isFavorite = presenter.favoriteState(it)
        }
        event.strHomeTeam?.let {
            presenter.getFootballClubDetail(it, true)
        }
        event.strAwayTeam?.let {
            presenter.getFootballClubDetail(it, false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_match, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) {
                    event.idEvent?.let { presenter.removeFromFavorite(it) }
                } else {
                    presenter.addToFavorite(event)
                }

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun showLoading() {
        loadingIndicator.visible()
    }

    override fun hideLoading() {
        loadingIndicator.hide()
    }

    override fun showSuccessMessage(successMessage: String) {
        toast(successMessage)
    }

    override fun showErrorMessage(message: String) {
        toast(message)
    }

    override fun showData(event: DetailEvent) {
        textEventDate.setFormattedDate(event.dateEvent)

        textScoreHome.text = event.intHomeScore
        textScoreAway.text = event.intAwayScore

        textHomeTeamName.text = event.strHomeTeam
        textAwayTeamName.text = event.strAwayTeam

        textHomeTeamFormation.text = event.strHomeFormation
        textAwayTeamFormation.text = event.strAwayFormation

        textSectionGoalsHome.text = event.strHomeGoalDetails
        textSectionGoalsAway.text = event.strAwayGoalDetails

        textSectionShotsHome.text = event.intHomeShots
        textSectionShotsAway.text = event.intAwayShots

        textSectionGoalkeeperHome.text = event.strHomeLineupGoalkeeper
        textSectionGoalkeeperAway.text = event.strAwayGoalDetails

        textSectionDefenseHome.text = event.strHomeLineupDefense
        textSectionDefenseAway.text = event.strAwayLineupDefense

        textSectionMidfieldHome.text = event.strHomeLineupMidfield
        textSectionMidfieldAway.text = event.strAwayLineupMidfield

        textSectionForwardHome.text = event.strHomeLineupForward
        textSectionForwardAway.text = event.strAwayLineupForward

        textSectionSubstitutesHome.text = event.strHomeLineupSubstitutes
        textSectionSubstitutesAway.text = event.strAwayLineupSubstitutes
    }

    override fun showHomeTeamImage(team: Team) {
        imageHomeTeam.loadUrl(team.teamBadge)
    }

    override fun showAwayTeamImage(team: Team) {
        imageAwayTeam.loadUrl(team.teamBadge)
    }
}