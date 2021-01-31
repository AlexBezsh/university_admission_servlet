package com.bezshtanko.university_admission_servlet.service;

public enum Services {
    USER_SERVICE(new UserService()),
    FACULTY_SERVICE(new FacultyService()),
    ENROLLMENT_SERVICE(new EnrollmentService());


    private final Service service;

    Services(Service service) {
        this.service = service;
    }

    public Service get() {
        return service;
    }
}
