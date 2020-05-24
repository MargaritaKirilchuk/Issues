package ru.netology.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Issue implements Comparable<Issue>{
    private int id;
    private String title;
    private String author;
    private int daysAgoOpen;
    private boolean isOpen;
    private Set<String> issueLabels = new HashSet<>();
    private Set<String> issueAssignee = new HashSet<>();

    @Override
    public int compareTo(Issue o) {
        return daysAgoOpen - o.daysAgoOpen;
    }
}
