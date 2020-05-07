
package com.nrboudreau.mazegenerator;

import com.nrboudreau.mazegenerator.generators.RecursiveBacktracking;
import com.nrboudreau.mazegenerator.model.Maze;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/recursiveBacktracking")
    public Maze maze(@RequestParam(value = "width", defaultValue = "10") String width, @RequestParam(value = "height", defaultValue = "10") String height) {
        try {
            return new RecursiveBacktracking().generate(width, height);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error",  e);
        }

    }

}
