package service;

import entity.Student;
import jakarta.ws.rs.*;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StudentService {

  private static final AtomicInteger nextStudentId = new AtomicInteger(1);

  private static final ConcurrentMap<Integer, Student> studentDb = new ConcurrentHashMap<>();

  public Student matriculate(Student s) {
    final var id = nextStudentId.getAndIncrement();
    s.setStudentNumber(id);
    return studentDb.put(id, s);
  }

  public Student exmatriculate(int studentId) {
    return studentDb.remove(studentId);
  }

  public Student getStudentById(int studentId) {
    return studentDb.get(studentId);
  }

  public Student updateStudentAccount(int studentId, Student newData) {
    return studentDb.put(studentId, newData);
  }

  public Collection<Student> getAllStudents() {
    return studentDb.values();
  }

  public Collection<Student> getStudentsByRange(int fromStudentId, int toStudentId) {
    return studentDb.values().stream()
        .filter(s -> s.getStudentNumber() >= fromStudentId && s.getStudentNumber() < toStudentId)
        .collect(Collectors.toSet());
  }
}
