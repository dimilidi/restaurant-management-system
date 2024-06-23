package bg.softuni.Pathfinder.service.dto;

import bg.softuni.Pathfinder.model.enums.LevelEnumType;

public class UserProfileDTO {

    private String username;
    private String fullName;
    private int age;
    private LevelEnumType level;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LevelEnumType getLevel() {
        return level;
    }

    public void setLevel(LevelEnumType level) {
        this.level = level;
    }
}

