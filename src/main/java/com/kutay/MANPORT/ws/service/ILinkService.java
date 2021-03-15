package com.kutay.MANPORT.ws.service;

import java.util.List;

public interface ILinkService {
    List<?> getAllLinksSortedForManagementPageByAppId(Long appId);

    List<?> getLinksGroupedByApplications();
}
