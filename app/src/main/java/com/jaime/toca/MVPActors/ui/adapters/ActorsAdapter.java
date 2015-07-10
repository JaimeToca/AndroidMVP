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
package com.jaime.toca.MVPActors.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jaime.toca.MVPActors.R;
import com.jaime.toca.MVPActors.domain.repository.model.Actor;
import com.jaime.toca.MVPActors.ui.listeners.RecyclerViewClickListener;
import java.util.List;


public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorViewHolder> {


    private List<Actor> actorsList;
    private LayoutInflater layoutInflater;
    private RecyclerViewClickListener RecyclerViewListener;


    public ActorsAdapter(List<Actor> actorsList) {
        this.actorsList = actorsList;
    }

    public void setRecyclerViewListener(RecyclerViewClickListener RecyclerViewListener) {
        this.RecyclerViewListener = RecyclerViewListener;
    }

    public List<Actor> getActorsList(){
        return actorsList;
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ActorViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ActorViewHolder holder, int position) {
        final Actor actor = actorsList.get(position);
        holder.name.setText(actor.getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewListener.onClickRecyclerItem(v, actor.getId());
            }
        });
    }

    @Override
    public int getItemCount(){
        if (actorsList != null)
            return actorsList.size();
        else return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /* Class View Holder for actors */
    static class ActorViewHolder extends RecyclerView.ViewHolder {

        public ImageView avatar;
        public TextView name;

        public ActorViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
