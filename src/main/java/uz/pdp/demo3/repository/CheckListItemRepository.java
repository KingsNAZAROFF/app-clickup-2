package uz.pdp.demo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.demo3.entity.Icon;

@RepositoryRestResource(path = "/checkListItem", collectionResourceRel = "list")
public interface CheckListItemRepository extends JpaRepository<Icon.CheckListItem, Long> {
}
