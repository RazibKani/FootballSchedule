package com.razibkani.footballschedule.injection.component

import com.razibkani.footballschedule.injection.PerActivity
import com.razibkani.footballschedule.injection.module.ActivityModule
import com.razibkani.footballschedule.ui.detailmatch.MatchDetailActivity
import com.razibkani.footballschedule.ui.favoritesmatch.FavoritesMatchFragment
import com.razibkani.footballschedule.ui.nextmatch.NextMatchListFragment
import com.razibkani.footballschedule.ui.prevmatch.PrevMatchListFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(matchDetailActivity: MatchDetailActivity)

    fun inject(prevMatchListFragment: PrevMatchListFragment)
    fun inject(nextMatchListFragment: NextMatchListFragment)
    fun inject(favoritesMatchFragment: FavoritesMatchFragment)

}