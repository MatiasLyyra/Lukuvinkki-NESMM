package lukuvinkki.repository;

import java.util.List;
import lukuvinkki.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findTagByName(String name);
    List<Tag> findByNameIgnoreCaseContaining(String name);
}
