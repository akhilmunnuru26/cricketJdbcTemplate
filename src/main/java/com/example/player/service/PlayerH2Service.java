package com.example.player.service;

import com.example.player.repository.PlayerRepository;
import com.example.player.model.Player;
import com.example.player.model.PlayerRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import javax.validation.OverridesAttribute;

@Service
public class PlayerH2Service implements PlayerRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Player> getPlayers(){
        List<Player>playersList = db.query("SELECT * FROM TEAM", new PlayerRowMapper());
        ArrayList<Player>players = new ArrayList<>(playersList);
        return players;
    }

    @Override
    public Player getPlayerById(int playerId){
        try{ 
            Player player = db.queryForObject("SELECT * FROM TEAM WHERE playerId=?",new PlayerRowMapper(),playerId);
            return player;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Player addPlayer(Player player){ 
        db.update("INSERT INTO TEAM(playerId,playerName,jerseyNumber,role) VALUES(?,?,?,?)",player.getPlayerId(),player.getPlayerName(),player.getJerseyNumber(),player.getRole());
        Player newPlayer = db.queryForObject("SELECT * FROM TEAM WHERE playerId=?",new PlayerRowMapper(),player.getPlayerId());
        return newPlayer;
    }

    @Override
    public Player updatePlayer(int playerId,Player player){ 
        if(player.getPlayerName() != null){
            db.update("UPDATE TEAM SET playerName=? WHERE playerId=?",player.getPlayerName(),playerId);
        }
        if(player.getJerseyNumber() != -1){
            db.update("UPDATE TEAM SET jerseyNumber=? WHERE playerId=?",player.getJerseyNumber(),playerId);
        }
        if(player.getRole() != null){
            db.update("UPDATE TEAM SET role=? WHERE playerId=?",player.getRole(),playerId);
        }
        return getPlayerById(playerId);
    }

    @Override
    public void deletePlayer(int playerId){ 
        db.update("DELETE FROM TEAM WHERE playerId=?", playerId);
    }

    
}