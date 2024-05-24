package recipe_saver.web_scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import recipe_saver.Ingredient;
import recipe_saver.IngredientComponent;
import recipe_saver.Recipe;
import recipe_saver.RecipeStep;

public class WebScraper {

  public static Recipe getRecipeFromUrl(String url) {
    try {
      Document doc = Jsoup.connect(url).get();

      List<Ingredient> ingredientsList = getIngredientsList(doc);
      int duration = getDurationMinutes(doc);
      List<RecipeStep> steps = new ArrayList<>();

      return new Recipe(ingredientsList, duration, steps);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static List<Ingredient> getIngredientsList(Document doc) {

    Map<String, IngredientComponent> classToIngredientComponent = new TreeMap<>() {{
      put("amount", IngredientComponent.QUANTITY);
      put("unit", IngredientComponent.QUANTITY);
      put("name", IngredientComponent.NAME);
      put("notes", IngredientComponent.NOTES);
    }};

    List<Element> ingredientsElementList = doc.getElementsByClass("wprm-recipe-ingredient");
    List<Ingredient> ingredientsList = new ArrayList<>();
    for (Element elem : ingredientsElementList) {
      Ingredient ingredient = new Ingredient();

      List<Element> children = elem.children();
      for (Element child : children) {
        for (String info : classToIngredientComponent.keySet()) {
          if (child.className().contains(info)) {
            String text = child.text()
                .replaceFirst("^[, ]*", "");
            switch (classToIngredientComponent.get(info)) {
              case QUANTITY -> ingredient.appendQuantity(text);
              case NAME -> ingredient.setName(text);
              case NOTES -> ingredient.setNotes(text);
            }
          }
        }
      }
      ingredientsList.add(ingredient);
    }

    return ingredientsList;
  }

  private static int getDurationMinutes(Document doc) {
    int mins = 0;

    List<Element> elements = doc.getElementsByClass("wprm-recipe-total_time-hours");
    if (!elements.isEmpty()) {
      mins += 60 * Integer.parseInt(elements.get(0).text().replaceAll("[^0-9]", ""));
    }

    elements = doc.getElementsByClass("wprm-recipe-total_time-minutes");
    if (!elements.isEmpty()) {
      mins += Integer.parseInt(elements.get(0).text().replaceAll("[^0-9]", ""));
    }

    return mins;
  }
}
