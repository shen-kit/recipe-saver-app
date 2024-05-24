package recipe_saver;

import java.util.List;

public class Recipe {

  private final List<Ingredient> ingredients;
  private final int duration;
  private final List<RecipeStep> instructions;

  public Recipe(List<Ingredient> ingredients, int duration, List<RecipeStep> instructions) {
    this.ingredients = ingredients;
    this.duration = duration;
    this.instructions = instructions;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  @Override
  public String toString() {
    StringBuilder recipeStr = new StringBuilder();
    // duration
    recipeStr.append("Duration: ").append(duration).append(" mins\n\n");

    // ingredients
    for (Ingredient ingredient : ingredients) {
      recipeStr.append(String.format("%20s   %-40s", ingredient.getQuantity(), ingredient.getName()));
      if (!ingredient.getNotes().isEmpty()) {
        recipeStr.append(" note: ").append(ingredient.getNotes());
      }
      recipeStr.append("\n");
    }

    return recipeStr.toString();
  }
}
