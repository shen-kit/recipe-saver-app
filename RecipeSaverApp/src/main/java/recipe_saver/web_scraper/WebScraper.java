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

public class WebScraper {

  public static Recipe getRecipeFromUrl(String url) {
    Map<String, IngredientComponent> wantedInfo = new TreeMap<>() {{
      put("amount", IngredientComponent.QUANTITY);
      put("unit", IngredientComponent.QUANTITY);
      put("name", IngredientComponent.NAME);
      put("notes", IngredientComponent.NOTES);
    }};

    try {
      Document doc = Jsoup.connect(url).get();

      List<Element> ingredientsElementList = doc.getElementsByClass("wprm-recipe-ingredient");
      List<Ingredient> ingredientsList = new ArrayList<>();
      for (Element elem : ingredientsElementList) {
        List<Element> children = elem.children();

        Ingredient ingredient = new Ingredient();
        for (Element child : children) {
          for (String info : wantedInfo.keySet()) {
            if (child.className().contains(info)) {
              String text = child.text()
                  .replaceFirst("^[, ]*", "");
              switch (wantedInfo.get(info)) {
                case QUANTITY -> ingredient.appendQuantity(text);
                case NAME -> ingredient.setName(text);
                case NOTES -> ingredient.setNotes(text);
              }
            }
          }
        }
        ingredientsList.add(ingredient);
      }

      return new Recipe(ingredientsList);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
