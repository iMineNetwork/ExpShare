package nl.imine.expshare.modal;

import org.bukkit.Bukkit;

import java.util.UUID;

public class PlayerExperience {

    private UUID playerId;
    private int levels;
    private float exp;

    public PlayerExperience() {
    }

    public PlayerExperience(UUID playerId, int levels, float exp) {
        this.playerId = playerId;
        this.levels = levels;
        this.exp = exp;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public float getExp() {
        return exp;
    }

    public void setExp(float exp) {
        this.exp = exp;
    }
}
