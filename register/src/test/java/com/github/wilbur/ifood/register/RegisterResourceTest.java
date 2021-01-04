package com.github.wilbur.ifood.register;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(RegisterTestLifecycleManager.class)
public class RegisterResourceTest
{

//    @BeforeAll
//    public static void init() {
//        String owner = "Owner Test";
//        String cnpj = "CNPJ Test";
//        String name = "Name Test";
//
//        Location location = new Location();
//        location.setLatitude("1");
//        location.setLongitude("2");
//
//        Restaurant restaurantDto = new Restaurant();
//        restaurantDto.setName(owner);
//        restaurantDto.setCnpj(cnpj);
//        restaurantDto.setName(name);
//        restaurantDto.setLocalization(location);
//
//        restaurantDto.persist();
//    }

//    @Test
////    @DataSet("restaurant-scenario-1.yml")
//    public void testSearchRegister() {
//        String result = given().when().get().then()
//                .statusCode(200).extract().asString();
//        System.out.println(result);
//    }

    @Test
//    @DataSet("restaurant-scenario-1.yml")
    public void shouldSeedDatabaseWithYAMLDataSet() {
//        List<Restaurant> restaurants = em().createQuery("select u from User").getResultList();
//        Assert.assertTrue(restaurants.isEmpty());
        List<String> result = em().createQuery("    CREATE TABLE restaurant(\n" + "        id SERIAL NOT NULL,\n"
                + "        owner VARCHAR NOT NULL,\n" + "        cnpj VARCHAR,\n" + "        name VARCHAR NOT NULL,\n"
                + "        data_created TIMESTAMP,\n" + "        data_updated TIMESTAMP\n" + "    );\n"
                + "    CREATE TABLE location(\n" + "        id SERIAL NOT NULL,\n"
                + "        latitude DOUBLE PRECISION,\n" + "        longitude DOUBLE PRECISION\n" + "    );\n"
                + "    CREATE TABLE dish(\n" + "        id SERIAL NOT NULL,\n" + "        name VARCHAR NOT NULL,\n"
                + "        description VARCHAR NOT NULL,\n" + "        price DECIMAL NOT NULL\n" + "    );")
        .getResultList();
        System.out.println(result);
    }
}
