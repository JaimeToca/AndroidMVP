/*
 * Copyright (C) 2015 Jaime Toca.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jaime.toca.MVPActors.mvp.presenters;
import com.jaime.toca.MVPActors.domain.interactor.GetPopularActorsImp;
import com.jaime.toca.MVPActors.domain.model.ActorsWrapper;
import com.jaime.toca.MVPActors.mvp.views.PopularActorsView;
import javax.inject.Inject;

import rx.Subscription;


public class PopularActorsPresenter extends Presenter {

    private GetPopularActorsImp mInteracPopularActors;
    private PopularActorsView mPopularActorsView;
    private Subscription mPopularActorsSubscription;

    @Inject
    public PopularActorsPresenter(GetPopularActorsImp getPopularActors){
        mInteracPopularActors = getPopularActors;
    }

    public void attachView (PopularActorsView ActorsView) {
        mPopularActorsView = ActorsView;
    }

    @Override
    public void start() {
        if (mPopularActorsView.EmptyList()){
            mPopularActorsView.showProgressBar();
            mPopularActorsSubscription = mInteracPopularActors.execute().subscribe(
                    ActorsWrapper -> popularActorsReceived(ActorsWrapper));
        }
    }

    public void popularActorsReceived(ActorsWrapper actorsWrapper){
        mPopularActorsView.showMovies(actorsWrapper.getResults());
        mPopularActorsView.hideProgressBar();
    }

    @Override
    public void stop() {}

}
