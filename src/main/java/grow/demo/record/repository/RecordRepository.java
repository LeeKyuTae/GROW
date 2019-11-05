package grow.demo.record.repository;

import grow.demo.record.domain.Records;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Records, Long> {
}
