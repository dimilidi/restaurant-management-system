package ExamPreparation.Classes.workout_JavaAdvancedRegularExam25June2022.workout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class Workout {
    private String type;
    private int exerciseCount;
    private List<Exercise> exercises;

    public Workout(String type, int exerciseCount) {
        this.type = type;
        this.exerciseCount = exerciseCount;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise exercise) {
        if(exerciseCount > exercises.size()) {
            exercises.add(exercise);
        }
    }

    public boolean removeExercise(String name, String muscle) {
        Exercise exerciseToRemove = getExercise(name, muscle);

        if(exerciseToRemove != null) {
            exercises.remove(exerciseToRemove);
            return true;
        }
        return false;
    }

    public Exercise getExercise(String name, String muscle) {
        return exercises.stream()
                .filter(exercise -> exercise.getName().equals(name) && exercise.getMuscle().equals(muscle))
                .findFirst()
                .orElse(null);
    }

    public Exercise getMostBurnedCaloriesExercise() {
        return exercises.stream().reduce(
                (ex1, ex2) -> ex1.getBurnedCalories() > ex2.getBurnedCalories() ? ex1 : ex2
        ).orElse(null);
    }

    public int getExerciseCount() {
        return exerciseCount;
    }

    public String getStatistics() {
        var sj = new StringJoiner("\n");
        sj.add("Workout type: " + type);
        exercises.stream().forEach(ex -> sj.add(ex.toString()));

        return sj.toString();
    }
}
