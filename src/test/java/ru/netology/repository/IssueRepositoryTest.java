package ru.netology.repository;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueRepositoryTest {
    IssueRepository repository =  new IssueRepository();
    Issue firstIssue = new Issue(1, "Error1", "Name1", 1, true, new HashSet<String>(Arrays.asList("label1","label2")), new HashSet<String>(Arrays.asList("assignee1", "assignee2")));
    Issue secondIssue = new Issue(2, "Error2", "Name2", 5, false, new HashSet<String>(Arrays.asList("label3","label4")), new HashSet<String>(Arrays.asList("assignee3", "assignee4")));
    Issue thirdIssue = new Issue(3, "Error3", "Name3", 10, true, new HashSet<String>(Arrays.asList("label5","label6")), new HashSet<String>(Arrays.asList("assignee5", "assignee6")));

    @Nested
    public class EmptyRepository {

        @Test
        void findOpenedIfEmpty() {
            repository.addAll(List.of());
            Collection<Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void findClosedIfEmpty() {
            repository.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void findByAuthorIfEmpty() {
            repository.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAuthor("Name1");
            assertEquals(expected, actual);
        }

        @Test
        void findByLabelIfEmpty() {
            repository.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label1")));
            assertEquals(expected, actual);
        }

        @Test
        void findByAssigneeIfEmpty() {
            repository.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAssignee( new HashSet<String>(Arrays.asList("assignee1")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfClose() {
            repository.addAll(List.of());
            boolean expected = false;
            boolean actual = repository.closeById(1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfOpen() {
            repository.addAll(List.of());
            boolean expected = false;
            boolean actual = repository.openById(2);
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {

        @Test
        void findOpenedIfExist() {
            repository.addAll(List.of(firstIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue));
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void findOpenedIfNotExist() {
            repository.addAll(List.of(secondIssue));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void findClosedIfExist() {
            repository.addAll(List.of(secondIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(secondIssue));
            Collection <Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void findClosedIfNotExist() {
            repository.addAll(List.of(thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void findByAuthor() {
            repository.addAll(List.of(firstIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue));
            Collection <Issue> actual = repository.findByAuthor("Name1");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoAuthor() {
            repository.addAll(List.of(firstIssue));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAuthor("Bob");
            assertEquals(expected, actual);
        }

        @Test
        void findByLabel() {
            repository.addAll(List.of(thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(thirdIssue));
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label6")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoLabel() {
            repository.addAll(List.of(firstIssue));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label6")));
            assertEquals(expected, actual);
        }

        @Test
        void findByAssignee() {
            repository.addAll(List.of(firstIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue));
            Collection <Issue> actual = repository.findByAssignee( new HashSet<String>(Arrays.asList("assignee1")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosed() {
            repository.addAll(List.of(firstIssue));
            boolean expected = !firstIssue.isOpen();
            boolean actual = repository.closeById(1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenClose() {
            repository.addAll(List.of(firstIssue));
            boolean expected = false;
            boolean actual = repository.closeById(3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfNotExist() {
            repository.addAll(List.of(secondIssue));
            boolean expected = false;
            boolean actual = repository.closeById(2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpened() {
            repository.addAll(List.of(secondIssue));
            boolean expected = firstIssue.isOpen();
            boolean actual = repository.openById(2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenOpen() {
            repository.addAll(List.of(firstIssue));
            boolean expected = false;
            boolean actual = repository.openById(4);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpenedIfNotExist() {
            repository.addAll(List.of(firstIssue));
            boolean expected = false;
            boolean actual = repository.openById(1);
            assertEquals(expected, actual);
        }

    }

    @Nested
    public class MultipleItems {

        @Test
        void findOpenedIfExist() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue, thirdIssue));
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void findOpenedIfNotExist() {
            repository.addAll(List.of(secondIssue));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void findClosedIfExist() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(secondIssue));
            Collection <Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void findClosedIfNotExist() {
            repository.addAll(List.of(firstIssue));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void findByAuthor() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue));
            Collection <Issue> actual = repository.findByAuthor("Name1");
            assertEquals(expected, actual);
        }

        @Test
        void findByAuthorIfNotExist() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAuthor("Name6");
            assertEquals(expected, actual);
        }

        @Test
        void findByLabel() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(secondIssue));
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label3")));
            assertEquals(expected, actual);
        }

        @Test
        void findByLabelIfNotExist() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByLabel( new HashSet<String>(Arrays.asList("label10")));
            assertEquals(expected, actual);
        }

        @Test
        void findByAssignee() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(thirdIssue));
            Collection <Issue> actual = repository.findByAssignee( new HashSet<String>(Arrays.asList("assignee5")));
            assertEquals(expected, actual);
        }

        @Test
        void findByAssigneeIfNotExist() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = repository.findByAssignee( new HashSet<String>(Arrays.asList("assignee7")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldClose() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            boolean expected = !firstIssue.isOpen();
            boolean actual = repository.closeById(1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenClose() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            boolean expected = false;
            boolean actual = repository.closeById(3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpen() {
            repository.addAll(List.of(firstIssue));
            boolean expected = secondIssue.isOpen();
            boolean actual = repository.openById(2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenOpen() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            boolean expected = false;
            boolean actual = repository.openById(5);
            assertEquals(expected, actual);
        }
    }
}

