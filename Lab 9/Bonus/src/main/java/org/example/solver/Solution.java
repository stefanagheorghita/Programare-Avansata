package org.example.solver;

import entity.Album;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        List<Album> albums = new ArrayList<>();
        albums.add(new Album("Album1", 2000));
        albums.add(new Album("Album2", 2001));
        albums.add(new Album("Album3", 2002));
        albums.add(new Album("Album4", 2000));
        albums.add(new Album("Album5", 2002));
        int k = 1;
        int p = 5;

        Model model = new Model("AlbumScheduling");

        IntVar[] selected = model.intVarArray("selected", albums.size(), 0, 1);
        IntVar[] releaseYears = new IntVar[albums.size()];
        IntVar[] firstLetters = new IntVar[albums.size()];
        char firstLetter = albums.get(0).getTitle().charAt(0);
        List<Album> selectedAlbums = new ArrayList<>();

        for (int i = 1; i < albums.size(); i++) {
            Album album = albums.get(i);
            if (album.getTitle().charAt(0) == firstLetter) {
                selectedAlbums.add(album);
                selected[i] = model.intVar("selected[" + i + "]", 1);
                System.out.println("selected[" + i + "]: " + selected[i].getValue());
            }
        }
        for (Album album : selectedAlbums) {
            System.out.println(album.getTitle());
        }
        for (int i = 0; i < albums.size(); i++) {
            Album album = albums.get(i);
            releaseYears[i] = model.intVar("releaseYear[" + i + "]", album.getReleaseYear());
            firstLetters[i] = model.intVar("firstLetter[" + i + "]", album.getTitle().charAt(0));
            selected[i] = model.intVar("selected[" + i + "]", 0);
            model.ifThen(
                    model.arithm(firstLetters[i], "=", firstLetters[0]),
                    model.arithm(selected[i], "=", 1)
            );
            System.out.println("selected[" + i + "]: " + selected[i].getValue());
        }


        model.sum(selected, ">=", k).post();

        IntVar[][] yearDiff = new IntVar[albums.size()][albums.size()];

        for (int i = 0; i < albums.size() - 1; i++) {
            for (int j = i + 1; j < albums.size(); j++) {
                int diff = Math.abs(albums.get(i).getReleaseYear() - albums.get(j).getReleaseYear());
                yearDiff[i][j] = model.intVar("yearDiff[" + i + "," + j + "]", diff);
                yearDiff[j][i] = yearDiff[i][j];
                model.arithm(yearDiff[i][j], "!=", 0).post();
                model.absolute(yearDiff[i][j], yearDiff[i][j]).post();
            }
        }

        Solver solver = model.getSolver();
        if (solver.solve()) {
            for (int i = 0; i < albums.size(); i++) {
                if (selected[i].getValue() == 1) {
                    Album album = albums.get(i);
                    System.out.println("Album: " + album.getTitle() + ", Release Year: " + album.getReleaseYear());
                }
            }
        } else {
            System.out.println("No solution found.");
        }

        System.out.println("Year Differences:");
        for (int i = 0; i < albums.size() - 1; i++) {
            for (int j = i + 1; j < albums.size(); j++) {
                System.out.println("yearDiff[" + i + "," + j + "]: " + yearDiff[i][j].getValue());
            }
        }
    }
}
