package hsm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {

    // Method to check if username and password match the ones in the User.txt file
    public static boolean validateCredentials(String username, String password) {
        String line;
        try {
            // Absolute path to User.txt file
            String filePath = "D:\\Multithreading\\HSM\\src\\hsm\\User.txt"; // Change this to your actual path
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while ((line = reader.readLine()) != null) {
                // Assuming the format is "username password" in the file
                String[] credentials = line.trim().split("\\s+");
                if (credentials.length == 2) {
                    String storedUsername = credentials[0];
                    String storedPassword = credentials[1];
                    
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true; // Found a match
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // No match found
    }
}
