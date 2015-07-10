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
package com.jaime.toca.MVPActors.dependencyInjection.modules;
import com.jaime.toca.MVPActors.domain.interactor.GetDetailActor;
import com.jaime.toca.MVPActors.domain.interactor.GetDetailActorImp;
import com.jaime.toca.MVPActors.domain.repository.rest.RestActorSource;
import com.jaime.toca.MVPActors.domain.repository.rest.RestDataSource;
import com.squareup.otto.Bus;
import dagger.Module;
import dagger.Provides;


@Module
public class ActorDetailModule {

    private final String actorId;

    public ActorDetailModule(String actorId) {
        this.actorId = actorId;
    }

    @Provides
    GetDetailActor provideGetActorDetailInteractor (Bus bus, RestActorSource actorSource) {
        return new GetDetailActorImp(actorId, bus, actorSource);
    }
}
