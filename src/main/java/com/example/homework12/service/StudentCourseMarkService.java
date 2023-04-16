package com.example.homework12.service;


import com.example.homework12.dto.*;
import com.example.homework12.entity.CourseEntity;
import com.example.homework12.entity.StudentCourseMarkEntity;
import com.example.homework12.entity.StudentEntity;
import com.example.homework12.repository.StudentCourseMarkCustomRepository;
import com.example.homework12.repository.StudentCourseMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentCourseMarkCustomRepository studentCourseMarkCustomRepository;

    public StudentCourseMarkEntity get(Integer id) {
        Optional<StudentCourseMarkEntity> optional = studentCourseMarkRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ArithmeticException("Student not found: " + id);
        }
        return optional.get();
    }

    //1
    public StudentCourseMarkDTO create(StudentCourseMarkDTO dto) {
        StudentEntity existS = studentService.get(dto.getStudentId());
        CourseEntity existC = courseService.get(dto.getCourseId());

        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setStudent(existS);
        entity.setCourse(existC);
        entity.setMark(dto.getMark());
        entity.setCreateDate(dto.getCreateDate());

        studentCourseMarkRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    //2
    public String update(Integer id, StudentCourseMarkDTO dto) {
        StudentEntity studentId = studentService.get(dto.getStudentId());
        CourseEntity courseId = courseService.get(dto.getCourseId());

        StudentCourseMarkEntity entity = get(id);
        entity.setStudent(studentId);
        entity.setCourse(courseId);
        entity.setMark(dto.getMark());
        entity.setCreateDate(dto.getCreateDate());
        studentCourseMarkRepository.save(entity);
        return "update successfully";
    }

    //3
    public StudentCourseMarkDTO getById(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();

        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setCourseId(entity.getCourse().getId());
        dto.setMark(entity.getMark());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    //4
    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> all = studentCourseMarkRepository.findAll();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        all.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    //5
    public Boolean delete(Integer id) {
        studentCourseMarkRepository.deleteById(id);
        return true;
    }

    //6
    public StudentCourseD getByIdDetail(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        StudentCourseD dto = new StudentCourseD();

        dto.setId(entity.getId());
        dto.setStudentD(new StudentD(entity.getStudent().getId(), entity.getStudent().getName(), entity.getStudent().getSurname()));
        dto.setCourseD(new CourseD(entity.getCourse().getId(), entity.getCourse().getName()));
        dto.setMark(entity.getMark());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }

    //7
    public List<StudentCourseMarkDTO> getGivenDate(LocalDateTime date) {
        List<StudentCourseMarkEntity> entities = studentCourseMarkRepository.getByCreateDate(date);
        List<StudentCourseMarkDTO> list = new LinkedList<>();
        entities.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            list.add(dto);
        });
        return list;
    }

    //8
    public List<StudentCourseMarkDTO> getBetweenDate(LocalDateTime firstDate, LocalDateTime secondDate) {
        List<StudentCourseMarkEntity> list = studentCourseMarkRepository.getByCreateDateBetween(firstDate, secondDate);
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        list.forEach(bDate -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(bDate.getId());
            dto.setStudentId(bDate.getStudent().getId());
            dto.setCourseId(bDate.getCourse().getId());
            dto.setMark(bDate.getMark());
            dto.setCreateDate(bDate.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    //9
    public List<StudentCourseMarkDTO> getAllByDateDesc(Integer id, Integer mark) {
        List<StudentCourseMarkEntity> all = studentCourseMarkRepository.findAllByStudentIdAndMarkOrderByCreateDateDesc(id, mark);
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    //10
    public List<StudentCourseMarkDTO> getAllByStudentDateDesc(Integer id, Integer courseId) {
        List<StudentCourseMarkEntity> all = studentCourseMarkRepository.getAllByStudentIdAndCourseIdOrderByCreateDateDesc(id, courseId);
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        all.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    //11
    public List<StudentCourseMarkDTO> getStudentIdEndMarks(Integer id) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop1ByStudentIdOrderByMarkAsc(id);
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    //12
    public List<StudentCourseMarkDTO> getStudentIdThreeMarks(Integer id) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop3ByStudentIdOrderByMarkDesc(id);
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    //13
    public List<StudentCourseMarkDTO> getStudentIdFirstMark(Integer id) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop1ByStudentIdOrderByCreateDate(id);
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }
    //14

    public List<StudentCourseMarkDTO> getCourseIdFirstMarks(Integer courseId, Integer studentId) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop1ByStudentIdAndCourseIdOrderByCreateDate(courseId, studentId);
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    //15
    public List<StudentCourseMarkDTO> getCourseIdBigFirstMarks(Integer courseId, Integer studentId) {
        List<StudentCourseMarkEntity> entityList = studentCourseMarkRepository.findTop1ByStudentIdAndCourseIdOrderByMarkDesc(courseId, studentId);
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        entityList.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    //16
//    public Double getAverageCourseId(Integer id) {
//       Double avg = repository.orderAvgByMark(id);
//        return avg;
//    }

    //17
    public Double getAverg(Integer a, Integer b) {

        Double avg = studentCourseMarkRepository.avgByMarkStudentAndCourseId(a, b);
        return avg;
    }

    //18
    public List<Integer> getMarkOfGivenOver(Integer courseId, Integer mark) {
        List<Integer> integers = studentCourseMarkRepository.orderMaxByMarkStudentId(courseId, mark);
        return integers;
    }

    //19
    public Integer getGivenCourseOfMaxMark(Integer courseId) {
        Integer max = studentCourseMarkRepository.orderMaxByMarkCourseId(courseId);
        return max;
    }

    //20
    public Integer getGivenCourseOfAvgMark(Integer id) {
        Integer max = studentCourseMarkRepository.avgByMarkStudentAndCourseId(id);
        return max;
    }


    public Integer getGivenCourseOfTakenMark(Integer id) {
        Integer max = studentCourseMarkRepository.countByMarkStudentAndCourseId(id);
        return max;
    }


    public void test() {
        studentCourseMarkRepository.findLastCourseMarkAsNative(1);
    }

    public Page<StudentCourseMarkDTO> paginationByGivenStudentId(Integer studentId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByStudentId(studentId, pageable);
        long totalElements = pageObj.getTotalElements();
        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDTO> response = new PageImpl<>(dtoList, pageable, totalElements);
        return response;
    }

    public Page<StudentCourseMarkDTO> paginationByGivenCourseId(Integer courseId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByCourseId(courseId, pageable);
        long totalElements = pageObj.getTotalElements();
        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDTO> response = new PageImpl<>(dtoList, pageable, totalElements);
        return response;
    }

    public Page<StudentCourseMarkDTO> paginationByGivenMark(Integer mark, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<StudentCourseMarkEntity> pageObj = studentCourseMarkRepository.findAllByMark(mark, pageable);
        long totalElements = pageObj.getTotalElements();
        List<StudentCourseMarkEntity> entityList = pageObj.getContent();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entityList) {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudent().getId());
            dto.setCourseId(entity.getCourse().getId());
            dto.setMark(entity.getMark());
            dto.setCreateDate(entity.getCreateDate());
            dtoList.add(dto);
        }
        Page<StudentCourseMarkDTO> response = new PageImpl<>(dtoList, pageable, totalElements);
        return response;
    }


//    public void test(Integer id) {
//        List<Object[]> courseObjList = repository.findLastCourseMarkAsNative(id);
//        if (courseObjList.size() > 0) {
//            Object[] courseObj = courseObjList.get(0);
//
//            CourseDTO courseDTO = new CourseDTO();
//            courseDTO.setId((Integer) courseObj[0]);
//            courseDTO.setName((String) courseObj[1]);
//            System.out.println(courseDTO);
//        }
//
//        System.out.println("dasda");
//    }

/*    public void test2(Integer id) {
    CourseInfoMapper courseInfoMapper= (CourseInfoMapper) repository.findLastCourseMarkAsNatives(id);
        if (courseInfoMapper != null) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(courseInfoMapper.getCId());
            courseDTO.setName(courseInfoMapper.getCName());
            System.out.println(courseDTO +" "+ courseInfoMapper.getMark());
        }

        System.out.println("dasda");
    }*/


    public List<StudentCourseMarkEntity> filter(StudentCourseFilterRequestDTO filterDTO) {
        List<StudentCourseMarkEntity> list = studentCourseMarkCustomRepository.filter(filterDTO);
        return list;
    }
}
