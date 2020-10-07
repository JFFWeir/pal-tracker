package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    TimeEntryRepository ter1;

    public TimeEntryController(TimeEntryRepository ter) {
        ter1 = ter;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry newTimeEntry = ter1.create(timeEntryToCreate);
        return new ResponseEntity<>(newTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry newTimeEntry = ter1.find(timeEntryId);
        if (newTimeEntry == null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(newTimeEntry, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        List<TimeEntry> lte = ter1.list();
        return new ResponseEntity<>(lte, HttpStatus.OK);
    }


    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {


        TimeEntry newTimeEntry = ter1.update(timeEntryId, expected);
        if (newTimeEntry != null)
            return new ResponseEntity<>(newTimeEntry, HttpStatus.OK);

        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {

        //if (ter1.find(timeEntryId) != null) {
            ter1.delete(timeEntryId);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        //}
        //else
        //    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
