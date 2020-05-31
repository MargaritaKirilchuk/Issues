package ru.netology.repository;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueRepositoryTest {
    IssueRepository repository =  new IssueRepository();
    private Issue firstIssue = new Issue(1, "Error1", "Name1", 1, true, new HashSet<String>(Arrays.asList("label1","label2")), new HashSet<String>(Arrays.asList("assignee1", "assignee2")));
    private Issue secondIssue = new Issue(2, "Error2", "Name2", 5, false, new HashSet<String>(Arrays.asList("label3","label4")), new HashSet<String>(Arrays.asList("assignee3", "assignee4")));
    private Issue thirdIssue = new Issue(3, "Error3", "Name3", 10, true, new HashSet<String>(Arrays.asList("label5","label6")), new HashSet<String>(Arrays.asList("assignee5", "assignee6")));

    @Nested
    public class EmptyRepository {

        @Test
        void findOpenedIfEmpty() {
            List<Issue> expected = new ArrayList<>();
            List <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }
        @Test
        void findClosedIfEmpty() {
            List <Issue> expected = new ArrayList<>();
            List <Issue> actual = repository.findClosed();
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
            repository.save(firstIssue);
            List <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue));
            List <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void findOpenedIfNotExist() {
            repository.save(secondIssue);
            List <Issue> expected = new ArrayList<>();
            List <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void findClosedIfExist() {
            repository.save(secondIssue);
            List <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(secondIssue));
            List <Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void findClosedIfNotExist() {
            repository.save(thirdIssue);
            List <Issue> expected = new ArrayList<>();
            List <Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosed() {
            repository.save(firstIssue);
            boolean expected = !firstIssue.isOpen();
            boolean actual = repository.closeById(1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenClose() {
            repository.save(firstIssue);
            boolean expected = false;
            boolean actual = repository.closeById(3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfNotExist() {
            repository.save(secondIssue);
            boolean expected = false;
            boolean actual = repository.closeById(2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpened() {
            repository.save(secondIssue);
            boolean expected = firstIssue.isOpen();
            boolean actual = repository.openById(2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnFalseIfWrongIdWhenOpen() {
            repository.save(firstIssue);
            boolean expected = false;
            boolean actual = repository.openById(4);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindOpenedIfNotExist() {
            repository.save(firstIssue);
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
            List <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(firstIssue, thirdIssue));
            List <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void findOpenedIfNotExist() {
            repository.save(firstIssue);
            List <Issue> expected = new ArrayList<>();
            List <Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void findClosedIfExist() {
            repository.addAll(List.of(firstIssue, secondIssue, thirdIssue));
            List <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(secondIssue));
            List <Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void findClosedIfNotExist() {
            repository.save(firstIssue);
            List <Issue> expected = new ArrayList<>();
            List <Issue> actual = repository.findClosed();
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
            repository.save(firstIssue);
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

