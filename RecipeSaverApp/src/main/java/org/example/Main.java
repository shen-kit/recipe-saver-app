package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

  public static void main(String[] args) {
    String url = "https://www.recipetineats.com/chicken-chow-mein/";

    List<String> wantedInfo = Arrays.asList("amount", "unit", "name", "notes");

    try {
      Document doc = Jsoup.connect(url).get();

      List<Element> ingredientsElementList = doc.getElementsByClass("wprm-recipe-ingredient");
      List<String> ingredients = new ArrayList<>();
      for (Element elem : ingredientsElementList) {
        List<Element> children = elem.children();
        StringBuilder ingredient = new StringBuilder();
        for (Element child : children) {
          for (String info : wantedInfo) {
            if (child.className().contains(info))
              ingredient.append(child.text()).append(" ");
          }
        }
        ingredients.add(ingredient.toString());
      }

      System.out.println("Ingredients:");
      ingredients.forEach(System.out::println);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
