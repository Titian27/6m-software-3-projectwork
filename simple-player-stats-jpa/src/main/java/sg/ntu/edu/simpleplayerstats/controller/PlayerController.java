package sg.ntu.edu.simpleplayerstats.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import sg.ntu.edu.simpleplayerstats.entity.Player;
import sg.ntu.edu.simpleplayerstats.entity.Statistic;
import sg.ntu.edu.simpleplayerstats.service.PlayerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;

    // CREATE
    @PostMapping("")
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody Player player) {
        return new ResponseEntity<>(playerService.createPlayer(player), HttpStatus.CREATED);
    }

    // READ (GET ALL)
    @GetMapping("")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        return new ResponseEntity<>(playerService.getPlayer(id), HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return new ResponseEntity<>(playerService.updatePlayer(id, player), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Player>> searchPlayers(@RequestParam String playerName) {
        List<Player> foundPlayers = playerService.searchPlayers(playerName);
        return new ResponseEntity<>(foundPlayers, HttpStatus.OK);
    }

    // NESTED ROUTE
    @PostMapping("{id}/statistics")
    public ResponseEntity<Statistic> addStatisticToPlayer(@PathVariable Long id,
            @RequestBody Statistic statistic) {
        Statistic newStatistic = playerService.addStatisticToPlayer(id, statistic);
        return new ResponseEntity<>(newStatistic, HttpStatus.CREATED);
    }
}
