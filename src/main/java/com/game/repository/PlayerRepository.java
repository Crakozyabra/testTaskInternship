package com.game.repository;

import com.game.entity.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository(value = "player_repository")
public class PlayerRepository implements IPlayerRepository {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    @Transactional
    public List<Player> getAll() {
        List<Player> players = new ArrayList<>();
        try {
            Query playerQuery = entityManager.createNativeQuery("SELECT * FROM rpg.player", Player.class);
            players = playerQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    @Transactional
    public Player save(Player player) {
        String insertQuery = "INSERT INTO rpg.player(name, title, race, profession, birthday, banned, experience, level, untilNextLevel) VALUES (:a, :b, :c, :d, :e, :f, :j, :k, :m)";
        Query query = entityManager.createNativeQuery(insertQuery).
                setParameter("a", player.getName()).
                setParameter("b", player.getTitle()).
                setParameter("c", player.getRace().name()).
                setParameter("d", player.getProfession().name()).
                setParameter("e", player.getBirthday()).
                setParameter("f", player.getBanned()).
                setParameter("j", player.getExperience()).
                setParameter("k", player.getLevel()).
                setParameter("m", player.getUntilNextLevel());
        int insertId = 0;

        try {
            insertId = query.executeUpdate();
        } catch (Exception exception) {
                exception.printStackTrace();
        }
        Player insertedPlayer = findById(insertId).get();
        return insertedPlayer;
    }

    @Override
    @Transactional
    public Player updateById(Player player) {

        StringBuilder updateQuery = new StringBuilder();
        String updateQueryFirstPath = "UPDATE rpg.player SET ";
        String updateQueryThirthPath =" WHERE id=:id";

        updateQuery.append(updateQueryFirstPath);

        if (player.getName()!=null) {
            updateQuery.append(" name=:name, ");
        }
        if (player.getTitle()!=null) {
            updateQuery.append(" title=:title, ");
        }
        if (player.getRace()!=null) {
            updateQuery.append(" race=:race, ");
        }
        if (player.getProfession()!=null) {
            updateQuery.append(" profession=:profession, ");
        }
        if (player.getBirthday()!=null) {
            updateQuery.append(" birthday=:birthday, ");
        }
        if (player.getBanned()!=null) {
            updateQuery.append(" banned=:banned, ");
        }
        if (player.getExperience()!=null) {
            updateQuery.append(" experience=:experience, ");
        }

        updateQuery.deleteCharAt(updateQuery.lastIndexOf(","));
        updateQuery.append(updateQueryThirthPath);
        System.out.println(updateQuery.toString());

        Query playerQuery = entityManager.createNativeQuery(updateQuery.toString(), Player.class);

        if (player.getName()!=null) {
            playerQuery.setParameter("name", player.getName());
        }
        if (player.getTitle()!=null) {
            playerQuery.setParameter("title", player.getTitle());
        }
        if (player.getRace()!=null) {
            playerQuery.setParameter("race", player.getRace().name());
        }
        if (player.getProfession()!=null) {
            playerQuery.setParameter("profession", player.getProfession().name());
        }
        if (player.getBirthday()!=null) {
            playerQuery.setParameter("birthday", player.getBirthday());
        }
        if (player.getBanned()!=null) {
            playerQuery.setParameter("banned", player.getBanned());
        }
        if (player.getExperience()!=null) {
            playerQuery.setParameter("experience", player.getExperience());
        }

        playerQuery.setParameter("id", player.getId());

        try {
            playerQuery.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return findById(player.getId()).orElse(null);
    }

    @Override
    public Optional<Player> findById(long id) {
        Player player = null;
        try {
            Query playerQuery = entityManager.createNativeQuery("SELECT * FROM rpg.player WHERE id=:id", Player.class);
            playerQuery.setParameter("id", id);
            player = (Player) playerQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(player);
    }


    @Override
    @Transactional
    public void deleteById(long id) {
        try {
            Query playerQuery = entityManager.createNativeQuery("DELETE FROM rpg.player WHERE id=:id", Player.class);
            playerQuery.setParameter("id", id);
            playerQuery.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
