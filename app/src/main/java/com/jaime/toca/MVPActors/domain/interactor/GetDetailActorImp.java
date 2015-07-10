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
import com.jaime.toca.MVPActors.domain.repository.model.Actor;
import com.jaime.toca.MVPActors.domain.repository.model.ActorDetail;
import com.jaime.toca.MVPActors.domain.repository.rest.RestDataSource;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class GetDetailActorImp implements GetDetailActor {

    private final RestDataSource restDataSource;
    private final Bus dBus;
    private final String actorId;
    private  ActorDetail actorDetail;

    public GetDetailActorImp(String Id,Bus bus,RestDataSource dataSource){
        restDataSource = dataSource;
        dBus = bus;
        actorId = Id;
        dBus.register(this);
    }

    @Override
    public void requestActorDetail(String actorId){
        restDataSource.getDetailActor(actorId);
    }

    @Override
    @Subscribe
    public void actorDetailResponse(ActorDetail actor){
        actorDetail = actor;

    }

    @Override
    public void sendDetailActorToPresenter(ActorDetail actorDetailResponse){
        dBus.post(actorDetailResponse);
    }

    @Override
    public void execute(){
        requestActorDetail(actorId);
    }

}
