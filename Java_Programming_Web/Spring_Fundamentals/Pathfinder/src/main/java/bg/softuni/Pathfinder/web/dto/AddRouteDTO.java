package bg.softuni.Pathfinder.web.dto;

import bg.softuni.Pathfinder.model.enums.LevelEnumType;

public class AddRouteDTO {
    private String name;
    private String description;
    private LevelEnumType level;
    private String videoUrl;

    public AddRouteDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LevelEnumType getLevel() {
        return level;
    }

    public void setLevel(LevelEnumType level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
