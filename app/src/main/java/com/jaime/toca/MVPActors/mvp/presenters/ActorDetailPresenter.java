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
import com.jaime.toca.MVPActors.domain.interactor.GetDetailActorImp;
import com.jaime.toca.MVPActors.domain.model.ActorDetail;
import com.jaime.toca.MVPActors.mvp.views.ActorDetailView;
import com.jaime.toca.MVPActors.utils.Constants;
import javax.inject.Inject;
import rx.Subscription;

public class ActorDetailPresenter implements Presenter {

    ActorDetailView mActorDetailView;
    private GetDetailActorImp mInteractDetailActor;
    private Subscription mDetailActorSubscription;

    @Inject
    public ActorDetailPresenter(GetDetailActorImp getDetailActor){
        mInteractDetailActor = getDetailActor;
    }

    public void attachView (ActorDetailView actorDetailView) {
        mActorDetailView = actorDetailView;
    }

    @Override
    public void start() {
        mActorDetailView.showProgressBar();
        mDetailActorSubscription = mInteractDetailActor.execute().subscribe(
                ActorDetail -> actorDetailReceived(ActorDetail));

    }

    @Override
    public void stop() {}

    @Override
    public void destroy() {}

    public void actorDetailReceived(ActorDetail actorDetail){
        showActorImage(actorDetail.getProfilePath());
        showName(actorDetail.getName());
        showBirthday(actorDetail.getBirthday());
        showPlaceOfBirth(actorDetail.getPlaceOfBirth());
        showHomepage(actorDetail.getHomepage());
        showBiography(actorDetail.getBiography());
    }

    public void showName(String name){
        mActorDetailView.setName(name);
    }

    public void showBirthday(String birthday){
        mActorDetailView.setBirthday(birthday);
    }

    public void showPlaceOfBirth(String placeOfBirth){
        if (placeOfBirth == null)
            mActorDetailView.setPlaceOfBirth(mActorDetailView.getContext().getString(R.string.noPlaceOfBirth));
        else
            mActorDetailView.setPlaceOfBirth(placeOfBirth);
    }

    public void showHomepage(String homepage){
        if (homepage.length() < Constants.MIN_URL_SIZE)
            mActorDetailView.setHomePage(mActorDetailView.getContext().getString(R.string.noHomepage));
        else
            mActorDetailView.setHomePage(homepage);
    }

    public void showBiography(String biography){
        mActorDetailView.setBiography(biography);
    }

    public void showActorImage(String urlImage){
        mActorDetailView.setActorImage(urlImage);
    }

    public void onPictureLoaded(){
        mActorDetailView.hideProgressBar();
    }

    public void onPictureError(){
        return;
    }
}
