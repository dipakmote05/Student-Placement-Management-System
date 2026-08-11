package service;

import dao.OpportunityDAO;
import model.Opportunity;

import java.util.List;

public class OpportunityService {

    private OpportunityDAO opportunityDAO;

    public OpportunityService() {
        opportunityDAO = new OpportunityDAO();
    }

    // CREATE
    public void addOpportunity(Opportunity opportunity) {
        opportunityDAO.addOpportunity(opportunity);
    }

    // READ ALL
    public List<Opportunity> getAllOpportunities() {
        return opportunityDAO.getAllOpportunities();
    }

    // READ BY ID
    public Opportunity getOpportunityById(int opportunityId) {
        return opportunityDAO.getOpportunityById(opportunityId);
    }

    // UPDATE
    public void updateOpportunity(Opportunity opportunity) {
        opportunityDAO.updateOpportunity(opportunity);
    }

    // DELETE
    public void deleteOpportunity(int opportunityId) {
        opportunityDAO.deleteOpportunity(opportunityId);
    }
}