package com.maximys.mbtiles.service;

import jakarta.annotation.PostConstruct;
import org.imintel.mbtiles4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Service
public class TileService {
    @Value("${mbTiles.filePath}")
    private String mbTilesFilePath; // Путь берётся из application.yml
    @Value("${mbTiles.filePathToSave}")
    private String mbTilesFilePathToSave; // Путь берётся из application.yml
    private MBTilesReader file;
    @PostConstruct
    public void init() throws IOException, MBTilesReadException {
        // Открываем файл один раз при старте приложения
        file = new MBTilesReader(new File(mbTilesFilePath));
    }


    public ResponseEntity<?> getTiles(int z, int x, int y){
        try {
            TileIterator tiles = file.getTiles();

            while (tiles.hasNext()) {
                Tile tile = tiles.next();
                int zoom = tile.getZoom();
                int column = tile.getColumn();
                int row = tile.getRow();

                // Возвращаем найденный тайл
                if (zoom == z && column == x && row == y) {
                    ByteArrayInputStream dataStream = (ByteArrayInputStream) tile.getData();
                    byte[] dataBytes = dataStream.readAllBytes();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_PNG);
                    return new ResponseEntity<>(dataBytes, headers, HttpStatus.OK);
                }
            }
            tiles.close();
            file.close();
            // Если тайл не найден, возвращаем сообщение
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Тайл не найден для заданных параметров: z=" + z + ", x=" + x + ", y=" + y);
        } catch (MBTilesReadException e) {
            throw new RuntimeException(e);
        }
    }
    public ResponseEntity<String> createMbtiles(MultipartFile file){
        /*MBTilesWriter w = new MBTilesWriter(file);
        String contentType = file.getContentType();
        file.getOriginalFilename();

         */

        return null;
    }
    /*MBTilesWriter w = new MBTilesWriter(new File("example.mbtiles"));
    MetadataEntry ent = new MetadataEntry();
//Add metadata parts
ent.setTilesetName("An example Tileset")
        .setTilesetType(MetadataEntry.TileSetType.BASE_LAYER)
	.setTilesetVersion("0.2.0")
	.setTilesetDescription("An example tileset description")
	.setTileMimeType(MetadataEntry.TileMimeType.PNG)
	.setAttribution("Tiles are Open Source!")
	.setTilesetBounds(-180, -85, 180, 85);
w.addMetadataEntry(ent);
//add someTile at Zoom (0), Column(0), Row (0)
w.addTile(someTileBytes, 0, 0, 0);
    File result = w.close();*/
}
