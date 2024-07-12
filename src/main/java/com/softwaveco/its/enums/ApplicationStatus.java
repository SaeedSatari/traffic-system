package com.softwaveco.its.enums;

public enum ApplicationStatus {
    PENDING, // The application has been submitted but not yet processed.
    IN_PROGRESS, // The application is currently being reviewed or processed.
    APPROVED, // The application has been approved.
    REJECTED, // The application has been denied.
    UNDER_REVIEW, // The application is under additional scrutiny or review.
    AWAITING_DOCUMENTS, // Additional documents are needed before proceeding.
    COMPLETED, // The application process is complete, and all actions have been finalized.
    CANCELED; // The application has been canceled by the applicant or the authority.
}
