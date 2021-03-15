package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.*;
import com.kutay.MANPORT.ws.dto.ApplicationDTO;
import com.kutay.MANPORT.ws.dto.LinkDTO;
import com.kutay.MANPORT.ws.dto.LinkPageItemDTO;
import com.kutay.MANPORT.ws.models.managementPageLink.*;
import com.kutay.MANPORT.ws.repository.LinkRepository;
import com.kutay.MANPORT.ws.service.IApplicationCountryService;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.service.ILinkService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LinkServiceImpl implements ILinkService {
    private final LinkRepository linkRepository;
    private final IApplicationCountryService applicationCountryService;
    private final IApplicationService applicationService;

    public LinkServiceImpl(LinkRepository linkRepository, IApplicationCountryService applicationCountryService, IApplicationService applicationService) {
        this.linkRepository = linkRepository;
        this.applicationCountryService = applicationCountryService;
        this.applicationService = applicationService;
    }

    @Override
    public List<ASpecificTypeWithCountriesAndEnvironment> getAllLinksSortedForManagementPageByAppId(Long appId) {
        List<ALinkSpecificTypeWithLinks> specificTypeWithLinks = sortLinksBySpecificTypes(linkRepository.getAllLinksByAppIdAndRowStatus(appId, RowStatus.ACTIVE));

        List<ASpecificTypeWithCountriesAndLinks> aSpecificTypeWithCountryAndLinks = sortByCountryName(specificTypeWithLinks, appId);
        return sortByEnvironmentTypes(aSpecificTypeWithCountryAndLinks);
    }

    @Override
    public List<LinkPageItemDTO> getLinksGroupedByApplications() {
        List<Application> applicationList = applicationService.findAll();
        List<LinkPageItemDTO> result = new ArrayList<>();
        for (Application application : applicationList) {
            LinkPageItemDTO linkPageItemDTO = new LinkPageItemDTO();
            List<ASpecificTypeWithCountriesAndEnvironment> aSpecificTypeWithCountriesAndEnvironments = getAllLinksSortedForManagementPageByAppId(application.getId());

            linkPageItemDTO.setAppShortName(application.getShortName());
            linkPageItemDTO.setASpecificTypeWithCountriesAndEnvironments(aSpecificTypeWithCountriesAndEnvironments);
            result.add(linkPageItemDTO);
        }
        return result;
    }

    private List<ASpecificTypeWithCountriesAndEnvironment> sortByEnvironmentTypes(List<ASpecificTypeWithCountriesAndLinks> param) {
        List<ASpecificTypeWithCountriesAndEnvironment> result = new ArrayList<>();

        List<LinkEnvironmentType> environmentTypeList = Arrays.asList(LinkEnvironmentType.values().clone());

        for (ASpecificTypeWithCountriesAndLinks aSpecificTypeWithCountriesAndLinks : param) {
            //Specific name burada var
            ASpecificTypeWithCountriesAndEnvironment aSpecificTypeWithCountriesAndEnvironment = new ASpecificTypeWithCountriesAndEnvironment();
            aSpecificTypeWithCountriesAndEnvironment.setSpecificTypeName(aSpecificTypeWithCountriesAndLinks.getLinkSpecificTypeName());

            for (ACountryNameWithLinks aCountryNameWithLinks : aSpecificTypeWithCountriesAndLinks.getACountryNameWithLinksList()) {
                //countrylerden birindeyiz suan
                ACountryNameWithEnvironmentTypes aCountryNameWithEnvironmentTypes = new ACountryNameWithEnvironmentTypes();
                aCountryNameWithEnvironmentTypes.setCountryName(aCountryNameWithLinks.getCountryName());
                for (LinkEnvironmentType linkEnvironmentType : environmentTypeList) {
                    String linkEnvironmentTypeString = linkEnvironmentType.toString();

                    AnEnvironmentTypeWithLinks anEnvironmentTypeWithLinks = new AnEnvironmentTypeWithLinks();
                    anEnvironmentTypeWithLinks.setEnvironmentTypeName(linkEnvironmentTypeString);

                    for (LinkDTO linkDTO : aCountryNameWithLinks.getLinkDTOList()) {
                        if (linkDTO.getLinkEnvironmentType().equals(linkEnvironmentTypeString)) {
                            anEnvironmentTypeWithLinks.getLinkDTOList().add(linkDTO);
                        }
                    }
                    if (anEnvironmentTypeWithLinks.getLinkDTOList().size() > 0) {
                        aCountryNameWithEnvironmentTypes.getAnEnvironmentTypeWithLinks().add(anEnvironmentTypeWithLinks);
                    }
                }
                if (aCountryNameWithEnvironmentTypes.getAnEnvironmentTypeWithLinks().size() > 0) {
                    aSpecificTypeWithCountriesAndEnvironment.getACountryNameWithEnvironmentTypes().add(aCountryNameWithEnvironmentTypes);
                }
            }
            result.add(aSpecificTypeWithCountriesAndEnvironment);
        }

        return result;
    }

    private List<ASpecificTypeWithCountriesAndLinks> sortByCountryName(List<ALinkSpecificTypeWithLinks> param, Long appId) {
        List<ASpecificTypeWithCountriesAndLinks> result = new ArrayList<>();

        Set<String> countryNameList = new HashSet<>();
        for (ALinkSpecificTypeWithLinks aLinkSpecificTypeWithLinks : param) {
            List<LinkDTO> linkDTOList = aLinkSpecificTypeWithLinks.getLinks();
            for (LinkDTO linkDTO : linkDTOList) {
                Application application = new Application();
                application.setId(appId);
                Country country = new Country();
                country.setId(linkDTO.getCountryId());
                ApplicationCountry applicationCountry = applicationCountryService.findFirstByApplicationAndCountry(application, country);
                if (applicationCountry.isAlive()) {
                    countryNameList.add(linkDTO.getCountryName());
                }
            }
        }


        for (ALinkSpecificTypeWithLinks aLinkSpecificTypeWithLinks : param) {
            ASpecificTypeWithCountriesAndLinks aSpecificTypeWithCountriesAndLinks = new ASpecificTypeWithCountriesAndLinks();
            aSpecificTypeWithCountriesAndLinks.setLinkSpecificTypeName(aLinkSpecificTypeWithLinks.getLinkSpecificType());

            List<LinkDTO> linkDTOList = aLinkSpecificTypeWithLinks.getLinks();

            for (String countryName : countryNameList) {
                ACountryNameWithLinks aCountryNameWithLinks = new ACountryNameWithLinks();
                aCountryNameWithLinks.setCountryName(countryName);

                for (LinkDTO linkDTO : linkDTOList) {
                    if (countryName.equals(linkDTO.getCountryName())) {
                        aCountryNameWithLinks.getLinkDTOList().add(linkDTO);
                    }
                }
                aSpecificTypeWithCountriesAndLinks.getACountryNameWithLinksList().add(aCountryNameWithLinks);
            }
            result.add(aSpecificTypeWithCountriesAndLinks);
        }


        return result;
    }

    private List<ALinkSpecificTypeWithLinks> sortLinksBySpecificTypes(List<Link> links) {
        List<ALinkSpecificTypeWithLinks> result = new ArrayList<>();

        for (LinkSpecificType linkSpecificType : LinkSpecificType.values()) {
            ALinkSpecificTypeWithLinks aLinkSpecificTypeWithLinks = new ALinkSpecificTypeWithLinks();

            String linkSpecificTypeString = linkSpecificType.toString();
            aLinkSpecificTypeWithLinks.setLinkSpecificType(linkSpecificTypeString);

            for (Link link : links) {
                if (link.getLinkSpecificType().toString().equals(linkSpecificTypeString)) {
                    LinkDTO linkDTO = new LinkDTO(link);
                    aLinkSpecificTypeWithLinks.getLinks().add(linkDTO);
                }
            }
            result.add(aLinkSpecificTypeWithLinks);
        }

        return result;
    }



    /*
    private List<ALinkTypeWithLinks> sortLinksByLinkTypes(List<Link> links){
        List<ALinkTypeWithLinks> result = new ArrayList<>();

        for(LinkType linkType : LinkType.values()){
            String linkTypeString = linkType.toString();

            ALinkTypeWithLinks aLinkTypeWithLinks = new ALinkTypeWithLinks();
            aLinkTypeWithLinks.setLinkType(linkTypeString);

            for(Link link : links){
                if(link.getLinkType().toString().equals(linkTypeString)){
                    LinkDTO linkDTO = new LinkDTO(link);
                    aLinkTypeWithLinks.getLinkDTOList().add(linkDTO);
                }
            }

            result.add(aLinkTypeWithLinks);
        }

        return result;
    }*/


}
