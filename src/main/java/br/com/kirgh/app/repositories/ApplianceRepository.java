package br.com.kirgh.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kirgh.app.entities.Appliance;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
}
