package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@AllArgsConstructor
@NoArgsConstructor
public class IssueRepository {
    private Collection<Issue> items = new ArrayList<>();

    public boolean addAll(List<Issue> items) {
        return this.items.addAll(items);
    }

    public List<Issue> findAll() {
        return (List<Issue>) items;
    }

    public boolean save(Issue item) {
        return items.add(item);
    }

    public List<Issue> findOpen() {
        List<Issue> issues = new ArrayList<>();
        for (Issue item : items)
            if (item.isOpen()) {
                issues.add(item);
            }
        return issues;
    }

    public List<Issue> findClosed() {
        List<Issue> issues = new ArrayList<>();
        for (Issue item : items)
            if (!item.isOpen()) {
                issues.add(item);
            }
        return issues;
    }

    public boolean closeById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && item.isOpen()) {
                item.setOpen(false);
                return item.isOpen();
            }
        }
        return false;
    }

    public boolean openById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && !item.isOpen()) {
                item.setOpen(true);
                return item.isOpen();
            }
        }
        return false;
    }
}
