package vn.paygate.wallet.core.repository;

import vn.paygate.wallet.core.entity.Cashin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashinRepository extends JpaRepository<Cashin, Long> {
}