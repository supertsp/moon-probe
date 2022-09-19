package br.com.tiagopedroso.moonprobe.entrypoint.repository;

import br.com.tiagopedroso.moonprobe.entrypoint.model.CelestialArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelestialAreaRepository extends JpaRepository<CelestialArea, Long> {

}
