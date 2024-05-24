package recipe_saver;

import recipe_saver.web_scraper.WebScraper;

public class Main {

  public static void main(String[] args) {
//    String url = "https://www.recipetineats.com/chicken-chow-mein/";
    String url = "https://www.recipetineats.com/cottage-pie/";

    Recipe recipe = WebScraper.getRecipeFromUrl(url);

    System.out.println("===== RECIPE =====");
    System.out.println(recipe);
  }
}
