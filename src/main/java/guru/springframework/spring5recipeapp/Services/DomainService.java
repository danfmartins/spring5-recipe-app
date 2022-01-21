package guru.springframework.spring5recipeapp.Services;

import java.util.List;

public interface DomainService<T> {

    List<T> getList();
}
