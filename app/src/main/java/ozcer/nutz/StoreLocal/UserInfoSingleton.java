package ozcer.nutz.StoreLocal;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

public class UserInfoSingleton {

  private static UserInfo userInfo = null;

  public static UserInfo getInstance() {
    if (userInfo == null) {
      userInfo = new UserInfo();
    }
    return userInfo;
  }

  public static void load_user_data(Context context, File file) throws ClassNotFoundException, IOException {
    if (file.exists()) {
      FileInputStream fileIn = new FileInputStream(file);
      if (fileIn != null) {
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Object deserialized = in.readObject();
        if (deserialized instanceof UserInfo) {
          userInfo = (UserInfo) deserialized;
        }
      }
    } else {
      file.createNewFile();
    }
  }

  public static void save_user_data(Context context, File file) throws IOException {
    if (!file.exists()) {
      file.createNewFile();
    }
    FileOutputStream fileOut = new FileOutputStream(file);
    if (fileOut != null) {
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(UserInfoSingleton.getInstance());
      out.close();
      fileOut.close();
    }
  }
}
