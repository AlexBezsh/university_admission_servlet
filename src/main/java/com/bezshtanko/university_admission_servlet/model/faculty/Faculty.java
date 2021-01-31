package com.bezshtanko.university_admission_servlet.model.faculty;

import com.bezshtanko.university_admission_servlet.model.AbstractEntity;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends AbstractEntity {

    private String nameEn;
    private String nameUa;
    private FacultyStatus status;
    private String descriptionEn;
    private String descriptionUa;
    private Integer stateFundedPlaces;
    private Integer contractPlaces;
    private Integer totalPlaces;
    private List<Subject> subjects;
    private List<Enrollment> enrollments;

    public Faculty() {
    }

    public Faculty(Long id,
                   String nameEn,
                   String nameUa,
                   FacultyStatus status,
                   String descriptionEn,
                   String descriptionUa,
                   Integer stateFundedPlaces,
                   Integer contractPlaces,
                   List<Subject> subjects,
                   List<Enrollment> enrollments) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameUa = nameUa;
        this.status = status;
        this.descriptionEn = descriptionEn;
        this.descriptionUa = descriptionUa;
        this.stateFundedPlaces = stateFundedPlaces;
        this.contractPlaces = contractPlaces;
        this.subjects = subjects;
        this.enrollments = enrollments;
    }

    private Faculty(FacultyBuilder builder) {
        this.id = builder.id;
        this.nameEn = builder.nameEn;
        this.nameUa = builder.nameUa;
        this.status = builder.status;
        this.descriptionEn = builder.descriptionEn;
        this.descriptionUa = builder.descriptionUa;
        this.stateFundedPlaces = builder.stateFundedPlaces;
        this.contractPlaces = builder.contractPlaces;
        this.subjects = builder.subjects;
        this.enrollments = builder.enrollments;
    }

    public static class FacultyBuilder {
        private Long id;
        private String nameEn;
        private String nameUa;
        private FacultyStatus status;
        private String descriptionEn;
        private String descriptionUa;
        private Integer stateFundedPlaces;
        private Integer contractPlaces;
        private List<Subject> subjects;
        private List<Enrollment> enrollments;

        public Faculty build() {
            return new Faculty(this);
        }

        public FacultyBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public FacultyBuilder setNameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }

        public FacultyBuilder setNameUa(String nameUa) {
            this.nameUa = nameUa;
            return this;
        }

        public FacultyBuilder setStatus(FacultyStatus status) {
            this.status = status;
            return this;
        }

        public FacultyBuilder setDescriptionEn(String descriptionEn) {
            this.descriptionEn = descriptionEn;
            return this;
        }

        public FacultyBuilder setDescriptionUa(String descriptionUa) {
            this.descriptionUa = descriptionUa;
            return this;
        }

        public FacultyBuilder setStateFundedPlaces(Integer stateFundedPlaces) {
            this.stateFundedPlaces = stateFundedPlaces;
            return this;
        }

        public FacultyBuilder setContractPlaces(Integer contractPlaces) {
            this.contractPlaces = contractPlaces;
            return this;
        }

        public FacultyBuilder setSubjects(List<Subject> subjects) {
            this.subjects = subjects;
            return this;
        }

        public FacultyBuilder setEnrollments(List<Enrollment> enrollments) {
            this.enrollments = enrollments;
            return this;
        }
    }

    public static FacultyBuilder builder() {
        return new FacultyBuilder();
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", nameEn='" + nameEn + '\'' +
                ", nameUa='" + nameUa + '\'' +
                ", status=" + status +
                ", descriptionEn='" + descriptionEn + '\'' +
                ", descriptionUa='" + descriptionUa + '\'' +
                ", stateFundedPlaces=" + stateFundedPlaces +
                ", contractPlaces=" + contractPlaces +
                ", totalPlaces=" + getTotalPlaces() +
                '}';
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

    public FacultyStatus getStatus() {
        return status;
    }

    public boolean isActive() {
        return status == FacultyStatus.ACTIVE;
    }

    public void setStatus(FacultyStatus status) {
        this.status = status;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
    }

    public Integer getStateFundedPlaces() {
        return stateFundedPlaces;
    }

    public void setStateFundedPlaces(Integer stateFundedPlaces) {
        this.stateFundedPlaces = stateFundedPlaces;
    }

    public Integer getContractPlaces() {
        return contractPlaces;
    }

    public void setContractPlaces(Integer contractPlaces) {
        this.contractPlaces = contractPlaces;
    }

    public Integer getTotalPlaces() {
        if (totalPlaces == null) {
            totalPlaces = stateFundedPlaces + contractPlaces;
        }
        return totalPlaces;
    }

    public List<Subject> getSubjects() {
        if (subjects == null) {
            subjects = new ArrayList<>();
        }
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Enrollment> getEnrollments() {
        if (enrollments == null) {
            enrollments = new ArrayList<>();
        }
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
