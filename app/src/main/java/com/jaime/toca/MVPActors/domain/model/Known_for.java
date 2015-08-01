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
package com.jaime.toca.MVPActors.domain.model;

@SuppressWarnings("UnusedDeclaration")
public class Known_for {

    private String adult;
    private String backdrop_path;
    private Number id;
    private String original_title;
    private String release_date;
    private String poster_path;
    private Number popularity;
    private String title;
    private Number vote_average;
    private Number vote_count;
    private String media_type;

    public String getAdult(){
        return adult;
    }

    public String getBackdropPath(){
        return backdrop_path;
    }

    public Number getId(){
        return id;
    }

    public String getOriginalTitle(){
        return original_title;
    }

    public String getReleaseDate(){
        return release_date;
    }

    public String getPosterPath(){
        return poster_path;
    }

    public Number getPopularity(){
        return popularity;
    }

    public Number getVoteAverage(){
        return vote_average;
    }

    public Number getVoteCount(){
        return vote_count;
    }

    private String getMediaType(){
        return media_type;
    }

}
