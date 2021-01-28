package com.bezshtanko.university_admission_servlet.model.mark;

import com.bezshtanko.university_admission_servlet.model.AbstractEntity;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;

import java.math.BigDecimal;

public class Mark extends AbstractEntity {

    private Enrollment enrollment;
    private Subject subject;
    private BigDecimal mark;

    public Mark() {
    }

    public Mark(Long id, Enrollment enrollment, Subject subject, BigDecimal mark) {
        this.id = id;
        this.enrollment = enrollment;
        this.subject = subject;
        this.mark = mark;
    }

    private Mark(MarkBuilder builder) {
        this.id = builder.id;
        this.enrollment = builder.enrollment;
        this.subject = builder.subject;
        this.mark = builder.mark;
    }

    public static class MarkBuilder {
        private Long id;
        private Enrollment enrollment;
        private Subject subject;
        private BigDecimal mark;

        public Mark build() {
            return new Mark(this);
        }

        public MarkBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public MarkBuilder setEnrollment(Enrollment enrollment) {
            this.enrollment = enrollment;
            return this;
        }

        public MarkBuilder setSubject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public MarkBuilder setMark(BigDecimal mark) {
            this.mark = mark;
            return this;
        }
    }

    public static MarkBuilder builder() {
        return new MarkBuilder();
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", enrollment=" + enrollment +
                ", subject=" + subject +
                ", mark=" + mark +
                '}';
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public BigDecimal getMark() {
        return mark;
    }

    public void setMark(BigDecimal mark) {
        this.mark = mark;
    }
}
