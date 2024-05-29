// tag::all[]
// tag::allButValidation[]
package tacos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
// tag::allButValidation[]

public class Taco {

    // end::allButValidation[]
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    // tag::allButValidation[]
    private String name;
    // end::allButValidation[]
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    // tag::allButValidation[]
    private List<String> ingredients;

    public @Size(min = 1, message = "You must choose at least 1 ingredient") List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(@Size(min = 1, message = "You must choose at least 1 ingredient") List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public @NotNull @Size(min = 5, message = "Name must be at least 5 characters long") String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
//end::allButValidation[]
//tag::end[]