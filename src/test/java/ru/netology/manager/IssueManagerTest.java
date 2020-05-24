package ru.netology.manager;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    Issue firstIssue = new Issue(1, "Error1", "Name1", 1, true, new HashSet<String>(Arrays.asList("label1","label2")), new HashSet<String>(Arrays.asList("assignee1", "assignee2")));
    Issue secondIssue = new Issue(2, "Error2", "Name2", 5, false, new HashSet<String>(Arrays.asList("label3","label4")), new HashSet<String>(Arrays.asList("assignee3", "assignee4")));
    Issue thirdIssue = new Issue(3, "Error3", "Name3", 10, true, new HashSet<String>(Arrays.asList("label5","label6")), new HashSet<String>(Arrays.asList("assignee5", "assignee6")));

    @Nested
    public class Empty{

        @Test
        void shouldSortByNewestIfEmpty() {
            manager.addAll(List.of());
            Collection<Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldestIfEmpty() {
            manager.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {

        @Test
        void shouldSortByNewestIfOneExist() {
            manager.addAll(List.of(firstIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.add(firstIssue);
            Collection <Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldestIfOneExist() {
            manager.addAll(List.of(firstIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.add(firstIssue);
            Collection <Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleItems{

        @Test
        void shouldAdd() {
            manager.issueAdd(firstIssue);
            manager.issueAdd(secondIssue);
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue, secondIssue));
            Collection <Issue> actual = manager.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByNewest() {
            manager.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldest() {
            manager.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(thirdIssue, secondIssue, firstIssue));
            Collection <Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }
    }


}