package fridgemanager.springboot;

import java.io.File;
import java.nio.file.Paths;

/**
 * A methode for deleting local save.
 */
public class DeleteLocalSave {

  /**
  * Delete file.
  */
  public static void deleteFile() {
    File savedFile = new File(Paths.get(".")
        .toAbsolutePath().normalize().toString() + "/RestServerSave.json");
    System.out.println(savedFile.delete());
  }
}
