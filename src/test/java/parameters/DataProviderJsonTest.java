package parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviderJsonTest {
    @Test(dataProvider = "Users") //DataProvider буде повертати юзерів
    public void t1(User user) {
        System.out.println(user.first_name);
    }

    @Test(dataProvider = "First and Last Name")
    //DataProvider буде повертати юзера але з 2-ма парметрами FirstName, LastName
    public void t2(String firstName, String lastName) {
        System.out.println(firstName + " " + lastName);
    }

    @DataProvider(name = "Users")
    public Object[] getUser() {

        //треба буде прочитати json файл де є 2 юзера, через Objectmapper (jackson) - String to Object (json Array to list java)
        //як перетворити масив в List of Objects
        //на виході має бути список перетворений в двохвимірний масив фором (почитати про двохвимірний масив)

        ObjectMapper mapper = new ObjectMapper();
        List<User> list = new ArrayList<User>();
        try {
            list = mapper.readValue(new File("C:\\Users\\ndorozhynska\\dataProvider\\Users.json"), new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toArray();
    }

    @DataProvider(name = "First and Last Name")
    public Object[][] getUserData() {
        Object[] users = getUser();

        Object[][] resultedData = new Object[users.length][2];

        for (int i = 0; i < users.length; i++) {
            User currentUser = (User) users[i];

            resultedData[i][0] = currentUser.first_name;
            resultedData[i][1] = currentUser.last_name;

            Object[] row = resultedData[i];

            row[0] = currentUser.first_name;
            row[1] = currentUser.last_name;
        }

        return resultedData;
    }
}

class User {
    public int id;
    public String first_name;
    public String last_name;
    public String avatar;
}
