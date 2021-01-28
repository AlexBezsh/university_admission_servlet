package com.bezshtanko.university_admission_servlet.model.user;

import com.bezshtanko.university_admission_servlet.model.AbstractEntity;
import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User extends AbstractEntity {

    private String fullName;
    private String email;
    private String password;
    private UserStatus status;
    private Set<UserRole> roles;
    private String city;
    private String region;
    private String education;
    private List<Enrollment> enrollments;

    public User() {
    }

    public User(Long id,
                String fullName,
                String email,
                String password,
                UserStatus status,
                Set<UserRole> roles,
                String city,
                String region,
                String education,
                List<Enrollment> enrollments) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.roles = roles;
        this.city = city;
        this.region = region;
        this.education = education;
        this.enrollments = enrollments;
    }

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.password = builder.password;
        this.status = builder.status;
        this.roles = builder.roles;
        this.city = builder.city;
        this.region = builder.region;
        this.education = builder.education;
        this.enrollments = builder.enrollments;
    }

    public static class UserBuilder {
        private Long id;
        private String fullName;
        private String email;
        private String password;
        private UserStatus status;
        private Set<UserRole> roles;
        private String city;
        private String region;
        private String education;
        private List<Enrollment> enrollments;

        public User build() {
            return new User(this);
        }

        public UserBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setStatus(UserStatus status) {
            this.status = status;
            return this;
        }

        public UserBuilder setRoles(Set<UserRole> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder setRegion(String region) {
            this.region = region;
            return this;
        }

        public UserBuilder setEducation(String education) {
            this.education = education;
            return this;
        }

        public UserBuilder setEnrollments(List<Enrollment> enrollments) {
            this.enrollments = enrollments;
            return this;
        }
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", roles=" + roles +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", education='" + education + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Set<UserRole> getRoles() {
        if (roles == null) {
            roles = new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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
