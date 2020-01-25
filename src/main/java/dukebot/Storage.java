package dukebot;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String storagePath;

    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    public void saveToFile(ArrayList<Task> data) {
        try{
            FileOutputStream writeData = new FileOutputStream(new File(this.storagePath));
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(data);
            writeStream.flush();
            writeStream.close();
            System.out.println("done");

        }catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
    }

    public ArrayList<Task> loadFromFile() {
        if (new File(this.storagePath).isFile()) {
            try{
                FileInputStream readData = new FileInputStream(new File(this.storagePath));
                ObjectInputStream readStream = new ObjectInputStream(readData);

                ArrayList<Task> data = (ArrayList<Task>) readStream.readObject();
                readStream.close();

                return data;
            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("fail load");
        return new ArrayList<>();
    }
}