package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {

    @Autowired
    TimeEntryRepository timeEntryRepository;

    @Override
    public Health health() {

        int numEntries = timeEntryRepository.list().size();
        if (numEntries >= 5) {
            return Health.down().withDetail(
                    "Number of Time Entries in repo is greater than 5. Number of Time Entries is ",
                    numEntries).build();
        }
        else
            return Health.up().build();

    }
}
