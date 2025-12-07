package service;

import entity.Student;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Path("students")
public class StudentService {

  private static final AtomicInteger nextStudentId = new AtomicInteger(1);

  private static final ConcurrentMap<Integer, Student> studentDb = new ConcurrentHashMap<>();

  @POST
  public Student matriculate(Student s) {
    final var id = nextStudentId.getAndIncrement();
    s.setStudentNumber(id);
    return studentDb.put(id, s);
  }

  @DELETE
  @Path("{id}")
  public Student exmatriculate(@PathParam("id") int studentId) {
    return studentDb.remove(studentId);
  }

  @GET
  @Path("{id}")
  public Student getStudentById(@PathParam("id") int studentId) {
    return studentDb.get(studentId);
  }

  @PUT
  @Produces("application/xml")
  @Consumes("application/xml")
  @Path("{id}")
  public Student updateStudentAccount(@PathParam("id") int studentId, Student newData) {
    if (newData.getStudentNumber() != studentId) {
      // FIXME returns error 500 and not 400
      throw new WebApplicationException(
          "Student number ("
              + newData.getStudentNumber()
              + ") does not match id ("
              + studentId
              + ")",
          Response.Status.BAD_REQUEST);
    }
    return studentDb.put(studentId, newData);
  }

  @GET
  @Path("all")
  public Collection<Student> getAllStudents() {
    return studentDb.values();
  }

  @GET
  public Collection<Student> getStudentsByRange(
      @QueryParam("from") int fromStudentId, @QueryParam("to") int toStudentId) {
    return studentDb.values().stream()
        .filter(s -> s.getStudentNumber() >= fromStudentId && s.getStudentNumber() < toStudentId)
        .collect(Collectors.toSet());
  }
}
