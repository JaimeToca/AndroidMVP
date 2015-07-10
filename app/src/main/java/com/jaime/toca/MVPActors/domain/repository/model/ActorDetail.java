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
package com.jaime.toca.MVPActors.domain.repository.model;
import java.util.List;


@SuppressWarnings("UnusedDeclaration")
public class ActorDetail {

    private boolean isLoaded;
    private String adult;
    private List<String> also_known_as;
    private String biography;
    private String birthday;
    private String deathday;
    private String homepage;
    private Number id;
    private String name;
    private String place_of_birth;
    private String profile_path;

    public String getAdult(){
        return adult;
    }

    public List<String> getAlsoKnownAs(){
        return also_known_as;
    }

    public String getBiography(){
        return biography;
    }

    public String getBirthday(){
        return birthday;
    }

    public String getDeathday(){
        return deathday;
    }

    public String getHomepage(){
        return homepage;
    }

    public Number getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPlaceOfBirth(){
        return place_of_birth;
    }

    public String getProfilePath(){
        return profile_path;
    }

    public void setIsLoaded(boolean isLoadedActor){
        this.isLoaded=isLoadedActor;
    }

    public Boolean getIsLoaded(){
        return isLoaded;
    }
}
