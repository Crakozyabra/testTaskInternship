package com.game.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

    @Entity
    public class PlayerRaceAndProfessionString {
        @Id
        @GeneratedValue
        private Long id;
        @Column(length = 12)
        private String name;
        @Column(length = 30)
        private String title;
        private String race;
        private String profession;
        private  Integer experience;
        private Integer level;
        private Integer untilNextLevel;
        private Date birthday;
        private Boolean banned;

        public PlayerRaceAndProfessionString() {}

        public PlayerRaceAndProfessionString(String name, String title, String race, String profession, Integer experience, Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
            this.name = name;
            this.title = title;
            this.race = race;
            this.profession = profession;
            this.experience = experience;
            this.level = level;
            this.untilNextLevel = untilNextLevel;
            this.birthday = birthday;
            this.banned = banned;
        }

        public PlayerRaceAndProfessionString(Long id, String name, String title, String race, String profession, Integer experience, Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
            this.id=id;
            this.name = name;
            this.title = title;
            this.race = race;
            this.profession = profession;
            this.experience = experience;
            this.level = level;
            this.untilNextLevel = untilNextLevel;
            this.birthday = birthday;
            this.banned = banned;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public Integer getExperience() {
            return experience;
        }

        public void setExperience(Integer experience) {
            this.experience = experience;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Integer getUntilNextLevel() {
            return untilNextLevel;
        }

        public void setUntilNextLevel(Integer untilNextLevel) {
            this.untilNextLevel = untilNextLevel;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public Boolean getBanned() {
            return banned;
        }

        public void setBanned(Boolean banned) {
            this.banned = banned;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    ", race=" + race +
                    ", profession=" + profession +
                    ", experience=" + experience +
                    ", level=" + level +
                    ", untilNextLevel=" + untilNextLevel +
                    ", birthday=" + birthday +
                    ", banned=" + banned +
                    '}';
        }

}
