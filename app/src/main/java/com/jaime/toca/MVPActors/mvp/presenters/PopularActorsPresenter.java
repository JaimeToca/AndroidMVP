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
import com.jaime.toca.MVPActors.domain.interactor.GetPopularActors;
import com.jaime.toca.MVPActors.domain.repository.model.ActorsWrapper;
import com.jaime.toca.MVPActors.mvp.views.PopularActorsView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;


public class PopularActorsPresenter extends Presenter {

    private final Bus mBus;
    private GetPopularActors mInteracPopularActors;
    private PopularActorsView mPopularActorsView;

    @Inject
    public PopularActorsPresenter(GetPopularActors getPopularActors,Bus bus){
        mInteracPopularActors = getPopularActors;
        mBus = bus;
    }

    public void attachView (PopularActorsView ActorsView) {
        mPopularActorsView = ActorsView;
    }

    @Subscribe
    public void popularActorsReceived(ActorsWrapper actorsWrapper){
        mPopularActorsView.showMovies(actorsWrapper.getResults());
    }

    @Override
    public void start() {
        if (mPopularActorsView.EmptyList()){
            mBus.register(this);
            mInteracPopularActors.execute();
        }
    }

    @Override
    public void stop() {
        mBus.unregister(this);
    }

}
