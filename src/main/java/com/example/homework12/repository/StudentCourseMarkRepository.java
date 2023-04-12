package com.example.homework12.repository;

import com.example.homework12.entity.StudentCourseMarkEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Integer> {
    List<StudentCourseMarkEntity> getByCreateDate(LocalDateTime createDate);

    List<StudentCourseMarkEntity> getByCreateDateBetween(LocalDateTime firstDate, LocalDateTime secondDate);

    List<StudentCourseMarkEntity> findAllByStudentIdAndMarkOrderByCreateDateDesc(Integer id, Integer courseId);

    List<StudentCourseMarkEntity> getAllByStudentIdAndCourseIdOrderByCreateDateDesc(Integer studentId, Integer courseId);

    List<StudentCourseMarkEntity> findTop1ByStudentIdOrderByMarkAsc(Integer id);

    @Query(value = "SELECT course_id from  student_course_mark where student_id = :studentId order by created_date desc limit 1 ", nativeQuery = true)
    Integer findLastCourseMarker(@Param("studentId") Integer studentId);

    @Query(value = "SELECT c.id, c.name " +
            " from  student_course_mark as scm " +
            " inner join course_t as c on c.id = scm.course_id  " +
            " where scm.student_id = :studentId " +
            " order by scm.create_date desc limit 1 ", nativeQuery = true)
    List<Object[]> findLastCourseMarkAsNative(@Param("studentId") Integer studentId);

    @Query(value = "SELECT c.id, c.name " +
            " from  student_course_mark as scm " +
            " inner join course_t as c on c.id = scm.course_id  " +
            " where scm.student_id = :studentId " +
            " order by scm.create_date desc limit 1 ", nativeQuery = true)
    List<Object[]> findLastCourseMarkAsNatives(@Param("studentId") Integer studentId);

    List<StudentCourseMarkEntity> findTop3ByStudentIdOrderByMarkDesc(Integer id);

    List<StudentCourseMarkEntity> findTop1ByStudentIdOrderByCreateDate(Integer id);

    List<StudentCourseMarkEntity> findTop1ByStudentIdAndCourseIdOrderByCreateDate(Integer courseId, Integer studentId);

    List<StudentCourseMarkEntity> findTop1ByStudentIdAndCourseIdOrderByMarkDesc(Integer courseId, Integer studentId);


//    @Query(value = "select avg(s.mark) from student_course_mark as s where student_id=:sid ")
//    Double orderAvgByMark(@Param("sId") Integer sId);

    @Query(value = "select avg(s.mark) from student_course_mark as s where student_id = :sid and course_id=:cid ", nativeQuery = true)
    Double avgByMarkStudentAndCourseId(@Param("sid") Integer id, @Param("cid") Integer cid);

    @Query(value = "select s.mark from student_course_mark as s where course_id=:id and mark > :mark", nativeQuery = true)
    List<Integer> orderMaxByMarkStudentId(@Param("id") Integer id, @Param("mark") Integer mark);

    @Query(value = "select max(s.mark) from student_course_mark as s where course_id=:sid ", nativeQuery = true)
    Integer orderMaxByMarkCourseId(@Param("sid") Integer id);

    @Query(value = "select avg(s.mark) from student_course_mark as s where course_id=:sid ", nativeQuery = true)
    Integer avgByMarkStudentAndCourseId(@Param("sid") Integer id);

    @Query(value = "select count(s.mark) from student_course_mark as s where course_id=:sid ", nativeQuery = true)
    Integer countByMarkStudentAndCourseId(@Param("sid") Integer id);
}
