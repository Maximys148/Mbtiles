package com.maximys.mbtiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO В общем, задачка такая:
// TODO- Есть файл в формате .mbtiles, который представляет из себя набор тайлов карты какой-либо местности (можешь почитать сам об этом поподробнее)
// TODO- Тебе нужно найти любой пример такого файла и скачать к себе, а потом добавить в проект на Java Spring
// TODO- Добавить эндпоинт (GetMapping), который будет доставать тайл из этого файла по параметрам z, x, y. Где z - зум, x - колонка, y - строка, в которой лежит этот тайл
// TODO- Если получится, можешь реализовать ещё эндпоинт (PostMapping) для загрузки нового файла в формате .mbtiles в своё приложение
// TODO- Вот библиотека, которая тебе поможет с заданием: https://github.com/imintel/mbtiles4j
@SpringBootApplication
public class MbtilesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MbtilesApplication.class, args);
    }
}