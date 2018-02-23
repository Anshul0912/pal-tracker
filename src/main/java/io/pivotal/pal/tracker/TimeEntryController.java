package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }


    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry fields) {
        TimeEntry createdEntry = timeEntryRepository.create(fields);
        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry foundEntry = timeEntryRepository.find(id);

        if (foundEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(foundEntry, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepository.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

     @PutMapping("/{id}")
     public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry fields) {
        TimeEntry updated = timeEntryRepository.update(id, fields);

        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {

        timeEntryRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
