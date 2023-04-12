package com.example.homework12.controller;

import com.example.homework12.dto.StudentCourseMarkDTO;
import com.example.homework12.dto.StudentDTO;
import com.example.homework12.service.StudentCourseMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("studentCourseMark")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseMarkDTO dto) {
        StudentCourseMarkDTO list = studentCourseMarkService.create(dto);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentCourseMarkDTO dto) {
        return ResponseEntity.ok(studentCourseMarkService.update(id, dto));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getById(id));
    }


    @GetMapping("/getByIdDetail/{id}")
    public ResponseEntity<?> getByIdDetail(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getByIdDetail(id));
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
       return ResponseEntity.ok(studentCourseMarkService.delete(id));
    }

    @GetMapping("/getGivenDate/{createDate}")
    public ResponseEntity<?> getGivenDate(@PathVariable("createDate") LocalDateTime createDate) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getGivenDate(createDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getBetweenDate")
    public ResponseEntity<?> getBetweenDate(@RequestParam("fromDate") LocalDateTime firstDate,
                                            @RequestParam("toDate") LocalDateTime secondDate) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getBetweenDate(firstDate,secondDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getAllByDateDESC")
    public ResponseEntity<?> getAllByDateDesc(@RequestParam("studentId") Integer id,
                                              @RequestParam("courseId") Integer courseId) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getAllByDateDesc(id,courseId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getAllByStudentDateDesc")
    public ResponseEntity<?> getAllByStudentDateDesc(@RequestParam("studentId") Integer studentId, @RequestParam("courseId") Integer courseId) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getAllByStudentDateDesc(studentId,courseId);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/finalMarkAndCourse/{id}")
    public ResponseEntity<?> getByThreeMark(@PathVariable("id") Integer id) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getStudentIdEndMarks(id);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/findTopThreeMark/{id}")
    public ResponseEntity<?> getByBigThreeMark(@PathVariable("id") Integer id) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getStudentIdThreeMarks(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getStudentIdFirstMark/{id}")
    public ResponseEntity<?> getStudentIdFirstMark(@PathVariable("id") Integer id) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getStudentIdFirstMark(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getCourseIdFirstMarks")
    public ResponseEntity<?> getByFirstThreeMark(@RequestParam("courseId") Integer courseId,
                                                 @RequestParam("studentId") Integer studentId) {
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getCourseIdFirstMarks(courseId,studentId);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/getCourseIdBigFirstMarks")
    public ResponseEntity<?> getCourseIdBigFirstMarks(@RequestParam("courseId") Integer id,
                                                      @RequestParam("studentId") Integer courseId) {
        return ResponseEntity.ok(studentCourseMarkService.getCourseIdBigFirstMarks(id,courseId));
    }

//    @GetMapping("/getAverageMarkAndCourseId/{id}")
//    public ResponseEntity<?> getAvgMarkAndCourseId(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok(studentCourseMarkService.getAverageCourseId(id));
//    }

    @GetMapping("/getAvgMarkAndCourseId")
    public ResponseEntity<?> getAvgMarkAndCourseId(@RequestParam("id") Integer id, @RequestParam("cid") Integer cid) {
        return ResponseEntity.ok(studentCourseMarkService.getAverg(id, cid));
    }



    @GetMapping("/getMarkOfGivenOver")
    public ResponseEntity<?> getMarkOfGivenOver(@RequestParam("courseId") Integer id, @RequestParam("mark") Integer mark) {
        return ResponseEntity.ok(studentCourseMarkService.getMarkOfGivenOver(id, mark));
    }

    @GetMapping("/getGivenCourseOfMaxMark/{id}")
    public ResponseEntity<?> getGivenCourseOfMaxMark(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getGivenCourseOfMaxMark(id));
    }


    @GetMapping("/getGivenCourseOfAvgMark/{id}")
    public ResponseEntity<?> getGivenCourseOfAvgMark(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getGivenCourseOfAvgMark(id));
    }


    @GetMapping("/getGivenCourseOfTakenMark/{id}")
    public ResponseEntity<?> getGivenCourseOfTakenMark(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.getGivenCourseOfTakenMark(id));
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<?> test() {
         studentCourseMarkService.test();
         return null;
    }

//    @GetMapping("/test2/{id}")
//    public void test(@PathVariable("id") Integer id) {
//        studentCourseMarkService.test2(id);
//    }






}
