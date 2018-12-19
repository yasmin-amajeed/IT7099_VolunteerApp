package com.example.it7099_volunteerapp.Database;


import android.provider.BaseColumns;

public final class VolunteerContractClass {
    private VolunteerContractClass()
    {

    }

    public static class UserContract implements BaseColumns
    {
        public static final String TABLE_USER = "User";
        public static final String User_UiD ="userId";
        public static final String User_email="email";
        public static final String User_password="password";
        public static final String User_firstName="firstName";
        public static final String User_lastName= "lastName";
        public static final String User_DOB = "DOB";
        public static final String User_education = "education";
        public static final String User_gender="gender";
        public static final String User_skills="skills";
        public static final String User_preference="preference";
        public static final String User_userType="userType";
        public static final String User_image="image";

    }
    public static  class EventContract implements BaseColumns
    {


        // EVENT TABLE COLUMNS - EX FOREIGN KEYS
        public static final String TABLE_EVENT = "Event";
        public static final String Event_Id="eventId";
        public static final String Event_title="eventTitle";
        public static final String Event_description="eventDescription";
        public static final String Event_StartDate="eventStartDate";
        public static final String Event_StartTime="eventStartTime";
        public static final String Event_EndDate="eventEndDate";
        public static final String Event_EndTime="eventEndTime";
        public static final String Event_Condition="eventCondition";
        //        public static final String Event_AgeLimit="eventAgeLimit";
        public static final String Event_Location="eventLocation";
        public static final String Event_Logo="eventLogo";
        // FK
        // ACTIVITY CATEGORY ID, ORGANIZATION ID,
    }
    public  static  class EventCategoryContract implements BaseColumns
    {
        // EVENT CATEGORY TABLE
        public static final String TABLE_CATEGORY = "EventCategory";
        public static final String EventCat_Id="eventCategoryId";
        public static final String EventCat_name="eventCategoryName";
    }


    public static  class EventReviewContract implements BaseColumns {
        // given by the user for the event
        public static final String TABLE_EVENT_REVIEW = "EventReview";
        public static final String EventRev_Id = "reviewId";
        public static final String EventRev_review = "review";

    }

    public static class EventRegistrationContract implements BaseColumns
    {
        public static final String TABLE_EVENT_REGISTRATION = "EventRegistration";
        public static final String EventReg_Id="registrationId";
        public static final String EventReg_checkIn="useCheckIn";
    }

    public static class OrganizationContract implements BaseColumns
    {
        public static final String TABLE_ORGANIZATION = "Organization";
        public static final String Organization_Id="organizationId";
        public static final String Organization_Name="organizationName";
        public static final String Organization_About="organizationAbout";
        public static final String Organization_mission="organizationMission";
        public static final String Organization_whatWeDo="organizationWhatWeDo";
        public static final String Organization_email="email";
        public static final String Organization_password="password";
        public static final String Organization_userType="O";
        public static final String Organization_logo="organizationLogo";
    }

    public  static class OrganizationCategoryContract implements BaseColumns
    {
        public static final String TABLE_ORGANIZATION_CATEGORY = "OrganizationCategory";
        public static final String OrganizationCat_Id="organizationCategoryId";
        public static final String OrganizationCat_name="organizationCategoryName;" ;

    }
    public static class UserFeedbackContract implements BaseColumns {

        private static final String TABLE_User_FEEDBACK = "UserFeedback";
        private static final String UserFeedback_Id = "userFeedbackId";
        private static final String UserFeedback_feedback = "userFeedback";
        private static final String UserFeedback_rating ="userRating";
        private static final String UserFeedback_noOfEvents="noOfEvents";
    }

    //   private static final String TABLE_ADMIN = "Admin"; // for future work
}

