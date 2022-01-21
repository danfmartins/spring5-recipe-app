package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.Services.RecipeService;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class indexControllerTest {


    indexController index;

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        index = new indexController(categoryRepository, unitOfMeasureRepository, recipeService);
    }

    @Test
    void getIndexPage(){

        assertEquals(index.getIndexPage(model), "index");
        verify(recipeService, times(1)).getList();
        verify(model, times(1)).addAttribute(eq("recipes"), anyList());

    }
}