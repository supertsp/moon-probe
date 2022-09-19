package br.com.tiagopedroso.moonprobe.entrypoint.repository;

import br.com.tiagopedroso.moonprobe.entrypoint.model.Probe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProbeRepository extends JpaRepository<Probe, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = """
            update probe
            set celestial_area_id = ?
            where id = ?   
            """, nativeQuery = true)
    void insertCelestialAreaId(
            Long celestialAreaId,
            Long probeId
    );

}
