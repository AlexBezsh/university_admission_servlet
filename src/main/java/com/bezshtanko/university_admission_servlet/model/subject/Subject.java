package com.bezshtanko.university_admission_servlet.model.subject;

import java.util.Objects;

public class Subject {

    private Long id;
    private SubjectType type;
    private String nameEn;
    private String nameUa;

    public Subject() {
    }

    public Subject(Long id, SubjectType type, String nameEn, String nameUa) {
        this.id = id;
        this.type = type;
        this.nameEn = nameEn;
        this.nameUa = nameUa;
    }

    private Subject(SubjectBuilder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.nameEn = builder.nameEn;
        this.nameUa = builder.nameUa;
    }

    public static class SubjectBuilder {
        private Long id;
        private SubjectType type;
        private String nameEn;
        private String nameUa;

        public Subject build() {
            return new Subject(this);
        }

        public SubjectBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public SubjectBuilder setType(SubjectType type) {
            this.type = type;
            return this;
        }

        public SubjectBuilder setNameEn(String nameEn) {
            this.nameEn = nameEn;
            return this;
        }

        public SubjectBuilder setNameUa(String nameUa) {
            this.nameUa = nameUa;
            return this;
        }
    }

    public static SubjectBuilder builder() {
        return new SubjectBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return type == subject.type && Objects.equals(nameUa, subject.nameUa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, nameUa);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", type=" + type +
                ", nameEn='" + nameEn + '\'' +
                ", nameUa='" + nameUa + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
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
}
