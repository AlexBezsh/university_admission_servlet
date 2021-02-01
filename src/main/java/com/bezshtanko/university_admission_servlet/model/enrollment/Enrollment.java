package com.bezshtanko.university_admission_servlet.model.enrollment;

import com.bezshtanko.university_admission_servlet.model.AbstractEntity;
import com.bezshtanko.university_admission_servlet.model.faculty.Faculty;
import com.bezshtanko.university_admission_servlet.model.mark.Mark;
import com.bezshtanko.university_admission_servlet.model.user.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Enrollment extends AbstractEntity {

    private User user;
    private Faculty faculty;
    private EnrollmentStatus status;
    private List<Mark> marks;
    private BigDecimal marksSum;

    public Enrollment() {
    }

    public Enrollment(Long id, User user, Faculty faculty, EnrollmentStatus status, List<Mark> marks, BigDecimal marksSum) {
        this.id = id;
        this.user = user;
        this.faculty = faculty;
        this.status = status;
        this.marks = marks;
        this.marksSum = marksSum;
    }

    private Enrollment(EnrollmentBuilder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.faculty = builder.faculty;
        this.status = builder.status;
        this.marks = builder.marks;
    }

    public static class EnrollmentBuilder {
        private Long id;
        private User user;
        private Faculty faculty;
        private EnrollmentStatus status;
        private List<Mark> marks;

        public Enrollment build() {
            return new Enrollment(this);
        }

        public EnrollmentBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public EnrollmentBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public EnrollmentBuilder setFaculty(Faculty faculty) {
            this.faculty = faculty;
            return this;
        }

        public EnrollmentBuilder setStatus(EnrollmentStatus status) {
            this.status = status;
            return this;
        }

        public EnrollmentBuilder setMarks(List<Mark> marks) {
            this.marks = marks;
            return this;
        }
    }

    public static EnrollmentBuilder builder() {
        return new EnrollmentBuilder();
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", facultyId=" + faculty.getId() +
                ", status=" + status +
                ", marksSum=" + getMarksSum() +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public boolean isNew() {
        return status == EnrollmentStatus.NEW;
    }

    public boolean isApproved() {
        return status == EnrollmentStatus.APPROVED;
    }

    public boolean isFinalized() {
        return status == EnrollmentStatus.FINALIZED;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public List<Mark> getMarks() {
        if (marks == null) {
            marks = new ArrayList<>();
        }
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public BigDecimal getMarksSum() {
        if (marksSum == null) {
            marksSum = marks.stream()
                    .map(Mark::getMark)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return marksSum;
    }

}
