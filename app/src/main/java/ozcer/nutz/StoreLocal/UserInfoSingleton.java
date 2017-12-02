package ozcer.nutz.StoreLocal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserInfoSingleton {

  private static UserInfo userInfo = null;

  public static UserInfo getInstance() {
    if (userInfo == null) {
      userInfo = new UserInfo();
    }
    return userInfo;
  }

  public static void load_user_data(String filePath) throws ClassNotFoundException, IOException {
    FileInputStream fileIn = new FileInputStream(filePath);
    ObjectInputStream in = new ObjectInputStream(fileIn);
    Object deserialized = in.readObject();
    if (deserialized instanceof UserInfo) {
      userInfo = (UserInfo)deserialized;
    }
    in.close();
    fileIn.close();
  }

  public static void save_user_data(UserInfo userInfo, String filePath) throws IOException {
    FileOutputStream fileOut = new FileOutputStream(filePath);
    ObjectOutputStream out = new ObjectOutputStream(fileOut);
    out.writeObject(userInfo);
    out.close();
    fileOut.close();
  }
}
