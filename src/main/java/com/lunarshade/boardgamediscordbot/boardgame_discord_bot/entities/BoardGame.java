package com.lunarshade.boardgamediscordbot.boardgame_discord_bot.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardGame extends AbstractEntity {

    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String name2;
    @Column(columnDefinition = "text")
    private String description;
    private String teseraUrl;
    private String picture;
    @Column(unique = true)
    private long teseraId;
    @Column(unique = true)
    private long bggId;
    private int minPlayerNumber;
    private int maxPlayerNumber;
    private int minTime;
    private int maxTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private BoardGame parent;

    @OneToMany(mappedBy = "parent")
    private Set<BoardGame> expansions;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((name2 == null) ? 0 : name2.hashCode());
        result = prime * result + (int) (teseraId ^ (teseraId >>> 32));
        result = prime * result + (int) (bggId ^ (bggId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BoardGame other = (BoardGame) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (name2 == null) {
            if (other.name2 != null)
                return false;
        } else if (!name2.equals(other.name2))
            return false;
        if (teseraId != other.teseraId)
            return false;
        if (bggId != other.bggId)
            return false;
        return true;
    }

}
