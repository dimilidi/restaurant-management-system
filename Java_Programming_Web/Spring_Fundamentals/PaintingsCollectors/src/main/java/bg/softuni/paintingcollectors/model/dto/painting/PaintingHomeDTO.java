package bg.softuni.paintingcollectors.model.dto.painting;

import java.util.List;

public class PaintingHomeDTO {
    private List<MyPaintingDTO> myPaintings;
    private List<FavouritePaintingDTO> favouritePaintings;
    private List<OtherPaintingDTO> allOtherPaintings;
    private List<MostRatedDTO> mostRated;


    public PaintingHomeDTO() {
    }


    public PaintingHomeDTO(List<MyPaintingDTO> myPaintings, List<FavouritePaintingDTO> favouritePaintings, List<OtherPaintingDTO> allOtherPaintings, List<MostRatedDTO> mostRated) {
        this.myPaintings = myPaintings;
        this.favouritePaintings = favouritePaintings;
        this.allOtherPaintings = allOtherPaintings;
        this.mostRated = mostRated;
    }

    public List<MyPaintingDTO> getMyPaintings() {
        return myPaintings;
    }

    public void setMyPaintings(List<MyPaintingDTO> myPaintings) {
        this.myPaintings = myPaintings;
    }

    public List<FavouritePaintingDTO> getFavouritePaintings() {
        return favouritePaintings;
    }

    public void setFavouritePaintings(List<FavouritePaintingDTO> favouritePaintings) {
        this.favouritePaintings = favouritePaintings;
    }

    public List<OtherPaintingDTO> getAllOtherPaintings() {
        return allOtherPaintings;
    }

    public void setAllOtherPaintings(List<OtherPaintingDTO> allOtherPaintings) {
        this.allOtherPaintings = allOtherPaintings;
    }

    public List<MostRatedDTO> getMostRated() {
        return mostRated;
    }

    public void setMostRated(List<MostRatedDTO> mostRated) {
        this.mostRated = mostRated;
    }
}
