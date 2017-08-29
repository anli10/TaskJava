import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TestSuite extends TestBase{

    String read(String key) {
        try {
            File file = new File("data.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            //Enumeration enuKeys = properties.keys();
            String value = properties.getProperty(key);
            return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String extractDigits(String src) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }
//    Register Test:
//1. Navigate My Account -> Register
//2. Fill the form with your information (email address, name etc)
//3. Summit the form
//4. Assert user from Account -> Edit Information
//4. Log out


    @Test
    public void registerTest() {

        RegisterPage registerPage =homePage.register();
        registerPage.fillInformation("Beatrice","Ghiuta","ghiutaanamaria@yahoo.com","0730341580","Password1","Password1");
        registerPage.submitForm();
        EditInformation editInformation=registerPage.goToEditInformation();
        Assert.assertEquals(read("firstname"),editInformation.getFirstName().getAttribute("value"));
        Assert.assertEquals(read("lastname"),editInformation.getLastName().getAttribute("value"));
        Assert.assertEquals(read("email"),editInformation.getEmail().getAttribute("value"));
        Assert.assertEquals(read("telephone"),editInformation.getTelephone().getAttribute("value"));
        homePage.logOut();
    }
//    Login Test:
//            1. Navigate to My Account -> Login
//2. Fill your email and password
//3. Verify login was successful
//4. Log out
    @Test
    public void logInTest() {

        LogInPage logInPage = homePage.logIn();
        logInPage.fillCredentials(read("email"),read("password"));
        Assert.assertTrue(homePage.getLogOutButton().isDisplayed());
        homePage.getMyAccount().click();
        homePage.getLogOutButton().click();

    }
//    Add address book test:

//1. Connect to database
//2. Add a new entry for your account in the oc_address ( dummy address)
//3. Login in application
//4. Navigate Account -> Address book
//5. Assert that the address is present in the UI

    @Test
    public void addAddress() throws SQLException {

        LogInPage logInPage = homePage.logIn();
        logInPage.fillCredentials(read("email"),read("password"));
        AddressBook addressBook=logInPage.goToAddressPage();
        int n=addressBook.getAddressBook().size();

        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("insert into oc_address (customer_id, firstname, lastname, company, address_1, address_2, city, postcode, country_id, zone_id, custom_field) values ((select customer_id from oc_customer where email=\"" + read("email") + "\"), \"Beatrice\", \"Ghiuta\", \"Endava\", \"adresa1\", \"adresa1\", \"Bucharest\", \"123\", 123,123,0);");
        stmt.close();
        connection.close();

        logInPage.goToAddressPage();
        int m=addressBook.getAddressBook().size();
        Assert.assertTrue(n<m);
        homePage.logOut();
    }
//    Product selection:
//            1. Login in the application
//2. Click on a random product from Home Page
//3. Add any required information
//4. From database (oc_cart) for the current session increase quantity by 1
//            5. Verify in the UI that the values has been increased
//6. Log out

    @Test
    public void produsSelection() throws SQLException {

        LogInPage logInPage = homePage.logIn();
        logInPage.fillCredentials("ghiutaanamaria@yahoo.com","Password1");
        homePage.getHomeButton().click();
        ProductPage productPage=homePage.selectProduct();
        productPage.addToCart();

        String initial=extractDigits(productPage.getQuantity());
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("update oc_cart set quantity=quantity+1 ");
        stmt.close();
        connection.close();
        String after=extractDigits(productPage.getQuantity());
        Assert.assertTrue(Integer.parseInt(initial) < Integer.parseInt(after));
        homePage.logOut();
    }




}

