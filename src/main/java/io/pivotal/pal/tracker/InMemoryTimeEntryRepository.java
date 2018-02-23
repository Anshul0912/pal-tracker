package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private List<TimeEntry> timeEntryList;

    public InMemoryTimeEntryRepository() {
        timeEntryList = new ArrayList<>();
    }

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(
                generateId(),
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryList.add(newTimeEntry);
        return newTimeEntry;
    }


    public TimeEntry find(long id) {
        for (TimeEntry t : timeEntryList) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public List<TimeEntry> list() {
        return Collections.unmodifiableList(timeEntryList);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        delete(id);
        TimeEntry updatedTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntryList.add(updatedTimeEntry);
        return updatedTimeEntry;
    }

    public void delete(long id) {
        TimeEntry foundTimeEntry = find(id);
        timeEntryList.remove(foundTimeEntry);
    }


    private long lastUsedId = 0;

    private long generateId() {
        lastUsedId = lastUsedId + 1;
        return lastUsedId;
    }
}
