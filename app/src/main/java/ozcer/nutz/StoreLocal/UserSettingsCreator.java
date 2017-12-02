package ozcer.nutz.StoreLocal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserSettingsCreator {

  public static UserSettings load_user_data(String filePath) throws ClassNotFoundException, IOException {
    FileInputStream fileIn = new FileInputStream(filePath);
    ObjectInputStream in = new ObjectInputStream(fileIn);
    Object deserialized = in.readObject();
    UserSettings userSettings = null;
    if (deserialized instanceof UserSettings) {
      userSettings = (UserSettings)deserialized;
    }
    in.close();
    fileIn.close();
    return userSettings;
  }

  public static void save_user_data(UserSettings userSettings, String filePath) throws IOException {
    FileOutputStream fileOut = new FileOutputStream(filePath);
    ObjectOutputStream out = new ObjectOutputStream(fileOut);
    out.writeObject(userSettings);
    out.close();
    fileOut.close();
  }
}
