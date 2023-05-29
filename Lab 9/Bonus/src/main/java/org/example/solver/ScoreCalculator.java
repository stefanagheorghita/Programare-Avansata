//package org.example.solver;
//
//import entity.Album;
//import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
//import org.optaplanner.core.api.score.stream.*;
//
//import javax.persistence.Tuple;
//import java.util.*;
//
//public class ScoreCalculator implements ConstraintProvider {
//
//    private static final int MAX_TIME_DIFFERENCE = 5;
//
//    @Override
//    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
//        return new Constraint[]{
//                titlesStartWithSameLetter(constraintFactory),
//                releasedWithinTimePeriod(constraintFactory)
//        };
//    }
//
//    private Constraint titlesStartWithSameLetter(ConstraintFactory constraintFactory) {
//        return constraintFactory.from(Album.class)
//                .groupBy(Album::getTitle)
//                .filter((title, albums) -> {
//                    Set<Character> firstLetters = new HashSet<>();
//                    for (Album album : albums) {
//                        char firstLetter = album.getTitle().charAt(0);
//                        firstLetters.add(firstLetter);
//                    }
//                    return firstLetters.size() == 1;
//                })
//                .penalize("Titles start with different letters",
//                        HardSoftScore.ONE_HARD);
//    }
//
//
//
//
//    private boolean startsWithDifferentLetters(String title, List<Album> albums) {
//        if (albums.size() <= 1) {
//            return false;
//        }
//        char firstLetter = albums.get(0).getTitle().charAt(0);
//        for (Album album : albums) {
//            if (album.getTitle().charAt(0) != firstLetter) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    private Constraint releasedWithinTimePeriod(ConstraintFactory constraintFactory) {
//        return constraintFactory.from(Album.class)
//                .join(Album.class,
//                        Joiners.equal(Album::getReleaseYear),
//                        Joiners.equal(Album::getTitle))
//                .filter((album1, album2) ->
//                        Math.abs(album1.getReleaseYear() - album2.getReleaseYear()) > MAX_TIME_DIFFERENCE)
//                .penalize("Albums released outside the time period",
//                        HardSoftScore.ONE_HARD);
//    }
//}
