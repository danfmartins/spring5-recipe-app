package guru.springframework.spring5recipeapp.Services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RecipeService implements DomainService<Recipe> {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getList() {
        log.debug("I'm in the service");

        List<Recipe> listRecipes = new ArrayList<>();
        recipeRepository.findAll().forEach(listRecipes::add);

        return listRecipes;
    }
}
