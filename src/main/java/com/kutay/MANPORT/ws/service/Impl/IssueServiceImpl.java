package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.dto.IssueDTO;
import com.kutay.MANPORT.ws.dto.IssuesFilterDTO;
import com.kutay.MANPORT.ws.dto.PageableDTO;
import com.kutay.MANPORT.ws.dto.TopIssueDTO;
import com.kutay.MANPORT.ws.repository.IssueRepository;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.service.IIssueService;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IIssueService {

    private final IssueRepository issueRepository;
    private final IApplicationService applicationService;

    public IssueServiceImpl(IssueRepository issueRepository, IApplicationService applicationService) {
        this.issueRepository = issueRepository;
        this.applicationService = applicationService;
    }

    @Override
    public List<TopIssueDTO> findTop3() {
        List<Issue> top3IssuesFromDB = issueRepository.findTop3ByRowStatusOrderByIdDesc(RowStatus.ACTIVE);

        List<TopIssueDTO> top3TopIssuesToReturn = convertIssueListToTopIssueDTOList(top3IssuesFromDB);

        return top3TopIssuesToReturn;
    }

    @Override
    public PageableDTO<?> findAll(Pageable pageable) {
        Page<Issue> issuePage = issueRepository.findAllByRowStatus(RowStatus.ACTIVE, pageable);
        return convertToPageableIssueDto(issuePage);
    }

    @Override
    public PageableDTO<?> findAllByFilter(Pageable pageable, IssuesFilterDTO issuesFilterDTO) throws ParseException {
        PageableDTO<IssueDTO> result;
        Long appId = issuesFilterDTO.getAppId();
        Boolean status = issuesFilterDTO.getStatus();

        String selectedFromDate = issuesFilterDTO.getSelectedFromDate();
        String selectedToDate = issuesFilterDTO.getSelectedToDate();
        if (selectedFromDate == null || selectedFromDate.isEmpty()) {
            selectedFromDate = "1900-01-01";
        }
        if (selectedToDate == null || selectedToDate.isEmpty()) {
            selectedToDate = CurrentDateCreator.currentDateAsString();
        }
        selectedFromDate = selectedFromDate.substring(0, 10);
        selectedToDate = selectedToDate.substring(0, 10);
        selectedFromDate = addOneDay(selectedFromDate);
        selectedToDate = addOneDay(selectedToDate);


        if (appId != null && status != null) {
            Application application = applicationService.findFirstById(appId);
            Page<Issue> page = issueRepository.findAllByRowStatusAndApplicationAndStatusAndCreatedDateBetween(RowStatus.ACTIVE, application, status, selectedFromDate, selectedToDate, pageable);
            result = convertToPageableIssueDto(page);
            return result;
        }

        if (appId != null) {
            Application application = applicationService.findFirstById(appId);
            Page<Issue> page = issueRepository.findAllByRowStatusAndApplicationAndCreatedDateBetween(RowStatus.ACTIVE, application, selectedFromDate, selectedToDate, pageable);
            result = convertToPageableIssueDto(page);
            return result;
        }

        if (status != null) {
            Page<Issue> page = issueRepository.findAllByRowStatusAndStatusAndCreatedDateBetween(RowStatus.ACTIVE, status, selectedFromDate, selectedToDate, pageable);
            result = convertToPageableIssueDto(page);
            return result;
        }


        Page<Issue> page = issueRepository.findAllByRowStatusAndCreatedDateBetween(RowStatus.ACTIVE, selectedFromDate, selectedToDate, pageable);
        result = convertToPageableIssueDto(page);
        return result;
    }

    private String addOneDay(String dateString) throws ParseException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(CurrentDateCreator.getDatePattern());
        LocalDate date = LocalDate.parse(dateString, fmt);
        return fmt.format(date.plusDays(1));
    }

    private PageableDTO<IssueDTO> convertToPageableIssueDto(Page<Issue> page) {
        List<IssueDTO> issueDTOS;
        issueDTOS = convertIssueListToIssueDTOList(page.getContent());

        PageableDTO<IssueDTO> result = new PageableDTO<>();
        result.setContent(issueDTOS);
        result.setFirst(page.isFirst());
        result.setLast(page.isLast());
        result.setNumber(page.getNumber());

        return result;
    }

    private List<IssueDTO> convertIssueListToIssueDTOList(List<Issue> issues) {
        List<IssueDTO> issueDTOS = new ArrayList<>();
        for (Issue issue : issues) {
            IssueDTO issueDTO = new IssueDTO();
            JobImplement jobImplement = issue.getJobImplement();
            JobInterface jobInterface = jobImplement.getJobInterface();

            issueDTO.setId(issue.getId());
            issueDTO.setName(issue.getName());
            issueDTO.setImpact(issue.getImpactType().toString());
            issueDTO.setDescription(issue.getDescription());
            issueDTO.setCountryName(jobImplement.getServer().getCountry().getName());
            issueDTO.setCreatedDate(issue.getCreatedDate().toString());
            issueDTO.setAppShortName(jobInterface.getApplication().getShortName());
            issueDTO.setJobName(jobInterface.getName());
            issueDTO.setStatus(issue.getStatus());

            issueDTOS.add(issueDTO);
        }
        return issueDTOS;
    }

    //Burayi kesinlikle refactor etmelisin cunku her get yaptiginda veritabanina sorgu yapiyor mumkun oldugunca az sorgu yollamaya calis
    private List<TopIssueDTO> convertIssueListToTopIssueDTOList(List<Issue> top3IssuesFromDB) {
        List<TopIssueDTO> top3TopIssuesToReturn = new ArrayList<>();
        for (Issue issue : top3IssuesFromDB) {
            TopIssueDTO topIssueDTO = new TopIssueDTO();
            topIssueDTO.setId(issue.getId());
            topIssueDTO.setCreatedDate(issue.getCreatedDate().toString());
            topIssueDTO.setImpactType(issue.getImpactType().toString());
            JobImplement jobImplement = issue.getJobImplement();
            JobInterface jobInterface = jobImplement.getJobInterface();
            Server server = jobImplement.getServer();
            Application application = jobInterface.getApplication();
            topIssueDTO.setCountryName(server.getCountry().getName());
            topIssueDTO.setApplicationShortName(application.getShortName());
            topIssueDTO.setJobName(jobInterface.getName());
            topIssueDTO.setServerName(server.getName());
            top3TopIssuesToReturn.add(topIssueDTO);
        }
        return top3TopIssuesToReturn;
    }
}
