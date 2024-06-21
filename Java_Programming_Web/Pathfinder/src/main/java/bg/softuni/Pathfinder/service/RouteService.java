package bg.softuni.Pathfinder.service;

import bg.softuni.Pathfinder.data.RouteRepository;
import bg.softuni.Pathfinder.data.UserRepository;
import bg.softuni.Pathfinder.model.Picture;
import bg.softuni.Pathfinder.model.Route;
import bg.softuni.Pathfinder.service.dto.RouteShortInfoDTO;
import bg.softuni.Pathfinder.web.dto.AddRouteDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final Random random;

    public RouteService(RouteRepository routeRepository, UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.random = new Random();
    }

    @Transactional
    public List<RouteShortInfoDTO> getAll() {
        return routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfo)
                .toList();
    }

    @Transactional
    public RouteShortInfoDTO getRandomRoute() {
        long routeCount = routeRepository.count();
        long randomId = random.nextLong(routeCount) + 1;

        Optional<Route> route = routeRepository.findById(randomId);

        RouteShortInfoDTO dto = modelMapper.map(route.get(), RouteShortInfoDTO.class);

        Optional<Picture> first = route.get().getPictures().stream().findFirst();

        dto.setImageUrl(first.get().getUrl());

        return mapToShortInfo(route.get());
    }

    private RouteShortInfoDTO mapToShortInfo(Route route) {
        RouteShortInfoDTO dto = modelMapper.map(route, RouteShortInfoDTO.class);

        Optional<Picture> first = route.getPictures().stream().findFirst();
        dto.setImageUrl(first.get().getUrl());

        return dto;
    }

    public boolean add(AddRouteDTO data, MultipartFile gpxFile) throws IOException {
        Route toInsert = modelMapper.map(data, Route.class);

        Path destinationFile = Paths
                .get("src", "main","resources", "uploads", "file.gpx")
                .normalize()
                .toAbsolutePath();

        try (InputStream inputStream = gpxFile.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        }

        return false;

    }
}
