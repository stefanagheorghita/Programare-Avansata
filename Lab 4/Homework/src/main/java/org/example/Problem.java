package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem {
    private Map<Student, List<Project>> prefMap = new HashMap<>();

    public Problem() {
    }


    public Problem(Map<Student, List<Project>> prefMap) {
        this.prefMap = prefMap;
    }

    public void newKey(Student s, List<Project> p) {
        prefMap.put(s, p);
    }

    public void getLowPref() {
        double average = prefMap.values().stream().mapToInt(List::size).average().orElse(0.0);
        prefMap.entrySet().stream().filter(entry -> entry.getValue().size() < average).forEach(System.out::println);
    }

    public Map<Student, List<Project>> getPrefMap() {
        return prefMap;
    }

    public void setPrefMap(Map<Student, List<Project>> prefMap) {
        this.prefMap = prefMap;
    }

    @Override
    public String toString() {
        return "Problem{" + "prefMap=" + prefMap + '}';
    }
}
