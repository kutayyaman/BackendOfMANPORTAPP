package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Link;
import com.kutay.MANPORT.ws.domain.RowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query(
            "select L " +
                    "from Link L " +
                    "left join fetch L.applicationServer appServer " +
                    "left join fetch appServer.application app " +
                    "left join fetch appServer.server server " +
                    "left join fetch server.country country " +
                    "where app.id = :appId " +
                    "and L.rowStatus = :rowStatus " +
                    "and appServer.rowStatus = :rowStatus " +
                    "and app.rowStatus = :rowStatus "
    )
    List<Link> getAllLinksByAppIdAndRowStatus(Long appId, RowStatus rowStatus);
}
