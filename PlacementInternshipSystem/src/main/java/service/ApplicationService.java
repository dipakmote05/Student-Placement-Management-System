package service;

import dao.ApplicationDAO;
import model.Application;

import java.util.List;

public class ApplicationService {

    private ApplicationDAO applicationDAO;

    public ApplicationService() {
        applicationDAO = new ApplicationDAO();
    }

    // CREATE
    public void addApplication(Application application) {
        applicationDAO.addApplication(application);
    }

    // READ ALL
    public List<Application> getAllApplications() {
        return applicationDAO.getAllApplications();
    }

    // READ BY ID
    public Application getApplicationById(int applicationId) {
        return applicationDAO.getApplicationById(applicationId);
    }

    // UPDATE
    public void updateApplication(Application application) {
        applicationDAO.updateApplication(application);
    }

    // DELETE
    public void deleteApplication(int applicationId) {
        applicationDAO.deleteApplication(applicationId);
    }
    // CHECK DUPLICATE APPLICATION
    public boolean hasAlreadyApplied(int studentId, int opportunityId) {

        return applicationDAO.hasAlreadyApplied(
                studentId,
                opportunityId
        );
    }
}