package guru.springframework.spring5recipeapp.Bootstrap;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    public List<Recipe> getRecipes(){

        log.debug("I'm on data loader");

        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> tableOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        // Do for others eventually
        if (tableOptional.isEmpty()) {
            throw new RuntimeException("Expected UOM Not found");
        }

        UnitOfMeasure tablespoon = tableOptional.get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure clove = unitOfMeasureRepository.findByDescription("Clove").get();
        UnitOfMeasure unit = unitOfMeasureRepository.findByDescription("Unit").get();
        UnitOfMeasure pint = unitOfMeasureRepository.findByDescription("Pint").get();
        UnitOfMeasure cup = unitOfMeasureRepository.findByDescription("Cup").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();

        Category mexican = categoryRepository.findByDescription("Mexican").get();

        Recipe grilledChicken = new Recipe();
        grilledChicken.setDirections("In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        grilledChicken.setDescription("Spicy Grilled Chicken Tacos");
        grilledChicken.setDifficulty(Difficulty.EASY);
        grilledChicken.setCookTime(15);
        grilledChicken.setPrepTime(25);
        grilledChicken.setServings(6);
        grilledChicken.setSource("Simply Recipes");
        grilledChicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        Notes note1 = new Notes();
        note1.setRecipe(grilledChicken);
        note1.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        grilledChicken.setNotes(note1);
        recipes.add(grilledChicken);

        grilledChicken.addIngredient(new Ingredient("ancho chili powder", BigDecimal.valueOf(2L),tablespoon));
        grilledChicken.addIngredient(new Ingredient("dried oregano", BigDecimal.valueOf(1L),teaspoon));
        grilledChicken.addIngredient(new Ingredient("dried cumin", BigDecimal.valueOf(1L),teaspoon));
        grilledChicken.addIngredient(new Ingredient("sugar", BigDecimal.valueOf(1L),teaspoon));
        grilledChicken.addIngredient(new Ingredient("salt", BigDecimal.valueOf(0.5),teaspoon));
        grilledChicken.addIngredient(new Ingredient("garlic, finely chopped", BigDecimal.valueOf(1L),clove));
        grilledChicken.addIngredient(new Ingredient("finely grated orange zest", BigDecimal.valueOf(1L),tablespoon));
        grilledChicken.addIngredient(new Ingredient("fresh-squeezed orange juice", BigDecimal.valueOf(3),tablespoon));
        grilledChicken.addIngredient(new Ingredient("olive oil", BigDecimal.valueOf(2L),tablespoon));
        grilledChicken.addIngredient(new Ingredient("skinless, boneless chicken thighs", BigDecimal.valueOf(6L),unit));
        grilledChicken.addIngredient(new Ingredient("small corn tortillas", BigDecimal.valueOf(8L),unit));
        grilledChicken.addIngredient(new Ingredient("packed baby arugula", BigDecimal.valueOf(3),cup));
        grilledChicken.addIngredient(new Ingredient("medium ripe avocados, sliced", BigDecimal.valueOf(2L),unit));
        grilledChicken.addIngredient(new Ingredient("radishes, thinly sliced", BigDecimal.valueOf(4L),unit));
        grilledChicken.addIngredient(new Ingredient("cherry tomatoes, halved", BigDecimal.valueOf(0.5),pint));
        grilledChicken.addIngredient(new Ingredient("red onion, thinly sliced", BigDecimal.valueOf(0.25),unit));
        grilledChicken.addIngredient(new Ingredient("roughly chopped cilantro", BigDecimal.valueOf(1),unit));
        grilledChicken.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", BigDecimal.valueOf(0.5),cup));
        grilledChicken.addIngredient(new Ingredient("lime, cut into wedges", BigDecimal.valueOf(1),unit));

        Recipe guacamole = new Recipe();
        guacamole.setDirections("Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.).\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");

        guacamole.setDescription("Best guacamole");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setCookTime(0);
        guacamole.setPrepTime(10);
        guacamole.setServings(4);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Notes note2 = new Notes();
        note2.setRecipe(guacamole);
        note2.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");
        guacamole.setNotes(note2);
        recipes.add(guacamole);

        Ingredient chilis = new Ingredient();
        chilis.setDescription("serrano (or jalapeño) chilis, stems and seeds removed, minced");
        chilis.setAmount(BigDecimal.valueOf(2L));
        chilis.setUom(unit);
        chilis.setRecipe(guacamole);
        guacamole.getIngredients().add(chilis);

        Ingredient pepper = new Ingredient();
        pepper.setDescription("freshly ground black pepper");
        pepper.setAmount(BigDecimal.valueOf(1L));
        pepper.setUom(pinch);
        pepper.setRecipe(guacamole);
        guacamole.getIngredients().add(pepper);

        Ingredient salt2 = new Ingredient();
        salt2.setDescription("salt, plus more to taste");
        salt2.setAmount(BigDecimal.valueOf(0.25));
        salt2.setUom(teaspoon);
        salt2.setRecipe(guacamole);
        guacamole.getIngredients().add(salt2);

        Ingredient tortillas2 = new Ingredient();
        tortillas2.setDescription("Tortilla chips, to serve");
        tortillas2.setAmount(BigDecimal.valueOf(1L));
        tortillas2.setUom(unit);
        tortillas2.setRecipe(guacamole);
        guacamole.getIngredients().add(tortillas2);

        Ingredient avocados2 = new Ingredient();
        avocados2.setDescription("ripe avocados");
        avocados2.setAmount(BigDecimal.valueOf(2L));
        avocados2.setUom(unit);
        avocados2.setRecipe(guacamole);
        guacamole.getIngredients().add(avocados2);

        Ingredient radishes2 = new Ingredient();
        radishes2.setDescription("Red radish or jicama slices for garnish (optional)");
        radishes2.setAmount(BigDecimal.valueOf(1L));
        radishes2.setUom(unit);
        radishes2.setRecipe(guacamole);
        guacamole.getIngredients().add(radishes2);

        Ingredient tomato = new Ingredient();
        tomato.setDescription("ripe tomato, chopped (optional)");
        tomato.setAmount(BigDecimal.valueOf(0.5));
        tomato.setUom(pint);
        tomato.setRecipe(guacamole);
        guacamole.getIngredients().add(tomato);

        Ingredient redOnion2 = new Ingredient();
        redOnion2.setDescription("minced red onion or thinly sliced green onion");
        redOnion2.setAmount(BigDecimal.valueOf(4));
        redOnion2.setUom(tablespoon);
        redOnion2.setRecipe(guacamole);
        guacamole.getIngredients().add(redOnion2);

        Ingredient cilantro2 = new Ingredient();
        cilantro2.setDescription("cilantro (leaves and tender stems), finely chopped");
        cilantro2.setAmount(BigDecimal.valueOf(2));
        cilantro2.setUom(tablespoon);
        cilantro2.setRecipe(guacamole);
        guacamole.getIngredients().add(cilantro2);

        Ingredient limeOrLemon = new Ingredient();
        limeOrLemon.setDescription("fresh lime or lemon juice");
        limeOrLemon.setAmount(BigDecimal.valueOf(1));
        limeOrLemon.setUom(tablespoon);
        limeOrLemon.setRecipe(guacamole);
        guacamole.getIngredients().add(limeOrLemon);

        grilledChicken.getCategories().add(mexican);
        guacamole.getCategories().add(mexican);

        return recipes;
    }

}
