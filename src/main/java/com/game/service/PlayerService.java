package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class PlayerService {
    private final IPlayerRepository IPlayerRepository;
    //private final Repository playerRepository;

    public PlayerService(@Qualifier("player_repository") @Autowired IPlayerRepository IPlayerRepository) {
    /*public PlayerService(@Qualifier("player_repository") @Autowired IPlayerRepository playerRepository) {*/
        this.IPlayerRepository = IPlayerRepository;
    }

    public List<Player> getPlayers(String name,
                                   String title,
                                   Race race,
                                   Profession profession,
                                   Long after,
                                   Long before,
                                   Boolean banned,
                                   Integer minExperience,
                                   Integer maxExperience,
                                   Integer minLevel,
                                   Integer maxLevel,
                                   PlayerOrder order,
                                   Integer pageNumber,
                                   Integer pageSize) {

        List<Player> filteredPlayerstList = filterPlayer (
                name,
                title,
                race,
                profession,
                after,
                before,
                banned,
                minExperience,
                maxExperience,
                minLevel,
                maxLevel
        );

        if (order == PlayerOrder.ID) {
            Comparator<Player> playerIdComparator = Comparator.comparingLong(Player::getId);
            filteredPlayerstList.sort(playerIdComparator);
        }
        else if (order == PlayerOrder.NAME) {
            Comparator<Player> playerNameComparator = Comparator.comparing(Player::getName);
            filteredPlayerstList.sort(playerNameComparator);
        }
        else if (order == PlayerOrder.EXPERIENCE) {
            Comparator<Player> playerExperienceComparator = Comparator.comparingInt(Player::getExperience);
            filteredPlayerstList.sort(playerExperienceComparator);
        } else {
            Comparator<Player> playerBirthdayComparator = Comparator.comparing(Player::getBirthday);
            filteredPlayerstList.sort(playerBirthdayComparator);
        };

        List<Player> skipedFilteredPlayerstList = filteredPlayerstList.stream().skip((long) pageNumber *pageSize).limit(pageSize).collect(Collectors.toList());

        return skipedFilteredPlayerstList;
    }

    public Integer getPlayersCount(String name,
                                   String title,
                                   Race race,
                                   Profession profession,
                                   Long after,
                                   Long before,
                                   Boolean banned,
                                   Integer minExperience,
                                   Integer maxExperience,
                                   Integer minLevel,
                                   Integer maxLevel) {
        return filterPlayer (
                name,
                title,
                race,
                profession,
                after,
                before,
                banned,
                minExperience,
                maxExperience,
                minLevel,
                maxLevel
        ).size();
    }


    private List<Player> filterPlayer (String name,
                               String title,
                               Race race,
                               Profession profession,
                               Long after,
                               Long before,
                               Boolean banned,
                               Integer minExperience,
                               Integer maxExperience,
                               Integer minLevel,
                               Integer maxLevel) {

       return IPlayerRepository.getAll().stream().
                filter(player ->  {
                    return player.getName().contains(name);
                }).
                filter(player -> {
                    return player.getTitle().contains(title);
                }).
                filter(player -> {
                    if (race == null) return true;
                    return player.getRace() == race;
                }).
                filter(player -> {
                    if (profession == null) return true;
                    return (player.getProfession() == profession);
                }).
                filter(player -> {
                    return (player.getBirthday().getTime() >= after);
                }).
                filter(player -> {
                    return (player.getBirthday().getTime() <= before);
                }).filter(player -> {
                    return (player.getBanned() == banned);
                }).filter(player -> {
                    return (player.getExperience() >= minExperience);
                }).filter(player -> {
                    return (player.getExperience() <= maxExperience);
                }).filter(player -> {
                    return (player.getLevel() >= minLevel);
                }).filter(player -> {
                    return (player.getLevel() <= maxLevel);
                }).collect(Collectors.toList());
    }


    public Player getPlayerById(Long id) {
        return IPlayerRepository.findById(id).get();
    }



    public Player createPlayer(String name, String title, Race race, Profession profession, long birthday, boolean banned, int experience) {
        Player player = makePlayerFromData(null, name, title, race, profession, birthday, banned, experience);
        return IPlayerRepository.save(player);
    }



    private int calculatePlayerLevel(int experience){
        return (int) Math.round((Math.sqrt(2500.0 + 200 * experience) - 50.0) / 100.0);
    }



    private int calculatePlayerUntilNextLevel(int level, int experience) {
        return 50 * (level + 1) * (level + 2) - experience;
    }



    private Player makePlayerFromData(Long id, String name, String title, Race race, Profession profession, long birthday, boolean banned, int experience) {
        Player player = new Player();
        player.setId(id);
        player.setName(name);
        player.setTitle(title);
        player.setRace(race);
        player.setProfession(profession);
        player.setBirthday(new Date(birthday));
        player.setBanned(banned);
        player.setExperience(experience);

        int level = calculatePlayerLevel(experience);
        int untilNextLevel = calculatePlayerUntilNextLevel(level,experience);

        player.setLevel(level);
        player.setUntilNextLevel(untilNextLevel);
        return player;
    }



    public Player updatePlayer(long id, String name, String title, Race race, Profession profession, long birthday, boolean banned, int experience) {
        Player playerById = (Player) IPlayerRepository.findById(id).orElse(null);
        if (isNull(playerById)) {
            return null;
        }

        Player player = makePlayerFromData(id, name, title, race, profession, birthday, banned, experience);
        return IPlayerRepository.updateById(player);
    }



    public Player deletePlayer(long id) {
        Player player = IPlayerRepository.findById(id).orElse(null);
        if (isNull(player)) {
            return null;
        }

        IPlayerRepository.deleteById(id);
        return player;
    }
}
