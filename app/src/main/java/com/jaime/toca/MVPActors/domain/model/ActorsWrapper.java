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

import java.io.Serializable;
import java.util.List;

public class ActorsWrapper implements Serializable {

    private Number page;
    private List<Actor> results;
    private Number total_pages;
    private Number total_results;

    public ActorsWrapper(List<Actor> results) {

        this.results = results;
    }

    public List<Actor> getResults() {

        return results;
    }

    public Number getPage() {

        return this.page;
    }

    public void setPage(Number page) {

        this.page = page;
    }

    public Number getTotal_results() {

        return this.total_results;
    }

    public void setTotal_results(Number total_results) {

        this.total_results = total_results;
    }

    public Number getTotal_pages() {

        return this.total_pages;
    }

    public void setTotal_pages(Number total_pages) {

        this.total_pages = total_pages;
    }

}
