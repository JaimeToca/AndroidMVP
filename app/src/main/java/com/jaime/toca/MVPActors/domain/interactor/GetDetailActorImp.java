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
package com.jaime.toca.MVPActors.domain.interactor;
import com.jaime.toca.MVPActors.domain.model.ActorDetail;
import com.jaime.toca.MVPActors.domain.repository.rest.RestDataSource;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetDetailActorImp implements Interactor<com.jaime.toca.MVPActors.domain.model.ActorDetail> {

    private final RestDataSource mRestDataSource;
    private final String mActorId;
    private ActorDetail mActorDetail;

    public GetDetailActorImp(String id,RestDataSource dataSource){
        mRestDataSource = dataSource;
        mActorId = id;
    }

    @Override
    public Observable<ActorDetail> execute() {
        return mRestDataSource.getDetailActor(mActorId)
                .map(actorDetail -> mActorDetail = actorDetail)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
