package zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @Operation(summary = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장", description = "/create/diary")
    @PostMapping("/create/diary")
    void createDiary(
            @Parameter(description = "일기를 저장할 날짜 (YYYY-MM-DD 형식)", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(description = "저장할 일기 텍스트", required = true)
            @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @Parameter(description = "일기를 조회할 날짜 (YYYY-MM-DD 형식)", required = true, example ="YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "선택한 기간 중의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @Parameter(description = "조회할 시작 날짜 (YYYY-MM-DD 형식)", required = true, example ="YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "조회할 종료 날짜 (YYYY-MM-DD 형식)", required = true, example ="YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @Operation(summary = "선택한 날짜의 일기 데이터를 업데이트합니다")
    @PutMapping("/update/diary")
    void updateDiary(
            @Parameter(description = "일기를 업데이트할 날짜 (YYYY-MM-DD 형식)", required = true, example ="YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(description = "업데이트할 일기 텍스트", required = true)
            @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "선택한 날짜의 일기 데이터를 삭제합니다")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @Parameter(description = "일기를 삭제할 날짜 (YYYY-MM-DD 형식)", required = true, example ="YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
