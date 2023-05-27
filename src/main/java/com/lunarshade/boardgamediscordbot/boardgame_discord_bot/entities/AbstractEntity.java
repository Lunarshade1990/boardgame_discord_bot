package com.lunarshade.boardgamediscordbot.boardgame_discord_bot.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;

@Entity
public class AbstractEntity extends AbstractPersistable<Integer> {

    public AbstractEntity() {
    }

    public AbstractEntity(Integer id) {
        this.setId(id);
    }
}
