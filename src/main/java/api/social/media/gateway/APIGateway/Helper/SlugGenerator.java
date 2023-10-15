package api.social.media.gateway.APIGateway.Helper;

import java.util.Random;

public class SlugGenerator {

    public static String generateSlug(String input) {
        // Convert the input string to lowercase
        String lowercaseInput = input.toLowerCase();
        
        // Replace spaces with hyphens and remove special characters
        String slug = lowercaseInput.replaceAll("[^a-z0-9\\s-]", "")
                                   .replaceAll("[\\s-]+", "-")
                                   .replaceAll("^-|-$", "");

        // Generate a random number (e.g., a 4-digit number)
        int randomSuffix = new Random().nextInt(10000);
        
        // Append the random number to the slug
        slug += "-" + randomSuffix;
        
        return slug;
    }
}
