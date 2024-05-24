package recipe_saver;

import java.util.List;

public class Recipe {

  private final List<Ingredient> ingredients;

  public Recipe(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  @Override
  public String toString() {
    StringBuilder ingredientsStr = new StringBuilder();
    for (Ingredient ingredient : ingredients) {
      ingredientsStr.append(String.format("%20s   %-40s note: %s\n", ingredient.getQuantity(), ingredient.getName(), ingredient.getNotes()));
    }
    return ingredientsStr.toString();
  }
}
