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

    private Issue firstIssue = new Issue(1, "Error1", "Name1", 1, true, new HashSet<String>(Arrays.asList("label1","label2")), new HashSet<String>(Arrays.asList("assignee1", "assignee2")));
    private Issue secondIssue = new Issue(2, "Error2", "Name2", 5, false, new HashSet<String>(Arrays.asList("label3","label4")), new HashSet<String>(Arrays.asList("assignee3", "assignee4")));
    private Issue thirdIssue = new Issue(3, "Error3", "Name3", 10, true, new HashSet<String>(Arrays.asList("label5","label6")), new HashSet<String>(Arrays.asList("assignee5", "assignee6")));

    @Nested
    public class Empty{

        @Test
        void shouldSortByNewestIfEmpty() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldestIfEmpty() {
            List <Issue> expected = new ArrayList<>();
            List <Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }
        @Test
        void shouldFindByAuthorIfEmpty() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.findByAuthor("Name1"));
        }

        @Test
        void shouldFindByLabelIfEmpty() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.findByLabel(Collections.singleton("label1")));
        }

        @Test
        void shouldFindByAssigneeIfEmpty() {
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.findByAssignee(Collections.singleton("assignee1")));
        }
    }

    @Nested
    public class SingleItem {

        @Test
        void shouldSortByNewestIfOneExist() {
            manager.issueAdd(firstIssue);
            List <Issue> expected = new ArrayList<>();
            expected.add(firstIssue);
            List <Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldestIfOneExist() {
            manager.issueAdd(firstIssue);
            List <Issue> expected = new ArrayList<>();
            expected.add(firstIssue);
            List <Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAuthorIfOneExist() {
            manager.issueAdd(firstIssue);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.findByLabel(Collections.singleton("name1")));
        }

        @Test
        void shouldFindByLabelIfOneExist() {
            manager.issueAdd(firstIssue);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.findByLabel(Collections.singleton("label1")));
        }

        @Test
        void shouldFindByAssigneeIfOneExist() {
            manager.issueAdd(firstIssue);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.findByAssignee(Collections.singleton("assignee1")));
        }
    }

    @Nested
    public class MultipleItems{

        @Test
        void shouldAdd() {
            manager.issueAdd(firstIssue);
            manager.issueAdd(secondIssue);
            List <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue, secondIssue));
            List <Issue> actual = manager.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByNewest() {
            manager.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            List <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            List <Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldest() {
            manager.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            List <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(thirdIssue, secondIssue, firstIssue));
            List <Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAuthor() {
            manager.addAll(List.of(firstIssue,secondIssue,thirdIssue));
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.findByLabel(Collections.singleton("name1")));
        }

        @Test
        void shouldFindByLabel() {
            manager.addAll(List.of(firstIssue,secondIssue,thirdIssue));
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.findByLabel(Collections.singleton("label1")));
        }

        @Test
        void shouldFindByAssignee() {
            manager.addAll(List.of(firstIssue,secondIssue,thirdIssue));
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, manager.findByAssignee(Collections.singleton("assignee1")));
        }

    }


}