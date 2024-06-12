package bg.softuni.Pathfinder.service;

import bg.softuni.Pathfinder.data.RouteRepository;
import bg.softuni.Pathfinder.model.Picture;
import bg.softuni.Pathfinder.model.Route;
import bg.softuni.Pathfinder.service.dto.RouteShortInfoDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper  modelMapper;
    private final Random random;

    public RouteService(RouteRepository routeRepository, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
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
        long randomId = random.nextLong(routeCount) + 1 ;

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
}
