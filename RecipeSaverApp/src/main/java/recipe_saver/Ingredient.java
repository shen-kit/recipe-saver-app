package recipe_saver;

public class Ingredient {

  private String quantity;
  private String name;
  private String notes;

  public Ingredient() {
    this.quantity = this.name = this.notes = "";
  }

  public String getQuantity() {
    return quantity;
  }

  public String getName() {
    return name;
  }

  public String getNotes() {
    return notes;
  }

  public void appendQuantity(String quantity) {
    this.quantity += quantity;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
