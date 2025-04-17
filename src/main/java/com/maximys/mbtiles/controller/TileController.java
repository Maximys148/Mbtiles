package com.maximys.mbtiles.controller;

import com.maximys.mbtiles.service.TileService;
import org.imintel.mbtiles4j.MBTilesReadException;
import org.imintel.mbtiles4j.MBTilesReader;
import org.imintel.mbtiles4j.Tile;
import org.imintel.mbtiles4j.TileIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/tiles")
public class TileController {

    @Autowired
    private final TileService tileService;

    public TileController(TileService tileService) {
        this.tileService = tileService;
    }

    @GetMapping("/getTile/{z}/{x}/{y}")
    public ResponseEntity<?> getTile(@PathVariable int z, @PathVariable int x, @PathVariable int y) {
        return tileService.getTiles(z, x, y);
    }
    @PostMapping("/createMbtiles")
    public ResponseEntity<String> uploadMbTiles(@RequestParam("file") MultipartFile file) {
        return tileService.createMbtiles(file);
    }
}