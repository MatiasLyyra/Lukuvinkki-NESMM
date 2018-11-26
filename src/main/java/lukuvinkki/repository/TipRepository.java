package lukuvinkki.repository;

import lukuvinkki.domain.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipRepository extends JpaRepository<Tip, Long> {
    List<Tip> findAllByOrderByCreatedDesc();
    List<Tip> findByTitleIgnoreCaseContainingOrAuthorIgnoreCaseContainingOrderByCreatedDesc(String title, String author);
}
