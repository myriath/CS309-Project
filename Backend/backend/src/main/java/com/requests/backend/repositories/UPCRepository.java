package com.requests.backend.repositories;

//import com.requests.backend.models.UPC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UPCRepository extends JpaRepository<String, Integer> {

    @Query (
            value = "SELECT fdc_id FROM upc WHERE upc = :upc",
            nativeQuery = true
    )
    String[] queryUPCtoID(@Param("upc") String upc);
}
