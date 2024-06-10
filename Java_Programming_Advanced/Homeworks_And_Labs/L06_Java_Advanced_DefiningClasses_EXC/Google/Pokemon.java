package Homeworks_And_Labs.L06_Java_Advanced_DefiningClasses_EXC.Google;

public class Pokemon {
    private String pokemonName;
    private String pokemonType;

    public Pokemon(String pokemonName, String pokemonType) {
        this.pokemonName = pokemonName;
        this.pokemonType = pokemonType;
    }

    @Override
    public String toString() {
        return pokemonName + " " + pokemonType;
    }
}
