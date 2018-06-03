package com.razibkani.footballschedule.ui.homepage

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.razibkani.footballschedule.R
import com.razibkani.footballschedule.ui.base.BaseActivity
import com.razibkani.footballschedule.ui.favoritesmatch.FavoritesMatchFragment
import com.razibkani.footballschedule.ui.nextmatch.NextMatchListFragment
import com.razibkani.footballschedule.ui.prevmatch.PrevMatchListFragment
import com.razibkani.footballschedule.utils.inTransaction
import kotlinx.android.synthetic.main.activity_homepage.*

class HomepageActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev_match -> {
                supportFragmentManager.inTransaction {
                    replace(R.id.fragmentContainer, PrevMatchListFragment.newInstance(), PrevMatchListFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                supportFragmentManager.inTransaction {
                    replace(R.id.fragmentContainer, NextMatchListFragment.newInstance(), NextMatchListFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite_match -> {
                supportFragmentManager.inTransaction {
                    replace(R.id.fragmentContainer, FavoritesMatchFragment.newInstance(), FavoritesMatchFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.inTransaction {
            replace(R.id.fragmentContainer, PrevMatchListFragment.newInstance(), PrevMatchListFragment.TAG)
        }
    }
}
