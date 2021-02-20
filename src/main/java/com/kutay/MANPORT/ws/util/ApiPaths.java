package com.kutay.MANPORT.ws.util;

public final class ApiPaths {

    private static final String BASE_PATH = "/api";

    public static final class UserCtrl {
        public static final String CTRL = BASE_PATH + "/users";
    }

    public static final class AuthCtrl {
        public static final String CTRL = BASE_PATH + "/auth";
    }

    public static final class IssueCtrl {
        public static final String CTRL = BASE_PATH+"/issue";
    }

    public static final class ApplicationSummaryCtrl {
        public static final String CTRL = BASE_PATH+"/applicationsummary";
    }

    public static final class ApplicationCtrl{
        public static final String CTRL = BASE_PATH+"/application";
    }

}
