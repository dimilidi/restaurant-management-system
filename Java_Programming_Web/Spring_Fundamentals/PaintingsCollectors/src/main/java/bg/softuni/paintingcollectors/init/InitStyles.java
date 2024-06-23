package bg.softuni.paintingcollectors.init;

import bg.softuni.paintingcollectors.model.entity.StyleEntity;
import bg.softuni.paintingcollectors.model.enums.StyleNameEnum;
import bg.softuni.paintingcollectors.repository.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class InitStyles implements CommandLineRunner {

    private final Map<StyleNameEnum, String> styleDescriptions = Map.of(
            StyleNameEnum.IMPRESSIONISM, "Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture.",
            StyleNameEnum.ABSTRACT,"Abstract art does not attempt to represent recognizable subjects in a realistic manner. ",
            StyleNameEnum.EXPRESSIONISM, "Expressionism is a style of art that doesn't concern itself with realism.",
            StyleNameEnum.SURREALISM, "Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation.",
            StyleNameEnum.REALISM, "Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance."
    );

    private final StyleRepository styleRepository;

    public InitStyles(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = styleRepository.count();

        if (count > 0) {
            return;
        }

        for (StyleNameEnum styleNameEnum : styleDescriptions.keySet()) {
            StyleEntity style = new StyleEntity(styleNameEnum, styleDescriptions.get(styleNameEnum));


            styleRepository.save(style);
        }
    }
}


