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
import com.jaime.toca.MVPActors.R;
import com.jaime.toca.MVPActors.domain.interactor.GetDetailActor;
import com.jaime.toca.MVPActors.domain.interactor.GetDetailActorImp;
import com.jaime.toca.MVPActors.domain.interactor.GetPopularActors;
import com.jaime.toca.MVPActors.domain.repository.model.ActorDetail;
import com.jaime.toca.MVPActors.mvp.views.ActorDetailView;
import com.jaime.toca.MVPActors.ui.activities.ActorDetailActivity;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * Created by Jaime Toca on 08/07/2015.
 */
public class ActorDetailPresenter extends Presenter {

    private final static int MIN_URL_SIZE = 2;
    ActorDetailView pActorDetailView;
    private GetDetailActor interacDetailActor;
    private Bus pBus;
    private Boolean isLoadingImage = false;

    @Inject
    public ActorDetailPresenter(GetDetailActor getDetailActor,Bus bus){
        interacDetailActor = getDetailActor;
        pBus = bus;
    }

    public void attachView (ActorDetailView actorDetailView) {
        pActorDetailView = actorDetailView;
    }

    @Override
    public void start() {
        pBus.register(this);
        pActorDetailView.showProgressBar();
        interacDetailActor.execute();
    }

    @Override
    public void stop() {
        pBus.unregister(this);
    }

    @Subscribe
    public void actorDetailResponse(ActorDetail actorDetail){
        showActorImage(actorDetail.getProfilePath());
        showName(actorDetail.getName());
        showBirthday(actorDetail.getBirthday());
        showPlaceOfBirth(actorDetail.getPlaceOfBirth());
        showHomepage(actorDetail.getHomepage());
        showBiography(actorDetail.getBiography());
    }

    public void showName(String name){
        pActorDetailView.setName(name);
    }

    public void showBirthday(String birthday){
        pActorDetailView.setBirthday(birthday);
    }

    public void showPlaceOfBirth(String placeOfBirth){
        pActorDetailView.setPlaceOfBirth(placeOfBirth);
    }

    public void showHomepage(String homepage){
        if (homepage.length() < MIN_URL_SIZE) {
            pActorDetailView.setHomePage(pActorDetailView.getContext().getString(R.string.noHomepage));
        } else
            pActorDetailView.setHomePage(homepage);
    }

    public void showBiography(String biography){
        pActorDetailView.setBiography(biography);
    }

    public void showActorImage(String urlImage){
        pActorDetailView.setActorImage(urlImage);
    }

    public Boolean isLoadingImage(){
        return (isLoadingImage == true);
    }
}
