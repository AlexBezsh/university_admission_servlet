package com.bezshtanko.university_admission_servlet.dto;

import com.bezshtanko.university_admission_servlet.model.enrollment.Enrollment;
import com.bezshtanko.university_admission_servlet.model.user.User;
import com.bezshtanko.university_admission_servlet.model.user.UserRole;
import com.bezshtanko.university_admission_servlet.model.user.UserStatus;

import java.util.List;
import java.util.Set;

public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private UserStatus status;
    private Set<UserRole> roles;
    private String city;
    private String region;
    private String education;
    private List<Enrollment> enrollments;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.roles = user.getRoles();
        this.city = user.getCity();
        this.region = user.getRegion();
        this.education = user.getEducation();
        this.enrollments = user.getEnrollments();
    }

    public UserDTO(Long id,
                String fullName,
                String email,
                UserStatus status,
                Set<UserRole> roles,
                String city,
                String region,
                String education,
                List<Enrollment> enrollments) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.status = status;
        this.roles = roles;
        this.city = city;
        this.region = region;
        this.education = education;
        this.enrollments = enrollments;
    }

    private UserDTO(UserDTO.UserDTOBuilder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.status = builder.status;
        this.roles = builder.roles;
        this.city = builder.city;
        this.region = builder.region;
        this.education = builder.education;
        this.enrollments = builder.enrollments;
    }

    public static class UserDTOBuilder {
        private Long id;
        private String fullName;
        private String email;
        private UserStatus status;
        private Set<UserRole> roles;
        private String city;
        private String region;
        private String education;
        private List<Enrollment> enrollments;

        public UserDTO build() {
            return new UserDTO(this);
        }

        public UserDTO.UserDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserDTO.UserDTOBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserDTO.UserDTOBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDTO.UserDTOBuilder setStatus(UserStatus status) {
            this.status = status;
            return this;
        }

        public UserDTO.UserDTOBuilder setRoles(Set<UserRole> roles) {
            this.roles = roles;
            return this;
        }

        public UserDTO.UserDTOBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public UserDTO.UserDTOBuilder setRegion(String region) {
            this.region = region;
            return this;
        }

        public UserDTO.UserDTOBuilder setEducation(String education) {
            this.education = education;
            return this;
        }

        public UserDTO.UserDTOBuilder setEnrollments(List<Enrollment> enrollments) {
            this.enrollments = enrollments;
            return this;
        }
    }

    public static UserDTO.UserDTOBuilder builder() {
        return new UserDTO.UserDTOBuilder();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Set<UserRole> getRoles() {
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
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }


}
