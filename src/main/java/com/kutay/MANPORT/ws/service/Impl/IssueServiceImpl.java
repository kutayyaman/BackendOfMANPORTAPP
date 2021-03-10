package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.MyAnnotations.CurrentUser;
import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.dto.IssueDTO;
import com.kutay.MANPORT.ws.dto.IssuesFilterDTO;
import com.kutay.MANPORT.ws.dto.PageableDTO;
import com.kutay.MANPORT.ws.dto.TopIssueDTO;
import com.kutay.MANPORT.ws.error.JobDoesntExistInServerException;
import com.kutay.MANPORT.ws.error.NotFoundException;
import com.kutay.MANPORT.ws.repository.IssueRepository;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.service.IIssueService;
import com.kutay.MANPORT.ws.service.IServerService;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class IssueServiceImpl implements IIssueService {

    private final IssueRepository issueRepository;
    private final IApplicationService applicationService;
    private final MessageSource messageSource;
    private final IServerService serverService;

    public IssueServiceImpl(IssueRepository issueRepository, IApplicationService applicationService, MessageSource messageSource, IServerService serverService) {
        this.issueRepository = issueRepository;
        this.applicationService = applicationService;
        this.messageSource = messageSource;
        this.serverService = serverService;
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

    @Override
    public IssueDTO getById(Long id) {
        Issue issue = issueRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
        if (issue == null) {
            throw new NotFoundException();
        }
        IssueDTO issueDTO = new IssueDTO(issue);
        JobImplement jobImplement = issue.getJobImplement();
        Server server = jobImplement.getServer();
        issueDTO.setCountryId(server.getCountry().getId());
        issueDTO.setAppId(issue.getApplication().getId());
        issueDTO.setJobInterfaceId(jobImplement.getJobInterface().getId());
        issueDTO.setServerName(server.getName());
        issueDTO.setServerId(server.getId());
        return issueDTO;
    }

    @Override
    public IssueDTO updateIssue(IssueDTO issueDTO, User user) {
        Long jobInterfaceId = issueDTO.getJobInterfaceId();
        Long serverId = issueDTO.getServerId();

        Server server = serverService.findFirstById(serverId);
        List<JobImplement> jobImplements = server.getJobImplements();

        JobImplement jobImplement = findJobImplementInAListByJobInterfaceId(jobImplements, jobInterfaceId);
        if (jobImplement == null) {
            throw new JobDoesntExistInServerException(messageSource);
        }

        //jobImplement'e eklenecek bu issue demekki bunu anladik buraya geldiysek

        Issue issue = issueRepository.getOne(issueDTO.getId());

        Application application = applicationService.findFirstById(issueDTO.getAppId());
        if (application == null) {
            throw new NotFoundException();
        }

        issue.setName(issueDTO.getName());
        issue.setDescription(issueDTO.getDescription());
        issue.setImpactType(ImpactType.valueOf(issueDTO.getImpact()));
        issue.setApplication(application);
        issue.setJobImplement(jobImplement);
        issue.setStatus(issueDTO.getStatus());
        if (user != null) {
            issue.setModifiedBy(user.getEmail());
        }
        issue.setModifiedDate(CurrentDateCreator.currentDateAsDate());
        issueRepository.save(issue);


        return issueDTO;
    }

    @Override
    public ResponseEntity<?> deleteIssueById(Long id) { //TODO: yapilacak burasi
        //issueRepository.deleteIssueById(id);
        Issue issue = issueRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
        issue.setRowStatus(RowStatus.DELETED);
        issueRepository.save(issue);

        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("manportapp.info.issue.Deleted", null, locale);
        return ResponseEntity.ok().body(message);
    }

    @Override
    public ResponseEntity<?> changeIssueStatusById(Boolean status, Long id, User user) {
        Issue issue = issueRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
        if(issue == null){
            throw new NotFoundException();
        }
        issue.setStatus(status);
        issue.setModifiedDate(CurrentDateCreator.currentDateAsDate());
        issue.setModifiedBy(user.getEmail());
        issueRepository.save(issue);

        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("manportapp.info.issue.status.changed", null, locale);
        return ResponseEntity.ok().body(message);
    }

    @Override
    public ResponseEntity<?> changeIssueTrackById(Boolean track, Long id, User user) {
        Issue issue = issueRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
        if(issue == null){
            throw new NotFoundException();
        }
        issue.setTrack(track);
        issue.setModifiedDate(CurrentDateCreator.currentDateAsDate());
        issue.setModifiedBy(user.getEmail());
        issueRepository.save(issue);


        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("manportapp.info.issue.track.changed", null, locale);
        return ResponseEntity.ok().body(message);
    }

    @Override
    public PageableDTO<?> findAllByAppId(Long appId, Pageable pageable) {
        PageableDTO<IssueDTO> result;

        Application application = applicationService.findFirstById(appId);
        if (application == null) {
            throw new NotFoundException();
        }
        Page<Issue> issuePage = issueRepository.findAllByRowStatusAndApplication(RowStatus.ACTIVE, application, pageable);

        result = convertToPageableIssueDto(issuePage);
        return result;
    }

    private JobImplement findJobImplementInAListByJobInterfaceId(List<JobImplement> jobImplements, Long jobInterfaceId) {
        for (JobImplement jobImplement : jobImplements) {
            JobInterface jobInterface = jobImplement.getJobInterface();
            Long id = jobInterface.getId();
            if (id.equals(jobInterfaceId)) {
                return jobImplement;
            }
        }
        return null;
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
            Server server = jobImplement.getServer();

            issueDTO.setId(issue.getId());
            issueDTO.setName(issue.getName());
            issueDTO.setImpact(issue.getImpactType().toString());
            issueDTO.setDescription(issue.getDescription());
            Country country = server.getCountry();
            issueDTO.setCountryName(country.getName());
            issueDTO.setCountryId(country.getId());
            issueDTO.setCreatedDate(issue.getCreatedDate().toString());
            Application application = issue.getApplication();
            issueDTO.setAppShortName(application.getShortName());
            issueDTO.setAppId(application.getId());
            issueDTO.setJobName(jobInterface.getName());
            issueDTO.setStatus(issue.getStatus());
            issueDTO.setJobInterfaceId(jobInterface.getId());
            issueDTO.setServerName(server.getName());
            issueDTO.setServerId(server.getId());
            issueDTO.setTrack(issue.getTrack());


            issueDTOS.add(issueDTO);
        }
        return issueDTOS;
    }

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
