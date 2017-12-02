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
  private final static String userInfoFile = "userInfo.ser";

  public static UserInfo getInstance() {
    if (userInfo == null) {
      userInfo = new UserInfo();
    }
    return userInfo;
  }

  public static void load_user_data(Context context, String filePath) throws ClassNotFoundException, IOException {
    if (filePath == null) {
      filePath = userInfoFile;
    }
    FileInputStream fileIn = null;
    try {
      fileIn = context.openFileInput(filePath);
    } catch (IOException e) {
      FileOutputStream fileOut = context.openFileOutput(filePath, MODE_PRIVATE);
    }
    if (fileIn != null) {
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Object deserialized = in.readObject();
      if (deserialized instanceof UserInfo) {
        userInfo = (UserInfo) deserialized;
      }
    }
  }

  public static void save_user_data(Context context, String filePath) throws IOException {
    if (filePath == null) {
      filePath = userInfoFile;
    }
    FileOutputStream fileOut = context.openFileOutput(filePath, MODE_PRIVATE);
    if (fileOut != null) {
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(UserInfoSingleton.getInstance());
      out.close();
      fileOut.close();
    }
  }
}
