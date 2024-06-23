package bg.softuni.paintingcollectors.service;

import bg.softuni.paintingcollectors.model.dto.painting.*;
import bg.softuni.paintingcollectors.model.entity.PaintingEntity;
import bg.softuni.paintingcollectors.model.entity.StyleEntity;
import bg.softuni.paintingcollectors.model.entity.UserEntity;
import bg.softuni.paintingcollectors.repository.PaintingRepository;
import bg.softuni.paintingcollectors.repository.StyleRepository;
import bg.softuni.paintingcollectors.repository.UserRepository;
import bg.softuni.paintingcollectors.util.CurrentUser;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaintingService {
    private final ModelMapper modelMapper;
    private final StyleRepository styleRepository;
    private final PaintingRepository paintingRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final UserService userService;

    public PaintingService(ModelMapper modelMapper, StyleRepository styleRepository, PaintingRepository paintingRepository, CurrentUser currentUser, UserRepository userRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.styleRepository = styleRepository;
        this.paintingRepository = paintingRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public void add(AddPaintingDTO data) {
        PaintingEntity paintingToAdd = modelMapper.map(data, PaintingEntity.class);
        StyleEntity style = styleRepository.findByName(data.getStyle()).orElse(null);
        paintingToAdd.setStyle(style);
        paintingToAdd.setOwner(userRepository.findById(currentUser.id()).orElse(null));

        paintingRepository.save(paintingToAdd);
    }


    public List<PaintingEntity> getTopRatedPaintings() {
         PageRequest pageRequest = PageRequest.of(0, 2);
        return paintingRepository.findTopTwoByOrderByVotesDescNameAsc(pageRequest);
    }


    public PaintingHomeDTO getPaintingsForHomePage() {
        List<PaintingEntity> paintings = paintingRepository.findAll();

        List<MyPaintingDTO> myPaintings = new ArrayList<>();
        List<FavouritePaintingDTO> favouritePaintings = new ArrayList<>();
        List<OtherPaintingDTO> otherPaintings = new ArrayList<>();
        List<MostRatedDTO> ratedPaintings = new ArrayList<>();


        for (PaintingEntity painting : paintings) {
            if (painting.getOwner().getUsername().equals(currentUser.username())) {
                myPaintings.add(new MyPaintingDTO(painting));
            } else {
                otherPaintings.add(new OtherPaintingDTO(painting));
            }
        }

        List<PaintingEntity> favoritePaintingsById = userRepository.findFavoritePaintingsByUserId(currentUser.id());
        for (PaintingEntity paintingEntity : favoritePaintingsById) {
            favouritePaintings.add(new FavouritePaintingDTO(paintingEntity));
        }

        for (PaintingEntity topRatedPainting : getTopRatedPaintings()) {
            ratedPaintings.add(new MostRatedDTO(topRatedPainting));
        }


        return new PaintingHomeDTO(myPaintings, favouritePaintings, otherPaintings, ratedPaintings);
    }

    @Transactional
    public void addToFavorites(Long paintingId) {
        Optional<PaintingEntity> paintingOptional = paintingRepository.findById(paintingId);
        Optional<UserEntity> userOptional = userRepository.findByUsername(currentUser.username());

        if (paintingOptional.isPresent() && userOptional.isPresent()) {
            PaintingEntity painting = paintingOptional.get();
            UserEntity user = userOptional.get();

            if (!user.getFavoritePaintings().contains(painting)) {
                painting.setFavorite(true);
                user.addToFavorites(painting);

                paintingRepository.save(painting);
                userRepository.save(user);
            }
        }
    }


    @Transactional
    public void delete(Long id) {
        paintingRepository.deleteById(id);
    }

    @Transactional
    public void deleteFavourite(Long id) {
        Optional<PaintingEntity> paintingOptional = paintingRepository.findById(id);
        Optional<UserEntity> userOptional = userRepository.findByUsername(currentUser.username());

        if (paintingOptional.isPresent() && userOptional.isPresent()) {
            PaintingEntity painting = paintingOptional.get();
            UserEntity user = userOptional.get();

            user.getFavoritePaintings().remove(painting);
            painting.setFavorite(false);

            userRepository.save(user);
            paintingRepository.save(painting);
        }
    }

    @Transactional
    public void vote(Long id) {
        Optional<PaintingEntity> paintingOptional = paintingRepository.findById(id);
        Optional<UserEntity> userOptional = userRepository.findByUsername(currentUser.username());

        if (paintingOptional.isPresent() && userOptional.isPresent()) {
            PaintingEntity painting = paintingOptional.get();
            UserEntity user = userOptional.get();

            if (user.getRatedPaintings().contains(painting)) {
                return;
            }
            painting.addVote();
            user.addRatedPainting(painting);
            paintingRepository.save(painting);
            userRepository.save(user);
        }
    }
}
