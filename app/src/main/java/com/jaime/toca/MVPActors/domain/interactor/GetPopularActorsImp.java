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

import com.jaime.toca.MVPActors.domain.repository.model.ActorDetail;
import com.jaime.toca.MVPActors.domain.repository.model.ActorsWrapper;
import com.jaime.toca.MVPActors.domain.repository.rest.RestDataSource;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import javax.inject.Inject;

public class GetPopularActorsImp implements GetPopularActors{

    private final RestDataSource restDataSource;
    private final Bus dBus;
    private int page = 1;

    @Inject
    public GetPopularActorsImp(RestDataSource dataSource, Bus Bus) {
        restDataSource = dataSource;
        dBus = Bus;
        dBus.register(this);
    }

    @Override
    public void requestPopularActors(){
        restDataSource.getPopularActors();
    }

    @Override
    public void unRegister(){
        dBus.unregister(this);
    }

    @Override
    public void execute(){
        requestPopularActors();
        page++;
    }

}
